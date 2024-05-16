package be.webtechie.api;

import be.webtechie.service.CameraService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CameraController extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(CameraController.class.getName());
    private final CameraService cameraService = CameraService.instance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (req.getParameter("viewWidth") != null) {
                cameraService.setViewWidth(Integer.parseInt(req.getParameter("viewWidth")));
            }
            if (req.getParameter("viewHeight") != null) {
                cameraService.setViewHeight(Integer.parseInt(req.getParameter("viewHeight")));
            }

            if (req.getParameter("zoomOffsetX") != null) {
                cameraService.setZoomOffsetX(Double.parseDouble(req.getParameter("zoomOffsetX")));
            }
            if (req.getParameter("zoomOffsetY") != null) {
                cameraService.setZoomOffsetY(Double.parseDouble(req.getParameter("zoomOffsetY")));
            }
            if (req.getParameter("zoomHeight") != null) {
                cameraService.setZoomHeight(Double.parseDouble(req.getParameter("zoomHeight")));
            }
            if (req.getParameter("zoomWidth") != null) {
                cameraService.setZoomWidth(Double.parseDouble(req.getParameter("zoomWidth")));
            }
            cameraService.applySettings();


        } catch (Exception e) {
            LOGGER.error("Error while handling API request: {}", e.getMessage());
        }
    }
}
