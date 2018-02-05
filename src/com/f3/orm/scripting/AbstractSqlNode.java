package com.f3.orm.scripting;

import java.util.List;
import java.util.Map;

import com.f3.orm.Context;

public abstract class AbstractSqlNode implements SqlNode {

	protected String field;
	protected String prepend;
	protected String comparedValue;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getPrepend() {
		return prepend;
	}

	public void setPrepend(String prepend) {
		this.prepend = prepend;
	}

	public String getComparedValue() {
		return comparedValue;
	}

	public void setComparedValue(String comparedValue) {
		this.comparedValue = comparedValue;
	}

	public abstract SqlNodeType getType();

	protected List<SqlNode> subTags;

	public List<SqlNode> getSubTags() {
		return subTags;
	}

	public void setSubTags(List<SqlNode> subTags) {
		this.subTags = subTags;
	}

	public boolean test(Context context) {
		return true;
	}
	
	@Override
	public String buildSql(Context context) {
		//��Ҫ�ж�
		StringBuilder sBuilder=new StringBuilder();
		if (test(context)) {
			if (this.subTags!=null && this.subTags.size()>0) {
				
				for (SqlNode tag : subTags) {
					String sql=tag.buildSql(context);
					if (sql==null || sql.equals("")) {
						continue;
					}

					sBuilder.append(sql);
				}
				
			}
		}
		return sBuilder.toString();
	}

	public Object getPropertyValue(Context context) {
		Map<String, Object> prameters = context.prameters;

		if (prameters.containsKey(field)) {
			return prameters.get(field);
		}

		return null;
	}
}
