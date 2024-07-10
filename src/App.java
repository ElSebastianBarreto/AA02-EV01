import java.sql.*;

public class App {
    public static void main(String[] args) {
     
        String dbUrl = "jdbc:postgresql://localhost:5432/prueba";
        String user = "postgres";
        String clave = "root";

         class Cliente {

            int Cedula; 
            String Nombre; 
            String Apellido;
            int Edad; 
            String Telefono;
            String Correo; 
            String Clave; 
         }
    
        try (Connection conn = DriverManager.getConnection(dbUrl, user, clave);
             Statement stmt = conn.createStatement();
             ) {











        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}