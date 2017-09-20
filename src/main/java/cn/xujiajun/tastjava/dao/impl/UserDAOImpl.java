package cn.xujiajun.tastjava.dao.impl;

import cn.xujiajun.tastjava.dao.UserDAO;
import cn.xujiajun.tastjava.help.DbHelper;
import cn.xujiajun.tastjava.entity.User;
import cn.xujiajun.tastjava.help.MyBatisHelper;
import cn.xujiajun.tastjava.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.pmw.tinylog.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public List<User> GetUsers() {
//        try {
//            List<User> listUser = new ArrayList<User>();
//
//            Connection jdbcConnection = DbHelper.getConnect();
//            String sql = "SELECT * FROM user ";
//            Statement statement = jdbcConnection.createStatement();
//            ResultSet resultSet = statement.executeQuery(sql);
//
//            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String userName = resultSet.getString("userName");
//                String password = resultSet.getString("password");
//
//                User user = new User();
//                user.setId(id);
//                user.setPassword(password);
//                user.setUserName(userName);
//                listUser.add(user);
//            }
//
//            resultSet.close();
//            statement.close();
//            DbHelper.disconnect();
//            Logger.info(listUser);
//            return listUser;
//
//        } catch (SQLException e) {
//            throw new RuntimeException("Exception in GetUsers,msg: " + e.getMessage() + e.toString(), e);
//        }
        return null;
    }

    public User GetUser() {
        SqlSessionFactory mySqlSessionFactory = MyBatisHelper.getSqlSessionFactory();
        SqlSession session = mySqlSessionFactory.openSession();

        try {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            User user = userMapper.getUser(1);
            return user;
        } finally {
            session.close();
        }
    }

    public User GetUser2(int id) {
        Connection jdbcConnection = null;
        try {
//            jdbcConnection = DbHelper.getDatasource().getConnection();
//            jdbcConnection = DbHelper.getDruidDataSource().getConnection();
            jdbcConnection = DbHelper.getDatasource().getConnection();
            String sql = "SELECT * FROM user Where id =?";
            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int userid = resultSet.getInt("id");
                String userName = resultSet.getString("userName");
                String password = resultSet.getString("password");

                User user = new User();
                user.setId(userid);
                user.setPassword(password);
                user.setUserName(userName);
                return user;
            }

            resultSet.close();
            statement.close();


        } catch (SQLException e) {
            throw new RuntimeException("Exception in GetUser2,msg: " + e.getMessage() + e.toString(), e);
        } finally {
            if (jdbcConnection != null) try {
                jdbcConnection.close();
            } catch (Exception ignore) {
            }
        }
        return null;
    }
}
