<%-- 
    Document   : add
    Created on : 30-ago-2018, 19:58:16
    Author     : Joel
--%>

<%@page import="java.util.Iterator"%>
<%@page import="ModeloDAO.GeneroDAO"%>
<%@page import="Modelo.Genero"%>
<%@page import="java.util.List"%>
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
                <h1>Agregar Persona</h1>
                <form action="Controlador">
                    DNI:<br>
                    <input class="form-control" type="text" name="txtDpi"><br>
                    Nombres: <br>
                    <input class="form-control" type="text" name="txtNom"><br>
                    Contraseña: <br>
                    <input class="form-control" type="password" name="txtPass"><br>
                   Genero: <br>
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
                        <option value="<%= genero.getidGenero()%>" ><%= genero.getNombre()%></option>
                        <%}%>
                    </select>
                    <input class="btn btn-primary" type="submit" name="accion" value="Agregar">
                    <a href="Controlador?accion=listar">Regresar</a>
                </form>
            </div>

        </div>
    </body>
</html>
