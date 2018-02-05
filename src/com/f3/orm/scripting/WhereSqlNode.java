package com.f3.orm.scripting;

import java.util.Map;

public class WhereSqlNode extends AbstractSqlNode{

	@Override
	public String buildSql(Map<String,Object> parameters) {
		StringBuilder sBuilder=new StringBuilder();
		if (this.subTags!=null && this.subTags.size()>0) {
			boolean isFirst=true;
			for (SqlNode tag : subTags) {
				String sql=tag.buildSql(parameters);
				if (sql==null || sql.equals("")) {
					continue;
				}
				
				if (isFirst) {
					if (tag instanceof SqlNode) {
						//trim
						sql = tag.buildSql(parameters);
						isFirst=false;
						
					}
				}
				sBuilder.append(sql);
			}
			
		}
		return sBuilder.toString();
	}

	@Override
	public SqlNodeType getType() {
		return SqlNodeType.Where;
	}


}
