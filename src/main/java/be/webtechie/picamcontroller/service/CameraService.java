package be.webtechie.picamcontroller.service;

import be.webtechie.picamcontroller.exec.Executor;
import be.webtechie.picamcontroller.registry.RegistryHelper;
import be.webtechie.picamcontroller.registry.RegistryKey;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CameraService {

    private static final Logger LOGGER = LogManager.getLogger(CameraService.class.getName());
    private static final RegistryHelper registryHelper = new RegistryHelper();
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

    public void loadSavedSettings() {
        String savedViewWidth = registryHelper.get(RegistryKey.VIEW_WIDTH);
        if (savedViewWidth != null && !savedViewWidth.isBlank()) {
            this.viewWidth = Integer.parseInt(savedViewWidth);
        }
        String savedViewHeight = registryHelper.get(RegistryKey.VIEW_HEIGHT);
        if (savedViewHeight != null && !savedViewHeight.isBlank()) {
            this.viewHeight = Integer.parseInt(savedViewHeight);
        }
        String savedZoomOffsetX = registryHelper.get(RegistryKey.ZOOM_OFFSET_X);
        if (savedZoomOffsetX != null && !savedZoomOffsetX.isBlank()) {
            this.zoomOffsetX = Double.parseDouble(savedZoomOffsetX);
        }
        String savedZoomOffsetY = registryHelper.get(RegistryKey.ZOOM_OFFSET_Y);
        if (savedZoomOffsetY != null && !savedZoomOffsetY.isBlank()) {
            this.zoomOffsetY = Double.parseDouble(savedZoomOffsetY);
        }
        String savedZoomWidth = registryHelper.get(RegistryKey.ZOOM_WIDTH);
        if (savedZoomWidth != null && !savedZoomWidth.isBlank()) {
            this.zoomWidth = Double.parseDouble(savedZoomWidth);
        }
        String savedZoomHeight = registryHelper.get(RegistryKey.ZOOM_HEIGHT);
        if (savedZoomHeight != null && !savedZoomHeight.isBlank()) {
            this.zoomHeight = Double.parseDouble(savedZoomHeight);
        }
    }

    public void applySettings() {
        // Stop current camera, result can be ignored
        Executor.getCommandOutput("killall -9 libcamera-hello");

        // Start camera with requested settings
        String settings = " --viewfinder-width " + viewWidth + " --viewfinder-height " + viewHeight +
                " --roi " + zoomOffsetX + "," + zoomOffsetY + "," + zoomWidth + "," + zoomHeight;
        LOGGER.info("Applying settings: {}", settings);
        Executor.getCommandOutput("libcamera-hello  " + settings + " -f -t 0");

        LOGGER.info("Saving camera settings in registry");
        registryHelper.put(RegistryKey.VIEW_WIDTH, String.valueOf(this.viewWidth));
        registryHelper.put(RegistryKey.VIEW_HEIGHT, String.valueOf(this.viewHeight));
        registryHelper.put(RegistryKey.ZOOM_OFFSET_X, String.valueOf(this.zoomOffsetX));
        registryHelper.put(RegistryKey.ZOOM_OFFSET_Y, String.valueOf(this.zoomOffsetY));
        registryHelper.put(RegistryKey.ZOOM_WIDTH, String.valueOf(this.zoomWidth));
        registryHelper.put(RegistryKey.ZOOM_HEIGHT, String.valueOf(this.zoomHeight));
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
