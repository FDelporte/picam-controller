package be.webtechie.picamcontroller;

import be.webtechie.picamcontroller.service.CameraService;
import be.webtechie.picamcontroller.webserver.JettyServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PiCamController {

    private static final Logger LOGGER = LogManager.getLogger(PiCamController.class.getName());
    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) {
        LOGGER.info("Initializing camera controller service...");

        LOGGER.info("Starting camera");
        CameraService cameraService = CameraService.instance();
        cameraService.loadSavedSettings();
        cameraService.applySettings();

        LOGGER.info("Starting Jetty webserver on port {}", SERVER_PORT);
        new JettyServer(SERVER_PORT);
    }
}