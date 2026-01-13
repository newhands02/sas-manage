package com.sas.config;

import com.sas.typeHandler.FinancialLiabilitiesHandler;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan("com.sas.mapper")
public class MyBatisConfig {
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:mapper/*.xml"));

        // 获取Configuration对象
        SqlSessionFactory sqlSessionFactory = sessionFactory.getObject();
        org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();

        // 设置日志实现
        configuration.setLogImpl(org.apache.ibatis.logging.slf4j.Slf4jImpl.class);

        // 其他配置...
        configuration.setMapUnderscoreToCamelCase(true);


        return sqlSessionFactory;
    }
}
