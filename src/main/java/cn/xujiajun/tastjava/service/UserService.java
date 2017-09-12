package cn.xujiajun.tastjava.service;

import cn.xujiajun.tastjava.dao.UserDAO;
import cn.xujiajun.tastjava.entity.User;

import java.util.List;

public class UserService {
    private UserDAO userDAO;

    public List<User> GetUsers() {
        return this.userDAO.GetUsers();
    }

    public User GetUser() {
        return this.userDAO.GetUser();
    }

    /**
     * @return the userDAO
     */
    public UserDAO getUserDAO() {
        return userDAO;
    }

    /**
     * @param userDAO the userDAO to set
     */
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
