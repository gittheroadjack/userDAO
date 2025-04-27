package org.example.AbstractFactory
import org.example.Conexión.ConnectionBuilder
import org.example.userDAO.IUserDAO
import org.example.userDAO.UserDAOBD

class FactoryDB: IFactory {
    override fun crear(): IUserDAO {
        val daobd: IUserDAO = UserDAOBD(ConnectionBuilder().connection)
        return daobd
    }
}