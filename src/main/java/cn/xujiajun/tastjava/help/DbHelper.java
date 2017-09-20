package cn.xujiajun.tastjava.help;

import cn.xujiajun.tastjava.provider.datasource.DruidProvider;
import cn.xujiajun.tastjava.provider.datasource.HikariCPProvider;
import cn.xujiajun.tastjava.util.PropsUtil;
import cn.xujiajun.tastjava.util.StrUtil;

import javax.sql.DataSource;
import java.util.Properties;

public class DbHelper {
    private static String jdbcProvider;

    static {
        Properties conf = PropsUtil.loadProps("config.properties");
        jdbcProvider = conf.getProperty("jdbc.provider");
    }

    public static DataSource getDatasource() {
        if (StrUtil.isBlank(jdbcProvider) || (!StrUtil.isBlank(jdbcProvider) && ("HikariCP" == jdbcProvider))) {
            HikariCPProvider dataSourceProvider = HikariCPProvider.getInstance();
            dataSourceProvider.register();
            return dataSourceProvider.getDataSource();
        }

        if (!StrUtil.isBlank(jdbcProvider) && ("Druid" == jdbcProvider)) {
            DruidProvider dataSourceProvider = DruidProvider.getInstance();
            dataSourceProvider.register();
            return dataSourceProvider.getDataSource();
        }

        return null;
    }
}
