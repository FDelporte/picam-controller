package be.webtechie.webserver;

import be.webtechie.api.CameraService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.websocket.jakarta.server.config.JakartaWebSocketServletContainerInitializer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;

/**
 * The JettyServer class represents a Jetty server that can handle HTTP requests, API servlets, and websockets.
 * <br/><br/>
 * All the endpoints that are exposed by the webserver in the Trainer app, are configured here.
 */
public class JettyServer implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(JettyServer.class.getName());

    private final Server server;

    public JettyServer(int port) {
        server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        server.addConnector(connector);

        ContextHandlerCollection contexts = new ContextHandlerCollection();
        server.setHandler(contexts);

        // API handler
        try {
            ServletContextHandler apiHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
            apiHandler.setContextPath("/api");
            contexts.addHandler(apiHandler);
            apiHandler.addServlet(CameraService.class, "/camera");

            LOGGER.info("Added handler for API");
        } catch (Exception e) {
            LOGGER.error("Could not initialize the API handler: {}", e.getMessage());
        }

        // Websocket handler
        try {
            ServletContextHandler webserviceContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
            webserviceContextHandler.setContextPath("/ws");
            contexts.addHandler(webserviceContextHandler);
            JakartaWebSocketServletContainerInitializer.configure(webserviceContextHandler, (servletContext, websocketContainer) -> {
                websocketContainer.setDefaultMaxTextMessageBufferSize(128 * 1024);
                websocketContainer.addEndpoint(EventSocket.class);
            });

            LOGGER.info("Added handler for WEBSOCKETS");
        } catch (Exception e) {
            LOGGER.error("Could not initialize the websocket handler: {}", e.getMessage());
        }
    }

    public void start() {
        try {
            server.start();
        } catch (Exception e) {
            LOGGER.error("Could not start the webserver: {}", e.getMessage());
        }
    }

    public void stop() {
        try {
            server.stop();
        } catch (Exception e) {
            LOGGER.error("Could not stop the webserver: {}", e.getMessage());
        }
    }

    public void join() {
        try {
            server.join();
        } catch (Exception e) {
            LOGGER.error("Could not join the webserver: {}", e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            start();
            join();
        } catch (Exception e) {
            LOGGER.error("Could not start server: {}", e.getMessage());
        }
    }
}
