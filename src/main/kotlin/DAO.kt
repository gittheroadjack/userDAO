package org.example
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class FactoryBD:IFactory
{
    override fun crear(): IUserDAO {
        val daobd:IUserDAO = UserDAOBD(Conexion().c)
        return daobd
    }

}

class Conexion
{
    lateinit var c: Connection
    private val url = "jdbc:h2:./DataBase/mydb"
    private val usuario = "user"
    private val contraseña = "password"

    init {
        try {
            Class.forName("org.h2.Driver")
            c = DriverManager.getConnection(url, usuario, contraseña)
        } catch (e: SQLException) {
            println("Error en la conexión: ${e.message}")
        } catch (e: ClassNotFoundException) {
            println("No se encontró el driver JDBC: ${e.message}")
        }
    }
}

class UserDAOBD(private val c: Connection): IUserDAO
{
    override fun insertar(user: User) {
        try {
            c.prepareStatement("INSERT INTO users (name) VALUES (?)").use { st ->
                st.setString(1, user.name)
                st.executeUpdate()
            }
        } catch (e: SQLException) {println("Error de BBDD")}
    }

    override  fun modificar(user: User) {
        val statement = c.prepareStatement("UPDATE users SET name = ? WHERE id = ?")
        statement.setString(1, user.name)
        statement.setInt(2, user.id)
        statement.executeUpdate()
        statement.close()
    }

    override fun mostrar():MutableList<User> {
        val statement = c.createStatement()
        val query = "SELECT id, name FROM users"
        val resultSet = statement.executeQuery(query)

        val usuarios = mutableListOf<User>()
        while (resultSet.next()) {
            val id = resultSet.getInt("id")
            val nombre = resultSet.getString("name")
            usuarios.add(User(id, nombre))
        }

        statement.close()

        return usuarios
    }
    override fun eliminar(user: User) {
        val statement = c.prepareStatement("DELETE FROM users WHERE id = ?")
        statement.setInt(1, user.id)
        statement.executeUpdate()
        statement.close()
    }

}