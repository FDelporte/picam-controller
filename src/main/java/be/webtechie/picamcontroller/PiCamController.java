package be.webtechie.picamcontroller;

import be.webtechie.picamcontroller.camera.CameraService;
import be.webtechie.picamcontroller.webserver.JettyServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PiCamController {

    private static final Logger LOGGER = LogManager.getLogger(PiCamController.class.getName());
    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) {
        LOGGER.info("Starting up...");

        LOGGER.info("Initializing camera server");
        CameraService cameraService = new CameraService();

        LOGGER.info("Starting Jetty webserver on port {}", SERVER_PORT);
        new JettyServer(cameraService, SERVER_PORT);
    }
}