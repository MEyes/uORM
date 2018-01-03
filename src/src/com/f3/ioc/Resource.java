package com.f3.ioc;

import java.io.InputStream;

public interface Resource {
	InputStream getInputStream() throws Exception;
}
