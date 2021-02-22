package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        writePage(resp, false);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
      String login = req.getParameter("login");
        String password = req.getParameter("password");

        if(login.equalsIgnoreCase("admin") && password.equals("12345")){
            resp.sendRedirect("/list");
        } else {
            writePage(resp, true);
        }
    }
    private void writePage(HttpServletResponse resp, boolean isError) throws  IOException{
        PrintWriter writer = resp.getWriter();

        resp.getWriter().println("<html>");
        resp.getWriter().println("<head>");
        resp.getWriter().println("</head>");
        resp.getWriter().println("<body>");

        if (isError){
            writer.println("<h3>Invalid log/pass</h3>");
        }
        resp.getWriter().println("<form method='POST'>");
        resp.getWriter().println("<input type='text' name='login'/><br/>");
        resp.getWriter().println("<input type='password' name='password'/><br/>");
        resp.getWriter().println("<input type='submit' name='submit'/><br/>");
        resp.getWriter().println("</body>");
        resp.getWriter().println("</html>");

    }
}

