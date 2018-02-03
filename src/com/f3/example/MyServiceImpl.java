package com.f3.example;

import com.f3.mvc.annotation.Service;

@Service("com.f3.example.MyService")
public class MyServiceImpl implements MyService {

	@Override
	public String insert() {
		// TODO Auto-generated method stub
		System.out.println("MyService.insert");
		return "insert success";
	}

	@Override
	public String delete() {
		System.out.println("MyService.delete");
		return "delete success";
	}

}
