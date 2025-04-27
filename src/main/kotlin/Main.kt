package org.example
import org.example.Conexión.*
import org.example.userDAO.*
import org.example.AbstractFactory.*
import org.example.Datos.User

fun main() {
    val c = ConnectionBuilder()

    if(c.connection.isValid(10)){
        c.connection.use {
            val fabrica:IFactory = FactoryDB()
            val userDAO: IUserDAO = fabrica.crear()

            val usuario = User(50, "Roberto")
            var filasAfectadas = userDAO.insertar(usuario)
            println("En la inserción, ha sido afectada $filasAfectadas fila.")

            val usuario2 = User(50, "Paco")
            filasAfectadas = userDAO.modificar(usuario2)
            println("En la modificación, ha sido afectada $filasAfectadas fila.")

            filasAfectadas = userDAO.eliminarPorID(49)
            println("En la eliminación, ha sido afectada $filasAfectadas fila.")

            val usuarios = userDAO.obtenerTodos()
            print(usuarios)
        }

    }
    else
        println("Error de conexión")

}