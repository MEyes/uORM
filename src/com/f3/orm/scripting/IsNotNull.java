package com.f3.orm.scripting;

import com.f3.orm.Context;

public class IsNotNull extends AbstractTag{


	@Override
	public TagType getType() {
		return TagType.IsNotEmpty;
	}
	@Override
	public boolean test(Context context) {
		Object propertyValue = getPropertyValue(context);
		
		if (propertyValue==null || propertyValue.equals("")) {
			return false;
		}
		
		return true;
	}
}
