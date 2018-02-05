package com.f3.orm.mapping;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.f3.orm.Context;
import com.f3.orm.SqlMapList;
import com.f3.orm.scripting.AbstractSqlNode;
import com.f3.orm.scripting.IncludeSqlNode;
import com.f3.orm.scripting.IsNotNullSqlNode;
import com.f3.orm.scripting.TextSqlNode;
import com.f3.orm.scripting.SqlNode;
import com.f3.orm.scripting.WhereSqlNode;

public class MappedStatement {
	private String id;
	private List<SqlNode> sqlNodes;
	private MappedStatement(){
		
	}
	public List<SqlNode> getTags() {
		return sqlNodes;
	}

	public void setTags(List<SqlNode> tags) {
		this.sqlNodes = tags;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static MappedStatement build(Element element) {

		MappedStatement statement = new MappedStatement();
		statement.setTags(new ArrayList<SqlNode>());
		String id = element.getAttribute("id");
		statement.setId(id);
		
		NodeList childNodes = element.getChildNodes();
		List<IncludeSqlNode> includes = new ArrayList<IncludeSqlNode>();
		for (int i = 0; i < childNodes.getLength(); i++) {

			Node node = childNodes.item(i);
			SqlNode tag = loadSqlNode( node, includes);
			if (tag != null) {
				statement.sqlNodes.add(tag);
			}

		}

		// init includes
		
		for (int i = 0; i < includes.size(); i++) {
			IncludeSqlNode includeSqlNode=includes.get(i);
			String refId=includeSqlNode.getRefId();
			if (statement.getId().equals(refId)) {
				throw new RuntimeException();
			}
			
			Iterator<MappedStatement> iterator = SqlMapList.statements.iterator();
			while (iterator.hasNext()) {
				MappedStatement sm = (MappedStatement) iterator.next();
				if (sm.getId().equals(refId)) {
					includeSqlNode.setStatement(sm);
				}
				
			}
		}
		SqlMapList.addStatement(statement);

		return statement;

	}

	public static SqlNode loadSqlNode(Node node, List<IncludeSqlNode> includes) {
		
		if (!( node instanceof Element)) {
			String content = node.getTextContent().trim();
			if (content!=null && content.length()>0) {
				TextSqlNode sqlTextTag=new TextSqlNode();
				sqlTextTag.setBodyText(content);
				return sqlTextTag;
			}
			return null;
			
		}
		Element element=(Element)node;
		
		String prepend = element.getAttribute("prepend");
		String field = element.getAttribute("field");

		SqlNode tag = null;
		switch (element.getNodeName()) {
		case "#text":
			String value = element.getTextContent().trim();
			TextSqlNode textSqlNode = new TextSqlNode();
			textSqlNode.setBodyText(value);
			return textSqlNode;
		case "where":
			WhereSqlNode whereSqlNode = new WhereSqlNode();
			whereSqlNode.setSubTags(new ArrayList<SqlNode>());
			tag = whereSqlNode;
			break;
		case "include":
			String refId = element.getAttribute("ref");
			IncludeSqlNode includeSqlNode = new IncludeSqlNode();
			includeSqlNode.setRefId(refId);
			includeSqlNode.setPrepend(prepend);
			includes.add(includeSqlNode);
			tag = includeSqlNode;
			break;
		case "IsNotNull":
			IsNotNullSqlNode isNotEmptySqlNode=new IsNotNullSqlNode();
			isNotEmptySqlNode.setField(field);
			isNotEmptySqlNode.setPrepend(prepend);
			isNotEmptySqlNode.setSubTags(new ArrayList<SqlNode>());
			tag=isNotEmptySqlNode;
			break;
		default:
			return null;
		}

		NodeList childNodes = element.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node cNode = childNodes.item(i);
			SqlNode sqlNode = loadSqlNode(cNode, includes);
			if (tag != null && sqlNode != null) {
				((AbstractSqlNode) tag).getSubTags().add(sqlNode);
			}

		}

		return tag;

	}
	
	public String buildSql(Context context){
		StringBuilder sBuilder=new StringBuilder();
		for (SqlNode tag : sqlNodes) {
			String sql = tag.buildSql(context);
			sBuilder.append(sql);
		}
		
		return sBuilder.toString();
	}
}
