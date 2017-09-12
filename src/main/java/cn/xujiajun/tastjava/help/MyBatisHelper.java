package cn.xujiajun.tastjava.help;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class MyBatisHelper {
    private static String resource = "mybatis/Configuration.xml";

    public static SqlSessionFactory getSqlSessionFactory() {

        try {
            Reader reader = getReader();
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            return sessionFactory;
        } catch (IOException e) {
            throw new RuntimeException("Exception in SqlSessionFactory", e);
        }

    }

    private static Reader getReader() throws IOException {
        Reader reader = Resources.getResourceAsReader(resource);
        return reader;
    }
}
