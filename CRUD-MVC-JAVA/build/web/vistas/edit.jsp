
<%@page import="java.util.Iterator"%>
<%@page import="ModeloDAO.GeneroDAO"%>
<%@page import="Modelo.Genero"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Persona"%>
<%@page import="ModeloDAO.PersonaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <div class="col-lg-6">
              <%
              PersonaDAO dao=new PersonaDAO();
              int id=Integer.parseInt((String)request.getAttribute("idper"));
              Persona p=(Persona)dao.list(id);
          %>
             <h1>Modificar Persona</h1>
            <form action="Controlador">
                DNI:<br>
                <input class="form-control" type="text" name="txtDpi" value="<%= p.getDpi()%>"><br>
                Nombres: <br>
                <input class="form-control" type="text" name="txtNom" value="<%= p.getNom()%>"><br>
                 <select class="form-control mb-3" name="txtGen" >
                <%
                           
        
                    List<Genero>list=GeneroDAO.listaGenero;
                    Iterator<Genero>iter=list.iterator();
                    Genero genero=null;
                    int leido = 0;
                    while(leido <3 && iter.hasNext()){
                        genero=iter.next();
                        leido++;
                    
                %>                    
                        %>
                        <option value="<%= genero.getNombre()%>" ><%= genero.getNombre()%></option>
                        <%}%>
                    </select>
                <input type="hidden" name="txtid" value="<%= p.getid()%>">
                <input class="btn btn-primary" type="submit" name="accion" value="Actualizar"> 
                <a href="Controlador?accion=listar">Regresar</a>
            </form>
          </div>
          
        </div>
    </body>
</html>
