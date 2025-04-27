package org.example.userDAO
import org.example.Datos.User
import java.sql.Connection
import java.sql.SQLException

class UserDAOBD(private val c: Connection): IUserDAO
{
    override fun insertar(user: User): Int {
        var filasAfectadas = 0

        try {
            c.prepareStatement("INSERT INTO users (name) VALUES (?)").use { st ->
                st.setString(1, user.name)
                filasAfectadas = st.executeUpdate()
            }
        } catch (e: SQLException) {println("Error de BBDD")}

        return filasAfectadas
    }

    override  fun modificar(user: User): Int {
        var filasAfectadas = 0

        try {
            val statement = c.prepareStatement("UPDATE users SET name = ? WHERE id = ?")
            statement.setString(1, user.name)
            statement.setInt(2, user.id)
            filasAfectadas = statement.executeUpdate()
            statement.close()
        }catch (e: SQLException) {println("Error de BBDD")}

        return filasAfectadas
    }

    override fun obtenerTodos():MutableList<User> {
        val usuarios = mutableListOf<User>()
        try {
            val statement = c.createStatement()
            val query = "SELECT id, name FROM users"
            val resultSet = statement.executeQuery(query)

            while (resultSet.next()) {
                val id = resultSet.getInt("id")
                val nombre = resultSet.getString("name")
                usuarios.add(User(id, nombre))
            }

            resultSet.close()
            statement.close()
        }
        catch (e: SQLException) {println("Error de BBDD")}

        return usuarios

    }
    override fun eliminarPorID(id: Int): Int {
        var filasAfectadas = 0

        try {
            val statement = c.prepareStatement("DELETE FROM users WHERE id = ?")
            statement.setInt(1, id)
            filasAfectadas = statement.executeUpdate()
            statement.close()
        }catch (e:SQLException){println("Error de BBDDD")}

        return filasAfectadas
    }

}