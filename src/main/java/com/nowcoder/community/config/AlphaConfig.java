package com.nowcoder.community.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

// 使用自定义配置类去get他人封装好的bean
@Configuration   // 说明这是一个配置类
public class AlphaConfig {

    // 方法返回的对象将被装配到容器里
    @Bean
    public SimpleDateFormat simpleDateFormat() {    // 方法名就是bean的名字
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}
