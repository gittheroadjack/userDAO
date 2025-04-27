package org.example.AbstractFactory

import org.example.userDAO.IUserDAO

interface IFactory {
    fun crear(): IUserDAO
}