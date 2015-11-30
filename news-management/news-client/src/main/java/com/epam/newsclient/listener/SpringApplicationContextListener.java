package com.epam.newsclient.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplicationContextListener implements ServletContextListener {
	
	public void contextInitialized(ServletContextEvent sce) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring-client-context.xml");
		sce.getServletContext().setAttribute("applicationContext", ac);// ????
	}

	public void contextDestroyed(ServletContextEvent arg0) {
	}

}