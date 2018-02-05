package com.f3.orm.scripting;

import com.f3.orm.Context;

public class WhereSqlNode extends AbstractSqlNode{

	@Override
	public String buildSql(Context context) {
		StringBuilder sBuilder=new StringBuilder();
		if (this.subTags!=null && this.subTags.size()>0) {
			boolean isFirst=true;
			for (SqlNode tag : subTags) {
				String sql=tag.buildSql(context);
				if (sql==null || sql.equals("")) {
					continue;
				}
				
				if (isFirst) {
					if (tag instanceof SqlNode) {
						//trim
						sql = tag.buildSql(context);
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
