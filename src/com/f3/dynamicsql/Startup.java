package com.f3.dynamicsql;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Startup {
	public static void main(String[] args) throws Exception, Exception, Exception {
		
		InputStream is = Startup.class.getResourceAsStream("sql.xml");

		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
		
		Element root = document.getDocumentElement();
		
		NodeList statements = root.getElementsByTagName("statement");
		
		for (int i = 0; i < statements.getLength(); i++) {
			Element statementEl = (Element)statements.item(i);
			Statement.load(statementEl);
		}
		SqlBuilder builder=new SqlBuilder();
		Statement executeStatement = SqlMapList.maps.get("get");
		
		Context context=new Context();
		context.setSqlId("get");
		Map<String, Object> maps=new HashMap<>();
		maps.put("id1", 1);
		maps.put("type",1);
		context.setPrameters(maps);
		String sql=builder.buildSql(context, executeStatement);
		
		System.out.println(sql);
		
	}
}
