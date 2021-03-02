package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


// 容器自动管理方法演示
@Service
// @Scope("Singleton")默认的单例bean
// @Scope("prototype")加上这个注解后，每次getBean会创建新的实例
public class AlphaService {

    // 构造器
    public AlphaService() {
        System.out.println("实例化 AlphaService");
    }

    // 为bean增加一个初始化方法
    @PostConstruct // 让容器自动管理这个方法，方法会在构造器之后调用
    public void init() {
        System.out.println("初始化 AlphaService");
    }

    @PreDestroy // 方法在销毁之前调用，已销毁显然无法调用
    public void destroy() {
        System.out.println("销毁 AlphaService");
    }

}
