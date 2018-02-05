package com.f3.orm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.f3.orm.scripting.AbstractTag;
import com.f3.orm.scripting.IncludeTag;
import com.f3.orm.scripting.IsNotNull;
import com.f3.orm.scripting.SqlTextTag;
import com.f3.orm.scripting.Tag;
import com.f3.orm.scripting.WhereTag;

public class Statement {
	String id;
	List<Tag> tags;

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static Statement load(Element element) {

		Statement statement = new Statement();
		statement.setTags(new ArrayList<Tag>());
		String id = element.getAttribute("id");
		statement.setId(id);
		
		NodeList childNodes = element.getChildNodes();
		List<IncludeTag> includes = new ArrayList<IncludeTag>();
		for (int i = 0; i < childNodes.getLength(); i++) {

			Node node = childNodes.item(i);
			Tag tag = loadTag( node, includes);
			if (tag != null) {
				statement.tags.add(tag);
			}

		}

		// init includes
		
		for (int i = 0; i < includes.size(); i++) {
			IncludeTag includeTag=includes.get(i);
			String refId=includeTag.getRefId();
			if (statement.getId().equals(refId)) {
				throw new RuntimeException();
			}
			
			Iterator<Statement> iterator = SqlMapList.statements.iterator();
			while (iterator.hasNext()) {
				Statement sm = (Statement) iterator.next();
				if (sm.getId().equals(refId)) {
					includeTag.setStatement(sm);
				}
				
			}
		}
		SqlMapList.addStatement(statement);

		return statement;

	}

	public static Tag loadTag(Node node, List<IncludeTag> includes) {
		
		if (!( node instanceof Element)) {
			String content = node.getTextContent().trim();
			if (content!=null && content.length()>0) {
				SqlTextTag sqlTextTag=new SqlTextTag();
				sqlTextTag.setBodyText(content);
				return sqlTextTag;
			}
			return null;
			
		}
		Element element=(Element)node;
		
		String prepend = element.getAttribute("prepend");
		String field = element.getAttribute("field");

		Tag tag = null;
		switch (element.getNodeName()) {
		case "#text":
			String value = element.getTextContent().trim();
			SqlTextTag sqlTextTag = new SqlTextTag();
			sqlTextTag.setBodyText(value);
			return sqlTextTag;
		case "where":
			WhereTag whereTag = new WhereTag();
			whereTag.setSubTags(new ArrayList<Tag>());
			tag = whereTag;
			break;
		case "include":
			String refId = element.getAttribute("ref");
			IncludeTag includeTag = new IncludeTag();
			includeTag.setRefId(refId);
			includeTag.setPrepend(prepend);
			includes.add(includeTag);
			tag = includeTag;
			break;
		case "IsNotNull":
			IsNotNull isNotEmptyTag=new IsNotNull();
			isNotEmptyTag.setField(field);
			isNotEmptyTag.setPrepend(prepend);
			isNotEmptyTag.setSubTags(new ArrayList<Tag>());
			tag=isNotEmptyTag;
			break;
		default:
			return null;
		}

		NodeList childNodes = element.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node cNode = childNodes.item(i);
			Tag cTag = loadTag(cNode, includes);
			if (tag != null && cTag != null) {
				((AbstractTag) tag).getSubTags().add(cTag);
			}

		}

		return tag;

	}
	
	public String buildSql(Context context){
		StringBuilder sBuilder=new StringBuilder();
		for (Tag tag : tags) {
			String sql = tag.buildSql(context);
			sBuilder.append(sql);
		}
		
		return sBuilder.toString();
	}
}
