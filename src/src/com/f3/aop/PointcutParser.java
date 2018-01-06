package com.f3.aop;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.internal.Classes;

public class PointcutParser {

	public PointcutExpression parsePointcutExpression(String expression) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args)  throws Exception{
		String expression="com.f3.example.HelloWorldServiceImpl *(..) -> *";//com.f3.* *(*) -> *
		
		//去掉左右多余的空格
		expression=expression.trim();
		//任意多个空格
		String[] values = expression.split(" ");
		//验证
		String packageName=values[0];
		//File
		
		String method=values[1];
		int l=method.indexOf('(');
		int r=method.lastIndexOf(')');
		String methodName=method.substring(0, l);
		String methodParameters=method.substring(l+1,r);
		
		String returnType=values[3];
		
		parsePackage(packageName);
		parseMethod(methodName,methodParameters,returnType);
		
	}
	
	private static void parseMethod(String name,String parameters,String returnType) {
		
		
		for (Class clazz : classes) {
			
			Method[] declaredMethods = clazz.getDeclaredMethods();
			for (Method method : declaredMethods) {
				
				if ("*".equals(name)) {
					
				}else	{
			
					if (! method.getName().equals(name)) {
						continue;
					}
				}
				
				if ("..".equals(parameters)) {
					
				}else	{
			
					System.out.println(Arrays.toString(method.getParameters()));
					continue;
//					if (! method.getParameters().) {
//						continue;
//					}
				}
				
				
				if ("*".equals(returnType)) {
					
				}else	{
			
					if (! method.getReturnType().getName().equals(name)) {
						continue;
					}
				}
				
				methods.add(method);
				
			}
		}
		
	}
	
	private static List<Class> classes=new ArrayList<Class>();
	private static List<Method> methods=new ArrayList<Method>();

	private static void parsePackage(String packageName) throws Exception{
		//com.f3.example.HelloWorldServiceImpl *(..) -> *";//com.f3.* *(*) -> *
		if (packageName.endsWith(".*")) {
			
			
			//子包
			
			packageName=packageName.replace(".*", "");
		}
		
		
		String path=packageName.replaceAll("\\.", "\\\\");
		File file=new File(path);
		if (file.isDirectory()) {
			//包扫描
			//递归
		}else {
			Class clazz = Class.forName(packageName);
			classes.add(clazz);
		}
	}

}
