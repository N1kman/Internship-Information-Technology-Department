package com.crud.app.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SeatServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public SeatServlet() {
    	super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = (request.getPathInfo().split("/"))[1];
		request.setAttribute("id", id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/seat.jsp");
		dispatcher.forward(request, response);
	}

}
