/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.nexascoreappweb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NexasCoreAppWeb {
    // Conexión a la base de datos
    private static Connection connection;

    // Función para conectar con la base de datos
    public static void main(String[] args) {
        // Conectar con la base de datos
        conectarBaseDatos();
        // Insertar un libro
        insertarLibros(connection);
    }

    public static void conectarBaseDatos() {
        // Definir la URL de conexión correctamente con los parámetros de usuario y contraseña
        String connectionUrl = "jdbc:sqlserver://172.30.30.35:1433;"
                                + "databaseName=BIBLIOTECA;"
                                + "user=root;"
                                + "password=root;"
                                + "encrypt=true;"
                                + "trustServerCertificate=true;";

        try {
            // Establecer la conexión con la base de datos
            connection = DriverManager.getConnection(connectionUrl);
            System.out.println("Conexión exitosa a la base de datos!");

        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    public static void insertarLibros(Connection connection) {
        // Definir el SQL para insertar un libro
        String insertarLibro = "INSERT INTO LIBRO(Id_Libro, Titulo, Autor, Fecha, ISBN) VALUES(?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(insertarLibro)) {
            // Establecer los parámetros para el PreparedStatement
            stmt.setString(1, "3");
            stmt.setString(2, "Web II");
            stmt.setString(3, "Arturo");
            stmt.setString(4, "2025-02-08");
            stmt.setString(5, "987654321");

            // Ejecutar la inserción
            stmt.executeUpdate();
            System.out.println("El libro fue insertado satisfactoriamente");

        } catch (SQLException e) {
            System.out.println("Error al insertar el libro: " + e.getMessage());
        }
    }
    public static void insertarPrestamo(Connection connection) {
    String insertarPrestamo = "INSERT INTO PRESTAMO(ID_Prestamo, Titulo_Libro, ISBN, Fecha_de_Prestamo, Fecha_de_Devolucion) VALUES(?, ?, ?, ?, ?)";

    try (PreparedStatement stmt = connection.prepareStatement(insertarPrestamo)) {
        stmt.setInt(1, 1);  // ID_Prestamo, tipo numeric
        stmt.setString(2, "Web II");  // Titulo_Libro, tipo nvarchar
        stmt.setString(3, "987654321");  // ISBN, tipo nvarchar
        stmt.setString(4, "2025-02-01");  // Fecha_de_Prestamo, tipo datetime
        stmt.setString(5, "2025-02-15");  // Fecha_de_Devolucion, tipo datetime

        stmt.executeUpdate();
        System.out.println("El préstamo fue insertado satisfactoriamente");

    } catch (SQLException e) {
        System.out.println("Error al insertar el préstamo: " + e.getMessage());
    }
}
    public static void insertarRol(Connection connection) {
    String insertarRol = "INSERT INTO ROLES(ID_Rol, Nombre, Contrasena, Identificacion, Administrador, SuperAdministrador, Usuariolibro) VALUES(?, ?, ?, ?, ?, ?, ?)";

    try (PreparedStatement stmt = connection.prepareStatement(insertarRol)) {
        stmt.setInt(1, 1);  // ID_Rol, tipo numeric
        stmt.setString(2, "Admin");  // Nombre, tipo nvarchar
        stmt.setString(3, "admin123");  // Contrasena, tipo nvarchar
        stmt.setInt(4, 123456);  // Identificacion, tipo numeric
        stmt.setInt(5, 1);  // Administrador, tipo numeric
        stmt.setInt(6, 0);  // SuperAdministrador, tipo numeric
        stmt.setInt(7, 1);  // Usuariolibro, tipo numeric

        stmt.executeUpdate();
        System.out.println("El rol fue insertado satisfactoriamente");

    } catch (SQLException e) {
        System.out.println("Error al insertar el rol: " + e.getMessage());
    }
}
    public static void insertarUsuario(Connection connection) {
    String insertarUsuario = "INSERT INTO USUARIO(ID_Usuario, Nombre, Direccion, Telefono) VALUES(?, ?, ?, ?)";

    try (PreparedStatement stmt = connection.prepareStatement(insertarUsuario)) {
        stmt.setInt(1, 1);  // ID_Usuario, tipo numeric
        stmt.setString(2, "Carlos");  // Nombre, tipo nvarchar
        stmt.setString(3, "Calle Ficticia 123");  // Direccion, tipo nvarchar
        stmt.setInt(4, 1234567890);  // Telefono, tipo numeric

        stmt.executeUpdate();
        System.out.println("El usuario fue insertado satisfactoriamente");

    } catch (SQLException e) {
        System.out.println("Error al insertar el usuario: " + e.getMessage());
    }
}

}
