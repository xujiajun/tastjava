package cn.xujiajun.tastjava.provider.datasource;

import cn.xujiajun.tastjava.provider.BaseProvider;
import cn.xujiajun.tastjava.util.PropsUtil;

import java.util.Properties;

public class BaseDataSourceProvider extends BaseProvider {
    protected static String jdbcURL;
    protected static String jdbcDriver;
    protected static String jdbcUsername;
    protected static String jdbcPassword;

    static {
        Properties conf = PropsUtil.loadProps("config.properties");
        jdbcURL = conf.getProperty("jdbc.url");
        jdbcUsername = conf.getProperty("jdbc.username");
        jdbcPassword = conf.getProperty("jdbc.password");
        jdbcDriver = conf.getProperty("jdbc.driver");
    }
}
