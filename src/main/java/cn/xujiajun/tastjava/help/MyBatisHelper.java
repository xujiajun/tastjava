package cn.xujiajun.tastjava.help;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class MyBatisHelper {
    private static String resource = "mybatis/Configuration.xml";

    private static class sqlSessionFactoryBuilderHolder {
        private static final SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
    }

    public static SqlSessionFactory getSqlSessionFactory() {

        try {
            Reader reader = getReader();
            SqlSessionFactory sessionFactory = sqlSessionFactoryBuilderHolder.sqlSessionFactoryBuilder.build(reader);
            return sessionFactory;
        } catch (IOException e) {
            throw new RuntimeException("Exception in getSqlSessionFactory", e);
        }

    }

    private static Reader getReader() throws IOException {
        Reader reader = Resources.getResourceAsReader(resource);
        return reader;
    }
}
