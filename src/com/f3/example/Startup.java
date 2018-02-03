package com.f3.example;

import com.f3.ioc.context.ApplicationContext;
import com.f3.ioc.context.ClassPathXmlApplicationContext;

public class Startup {
	public static void main(String[] args)  throws Exception{
		//System.out.println();
//		InputStream inputStream = Startup.class.getClassLoader().getResourceAsStream("main.xml");
//		BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
//		String content;
//		while((content=bufferedReader.readLine())!=null){
//			System.out.println(content);
//		}
		
//		AbstractBeanFactory beanFactory =new AutowireCapableBeanFactory();
//		XmlBeanDefinitionReader xmlBeanDefinitionReader=new XmlBeanDefinitionReader(new ResourceLoader());
//		xmlBeanDefinitionReader.loadBeanDefinition("main.xml");
//		HashMap<String,BeanDefinition> beanDefinitionMap = xmlBeanDefinitionReader.getBeanDefinitionMap();
//		for (Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
//			beanFactory.registerBeanDefinition(entry.getKey(), entry.getValue());
//			System.out.println(entry.getKey());
//		}
/*		BeanDefinition beanDefinition=new BeanDefinition();
		beanDefinition.setBeanClassName("com.f3.ioc.HelloWorldService");
		
		PropertyValues propertyValues=new PropertyValues();
		propertyValues.add(new PropertyValue("content", "浣犲ソ"));
		
		beanDefinition.setPropertyValues(propertyValues);
		
		beanFactory.registerBeanDefinition("hello", beanDefinition);*/
		
		//beanFactory.preInstantiateSingletons();
		
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("main.xml");
		
		HelloWorldService bean = (HelloWorldService)applicationContext.getBean("hello");
		bean.sayHello();
//		byte[] bytes=ProxyGenerator.generateProxyClass("$proxy0",new Class[]{HelloWorldService.class});
//		
//		FileOutputStream outputStream=new FileOutputStream("$proxy0.class");
//		outputStream.write(bytes);
//		outputStream.close();
		
	}
}

