package com.f3.aop;


import java.util.List;

import com.f3.ioc.beans.BeanFactory;
import com.f3.ioc.beans.BeanFactoryAware;
import com.f3.ioc.beans.BeanPostProcessor;
import com.f3.ioc.context.AbstractBeanFactory;

public class AspectJAwareAdvisorAutoProxyCreator implements BeanPostProcessor, BeanFactoryAware {

	private AbstractBeanFactory beanFactory;

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
		System.out.println("Before--------"+beanName);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
		System.out.println("After--------"+beanName);
		if (bean instanceof AspectJExpressionPointcutAdvisor) {
			return bean;
		}
        if (bean instanceof MethodInterceptor) {
            return bean;
        }
		List<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansForType(AspectJExpressionPointcutAdvisor.class);
	
		for (AspectJExpressionPointcutAdvisor advisor : advisors) {
			if (advisor.getPointcut().getClassFilter().matches(bean.getClass())) {
				AdvisedSupport advisedSupport = new AdvisedSupport();
				advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
				advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());

				TargetSource targetSource = new TargetSource(bean, bean.getClass().getInterfaces());
				advisedSupport.setTargetSource(targetSource);

				return new JdkDynamicAopProxy(advisedSupport).getProxy();
			}
		}
		return bean;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws Exception {
		this.beanFactory = (AbstractBeanFactory) beanFactory;
	}
}
