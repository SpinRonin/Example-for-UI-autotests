package servlets;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlets.dto.TodoItem;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestServlet extends HttpServlet {
    private static List<TodoItem> database = new ArrayList(){};



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(database.size() == 0) {
            TodoItem todoItem = new TodoItem();
            todoItem.setText("Lightsabers");
            todoItem.setCreateDate(new Date());
            todoItem.setUserName("General_Grievous");
            database.add(todoItem);

            todoItem = new TodoItem();
            todoItem.setText("Lightsaber");
            todoItem.setCreateDate(new Date());
            todoItem.setUserName("General_Kenobi");
            database.add(todoItem);
        }
        WritePage(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            String userLogin = (String) req.getSession().getAttribute(LoginServlet.SESSION_USER_LOGIN_KEY);
            String itemtext = req.getParameter("text");

            if(itemtext != null || itemtext.length() > 0 && userLogin != null || userLogin.length() > 0) {
//                long someItemsCount = 0L;
//                for (TodoItem x : database) {
//                    if (x.getText().equals(itemtext)) {
//                        someItemsCount++;
//                    }
//                }

                long someItemsCount = database.stream()
                        .filter(x -> x.getText().equals(itemtext))
                        .count();

                if(someItemsCount == 0) {

                    TodoItem todoItem = new TodoItem();
                    todoItem.setText(itemtext);
                    todoItem.setCreateDate(new Date());
                    todoItem.setUserName(userLogin);
                    database.add(todoItem);
                }
            }

        WritePage(req, resp);

    }

    private void WritePage(HttpServletRequest req, HttpServletResponse resp) throws  IOException{
        PrintWriter writer = resp.getWriter();

        String userLogin = (String) req.getSession().getAttribute(LoginServlet.SESSION_USER_LOGIN_KEY);
        if (userLogin == null || userLogin.length() == 0) {

            resp.sendRedirect("/login");
            return;
        }

        resp.getWriter().println("<html>");
        resp.getWriter().println("<head>");
        resp.getWriter().println("</head>");
        resp.getWriter().println("<body>");
        resp.getWriter().println("<h3>Hello there, " + userLogin + "!</h3><br/>");
        resp.getWriter().println("<button onclick=\"window.location.href = '/logout';\">Logout</button>");
        writer.println("<ul>");
        for (TodoItem todoItem: database) {
            if(todoItem.getUserName().equals(userLogin)) {
                writer.println("<li>" + todoItem.getText() + "</li>");
            }
        }
        writer.println("</ul>");

        resp.getWriter().println("<form method='POST'>");
        resp.getWriter().println("<input type='text' name='text'/><br/>");
        resp.getWriter().println("<input type='submit' value='add'/><br/>");

        resp.getWriter().println("</body>");
        resp.getWriter().println("</html>");

    }
}

