package com.f3.orm.scripting;

import java.util.Map;

public interface SqlNode {

	String buildSql(Map<String,Object> prameters);
}
