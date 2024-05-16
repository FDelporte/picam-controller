package be.webtechie.service;

import be.webtechie.exec.CommandResult;
import be.webtechie.exec.Executor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CameraService {

    private static final Logger LOGGER = LogManager.getLogger(CameraService.class.getName());
    private static CameraService instance;

    private final int viewWidth = 1920;
    private final int viewHeight = 1080;

    private final double zoomOffsetX = 0D;
    private final double zoomOffsetY = 0D;
    private final double zoomWidth = 1D;
    private final double zoomHeight = 1D;

    public static CameraService instance() {
        if (instance == null) {
            instance = new CameraService();
        }
        return instance;
    }

    public void applySettings() {
        String cameraCommand = "libcamera-hello " +
                " --viewfinder-width " + viewWidth + " --viewfinder-height " + viewHeight +
                " --roi " + zoomOffsetX + "," + zoomOffsetY + "," + zoomWidth + "," + zoomHeight +
                " -f -t 0 &";
        CommandResult result = Executor.getCommandOutput("killall -9 libcamera-hello; " + cameraCommand);
        if (result.isSuccess()) {
            LOGGER.info("Camera settings were applied");
        } else {
            LOGGER.error("Error while applying settings: {}", result.getErrorMessage());
        }
    }
}
