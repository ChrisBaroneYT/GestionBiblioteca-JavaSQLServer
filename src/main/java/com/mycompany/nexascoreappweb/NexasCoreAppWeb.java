package com.mycompany.nexascoreappweb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NexasCoreAppWeb {

    // Conexión a la base de datos
    private static Connection connection;

    public static void main(String[] args) {
        // Conectar con la base de datos
        conectarBaseDatos();

        // Verificar si la conexión es válida antes de ejecutar las inserciones
        if (connection != null) {
            // Inserciones
            insertarLibros();
            insertarPrestamo();
            insertarRol();
            insertarUsuario();

            // Leer los libros desde la base de datos
            LeerLibro(connection);
        } else {
            System.out.println("No se pudo establecer la conexión. No se ejecutarán inserciones.");
        }

        // Cerrar la conexión después de usarla
        cerrarConexion();
    }

    public static void conectarBaseDatos() {
        String connectionUrl = "jdbc:sqlserver://172.30.30.35:1433;"
                + "databaseName=BIBLIOTECA;"
                + "user=root;"
                + "password=root;"
                + "encrypt=true;"
                + "trustServerCertificate=true;";

        try {
            // Cargar el driver (Opcional en versiones modernas de JDBC, pero útil para depuración)
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Establecer la conexión con la base de datos
            connection = DriverManager.getConnection(connectionUrl);
            System.out.println("Conexión exitosa a la base de datos!");

        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontró el driver JDBC.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    // Método para leer los libros desde la base de datos
    public static void LeerLibro(Connection connection) {
        String consultaLibroSql = 
            "SELECT TOP 1 Id_Libro, Titulo, Autor, Fecha, ISBN FROM LIBRO";

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(consultaLibroSql);

            if (rs.next()) {
                // Recuperar los datos de la primera fila del ResultSet
                int id = rs.getInt("Id_Libro");
                String nombre = rs.getString("Titulo");
                String autor = rs.getString("Autor");
                String fecha = rs.getString("Fecha");
                String isbn = rs.getString("ISBN");

                // Mostrar los resultados en la consola
                System.out.println("ID: " + id + ", Título: " + nombre + ", Autor: " + autor + ", Fecha: " + fecha + ", ISBN: " + isbn);
            } else {
                System.out.println("No se pudo leer ningún dato de la tabla LIBRO.");
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
        }
    }

    // Métodos para insertar los datos
    public static void insertarLibros() {
        if (connection == null) {
            System.out.println("No se puede insertar libros porque la conexión es nula.");
            return;
        }

        String insertarLibro = "INSERT INTO LIBRO(Id_Libro, Titulo, Autor, Fecha, ISBN) VALUES(?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(insertarLibro)) {
            stmt.setString(1, "3");
            stmt.setString(2, "Web II");
            stmt.setString(3, "Arturo");
            stmt.setString(4, "2025-02-08");
            stmt.setString(5, "987654321");

            stmt.executeUpdate();
            System.out.println("El libro fue insertado satisfactoriamente");

        } catch (SQLException e) {
            System.out.println("Error al insertar el libro: " + e.getMessage());
        }
    }

    // Método para insertar préstamo (sin cambios)
    public static void insertarPrestamo() {
        if (connection == null) {
            System.out.println("No se puede insertar préstamo porque la conexión es nula.");
            return;
        }

        String insertarPrestamo = "INSERT INTO PRESTAMO(ID_Prestamo, Titulo_Libro, ISBN, Fecha_de_Prestamo, Fecha_de_Devolucion) VALUES(?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(insertarPrestamo)) {
            stmt.setInt(1, 1);
            stmt.setString(2, "Web II");
            stmt.setString(3, "987654321");
            stmt.setString(4, "2025-02-01");
            stmt.setString(5, "2025-02-15");

            stmt.executeUpdate();
            System.out.println("El préstamo fue insertado satisfactoriamente");

        } catch (SQLException e) {
            System.out.println("Error al insertar el préstamo: " + e.getMessage());
        }
    }

    // Método para insertar rol (sin cambios)
    public static void insertarRol() {
        if (connection == null) {
            System.out.println("No se puede insertar rol porque la conexión es nula.");
            return;
        }

        String insertarRol = "INSERT INTO ROLES(ID_Rol, Nombre, Contrasena, Identificacion, Administrador, SuperAdministrador, Usuariolibro) VALUES(?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(insertarRol)) {
            stmt.setInt(1, 1);
            stmt.setString(2, "Admin");
            stmt.setString(3, "admin123");
            stmt.setInt(4, 123456);
            stmt.setInt(5, 1);
            stmt.setInt(6, 0);
            stmt.setInt(7, 1);

            stmt.executeUpdate();
            System.out.println("El rol fue insertado satisfactoriamente");

        } catch (SQLException e) {
            System.out.println("Error al insertar el rol: " + e.getMessage());
        }
    }

    // Método para insertar usuario (sin cambios)
    public static void insertarUsuario() {
        if (connection == null) {
            System.out.println("No se puede insertar usuario porque la conexión es nula.");
            return;
        }

        String insertarUsuario = "INSERT INTO USUARIO(ID_Usuario, Nombre, Direccion, Telefono) VALUES(?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(insertarUsuario)) {
            stmt.setInt(1, 1);
            stmt.setString(2, "Carlos");
            stmt.setString(3, "Calle Ficticia 123");
            stmt.setInt(4, 1234567890);

            stmt.executeUpdate();
            System.out.println("El usuario fue insertado satisfactoriamente");

        } catch (SQLException e) {
            System.out.println("Error al insertar el usuario: " + e.getMessage());
        }
    }

    // Cerrar la conexión
    public static void cerrarConexion() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
