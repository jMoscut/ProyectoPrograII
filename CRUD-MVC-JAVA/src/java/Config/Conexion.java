
package Config;

import java.sql.*;

public class Conexion {
    Connection con;
    public Conexion(){
        try {
          Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/crud","root","andrea2911");            
        } catch (Exception e) {
            System.err.println("Error"+e);
        }
    }
    public Connection getConnection(){
        return con;
    }
}
