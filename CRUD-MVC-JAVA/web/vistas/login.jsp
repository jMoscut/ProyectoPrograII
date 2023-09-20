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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <div class="row justify-content-center align-items-center" style="height: 90vh">              
                   <form action="Controlador">
                         <h1 class="text-center fw-bold">Iniciar Sesión</h1>
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
            </div>
        </div>
    </body>
</html>
