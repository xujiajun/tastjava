package cn.xujiajun.tastjava.provider.datasource;

import cn.xujiajun.tastjava.provider.IProvider;
import cn.xujiajun.tastjava.util.StrUtil;
import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;

public class DruidProvider extends BaseDataSourceProvider implements IProvider, IDataSourceProvider {

    private static DruidDataSource ds;
    private String name = null;

    /**
     * 最大连接池数量
     */
    private int maxActive = 100;

    /**
     * 最小连接池数量
     */
    private int minIdle = 10;

    /**
     * 用来检测连接是否有效的sql
     */
    private String validationQuery = "select 1";

    /**
     * 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
     */
    private boolean testOnBorrow = false;

    /**
     * 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
     */
    private boolean testOnReturn = false;

    /**
     * 建议配置为true，不影响性能，并且保证安全性。
     * 申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
     */
    private boolean testWhileIdle = true;

    /**
     * 有两个含义：
     * 1) Destroy线程会检测连接的间隔时间，如果连接空闲时间大于等于minEvictableIdleTimeMillis则关闭物理连接。
     * 2) testWhileIdle的判断依据，详细看testWhileIdle属性的说明
     * 单位是毫秒
     */
    private long timeBetweenEvictionRunsMillis = 60000;

    /**
     * 连接保持空闲而不被驱逐的最小时间
     * 单位是毫秒
     */
    private long minEvictableIdleTimeMillis = 300000;


    private DruidProvider() {
    }

    private static class SingletonHolder {
        private static final DruidProvider INSTANCE = new DruidProvider();
    }

    public static final DruidProvider getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public void setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public void setTestWhileIdle(boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public void setMinEvictableIdleTimeMillis(long minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    @Override
    public boolean register() {
        if (IsRegistered) {
            return HAS_REGISTERED;
        }

        DruidDataSource ds = new DruidDataSource();

        if (!StrUtil.isBlank(name)) {
            ds.setName(this.name);
        }

        ds.setUrl(jdbcURL);
        ds.setDriverClassName(jdbcDriver);
        ds.setUsername(jdbcUsername);
        ds.setPassword(jdbcPassword);

        ds.setMinIdle(minIdle);
        ds.setMaxActive(maxActive);
        ds.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        ds.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        ds.setValidationQuery(validationQuery);

        ds.setTestWhileIdle(testWhileIdle);
        ds.setTestOnBorrow(testOnBorrow);
        ds.setTestOnReturn(testOnReturn);

        setDataSource(ds);
        setIsRegistered(HAS_REGISTERED);
        return HAS_REGISTERED;
    }

    @Override
    public DataSource getDataSource() {
        return ds;
    }

    private void setDataSource(DruidDataSource datasource) {
        ds = datasource;
    }
}
