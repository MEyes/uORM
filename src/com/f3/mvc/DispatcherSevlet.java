package com.f3.mvc;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.f3.mvc.annotation.Controller;
import com.f3.mvc.annotation.Qualified;
import com.f3.mvc.annotation.RequestMapping;
import com.f3.mvc.annotation.Service;

public class DispatcherSevlet extends HttpServlet{
	
	private final Map<String, Object> map=new HashMap<String,Object>();
	@Override
	public void init() throws ServletException{
		try{
			scanComponent("com.f3.example");
		}catch(Exception e){
			System.out.println(e);
		}
		
	}
	private void scanComponent(String packageName) throws Exception{
		//得到包路徑
		String path = packageName.replaceAll("\\.","\\\\");
		//getResource方法會去找classpath下的文件
		URL url = this.getClass().getClassLoader().getResource(path);
		//getFile得到絕對路勁
		//System.out.println(url.getFile());
		File file=new File(url.getFile());
		File[] files = file.listFiles();
		for(File file2:files){
			//System.out.println(file2.getName());
			
			checkIsDecoratedAnnotation(packageName+"."+file2.getName().replace(".class", ""));
		}
		

		inject();
	}
	
	private void inject() throws Exception{
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			Object obj = entry.getValue();
		    if (obj.getClass().isAnnotationPresent(Controller.class)) {
				
		    	Field[] declaredFields = obj.getClass().getDeclaredFields();
				for(Field field:declaredFields){
					if (field.isAnnotationPresent(Qualified.class)) {
						field.setAccessible(true);
						Qualified anno = field.getAnnotation(Qualified.class);
						Object value = map.get(anno.value());
						field.set(obj, value);
					}
				}
			}
			
		}
		
	}
	private void checkIsDecoratedAnnotation (String className)  throws Exception{
	
		try {
			Class<?> clazz = Class.forName(className);
		
			if (clazz.isAnnotationPresent(Controller.class) ) {
				Controller controller = clazz.getAnnotation(Controller.class);
				System.out.println(controller.value());
				map.put(controller.value(),clazz.newInstance());
			}
			
			if(clazz.isAnnotationPresent(Service.class)){
				Service service = clazz.getAnnotation(Service.class);
				System.out.println(service.value());
				map.put(service.value(),clazz.newInstance());
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws ServletException ,java.io.IOException {
		String[] parameters=req.getRequestURI().split("/");
		//參數2是Controller
		//參數3是Action
		System.out.println(Arrays.toString(parameters));
		Object object=map.get(parameters[2]);
		Method[] declaredMethods = object.getClass().getDeclaredMethods();
		for(Method method:declaredMethods){
			method.setAccessible(true);
			
			if (method.isAnnotationPresent(RequestMapping.class)) {
				RequestMapping annotation = method.getAnnotation(RequestMapping.class);
				if (annotation.value().equals(parameters[3])) {
					try {
						method.invoke(object, new Object[]{req,resp});
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					continue;
				}
			}
		}
	};
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
