import java.sql.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        String dbUrl = "jdbc:postgresql://localhost:5432/prueba";
        String user = "postgres";
        String clave = "root";
       

        class Cliente {
            int cedula;
            String nombre;
            String apellido;
            int edad;
            String correo;
            String clave;
            String telefono;

            public int getCedula() {
                return cedula;
            }

            public String getNombre() {
                return nombre;
            }

            public String getApellido() {
                return apellido;
            }

            public int getEdad() {
                return edad;
            }

            public String getCorreo() {
                return correo;
            }

            public String getClave() {
                return clave;
            }

            public String getTelefono() {
                return telefono;
            }

            // Setters
            public void setCedula(int cedula) {
                this.cedula = cedula;
            }

            public void setNombre(String nombre) {
                this.nombre = nombre;
            }

            public void setApellido(String apellido) {
                this.apellido = apellido;
            }
            public void setEdad(int edad) {
                this.edad = edad;
            }

            public void setCorreo(String correo) {
                this.correo = correo;
            }

            public void setClave(String clave) {
                this.clave = clave;
            }

            public void setTelefono(String telefono) {
                this.telefono = telefono;
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
                        try (ResultSet rs = stmt.executeQuery("select * from cliente;")) {
                            while (rs.next()) {

                                System.out.print("Cedula: " + rs.getInt("cedula"));
                                System.out.print(", Nombre: " + rs.getString("nombre"));
                                System.out.print(", Apellido: " + rs.getString("apellido"));
                                System.out.print(", Edad: " + rs.getInt("edad"));
                                System.out.print(", Correo: " + rs.getString("correo"));
                                System.out.print(", Clave: " + rs.getString("clave"));
                                System.out.print(", telefono: " + rs.getString("telefono"));
                                System.out.println();
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        scanner.nextLine();
                        break;

                    case 2:
                        System.out.println("Crear cliente: \n");
                        Cliente cliente1 = new Cliente();
                        System.out.println("Id: ");
                        cliente1.setCedula(scanner.nextInt());
                        scanner.nextLine();


                        System.out.println("Nombre: ");
                        cliente1.setNombre(scanner.nextLine());

                        System.out.println("Apellido: ");
                        cliente1.setApellido(scanner.nextLine());

                        System.out.println("Edad: ");
                        cliente1.setEdad(scanner.nextInt());
                        scanner.nextLine();

                        System.out.println("Correo: ");
                        cliente1.setCorreo(scanner.nextLine());

                        System.out.println("Clave: ");
                        cliente1.setClave(scanner.nextLine());

                        System.out.println("Telefono: ");
                        cliente1.setTelefono(scanner.nextLine());

                       String objeto =  "Insert into cliente (cedula, nombre, apellido, edad, correo, clave, telefono) values (" +
                       cliente1.getCedula()+", '"+ cliente1.getNombre()+ "', '"+ cliente1.getApellido()+ "', " + cliente1.getEdad() +
                       ", '" + cliente1.getCorreo() + "', '" + cliente1.getClave() + "', '" + cliente1.getTelefono() + "');" ;
                       stmt.executeUpdate(objeto);
                        
                        System.out.println(
                                "Objeto insertado en base de datos vuelve a iniciar el programa para ver los cambios");
                        break;

                    case 3:

                        try (ResultSet ids = stmt.executeQuery("Select cedula from cliente")) {
                            while (ids.next()) {
                                System.out.print("Id: " + ids.getInt("cedula"));
                                System.out.println();
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Escriba la cedula a editar: ");
                        int edit = scanner.nextInt();
                        scanner.nextLine();


                        System.out.println("\n Columnas a editar: \n");
                        String datos = "SELECT column_name FROM information_schema.columns WHERE table_name = 'cliente';";
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

                        String edicion = "Update cliente set " + columna + " = '" + dato + "' where cedula = " + edit;
                        stmt.executeUpdate(edicion);
                        System.out.println("Objeto modificado en base de datos ");
                        scanner.nextLine();
                        break;

                    case 4:
                        try (ResultSet ids = stmt.executeQuery("Select cedula from cliente")) {
                            while (ids.next()) {

                                System.out.print("cedula: " + ids.getInt("cedula"));
                                System.out.println();
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Escriba la cedula a eliminar: ");

                        int eliminar = scanner.nextInt();

                        scanner.nextLine();

                        String elimiarRegistro = "delete from cliente where cedula =" + eliminar;
                        stmt.executeUpdate(elimiarRegistro);
                        System.out.println("Objeto Eliminado en base de datos ");
                        scanner.nextLine();
                        break;
        
                    

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