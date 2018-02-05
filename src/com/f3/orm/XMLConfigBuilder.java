package com.f3.orm;

import static org.hamcrest.CoreMatchers.is;

import java.io.InputStream;

import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.f3.orm.session.Configuration;

public class XMLConfigBuilder {
	private InputStream inputStream;
	private Configuration configuration;
	//private final TypeHandlerRegistry typeHandlerRegistry;
	public XMLConfigBuilder(InputStream inputStream) {
		this.inputStream=inputStream;
		this.configuration=new Configuration();
		//this.typeHandlerRegistry=this.configuration.
	}
	
	public Configuration parse(){
		try {
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(this.inputStream);
			Element root = document.getDocumentElement();
			parseDataSource(root);
			parseMappers(root);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.configuration;
		
	}

	private void parseDataSource(Element root){
		Node dsNode = root.getElementsByTagName("datasource").item(0);
		Element dsEl=(Element)dsNode;
		applyDataSource(dsEl);
		applyJdbcProperties(dsEl);
	}
private void applyDataSource(Element dsEl){
		
		String clazzName = dsEl.getAttribute("type");
		try {
			this.configuration.setDataSource((DataSource)Class.forName(clazzName).newInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	
	private void applyJdbcProperties(Element dsEl) {

		NodeList childNodes = dsEl.getChildNodes();
		for(int i=0;i<childNodes.getLength();i++){
			Node childNode = childNodes.item(i);
			if (childNode instanceof Element) {
				Element propertyEl=(Element)childNode;
				String propertyName=propertyEl.getAttribute("name");
				String propertyValue=propertyEl.getAttribute("value");
				if ("driver".equals(propertyName)) {
					this.configuration.setDriver(propertyValue);
				}else if("url".equals(propertyName)){
					this.configuration.setUrl(propertyValue);
				}else if("user".equals(propertyName)){
					this.configuration.setUrl(propertyValue);
				}else if("password".equals(propertyName)){
					this.configuration.setPassword(propertyValue);
				}
			}
		}
		
	}
	
	
	private void parseMappers(Element root) {
		Node mappersNode = root.getElementsByTagName("mappers").item(0);
		Element mappersEl=(Element)mappersNode;
		applyMappers(mappersEl);
	}
	

	
	
	private void applyMappers(Element mappersEl) {
		NodeList childNodes = mappersEl.getChildNodes();
		for(int i=0;i<childNodes.getLength();i++){
			Node childNode = childNodes.item(i);
			if (childNode instanceof Element) {
				Element propertyEl=(Element)childNode;
				String propertyName=propertyEl.getAttribute("name");
				String propertyValue=propertyEl.getAttribute("value");
				
			}
		}
		
	}

	
}
