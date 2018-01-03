package com.f3.ioc;

import java.io.InputStream;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BEncoderStream;


public class XmlBeanDefinitionReader  extends AbstractBeanDefinitionReader{

	public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
		super(resourceLoader);
	}

	@Override
	public void loadBeanDefinition(String localtion) throws Exception {
	 	InputStream inputStream = getResourceLoader().getResource(localtion).getInputStream();
	 	doLoadBeanDefinitions(inputStream);
	}

	protected void doLoadBeanDefinitions(InputStream inputStream) throws Exception {
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
		Document doc = documentBuilder.parse(inputStream);
		registerBeanDefinitions(doc);
		inputStream.close();
	
	}

	private void registerBeanDefinitions(Document doc) {
		Element root=doc.getDocumentElement();
		parseBeanDefinitions(root);
		
	}

	private void parseBeanDefinitions(Element root) {
		NodeList nl=root.getChildNodes();
		for(int i=0;i<nl.getLength();i++){
			Node node=nl.item(i);
			if (node instanceof Element) {
				Element ele=(Element)node;
				processBeanDefinition(ele);
			}
		}
		
	}

	private void processBeanDefinition(Element ele) {
		String name=ele.getAttribute("name");
		String className=ele.getAttribute("class");
		BeanDefinition beanDefinition=new BeanDefinition();
		processProperty(ele,beanDefinition);
		beanDefinition.setBeanClassName(className);
		this.getBeanDefinitionMap().put(name, beanDefinition);
	}

	private void processProperty(Element ele, BeanDefinition beanDefinition) {
		NodeList propertyNode=ele.getElementsByTagName("property");
		for (int i = 0; i < propertyNode.getLength(); i++) {
			Node node=propertyNode.item(i);
			if (node instanceof Element) {
				Element propertyElement=(Element)node;
				String name=propertyElement.getAttribute("name");
				String value=propertyElement.getAttribute("value");
				if (value!=null && value.length()>0) {
					beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
				}else{
					String ref=propertyElement.getAttribute("ref");
					if (ref==null || ref.length()==0) {
						throw new IllegalArgumentException("Illegal Argument");
					}
					BeanReference beanReference=new BeanReference(ref);
					beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, beanReference));
				}
				
			}
		}
		
	}

}
