package com.f3.orm;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.configuration;
		
	}
	
	public void parseConfiguration(Element root){
		
	}
	
}
