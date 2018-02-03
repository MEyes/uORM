package com.f3.example;

import com.f3.aop.MethodInterceptor;
import com.f3.aop.MethodInvocation;

public class TimerInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		long time = System.nanoTime();
		System.out.println("Invocation of Method " + invocation.getMethod().getName() + " start!");
		Object proceed = invocation.proceed();
		System.out.println("Invocation of Method " + invocation.getMethod().getName() + " end! takes " + (System.nanoTime() - time)
				+ " nanoseconds.");
		return proceed;
	}
}
