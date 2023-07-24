package mancala;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class App {
    private static final int PORT = 8080;

    public static void main(String[] args) throws Exception {
        Server server = createServer(PORT);
        server.start();

        System.out.println("Started server.");
        System.out.format("Listening on http://localhost:%d/%n", PORT);
        System.out.println("Press CTRL+C to exit.");

        server.join();
    }

    private static Server createServer(int port) {
        var server = new Server(port);

        ServletContextHandler context = createStatefulContext(server);
        registerServlets(context);

        return server;
    }

    private static ServletContextHandler createStatefulContext(Server server) {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        return context;
    }

    private static void registerServlets(ServletContextHandler context) {
        // Use the Jersey framework to translate the classes in the
        // mancala.api package to server endpoints (servlets).
        // For example, the StartMancala class will become an endpoint at
        // http://localost:8080/mancala/api/start
        ServletHolder serverHolder = context.addServlet(ServletContainer.class, "/mancala/api/*");
        serverHolder.setInitOrder(1);
        serverHolder.setInitParameter("jersey.config.server.provider.packages", "mancala.api");
    }
}
