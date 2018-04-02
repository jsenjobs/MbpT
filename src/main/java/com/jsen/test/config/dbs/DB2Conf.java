package com.jsen.test.config.dbs;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/2
 */
@Configuration
@MapperScan(basePackages = {"com.jsen.test.mapper.db2"}, sqlSessionFactoryRef = "sqlSessionFactory2")
public class DB2Conf {



    @Bean(name = "db2DS")
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSource dataSource2() {
        return DataSourceBuilder.create().build();
    }


    @Bean("sqlSessionFactory2")
    public SqlSessionFactory sqlSessionFactory2(@Qualifier("db2DS") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        // 使用titan数据源, 连接db1库
        factoryBean.setDataSource(dataSource);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/db2/*Mapper.xml"));

        return factoryBean.getObject();

    }

    @Bean(name = "testTransactionManager2")
    public DataSourceTransactionManager testTransactionManager2(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "testSqlSessionTemplate2")
    public SqlSessionTemplate sqlSessionTemplate2(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
