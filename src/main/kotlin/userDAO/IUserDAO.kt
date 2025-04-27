package org.example.userDAO
import org.example.Datos.User

interface IUserDAO {
    fun insertar(user: User): Int
    fun modificar(user: User): Int
    fun obtenerTodos():MutableList<User>
    fun eliminarPorID(id: Int): Int
}