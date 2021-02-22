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
            todoItem.setText("test item");
            todoItem.setCreateDate(new Date());
            todoItem.setUserName("admin");
            database.add(todoItem);

            todoItem = new TodoItem();
            todoItem.setText("test item2");
            todoItem.setCreateDate(new Date());
            todoItem.setUserName("admin2");
            database.add(todoItem);
        }
        WritePage(resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            String itemtext = req.getParameter("text");

            if(itemtext != null || itemtext.length() > 0) {
                long someItemsCount = 0L;
                for (TodoItem x : database) {
                    if (x.getText().equals(itemtext)) {
                        someItemsCount++;
                    }
                }

                if(someItemsCount == 0) {

                    TodoItem todoItem = new TodoItem();
                    todoItem.setText(itemtext);
                    todoItem.setCreateDate(new Date());
                    todoItem.setUserName("admin");
                    database.add(todoItem);
                }
            }

        WritePage(resp);

    }
    private void WritePage(HttpServletResponse resp) throws  IOException{
        PrintWriter writer = resp.getWriter();

        resp.getWriter().println("<html>");
        resp.getWriter().println("<head>");
        resp.getWriter().println("</head>");
        resp.getWriter().println("<body>");
        resp.getWriter().println("<form method='POST'>");

        writer.println("<ul>");
        for (TodoItem todoItem: database) {
            writer.println("<li>"+ todoItem.getText() + "</li>");
        }
        writer.println("</ul>");
        resp.getWriter().println("<form method='POST'>");
        resp.getWriter().println("<input type='text' name='text'/><br/>");
        resp.getWriter().println("<input type='submit' value='add'/><br/>");
        resp.getWriter().println("</body>");
        resp.getWriter().println("</html>");

    }
}

