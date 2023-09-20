    
package Controlador;

import Modelo.Persona;
import ModeloDAO.PersonaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Controlador extends HttpServlet {

    String listar="vistas/listar.jsp";
    String add="vistas/add.jsp";
    String edit="vistas/edit.jsp";  
    // constante que crea la lista
    String login="vistas/login.jsp";
    Persona p=new Persona();
    PersonaDAO dao=new PersonaDAO();
    int id;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Controlador</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controlador at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String acceso="";
          String action=request.getParameter("accion");
         
         
         HttpSession session = request.getSession();

       
       
        if(action.equalsIgnoreCase("listar")){
            //validamos si no hay generos aun y si la lista es vacia la llenamos con los generos
//            if (GeneroDAO.listaGenero.isEmpty()) {
//                GeneroDAO gen = new GeneroDAO();
//                gen.agregarDatos();
//            }
           acceso = session.getAttribute("login")  != null ?  listar : login;            
        }else if(action.equalsIgnoreCase("add")){
            acceso = session.getAttribute("login")  != null ?  add : login;
        }
        else if(action.equalsIgnoreCase("Agregar")){
            String dpi=request.getParameter("txtDpi");
            String nom=request.getParameter("txtNom");
            String gen=request.getParameter("txtGen");
            String pass=request.getParameter("txtPass");
            p.setDpi(dpi);
            p.setNom(nom);
            p.setGenero(gen);
            p.setPass(pass);
            dao.add(p);
            acceso = session.getAttribute("login")  != null ?  listar : login;
        }
        else if(action.equalsIgnoreCase("editar")){
            request.setAttribute("idper",request.getParameter("id"));
            acceso = session.getAttribute("login")  != null ?  edit : login;
        }
        else if(action.equalsIgnoreCase("Actualizar")){
            id=Integer.parseInt(request.getParameter("txtid"));
            String dni=request.getParameter("txtDpi");
            String nom=request.getParameter("txtNom");
            String gen=request.getParameter("txtGen");
            p.setid(id);
            p.setDpi(dni);
            p.setGenero(gen);
            p.setNom(nom);
            dao.edit(p);
            acceso = session.getAttribute("login")  != null ?  listar : login;
        }
        else if(action.equalsIgnoreCase("eliminar")){
            id=Integer.parseInt(request.getParameter("id"));
            p.setid(id);
            dao.eliminar(id);
           acceso = session.getAttribute("login")  != null ?  listar : login;
        }   else if(action.equalsIgnoreCase("login")) {
            acceso = login;
        } 
        else if(action.equalsIgnoreCase("credential")) {
            
             String pass=request.getParameter("txtPass");
            String user=request.getParameter("txtUsername");
            if(dao.password(user, pass)){
                    // Guardar una variable en la sesión
                     session.setAttribute("login", user);
                     acceso = listar;
             }
            else {
            acceso = login;
            }
      }
     
        RequestDispatcher vista=request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
