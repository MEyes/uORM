package com.f3.aop;

import java.lang.reflect.Method;

public class AspectJExpressionPointcut implements Pointcut, ClassFilter, MethodMatcher{

	private PointcutParser pointcutParser;

	private String expression;


	private PointcutExpression buildPointcutExpression() {
		return pointcutParser.parsePointcutExpression(expression);
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	@Override
	public ClassFilter getClassFilter() {
		return this;
	}

	@Override
	public MethodMatcher getMethodMatcher() {
		return this;
	}

	@Override
	public boolean matches(Class targetClass) {
		return true;
	}

	@Override
	public boolean matches(Method method, Class targetClass) {
		pointcutParser =new PointcutParser();
		return false;
	}

}
