package com.f3.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.aopalliance.intercept.MethodInterceptor;

public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {

	private AdvisedSupport advised;

	public JdkDynamicAopProxy(AdvisedSupport advised) {
		this.advised = advised;
	}

    @Override
	public Object getProxy() {
		try {
			return Proxy.newProxyInstance(getClass().getClassLoader(), new Class[] { advised.getTargetSource()
					.getTargetClass() }, this);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			throw new RuntimeException(e);
			

		}
	}

	@Override
	public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
		MethodInterceptor methodInterceptor = advised.getMethodInterceptor();
		return methodInterceptor.invoke(new ReflectiveMethodInvocation(advised.getTargetSource().getTarget(), method,
				args));
	}

}
