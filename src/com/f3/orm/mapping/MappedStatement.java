package com.f3.orm.mapping;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.f3.orm.scripting.AbstractSqlNode;
import com.f3.orm.scripting.IncludeSqlNode;
import com.f3.orm.scripting.IsNotNullSqlNode;
import com.f3.orm.scripting.SqlNode;
import com.f3.orm.scripting.TextSqlNode;
import com.f3.orm.scripting.WhereSqlNode;
import com.f3.orm.session.Configuration;

public class MappedStatement {
	private String id;
	private Class resultType;
	private Class parameterType;
	private SqlCommandType sqlCommandType;
	private Configuration configuration;
	public SqlCommandType getSqlCommandType() {
		return sqlCommandType;
	}

	public void setSqlCommandType(SqlCommandType sqlCommandType) {
		this.sqlCommandType = sqlCommandType;
	}

	public Class getResultType() {
		return resultType;
	}

	public void setResultType(Class resultType) {
		this.resultType = resultType;
	}

	public Class getParameterType() {
		return parameterType;
	}

	public void setParameterType(Class parameterType) {
		this.parameterType = parameterType;
	}

	private List<SqlNode> sqlNodes;

	private MappedStatement() {

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

	public static MappedStatement build(Element element,Configuration configuration) {

		MappedStatement statement = new MappedStatement();
		statement.setConfiguration(configuration);
		String tagName = element.getTagName();
		if ("select".equals(tagName)) {
			statement.setSqlCommandType(SqlCommandType.SELECT);
			String parameterType = element.getAttribute("parameterType");
			String resultType = element.getAttribute("resultType");
			try {
				//TODO：
				statement.setParameterType(Class.forName(parameterType));
				statement.setResultType(Class.forName(resultType));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		statement.setTags(new ArrayList<SqlNode>());
		String id = element.getAttribute("id");
		statement.setId(id);


		NodeList childNodes = element.getChildNodes();
		List<IncludeSqlNode> includes = new ArrayList<IncludeSqlNode>();
		for (int i = 0; i < childNodes.getLength(); i++) {

			Node node = childNodes.item(i);
			SqlNode tag = loadSqlNode(node, includes);
			if (tag != null) {
				statement.sqlNodes.add(tag);
			}

		}

		// init includes

		for (int i = 0; i < includes.size(); i++) {
			IncludeSqlNode includeSqlNode = includes.get(i);
			String refId = includeSqlNode.getRefId();
			if (statement.getId().equals(refId)) {
				throw new RuntimeException();
			}

			Iterator<Entry<String, MappedStatement>> iterator = statement.configuration.mappedStatements.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, MappedStatement> entry = iterator.next();
				MappedStatement sm =  entry.getValue();
				if (sm.getId().equals(refId)) {
					includeSqlNode.setStatement(sm);
				}

			}
		}
		statement.configuration.mappedStatements.put(id, statement);

		return statement;

	}

	public static SqlNode loadSqlNode(Node node, List<IncludeSqlNode> includes) {

		if (!(node instanceof Element)) {
			String content = node.getTextContent().trim();
			if (content != null && content.length() > 0) {
				TextSqlNode sqlTextTag = new TextSqlNode();
				sqlTextTag.setBodyText(content);
				return sqlTextTag;
			}
			return null;

		}
		Element element = (Element) node;

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
			IsNotNullSqlNode isNotEmptySqlNode = new IsNotNullSqlNode();
			isNotEmptySqlNode.setField(field);
			isNotEmptySqlNode.setPrepend(prepend);
			isNotEmptySqlNode.setSubTags(new ArrayList<SqlNode>());
			tag = isNotEmptySqlNode;
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

	public String buildSql(Map<String,Object> prameters) {
		StringBuilder sBuilder = new StringBuilder();
		for (SqlNode tag : sqlNodes) {
			String sql = tag.buildSql(prameters);
			sBuilder.append(sql);
		}

		return sBuilder.toString();
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
}
