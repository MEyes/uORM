package com.f3.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.f3.mvc.annotation.Controller;
import com.f3.mvc.annotation.Qualified;
import com.f3.mvc.annotation.RequestMapping;

@Controller(value="myController")
public class MyController {
	@Qualified("com.f3.example.MyService")
	public MyService myService;
	
	@RequestMapping("insert")
	public void insert(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		String result=myService.insert();
		PrintWriter writer = resp.getWriter();
		writer.println("<html>");
		writer.print("<body>");
		writer.print("<H1>Hello:"+result+"</H1>");
		writer.print("<body>");
		writer.print("</html>");
	}
	@RequestMapping("delete")
	public void delete(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		String result=myService.delete();
		PrintWriter writer = resp.getWriter();
		writer.println("<html>");
		writer.print("<body>");
		writer.print("<H1>Hello:"+result+"</H1>");
		writer.print("<body>");
		writer.print("</html>");
	}
}
