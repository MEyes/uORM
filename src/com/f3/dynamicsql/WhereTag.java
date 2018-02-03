package com.f3.dynamicsql;

public class WhereTag extends AbstractTag{

	@Override
	public String buildSql(Context context) {
		StringBuilder sBuilder=new StringBuilder();
		if (this.subTags!=null && this.subTags.size()>0) {
			boolean isFirst=true;
			for (Tag tag : subTags) {
				String sql=tag.buildSql(context);
				if (sql==null || sql.equals("")) {
					continue;
				}
				
				if (isFirst) {
					if (tag instanceof Tag) {
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
	public TagType getType() {
		return TagType.Where;
	}


}
