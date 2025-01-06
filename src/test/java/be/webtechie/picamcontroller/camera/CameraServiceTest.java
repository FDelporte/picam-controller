package be.webtechie.picamcontroller.camera;

import be.webtechie.picamcontroller.registry.RegistryHelper;
import be.webtechie.picamcontroller.registry.RegistryKey;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CameraServiceTest {

    @Test
    void shouldLoadValuesFromRegistry() {
        RegistryHelper registryHelper = new RegistryHelper();

        registryHelper.put(RegistryKey.VIEW_WIDTH, "100");
        registryHelper.put(RegistryKey.VIEW_HEIGHT, "200");
        registryHelper.put(RegistryKey.ZOOM_OFFSET_Y, "0.1");
        registryHelper.put(RegistryKey.ZOOM_OFFSET_X, "0.2");
        registryHelper.put(RegistryKey.ZOOM_WIDTH, "0.3");
        registryHelper.put(RegistryKey.ZOOM_HEIGHT, "0.4");

        CameraService cameraService = new CameraService();

        assertEquals(100, cameraService.getViewWidth());
        assertEquals(200, cameraService.getViewHeight());
        assertEquals(0.1, cameraService.getZoomOffsetY());
        assertEquals(0.2, cameraService.getZoomOffsetX());
        assertEquals(0.3, cameraService.getZoomWidth());
        assertEquals(0.4, cameraService.getZoomHeight());
    }

    @Test
    void shouldUseAndStoreNewSettings() {
        RegistryHelper registryHelper = new RegistryHelper();

        registryHelper.put(RegistryKey.VIEW_WIDTH, "1920");
        registryHelper.put(RegistryKey.VIEW_HEIGHT, "1080");
        registryHelper.put(RegistryKey.ZOOM_OFFSET_Y, "0.0");
        registryHelper.put(RegistryKey.ZOOM_OFFSET_X, "0.0");
        registryHelper.put(RegistryKey.ZOOM_WIDTH, "1.0");
        registryHelper.put(RegistryKey.ZOOM_HEIGHT, "1.0");

        CameraService cameraService = new CameraService();

        cameraService.setViewWidth(800);
        cameraService.setViewHeight(600);
        cameraService.setZoomOffsetY(0.5);
        cameraService.setZoomOffsetX(0.6);
        cameraService.setZoomWidth(0.7);
        cameraService.setZoomHeight(0.8);

        assertEquals(800, cameraService.getViewWidth());
        assertEquals(600, cameraService.getViewHeight());
        assertEquals(0.5, cameraService.getZoomOffsetY());
        assertEquals(0.6, cameraService.getZoomOffsetX());
        assertEquals(0.7, cameraService.getZoomWidth());
        assertEquals(0.8, cameraService.getZoomHeight());

        cameraService.applySettings();

        assertEquals("800", registryHelper.get(RegistryKey.VIEW_WIDTH));
        assertEquals("600", registryHelper.get(RegistryKey.VIEW_HEIGHT));
        assertEquals("0.5", registryHelper.get(RegistryKey.ZOOM_OFFSET_Y));
        assertEquals("0.6", registryHelper.get(RegistryKey.ZOOM_OFFSET_X));
        assertEquals("0.7", registryHelper.get(RegistryKey.ZOOM_WIDTH));
        assertEquals("0.8", registryHelper.get(RegistryKey.ZOOM_HEIGHT));
    }
}
