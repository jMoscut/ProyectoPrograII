<%-- 
    Document   : login
    Created on : 19 sept 2023, 01:15:26
    Author     : jackiesanchez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
           <form action="Controlador">
  <div class="mb-3">
    <label for="exampleInputEmail1" class="form-label">Username</label>
    <input name="txtUsername" type="text" class="form-control" id="exampleInputUsername" aria-describedby="usernameHelp">
  </div>
  <div class="mb-3">
    <label for="exampleInputPassword1" class="form-label">Contraseña</label>
    <input name="txtPass" type="password" class="form-control" id="exampleInputPassword1">
  </div>
   <input class="btn btn-primary" type="submit" name="accion" value="credential">
</form>
    </body>
</html>
