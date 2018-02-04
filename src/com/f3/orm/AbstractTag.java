package com.f3.orm;

import java.util.List;
import java.util.Map;

public abstract class AbstractTag implements Tag {

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

	public abstract TagType getType();

	protected List<Tag> subTags;

	public List<Tag> getSubTags() {
		return subTags;
	}

	public void setSubTags(List<Tag> subTags) {
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
				
				for (Tag tag : subTags) {
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
