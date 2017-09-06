package cn.xujiajun.tastjava.service;

import cn.xujiajun.tastjava.dao.UserDAO;
import cn.xujiajun.tastjava.model.User;

import java.util.List;

public class UserService {
    private UserDAO userDAO;

    public List<User> GetUsers() {
        return this.userDAO.GetUsers();
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
