# 📚 NexasCoreAppWeb

## ✨ Descripción
NexasCoreAppWeb es una aplicación en Java que permite la gestión de una biblioteca mediante conexión a una base de datos SQL Server. El proyecto implementa la inserción de registros en distintas tablas como **📖 LIBRO, 📑 PRESTAMO, 👤 ROLES y 🏠 USUARIO**.

## 🛠 Tecnologías Utilizadas
- ☕ **Java** (JDK 8 o superior)
- 🗄 **SQL Server**
- 🔗 **JDBC** (Java Database Connectivity)

## 📥 Instalación y Configuración
### 1️⃣ Requisitos previos
- Tener instalado **Java JDK** 🏗.
- Configurar un servidor **SQL Server** ⚙️.
- Agregar el conector **JDBC para SQL Server** 🔌.

### 2️⃣ Configuración de la Base de Datos
Ejecutar el siguiente script en SQL Server para crear las tablas necesarias 📌:

```sql
CREATE TABLE LIBRO (
    Id_Libro INT PRIMARY KEY,
    Titulo NVARCHAR(255),
    Autor NVARCHAR(255),
    Fecha DATE,
    ISBN NVARCHAR(20)
);

CREATE TABLE PRESTAMO (
    ID_Prestamo INT PRIMARY KEY,
    Titulo_Libro NVARCHAR(255),
    ISBN NVARCHAR(20),
    Fecha_de_Prestamo DATE,
    Fecha_de_Devolucion DATE
);

CREATE TABLE ROLES (
    ID_Rol INT PRIMARY KEY,
    Nombre NVARCHAR(255),
    Contrasena NVARCHAR(255),
    Identificacion INT,
    Administrador INT,
    SuperAdministrador INT,
    Usuariolibro INT
);

CREATE TABLE USUARIO (
    ID_Usuario INT PRIMARY KEY,
    Nombre NVARCHAR(255),
    Direccion NVARCHAR(255),
    Telefono INT
);
```

### 3️⃣ Configuración del Proyecto
Modificar la siguiente línea del código `NexasCoreAppWeb.java` con los valores adecuados para la conexión a la base de datos 📝:

```java
String connectionUrl = "jdbc:sqlserver://<IP_DEL_SERVIDOR>:1433;"
                        + "databaseName=BIBLIOTECA;"
                        + "user=<USUARIO>;"
                        + "password=<CONTRASEÑA>;"
                        + "encrypt=true;"
                        + "trustServerCertificate=true;";
```

## 🚀 Uso
1️⃣ Ejecutar la clase `NexasCoreAppWeb.java` para establecer la conexión con la base de datos.
2️⃣ Insertar registros en las tablas mediante las funciones `insertarLibros()`, `insertarPrestamo()`, `insertarRol()`, e `insertarUsuario()`.
3️⃣ Verificar los registros en SQL Server 🧐.

## 👨‍💻 Autor
**Cristian Sánchez** ✍️

## 📜 Licencia
Este proyecto es de uso académico y no tiene licencia definida.
