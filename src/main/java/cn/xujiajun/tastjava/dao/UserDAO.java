package cn.xujiajun.tastjava.dao;

import cn.xujiajun.tastjava.entity.User;

import java.util.List;

public interface UserDAO {
    List<User> GetUsers();
    User GetUser();
}
