package com.f3.ioc.beans;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.f3.ioc.io.ResourceLoader;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

	public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
		super(resourceLoader);
	}

	@Override
	public void loadBeanDefinition(String localtion) throws Exception {
		InputStream inputStream = getResourceLoader().getResource(localtion).getInputStream();
		doLoadBeanDefinitions(inputStream);
	}

	protected void doLoadBeanDefinitions(InputStream inputStream) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
		Document doc = documentBuilder.parse(inputStream);
		registerBeanDefinitions(doc);
		inputStream.close();

	}

	private void registerBeanDefinitions(Document doc) {
		Element root = doc.getDocumentElement();
		parseBeanDefinitions(root);

	}

	private void parseBeanDefinitions(Element root) {
		NodeList nl = root.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Node node = nl.item(i);
			if (node instanceof Element) {
				Element ele = (Element) node;
				processBeanDefinition(ele);
			}
		}

	}

	private void processBeanDefinition(Element ele) {
		String name = ele.getAttribute("id");
		String className = ele.getAttribute("class");
		BeanDefinition beanDefinition = new BeanDefinition();
		processProperty(ele, beanDefinition);
		beanDefinition.setBeanClassName(className);
		this.getBeanDefinitionMap().put(name, beanDefinition);
	}

	private void processProperty(Element ele, BeanDefinition beanDefinition) {
		NodeList propertyNode = ele.getElementsByTagName("property");
		for (int i = 0; i < propertyNode.getLength(); i++) {
			Node node = propertyNode.item(i);
			if (node instanceof Element) {
				Element propertyEle = (Element) node;
				String name = propertyEle.getAttribute("name");
				String value = propertyEle.getAttribute("value");
				if (value != null && value.length() > 0) {
					beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
				} else {
					String ref = propertyEle.getAttribute("ref");
					if (ref == null || ref.length() == 0) {
						throw new IllegalArgumentException("Illegal Argument");
					}
					BeanReference beanReference = new BeanReference(ref);
					beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, beanReference));
					// 处理Props
					NodeList props = propertyEle.getElementsByTagName("props");
					if (props != null) {
						processProps(props, beanDefinition, name);
					}
				}

			}
		}

	}

	private void processProps(NodeList props, BeanDefinition beanDefinition, String name) {
		for (int i = 0; i < props.getLength(); i++) {
			//直接item(0)
			Node node = props.item(i);
			if (node instanceof Element) {
				Element propsEle = (Element) node;
				//prop代表属性
				NodeList prop = propsEle.getElementsByTagName("prop");
				Map<String, Object> urlMap = new HashMap<String, Object>();
				for (int j = 0; j < prop.getLength(); j++) {
					Node pNode = prop.item(j);
					if (pNode instanceof Element) {
						Element propEle = (Element) pNode;
						String key = propEle.getAttribute("key");
						String value = propEle.getFirstChild().getTextContent();
						if (value != null && value.length() > 0) {
							urlMap.put(key, value);
						} else {
							throw new IllegalArgumentException("Configuration problem: <prop> element for prop '" + key
									+ "' must specify a TextContent value");
						}
					}
				}
				beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, urlMap));
			}
		}
	}

}
