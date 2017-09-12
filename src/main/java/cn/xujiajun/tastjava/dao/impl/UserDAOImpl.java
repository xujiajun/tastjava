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

        } catch (SQLException e) {
            throw new RuntimeException("Exception in GetUsers,msg: " + e.getMessage() + e.toString(), e);
        }
    }

    public User GetUser() {
        try {
            SqlSessionFactory mySqlSessionFactory = MyBatisHelper.getSqlSessionFactory();

            /**
             * 映射sql的标识字符串，
             * me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
             * getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
             */
            String statement = "cn.xujiajun.User.getUser";//映射sql的标识字符串

            //创建能执行映射文件中sql的sqlSession
            SqlSession session = mySqlSessionFactory.openSession();
            UserMapper userMapper = session.getMapper(UserMapper.class);

            //执行查询返回一个唯一user对象的sql
            User user = userMapper.getUser(1);
            session.close();
            return user;
        } catch (IOException $e) {
            throw new RuntimeException("Exception in GetUser", $e);
        }
    }
}
