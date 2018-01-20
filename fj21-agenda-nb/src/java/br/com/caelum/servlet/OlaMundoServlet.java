package br.com.caelum.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OlaMundoServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<title>Primeira Servlet</title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<h1>Ola Mundo Servlet!</h1>");
		pw.println("</body>");
		pw.println("</html>");
	}
}
