package servlets;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class LogoutServlet extends HttpServlet {
    public static final String SESSION_USER_LOGIN_KEY = "USER_LOGIN";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().removeAttribute(LoginServlet.SESSION_USER_LOGIN_KEY);
        resp.sendRedirect("/login");
    }
}