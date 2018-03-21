package com.jsen.test.config;

import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.plugins.SqlExplainInterceptor;
import com.baomidou.mybatisplus.plugins.parser.ISqlParser;
import com.baomidou.mybatisplus.plugins.parser.tenant.TenantHandler;
import com.baomidou.mybatisplus.plugins.parser.tenant.TenantSqlParser;
import com.jsen.test.config.injector.MySqlInjector;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.List;

@EnableTransactionManagement
@Configuration
@MapperScan("com.jsen.test.mapper")
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setLocalPage(true);
        List<ISqlParser> iSqlParserList = new ArrayList<>();
        TenantSqlParser tenantSqlParser = new TenantSqlParser();
        tenantSqlParser.setTenantHandler(new TenantHandler() {
            @Override
            public Expression getTenantId() {
                return new LongValue(1L);
            }

            @Override
            public String getTenantIdColumn() {
                return "TenantID";
            }

            @Override
            public boolean doTableFilter(String tableName) {
                if (tableName.equals("testtable")) {
                    return false;
                }
                return true;
            }
        });
        iSqlParserList.add(tenantSqlParser);
        paginationInterceptor.setSqlParserList(iSqlParserList);
        return paginationInterceptor;
    }


    @Bean
    // @Profile({"dev", "test"})
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }

    @Bean
    public SqlExplainInterceptor sqlExplainInterceptor() {
        return new SqlExplainInterceptor();
    }

    @Bean
    public MySqlInjector sqlInjector() {
        return new MySqlInjector();
    }
}
