package web.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.service.LoginService;

/**
 * HTTP end-point to handle login service.
 */
public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		System.out.println("[LoginServlet] GET");

		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		System.out.println("[LoginServlet] POST");

		String username = req.getParameter("username");
		String password = req.getParameter("passwd");
		String dob = req.getParameter("dob");
		
		System.out.println(dob);

		System.out.println("Username/password/dob: " + username + ", " + password + ", " + dob);

		boolean isAuthenticated = LoginService.login(username, password, dob);
		String loginStatus = isAuthenticated ? "success" : "fail";

		resp.setContentType("text/html");
		resp.setStatus(HttpServletResponse.SC_OK);

		resp.setHeader("X-Login-Status", loginStatus);

		String htmlResponse = "<!DOCTYPE html>\n" + "<html>\n" + "<head>\n" + "    <title>Login " + loginStatus
				+ "</title>\n" + "    <meta name=\"login-result\" content=\"" + loginStatus + "\">\n" + "</head>\n"
				+ "<body>\n" + "    <div id=\"login-container\">\n" + "        <h2 id=\"login-status\">Login status: "
				+ loginStatus + "</h2>\n" + "        <div id=\"login-message\">"
				+ (isAuthenticated ? "Welcome, " + username + "!" : "Authentication failed. Please try again.")
				+ "</div>\n" + "    </div>\n" + "</body>\n" + "</html>";

		PrintWriter writer = resp.getWriter();
		writer.println(htmlResponse);
	}
}

