package be.webtechie;

import be.webtechie.webserver.JettyServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class.getName());
    private static final int SERVER_PORT = 8080;
    
    public static void main(String[] args) {
        LOGGER.info("Initializing server...");

        JettyServer server = new JettyServer(SERVER_PORT);
        (new Thread(server)).start();

        System.out.println("Hello world!");
    }
}