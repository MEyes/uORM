package com.f3.orm;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.f3.orm.session.SqlSession;
import com.f3.orm.session.SqlSessionFactory;
import com.f3.orm.session.SqlSessionFactoryBuilder;

public class Startup {
	public static void main(String[] args) throws Exception, Exception, Exception {
		
//		MapperRegistry registry=new MapperRegistry(new Configuration());
//		//registry.addMapper(User.class);
//		System.out.println("--------------------------------");
//		System.out.println(registry.getMapper(User.class, null));
//		System.out.println("--------------------------------");
		
	/*	InputStream is = Startup.class.getResourceAsStream("sql.xml");

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
		
		System.out.println(sql);*/
		
		InputStream is = Startup.class.getResourceAsStream("sql.xml");
		//Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
		SqlSessionFactoryBuilder factoryBuilder=new SqlSessionFactoryBuilder();
		SqlSessionFactory factory=factoryBuilder.build(is);
		SqlSession sqlSession = factory.openSession();
		
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		List<User> list = mapper.get(new UserQueryDTO("HZ",1));
		System.out.println(list);
	}
}
