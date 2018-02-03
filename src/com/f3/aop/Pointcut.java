package com.f3.aop;

public interface Pointcut {
	ClassFilter getClassFilter();

	MethodMatcher getMethodMatcher();
}
