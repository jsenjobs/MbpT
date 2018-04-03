package com.jsen.test.config.dbs;

import com.google.common.collect.Maps;
import com.jsen.test.config.dbs.help.DbTypes;
import com.jsen.test.config.dbs.help.DynamicDatasource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Map;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/2
 */
@Configuration
@MapperScan("com.jsen.test.mapper")
public class MyBatisConf {
    @Bean(name = "db1DS")
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    public DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "db2DS")
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSource dataSource2() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DataSource dataSource(@Qualifier("db1DS") DataSource dataSource1, @Qualifier("db2DS") DataSource dataSource2) {
        Map<Object, Object> targetDatasources = Maps.newHashMap();
        targetDatasources.put(DbTypes.DB1, dataSource1);
        targetDatasources.put(DbTypes.DB2, dataSource2);

        DynamicDatasource dynamicDatasource = new DynamicDatasource();
        dynamicDatasource.setTargetDataSources(targetDatasources);
        dynamicDatasource.setDefaultTargetDataSource(dataSource1);

        return dynamicDatasource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        // 使用titan数据源, 连接titan库
        factoryBean.setDataSource(dataSource);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*Mapper.xml"));

        return factoryBean.getObject();
    }
}