package com.f3.orm;

import java.util.List;

public interface UserMapper {
	List<User> get(UserQueryDTO userQueryDTO);
}
