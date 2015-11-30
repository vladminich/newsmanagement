package com.epam.newsclient.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import com.epam.newsclient.command.CommandFactory;
import com.epam.newsclient.command.ICommand;
import com.epam.newsclient.utils.ConfigurationManager;
import com.epam.newscommon.exception.ServiceException;

@SuppressWarnings("serial")
@WebServlet("/controller")
public class Controller extends HttpServlet {
	
	private CommandFactory client;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ApplicationContext ap = (ApplicationContext) config.getServletContext().getAttribute("applicationContext");
		client = (CommandFactory) ap.getBean("CommandFactory");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = null;
		ICommand command = client.defineCommand(request);
		try {
			if (command != null){
				page = command.execute(request);
			}
			if (page != null) {

				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
				dispatcher.forward(request, response);
			} else {
				page = ConfigurationManager.getProperty("path.page.error");
				request.getSession().setAttribute("message", "NULL PAGE");
				response.sendRedirect(request.getContextPath() + page);

			}
		} catch (ServiceException e) {
			page = ConfigurationManager.getProperty("path.page.error");
			response.sendRedirect(request.getContextPath() + page);
		}
	}
}