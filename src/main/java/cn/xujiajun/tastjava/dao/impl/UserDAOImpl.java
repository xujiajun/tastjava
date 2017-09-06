package cn.xujiajun.tastjava.dao.impl;

import cn.xujiajun.tastjava.dao.UserDAO;
import cn.xujiajun.tastjava.help.DbHelper;
import cn.xujiajun.tastjava.model.User;
import org.pmw.tinylog.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public List<User> GetUsers() {
        try {
            List<User> listUser = new ArrayList<User>();

            Connection jdbcConnection = DbHelper.getConnect();
            String sql = "SELECT * FROM user ";
            Statement statement = jdbcConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String userName = resultSet.getString("userName");
                String password = resultSet.getString("password");

                User user = new User();
                user.setId(id);
                user.setPassword(password);
                user.setUserName(userName);
                listUser.add(user);
            }

            resultSet.close();
            statement.close();
            DbHelper.disconnect();
            Logger.info(listUser);
            return listUser;

        } catch (SQLException $e) {
            throw new RuntimeException("Exception in GetUsers", $e);
        }
    }
}
