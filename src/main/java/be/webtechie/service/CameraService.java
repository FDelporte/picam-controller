package be.webtechie.service;

import be.webtechie.exec.CommandResult;
import be.webtechie.exec.Executor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CameraService {

    private static final Logger LOGGER = LogManager.getLogger(CameraService.class.getName());
    private static CameraService instance;

    private int viewWidth = 1920;
    private int viewHeight = 1080;

    private double zoomOffsetX = 0D;
    private double zoomOffsetY = 0D;
    private double zoomWidth = 1D;
    private double zoomHeight = 1D;

    public static CameraService instance() {
        if (instance == null) {
            instance = new CameraService();
        }
        return instance;
    }

    public void applySettings() {
        String settings = " --viewfinder-width " + viewWidth + " --viewfinder-height " + viewHeight +
                " --roi " + zoomOffsetX + "," + zoomOffsetY + "," + zoomWidth + "," + zoomHeight;
        LOGGER.info("Applying settings: {}", settings);
        CommandResult result = Executor.getCommandOutput("killall -9 libcamera-hello; libcamera-hello  " + settings + " -f -t 0 &");
        if (result.isSuccess()) {
            LOGGER.info("Camera settings were applied");
        } else {
            LOGGER.error("Error while applying settings: {}", result.getErrorMessage());
        }
    }

    public void setViewWidth(int viewWidth) {
        this.viewWidth = viewWidth;
    }

    public void setViewHeight(int viewHeight) {
        this.viewHeight = viewHeight;
    }

    public void setZoomOffsetX(double zoomOffsetX) {
        this.zoomOffsetX = zoomOffsetX;
    }

    public void setZoomOffsetY(double zoomOffsetY) {
        this.zoomOffsetY = zoomOffsetY;
    }

    public void setZoomWidth(double zoomWidth) {
        this.zoomWidth = zoomWidth;
    }

    public void setZoomHeight(double zoomHeight) {
        this.zoomHeight = zoomHeight;
    }
}
