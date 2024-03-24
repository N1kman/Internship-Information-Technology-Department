package com.crud.app.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AircraftServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public AircraftServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = (request.getPathInfo().split("/"))[1];
		request.setAttribute("id", id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/aircraft.jsp");
		dispatcher.forward(request, response);
	}
}
