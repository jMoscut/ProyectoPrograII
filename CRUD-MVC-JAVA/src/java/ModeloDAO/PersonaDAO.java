package ModeloDAO;

import Config.Conexion;
import Intefaces.CRUD;
import Modelo.Genero;
import Modelo.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PersonaDAO implements CRUD <Persona> {
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Persona p=new Persona();
    GeneroDAO generoDAO= new GeneroDAO();
    public PersonaDAO(){       
              if (GeneroDAO.listaGenero.isEmpty()) {
                generoDAO.agregarDatos();
            }
    };
    
    @Override
    public List listar() {
        ArrayList<Persona>list=new ArrayList<>();
        String sql="select * from persona";
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Persona per=new Persona();
                per.setid(rs.getInt("id"));
                per.setDpi(rs.getString("DPI"));
                per.setNom(rs.getString("Nombres"));
                per.setEstado(rs.getBoolean("estado"));
                 int idGenero = rs.getInt("idGenero");
                 Genero genero = GeneroDAO.listaGenero.stream().filter (g -> g.getidGenero()== idGenero).findFirst().orElse(null);
                 per.setGenero(genero.getNombre());
                 per.setIdGenero(idGenero);
                list.add(per);
            }
        } catch (Exception e) {
        }
        
        //FILTRA LA INFORMACIÓN PARA EL ESTADO 
        return list.stream().filter(p -> p
                .getEstado()!= false)
                .collect(Collectors.toList());
    }

    @Override
    public Persona list(int id) {
        String sql="select * from persona where id="+id;
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){                
                p.setid(rs.getInt("id"));
                p.setDpi(rs.getString("DPI"));
                p.setNom(rs.getString("Nombres"));
                p.setIdGenero(rs.getInt("idGenero"));
            }
        } catch (Exception e) {
        }
        return p;
    }
    
     //
    
    @Override
    public boolean add(Persona obj) {
         Genero genero = GeneroDAO.listaGenero.stream().filter (g -> g.getNombre().equals(obj.getGenero())).findFirst().orElse(null);
       //String sql="insert into persona(DPI, Nombres, idGenero)values('"+obj.getDpi()+"','"+obj.getNom()+"','"+genero.getidGenero()+"')";
       //string builder sirve para convertirlo en varias lineas.
       StringBuilder query = new StringBuilder("insert into persona(DPI,Nombres,idGenero,clave) ");
       //%s signigica el campo en el que ingresara la informacion
        query.append(String.format("values('%s','%s',%s,SHA2('%s',256))", obj.getDpi(), obj.getNom(), obj.getGenero(), obj.getPass()));
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(query.toString());
            ps.executeUpdate();
        } catch (Exception e) {
        }
       return false;
    }

    @Override
    public boolean edit(Persona obj) {
          Genero genero = GeneroDAO.listaGenero.stream().filter (g -> g.getNombre().equals(obj.getGenero())).findFirst().orElse(null);
        String sql="update persona set DPI='"+obj.getDpi()+"',Nombres='"+obj.getNom()+"',idGenero='"+genero.getidGenero()+"' where Id="+obj.getid();
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return false;
    }
    
     //BORRADO LOGICO, NO SE BORRA LA DATA SOLO SE CAMBIA EL ESTADO DEL DATO. Y SE MANTIENE LA INFORMACIÓN
     //PARA UNA AUDITORÍA ADEMAS SE TIENE UN FILTRO PARA PODER VER EL ESTADO. 

    @Override
    public boolean eliminar(int id) {
        String sql="update persona set estado = false where Id="+id;
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return false;
    }
    
    public boolean password(String username, String password) {
        boolean response = false;
         StringBuilder query = new StringBuilder("select * from persona ");
         query.append(String.format("where nombres = '%s' and clave = SHA2('%s',256)", username, password));
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(query.toString());
            rs=ps.executeQuery();
            while(rs.next()){                
                p.setid(rs.getInt("id"));
                p.setDpi(rs.getString("DPI"));
                p.setNom(rs.getString("Nombres"));
                response = true;
            }
        } catch (Exception e) {
            response = false;
        }
        return response;
    }
    
}



//package ModeloDAO;
//
//import Config.Conexion;
//import Intefaces.CRUD;
//import Modelo.Genero;
//import Modelo.Persona;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class PersonaDAO implements CRUD <Persona> {
//    Conexion cn=new Conexion();
//    Connection con;
//    PreparedStatement ps;
//    ResultSet rs;
//    Persona p=new Persona();
//    GeneroDAO generoDAO= new GeneroDAO();
//    public PersonaDAO(){
//    generoDAO.agregarDatos();
//    
//     };
//    
//    
//    @Override
//    public List listar() {
//        ArrayList<Persona>list=new ArrayList<>();
//        String sql="select * from persona";
//        try {
//            con=cn.getConnection();
//            ps=con.prepareStatement(sql);
//            rs=ps.executeQuery();
//            while(rs.next()){
//                Persona per=new Persona();
//                per.setId(rs.getInt("id"));
//                per.setDpi(rs.getString("DPI"));
//                per.setNom(rs.getString("Nombres"));
//                per.setEstado(rs.getBoolean("estado"));
//                 int idGenero = rs.getInt("idGenero");
//                 per.setIdGenero(idGenero);
//                 Genero genero = GeneroDAO.listaGenero.stream().filter (g -> g.getIdGenero()== idGenero).findFirst().orElse(null);
//                 per.setGenero(genero.getNombre());
//                list.add(per);
//            }
//        } catch (Exception e) {
//        }
//        
//        //FILTRA LA INFORMACIÓN PARA EL ESTADO 
//        return list.stream()
//                .filter(per->per.getEstado()!= false)
//                .collect(Collectors.toList());
//        
//        
//        
//        
//    }
//
//    //
//    
//    @Override
//    public boolean add(Persona obj) {
//         Genero genero = GeneroDAO.listaGenero.stream().filter (g -> g.getNombre().equals(obj.getGenero())).findFirst().orElse(null);
//       String sql="insert into persona(DPI, Nombres, idGenero)values('"+obj.getDpi()+"','"+obj.getNom()+"','"+genero.getIdGenero()+"')";
//        try {
//            con=cn.getConnection();
//            ps=con.prepareStatement(sql);
//            ps.executeUpdate();
//        } catch (Exception e) {
//        }
//       return false;
//    }
//
//    @Override
//    public boolean edit(Persona obj) {
//          Genero genero = GeneroDAO.listaGenero.stream().filter (g -> g.getNombre().equals(obj.getGenero())).findFirst().orElse(null);
//        String sql="update persona set DPI='"+obj.getDpi()+"',Nombres='"+obj.getNom()+"',idGenero='"+genero.getIdGenero()+"' where Id="+obj.getId();
//        try {
//            con=cn.getConnection();
//            ps=con.prepareStatement(sql);
//            ps.executeUpdate();
//        } catch (Exception e) {
//        }
//        return false;
//    }
//
//    //BORRADO LOGICO, NO SE BORRA LA DATA SOLO SE CAMBIA EL ESTADO DEL DATO. Y SE MANTIENE LA INFORMACIÓN
//    //PARA UNA AUDITORÍA ADEMAS SE TIENE UN FILTRO PARA PODER VER EL ESTADO. 
//    
//    @Override
//    public boolean eliminar(int id) {
//        String sql="update persona set estado = false where Id="+id;
//        try {
//            con=cn.getConnection();
//            ps=con.prepareStatement(sql);
//            ps.executeUpdate();
//        } catch (Exception e) {
//        }
//        return false;
//    }
//    
//}
