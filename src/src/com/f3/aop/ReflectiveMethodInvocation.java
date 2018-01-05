package com.f3.aop;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

public class ReflectiveMethodInvocation implements MethodInvocation {

	private Object target;

	private Method method;

	private Object[] args;

	public ReflectiveMethodInvocation(Object target, Method method, Object[] args) {
		this.target = target;
		this.method = method;
		this.args = args;
	}

	@Override
	public Method getMethod() {
		return method;
	}

	@Override
	public Object[] getArguments() {
		return args;
	}

	@Override
	public Object proceed() throws Throwable {
		return method.invoke(target, args);
	}
/**
 * Returns the object that holds the current joinpoint's static part.
For instance, the target object for an invocation.
 */
	@Override
	public Object getThis() {
		return target;
	}
/**
 * Returns the static part of this joinpoint.
The static part is an accessible object on which a chain of interceptors are installed.
 */
	@Override
	public AccessibleObject getStaticPart() {
		return method;
	}
}
