package com.f3.aop;

import java.lang.reflect.AccessibleObject;

public interface Joinpoint {
	public Object proceed() throws Throwable;

	public abstract Object getThis();

	public abstract AccessibleObject getStaticPart();
}
