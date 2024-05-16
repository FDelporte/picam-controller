package be.webtechie.api;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CameraService extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(CameraService.class.getName());

    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.warn("Not implemented!");
    }
}
