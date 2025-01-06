package be.webtechie.picamcontroller.webserver;

import be.webtechie.picamcontroller.camera.CameraController;
import be.webtechie.picamcontroller.camera.CameraService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * The JettyServer class represents a Jetty server that can handle HTTP requests, API servlets, and websockets.
 * <br/><br/>
 * All the endpoints that are exposed by the webserver in the Trainer app, are configured here.
 */
public class JettyServer {

    private final Logger LOGGER = LogManager.getLogger(JettyServer.class.getName());

    private final Server server;

    public JettyServer(CameraService cameraService, int port) {
        server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        server.addConnector(connector);

        // Create a handler for servlets
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        context.setContextPath("/api");
        server.setHandler(context);

        // Register a servlet to handle requests at "/hello"
        context.addServlet(new ServletHolder(new CameraController(cameraService)), "/");

        try {
            // Start the Jetty server
            server.start();
            LOGGER.info("Server started at /api/");
            // Keep the server running
            server.join();
        } catch (Exception e) {
            LOGGER.error("Error while starting Jetty server: {}", e.getMessage());
        } finally {
            server.destroy();
        }
    }
}
