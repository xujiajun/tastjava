package cn.xujiajun.tastjava.provider.datasource;

import cn.xujiajun.tastjava.provider.IProvider;
import cn.xujiajun.tastjava.util.StrUtil;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class HikariCPProvider extends BaseDataSourceProvider implements IProvider, IDataSourceProvider {

    private static HikariDataSource ds;

    /**
     * This property controls the default auto-commit behavior of connections returned from the pool.
     * It is a boolean value.Default: true
     */
    private boolean autoCommit = true;

    /**
     * This property controls the maximum number of milliseconds that a client (that's you) will wait for a connection from the pool.
     * If this time is exceeded without a connection becoming available, a SQLException will be thrown.
     * Lowest acceptable connection timeout is 250 ms. Default: 30000 (30 seconds)
     */
    private long connectionTimeout = 30000;

    /**
     * This property controls the maximum amount of time that a connection is allowed to sit idle in the pool.
     * The minimum allowed value is 10000ms (10 seconds). Default: 600000 (10 minutes)
     */
    private long idleTimeout = 600000;

    /**
     * This property controls the maximum lifetime of a connection in the pool
     * Default: 1800000 (30 minutes)
     */
    private long maxLifetime = 1800000;


    /**
     * This property controls the maximum size that the pool is allowed to reach, including both idle and in-use connections
     * about pool sizing: https://github.com/brettwooldridge/HikariCP/wiki/About-Pool-Sizing
     * Default: 10
     */
    private int maximumPoolSize = 10;

    /**
     * This property represents a user-defined name for the connection pool and appears mainly in logging
     * and JMX management consoles to identify pools and pool configurations.
     * Default: auto-generated
     */
    private String poolName = null;

    /**
     * This property sets the default catalog for databases that support the concept of catalogs.
     * If this property is not specified, the default catalog defined by the JDBC driver is used.
     * Default: driver default
     */
    private String catalog = null;

    /**
     * This property sets a SQL statement that will be executed after every new connection creation before adding it to the pool.
     * If this SQL is not valid or throws an exception, it will be treated as a connection failure and the standard retry logic will be followed.
     * Default: none
     */
    private String connectionInitSql = null;

    /**
     * This property controls the default transaction isolation level of connections returned from the pool
     * Default: driver default
     */
    private String transactionIsolation = null;

    /**
     * This property controls the maximum amount of time that a connection will be tested for aliveness.
     * This value must be less than the connectionTimeout. Lowest acceptable validation timeout is 250 ms.
     * Default: 5000
     */
    private long validationTimeout = 5000;

    /**
     * This property controls the amount of time that a connection can be out of the pool before a message is logged indicating a possible connection leak.
     * A value of 0 means leak detection is disabled.Lowest acceptable value for enabling leak detection is 2000 (2 seconds).
     * Default: 0
     */
    private long leakDetectionThreshold = 0;

    private static class SingletonHolder {
        private static final HikariCPProvider INSTANCE = new HikariCPProvider();
    }

    private HikariCPProvider() {
    }

    public static final HikariCPProvider getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void setAutoCommit(boolean autoCommit) {
        this.autoCommit = autoCommit;
    }

    public void setConnectionTimeout(long connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public void setIdleTimeout(long idleTimeout) {
        this.idleTimeout = idleTimeout;
    }

    public void setMaxLifetime(long maxLifetime) {
        this.maxLifetime = maxLifetime;
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public void setPoolName(String poolName) {
        this.poolName = poolName;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public void setConnectionInitSql(String connectionInitSql) {
        this.connectionInitSql = connectionInitSql;
    }

    public void setTransactionIsolation(String transactionIsolation) {
        this.transactionIsolation = transactionIsolation;
    }

    public void setValidationTimeout(long validationTimeout) {
        this.validationTimeout = validationTimeout;
    }

    public void setLeakDetectionThreshold(long leakDetectionThreshold) {
        this.leakDetectionThreshold = leakDetectionThreshold;
    }

    @Override
    public boolean register() {
        if (IsRegistered) {
            return HAS_REGISTERED;
        }

        HikariDataSource ds = new HikariDataSource();

        //basic config
        ds.setJdbcUrl(jdbcURL);
        ds.setDriverClassName(jdbcDriver);
        ds.setUsername(jdbcUsername);
        ds.setPassword(jdbcPassword);

        //custom config
        ds.setAutoCommit(autoCommit);
        ds.setConnectionTimeout(connectionTimeout);
        ds.setIdleTimeout(idleTimeout);
        ds.setMaxLifetime(maxLifetime);
        ds.setMaximumPoolSize(maximumPoolSize);
        ds.setValidationTimeout(validationTimeout);
        ds.setLeakDetectionThreshold(leakDetectionThreshold);

        if (!StrUtil.isBlank(poolName)) {
            ds.setPoolName(poolName);
        }

        if (!StrUtil.isBlank(catalog)) {
            ds.setCatalog(catalog);
        }

        if (!StrUtil.isBlank(connectionInitSql)) {
            ds.setConnectionInitSql(connectionInitSql);
        }

        if (!StrUtil.isBlank(transactionIsolation)) {
            ds.setTransactionIsolation(transactionIsolation);
        }

        if (jdbcURL.contains(":mysql:")) {
            ds.addDataSourceProperty("cachePrepStmts", "true");
            ds.addDataSourceProperty("prepStmtCacheSize", "250");
            ds.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
            ds.addDataSourceProperty("useServerPrepStmts", "true");
        }

        setDataSource(ds);
        setIsRegistered(HAS_REGISTERED);
        return HAS_REGISTERED;
    }

    @Override
    public DataSource getDataSource() {
        return ds;
    }

    private void setDataSource(HikariDataSource datasource) {
        ds = datasource;
    }
}
