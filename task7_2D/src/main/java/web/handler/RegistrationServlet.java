package web.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.RegistrationService;

/**
 * HTTP end-point to handle registration request.
 */
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        System.out.println("[RegistrationServlet] GET");
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        System.out.println("[RegistrationServlet] POST");

        String fName     = req.getParameter("fname");
        String lName     = req.getParameter("lname");
        String email     = req.getParameter("email");
        String dob       = req.getParameter("dob");
        String password  = req.getParameter("password");
        String confPass  = req.getParameter("confPassword");

        System.out.println("Registration data: " 
            + fName + ", " 
            + lName + ", " 
            + email + ", " 
            + dob + ", [password hidden]"
        );

        // perform registration
        boolean isRegistered = RegistrationService.register(
            fName, lName, email, dob, password, confPass
        );
        String regStatus = isRegistered ? "success" : "fail";

        resp.setContentType("text/html");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setHeader("X-Registration-Status", regStatus);

        String html = "<!DOCTYPE html>\n"
            + "<html>\n"
            + "<head>\n"
            + "  <title>Registration " + regStatus + "</title>\n"
            + "  <meta name=\"registration-result\" content=\"" + regStatus + "\">\n"
            + "</head>\n"
            + "<body>\n"
            + "  <div id=\"registration-container\">\n"
            + "    <h2 id=\"registration-status\">Registration status: " + regStatus + "</h2>\n"
            + "    <div id=\"registration-message\">\n"
            +      (isRegistered
                     ? "Thank you, " + fName + ", your account is created."
                     : "Registration failed. Please check your inputs and try again.")
            + "    </div>\n"
            + "  </div>\n"
            + "</body>\n"
            + "</html>";

        try (PrintWriter out = resp.getWriter()) {
            out.println(html);
        }
    }
}
