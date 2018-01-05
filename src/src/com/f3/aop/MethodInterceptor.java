package com.f3.aop;

public interface MethodInterceptor extends Interceptor{

	  public  Object invoke(MethodInvocation paramMethodInvocation)
			    throws Throwable;
}
