package com.f3.orm.scripting;

import com.f3.orm.Context;

public interface SqlNode {

	String buildSql(Context context);
}
