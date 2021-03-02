package com.nowcoder.community.dao;

import org.springframework.stereotype.Repository;

// 定义一个bean
// @Repository 使这个bean可以被容器自动扫描装配
// 后面的字符串定义bean的名字，默认名字为类名首字母小写
@Repository("alphaDaoHibernate")
public class AlphaDaoHibernateImpl implements AlphaDao {
    @Override
    public String select() {
        return "Hibernate";
    }
}
