package com.f3.aop;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PointcutParser {

	public PointcutExpression parsePointcutExpression(String expression) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) throws Exception {

	}

	@Test
	public void Parse(String expression) throws Exception {
		// String expression = "com.f3.example.HelloWorldServiceImpl *(String,*)
		// -> *";// com.f3.*
		// *(*)
		// ->
		// *

		// 去掉左右多余的空格
		expression = expression.trim();
		// 任意多个空格
		String[] values = expression.split(" ");
		// 验证
		String packageName = values[0];
		// File

		String method = values[1];
		int l = method.indexOf('(');
		int r = method.lastIndexOf(')');
		String methodName = method.substring(0, l);
		String methodParameters = method.substring(l + 1, r);

		String returnType = values[3];

		parsePackage(packageName);
		parseMethod(methodName, methodParameters, returnType);
	}

	private static void parseMethod(String name, String parameters, String returnType) {

		for (Class clazz : classes) {

			Method[] declaredMethods = clazz.getDeclaredMethods();
			for (Method method : declaredMethods) {

				if ("*".equals(name)) {

				} else {

					if (!method.getName().equals(name)) {
						continue;
					}
				}

				boolean flag = false;
				if ("..".equals(parameters)) {// (..)匹配了接受一个任意数量参数的方法（零或者更多）

				} else if ("*".equals(parameters)) {// (*)匹配了接受一个任何类型的参数的方法
					// 一个任意 参数
					if (method.getParameterCount() != 1) {
						continue;
					}
				} else {// (*,String)匹配接受两个参数的方法，第一个可以是任意类型， 第二个则必须是String类型

					String[] pList = parameters.split(",");
					ArrayList<String> tempList = new ArrayList<String>();
					for (Class parameterType : method.getParameterTypes()) {
						tempList.add(parameterType.getSimpleName());
					}
					if (tempList.size() != pList.length) {
						continue;
					}

					for (int i = 0; i < pList.length; i++) {
						if ("*".equals(pList[i])) {
							continue;
						}

						if (!(pList[i].equals(tempList.get(i)))) {
							flag = true;
							break;
						}

					}

				}

				if (flag) {
					continue;
				}

				if ("*".equals(returnType)) {

				} else {

					if (!method.getReturnType().getName().equals(name)) {
						continue;
					}
				}

				methods.add(method);

			}
		}

	}

	private static List<Class> classes = new ArrayList<Class>();
	private static List<Method> methods = new ArrayList<Method>();

	private static void parsePackage(String packageName) throws Exception {
		// com.f3.example.HelloWorldServiceImpl *(..) -> *";//com.f3.* *(*) -> *
		if (packageName.endsWith(".*")) {

			// 子包

			packageName = packageName.replace(".*", "");
		}

		String path = packageName.replaceAll("\\.", "\\\\");
		File file = new File(path);
		if (file.isDirectory()) {
			// 包扫描
			// 递归
		} else {
			Class clazz = Class.forName(packageName);
			classes.add(clazz);
		}
	}

}
