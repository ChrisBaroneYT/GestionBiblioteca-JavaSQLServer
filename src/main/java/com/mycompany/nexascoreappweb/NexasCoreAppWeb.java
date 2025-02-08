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
                                + "databaseName=NEXASCORE;"
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
}
