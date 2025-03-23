package com.htkj.config;


import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.htkj.aspect.MyMetaObjectHandler;
import org.apache.ibatis.session.SqlSessionFactory;
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

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * The type Data source config.
 *
 * @author leichong
 */
@Configuration
@MapperScan(basePackages = {"com.htkj.mapper"}, sqlSessionFactoryRef = "sqlSessionFactory")
public class DataSourceConfig {
    @Resource
    private MybatisPlusConfig mybatisPlusConfig;

    /**
     * Sync data source data source.
     *
     * @return the data source
     */
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    @Primary
    public DataSource syncDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * Sync sql session factory sql session factory.
     *
     * @param dataSource the data source
     * @return the sql session factory
     * @throws Exception the exception
     */
    @Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory syncSqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();

        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));
        bean.setDataSource(dataSource);
        bean.setPlugins(mybatisPlusConfig.paginationInterceptor());
        GlobalConfig globalConfig = GlobalConfigUtils.defaults();
        globalConfig.setMetaObjectHandler(new MyMetaObjectHandler());
        bean.setGlobalConfig(globalConfig);
        return bean.getObject();
    }

    /**
     * Test transaction manager data source transaction manager.
     *
     * @param dataSource the data source
     * @return the data source transaction manager
     */
    @Bean(name = "transactionManager")
    @Primary
    public DataSourceTransactionManager testTransactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * Test sql session template sql session template.
     *
     * @param sqlSessionFactory the sql session factory
     * @return the sql session template
     */
    @Bean(name = "syncSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
