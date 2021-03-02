package com.nowcoder.community;

import com.nowcoder.community.dao.AlphaDao;
import com.nowcoder.community.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
// 启用CommunityApplication类为配置类
@ContextConfiguration(classes = CommunityApplication.class)
// 为了让CommunityApplicationTests类得到Spring IOC容器，我们实现一个接口
class CommunityApplicationTests implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	// ApplicationContext: spring容器接口，继承自BeanFactory -- 是Spring容器的顶层接口
	// Spring容器会在扫描组件时，调用set方法，把自身传递进来
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Test
	public void testApplicationContex() {
		// 测试打印Spring容器
		System.out.println(applicationContext);
		// 获取容器的bean
		// 获取bean时提供AlphaDao接口，而不是具体哪个bean，体现了面向接口编程的思想。在获取bean时，即是接口下有若干实现类，
		// 我只需给想要获取的bean设置一个@Primary ，而不需要在这里做任何更改。这也降低了bean之间的耦合度。
		AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);
		System.out.println(alphaDao.select());

		// 如果我必须使用未被设置为@Primary的bean，那么可以通过bean的名字强制获取bean
		// getBean默认返回的是Object类型，可以强制转换成AlphaDao类型，或者在getBean()加上参数指定类型
		alphaDao = applicationContext.getBean("alphaDaoHibernate", AlphaDao.class);
		System.out.println(alphaDao.select());
	}

	// 测试容器自动管理方法
	@Test
	public void testBeanManagement() {
		AlphaService alphaService = applicationContext.getBean(AlphaService.class);
		// 打印顺序：实例化 初始化 销毁，在整个程序运行过程中只存在同一个bean
		System.out.println(alphaService);
		alphaService = applicationContext.getBean(AlphaService.class);
		// 即是再次getBean()，输出结果也是实例化 初始化 销毁各一次，更验证了只存在同一个bean的结论
		System.out.println(alphaService);

	}

	@Test
	public void testBeanConfig() {
		SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
		System.out.println(simpleDateFormat.format(new Date()));
	}

	// 以上都是主动获取bean，下面将通过 依赖注入 的方式获取bean

	@Autowired	// @Autowired还可以标注在类构造器前通过构造器注入或者标注在set方法之前通过set方法注入
	//@Qualifier("alphaDaoHibernate")	// 获取指定bean，而非被@Primary修饰的bean
	private AlphaDao alphaDao;	// 注入到alphaDao属性上
								// 这里的bean仅依赖接口AlphaDao，而不去关心具体的实现，bean的增删改查在这里都不用变化
								// 这样就降低了bean之间的耦合度，这就是 依赖注入 的神妙

	@Autowired
	private AlphaService alphaService;

	@Autowired
	private SimpleDateFormat simpleDateFormat;

	@Test
	public void testDI() {
		// 测试输出通过 依赖注入 方式获取的bean
		System.out.println(alphaDao);
		System.out.println(alphaService);
		System.out.println(simpleDateFormat);
	}
}
