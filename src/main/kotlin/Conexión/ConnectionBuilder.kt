package org.example.Conexión
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class ConnectionBuilder {
    lateinit var connection: Connection
    private val url = "jdbc:h2:./DataBase/mydb"
    private val usuario = "user"
    private val contraseña = "password"

    init {
        try {
            Class.forName("org.h2.Driver")
            connection = DriverManager.getConnection(url, usuario, contraseña)
        } catch (e: SQLException) {
            println("Error en la conexión: ${e.message}")
        } catch (e: ClassNotFoundException) {
            println("No se encontró el driver JDBC: ${e.message}")
        }
    }
}