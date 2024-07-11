import java.sql.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        String dbUrl = "jdbc:postgresql://localhost:5432/prueba";
        String user = "postgres";
        String clave = "root";
       

        class Prueba {
            int id;
            String nombre;

            public int getCedula() {
                return id;
            }

            public String getNombre() {
                return nombre;
            }

            // Setters
            public void setCedula(int id) {
                this.id = id;
            }

            public void setNombre(String nombre) {
                this.nombre = nombre;
            }

        }

        try (Connection conn = DriverManager.getConnection(dbUrl, user, clave);
                Statement stmt = conn.createStatement();

        ) {

            Scanner scanner = new Scanner(System.in);
            boolean salir = false;

            do {
                System.out.println("\n");
                System.out.println("1. mostrar todos los clientes ");
                System.out.println("2. Crear un cliente ");
                System.out.println("3. Editar un cliente ");
                System.out.println("4. Eliminar un cliente ");
                System.out.println("\n Seleccione una opcion: ");
                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        try (ResultSet rs = stmt.executeQuery("select * from prueba;")) {
                            while (rs.next()) {

                                System.out.print("Id: " + rs.getInt("id"));
                                System.out.print(", Nombre: " + rs.getString("nombre"));
                                System.out.println();
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        scanner.nextLine();
                        break;

                    case 2:
                        System.out.println("Crear cliente: \n");

                        System.out.println("Id: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Nombre: ");
                        String nombre = scanner.nextLine();
                        Prueba prueba1 = new Prueba();
                        prueba1.setCedula(id);
                        prueba1.setNombre(nombre);
                        System.out.println("se insertara los datos: Cedula: " + prueba1.getCedula() + " Nombre: "
                                + prueba1.getNombre());
                        String obj1 = "INSERT INTO prueba (id, nombre) VALUES (" + prueba1.id + ", '" + prueba1.nombre
                                + "')";
                        stmt.executeUpdate(obj1);
                        System.out.println(
                                "Objeto insertado en base de datos vuelve a iniciar el programa para ver los cambios");
                        break;

                    case 3:

                        try (ResultSet ids = stmt.executeQuery("Select id from prueba")) {
                            while (ids.next()) {
                                System.out.print("Id: " + ids.getInt("id"));
                                System.out.println();
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Escriba el id a editar: ");
                        int edit = scanner.nextInt();
                        scanner.nextLine();


                        System.out.println("\n Columnas a editar: \n");
                        String datos = "SELECT column_name FROM information_schema.columns WHERE table_name = 'prueba';";
                        try (ResultSet tabla = stmt.executeQuery(datos)) {
                            while (tabla.next()) {

                                System.out.print(tabla.getString("column_name"));
                                System.out.println();
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Escriba la columna a editar");
                        String columna = scanner.nextLine();
                        System.out.println("Escriba el nuevo dato a establecer");
                        String dato = scanner.nextLine();

                        String edicion = "Update prueba set " + columna + " = '" + dato + "' where id = " + edit;
                        stmt.executeUpdate(edicion);
                        System.out.println("Objeto modificado en base de datos ");
                        scanner.nextLine();
                        break;

                    case 4:
                        try (ResultSet ids = stmt.executeQuery("Select id from prueba")) {
                            while (ids.next()) {

                                System.out.print("Id: " + ids.getInt("id"));
                                System.out.println();
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Escriba el id a eliminar: ");

                        int eliminar = scanner.nextInt();

                        scanner.nextLine();

                        String elimiarRegistro = "delete from prueba where id =" + eliminar;
                        stmt.executeUpdate(elimiarRegistro);
                        System.out.println("Objeto Eliminado en base de datos ");
                        scanner.nextLine();
                        break;

                        case 5: 
                        
                    

                    default:
                        salir = true;
                        break;
                }
            } while (salir == false);
            scanner.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}