package org.example

interface IUserDAO {
    fun insertar(user: User)
    fun modificar(user: User)
    fun mostrar():MutableList<User>
    fun eliminar(user: User)
}

interface IFactory {
    fun crear(): IUserDAO
}