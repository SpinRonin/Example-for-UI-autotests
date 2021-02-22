import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import servlets.LoginServlet;
import servlets.TestServlet;

public class Main {

    public static void main(String[] args) {

        try {
            Server server = new Server(8000);
            ServletContextHandler Handler = new ServletContextHandler();

            Handler.addServlet(LoginServlet.class, "/login");
            Handler.addServlet(TestServlet.class, "/list");

            server.setHandler(Handler);
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Hello world!");
    }
}
