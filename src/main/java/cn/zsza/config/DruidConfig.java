package cn.zsza.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

/**
 * @Author: ZhangSong
 * @Date: 2018/6/16 16:01
 * @Company: NoNo
 */
@Configuration
public class DruidConfig {

    @Value("${me.database.url}")
    private String dbUrl;

    @Value("${me.database.user}")
    private String username;

    @Value("${me.database.password}")
    private String password;

    @Value("${me.database.driverClass}")
    private String driverClassName;

    @Value("${me.database.dbInitialSize}")
    private int initialSize;

    @Value("${me.database.dbMinIdle}")
    private int minIdle;

    @Value("${me.database.dbMaxActive}")
    private int maxActive;

    @Value("${me.database.maxWait}")
    private int maxWait;

    @Value("${me.database.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${me.database.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${me.database.validationQuery}")
    private String validationQuery;

    @Value("${me.database.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${me.database.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${me.database.testOnReturn}")
    private boolean testOnReturn;

    @Value("${me.database.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    
    @Bean
    public DataSource druidDataSource(){
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(this.dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);

        return datasource;
    }

}
