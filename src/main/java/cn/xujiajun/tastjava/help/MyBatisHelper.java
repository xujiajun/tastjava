package cn.xujiajun.tastjava.help;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class MyBatisHelper {
    public static SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis/Configuration.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

        return sessionFactory;
    }
}
