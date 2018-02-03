package com.f3.aop;

import java.lang.reflect.Method;

public interface MethodMatcher {
	boolean matches(Method method, Class targetClass);
}
