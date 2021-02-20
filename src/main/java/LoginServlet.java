import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<html>");
        resp.getWriter().println("<head>");
        resp.getWriter().println("</head>");
        resp.getWriter().println("<body>");
        resp.getWriter().println("<form method='POST'>");
        resp.getWriter().println("<input type='text' name='login'/><br/>");
        resp.getWriter().println("<input type='password' name='password'/><br/>");
        resp.getWriter().println("<input type='submit' name='submit'/><br/>");
        resp.getWriter().println("</body>");
        resp.getWriter().println("</html>");

    }
}
