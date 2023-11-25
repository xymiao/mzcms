package com.xymiao.cms.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 这里需要注意， spring boot 3 之前的版本可以引入该注解，但是 3 之后， 应该是调整了策略。 没有细研究。
 * 无法直接在上层使用该配置了。后续看看有什么变动。在更新
 */
@Configuration
@MapperScan("com.xymiao.cms.mapper")
public class MybatisPlusConfiguration {
    //
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

}