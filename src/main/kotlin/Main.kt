package org.example

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
data class User(val id: Int, val name: String)

fun main() {
    val c = Conexion()

    if(c.c.isValid(10)){
        c.c.use {
            val fabrica:IFactory = FactoryBD()
            val userDAO:IUserDAO = fabrica.crear()

            val usuario = User(2, "Roberto")
            userDAO.insertar(usuario)

            val usuario2 = User(41, "Paco")
            userDAO.modificar(usuario2)

            val usuario3 = User(40, "Pepe")
            userDAO.eliminar(usuario3)

            val usuarios = userDAO.mostrar()
            print(usuarios)
        }

    }
    else
        println("Error de conexión")

}