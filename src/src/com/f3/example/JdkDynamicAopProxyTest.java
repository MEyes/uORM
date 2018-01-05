package com.f3.example;

import org.junit.Test;

import com.f3.aop.AdvisedSupport;
import com.f3.aop.JdkDynamicAopProxy;
import com.f3.aop.TargetSource;
import com.f3.ioc.context.ApplicationContext;
import com.f3.ioc.context.ClassPathXmlApplicationContext;


public class JdkDynamicAopProxyTest {

	@Test
	public void testInterceptor() throws Exception {
		// --------- helloWorldService without AOP
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("main.xml");
		HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("hello");
		helloWorldService.sayHello();

		// --------- helloWorldService with AOP
		// 1. 设置被代理对象(Joinpoint)
		AdvisedSupport advisedSupport = new AdvisedSupport();
		TargetSource targetSource = new TargetSource(helloWorldService, HelloWorldService.class);
		advisedSupport.setTargetSource(targetSource);

		// 2. 设置拦截器(Advice)
		TimerInterceptor timerInterceptor = new TimerInterceptor();
		advisedSupport.setMethodInterceptor(timerInterceptor);

		// 3. 创建代理(Proxy)
		JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(advisedSupport);
		HelloWorldService helloWorldServiceProxy = (HelloWorldService) jdkDynamicAopProxy.getProxy();

        // 4. 基于AOP的调用
        helloWorldServiceProxy.sayHello();

	}
}
