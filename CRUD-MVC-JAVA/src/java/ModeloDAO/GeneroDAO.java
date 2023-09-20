/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import Config.Conexion;
import Intefaces.CRUD;
import Modelo.Genero;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jackiesanchez
 */
public class GeneroDAO implements CRUD<Genero> {
  
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    //LISTA
    public static final List <Genero> listaGenero = new ArrayList<>();
    Genero genero = new Genero();
    
    
 
    

    @Override
    public List listar() {
        
        
        //METODO QUE RETORNA LA LISTA PARA EL GENERO. ESA LISTA LE PERTENECE A LA CLASE NO AL OBJETO YA QUE ES ESTATICA. 
        
        ArrayList<Genero>list=new ArrayList<>();
        String sql="select * from genero";
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Genero genero = new Genero();
                genero.setidGenero(rs.getInt("idGenero"));
                genero.setNombre(rs.getString("nombre"));
                list.add(genero);
            }
        } catch (Exception e) {
            
            
        }

//SIMULAR LA CACHE 
        return list;
        
    }

    
    // METODOS CREADOS 
    
    @Override
    public Genero list(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean add(Genero obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean edit(Genero obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    //VA A PASAR EL PARAMETRO DE UNA LISTA
    
    public void agregarDatos() {
        
        listaGenero.addAll(listar());

    }
    
}
