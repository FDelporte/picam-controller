package be.webtechie.webserver;

import be.webtechie.service.CameraService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Represents a WebSocket endpoint for handling events.
 */
@ClientEndpoint
@ServerEndpoint(value = "/")
public class EventSocket {

    private static final Logger LOGGER = LogManager.getLogger(EventSocket.class.getName());
    private final ObjectMapper mapper = new ObjectMapper();

    @OnOpen
    public void onWebSocketConnect(Session session) {
        LOGGER.info("Socket Connected: {}", session);
    }

    @OnMessage
    public void onWebSocketText(Session session, String message) throws IOException {
        LOGGER.info("Received TEXT message: {}", message);
        // TODO handle the received message
        try {
            var wsMessage = mapper.readValue(message, WebsocketClientMessage.class);
            if (wsMessage == null) {
                return;
            }
            var cameraService = CameraService.instance();
            if (wsMessage.getViewSettings() != null) {
                if (wsMessage.getViewSettings().getWidth() != null) {
                    cameraService.setViewWidth(wsMessage.getViewSettings().getWidth());
                }
                if (wsMessage.getViewSettings().getHeight() != null) {
                    cameraService.setViewHeight(wsMessage.getViewSettings().getHeight());
                }
            }
            if (wsMessage.getZoomSettings() != null) {
                if (wsMessage.getZoomSettings().getOffsetX() != null) {
                    cameraService.setZoomOffsetX(wsMessage.getZoomSettings().getOffsetX());
                }
                if (wsMessage.getZoomSettings().getOffsetY() != null) {
                    cameraService.setZoomOffsetY(wsMessage.getZoomSettings().getOffsetY());
                }
                if (wsMessage.getZoomSettings().getWidth() != null) {
                    cameraService.setZoomWidth(wsMessage.getZoomSettings().getWidth());
                }
                if (wsMessage.getZoomSettings().getHeight() != null) {
                    cameraService.setZoomHeight(wsMessage.getZoomSettings().getHeight());
                }
            }
            cameraService.applySettings();
        } catch (Exception e) {
            LOGGER.error("Error while handling websocket message {}: {}", message, e.getMessage());
        }
    }

    @OnClose
    public void onWebSocketClose(CloseReason reason) {
        LOGGER.warn("Socket Closed: {}", reason);
    }

    @OnError
    public void onWebSocketError(Throwable cause) {
        LOGGER.error("Websocket error: {}", cause.getMessage());
    }

    public void awaitClosure() throws InterruptedException {
        LOGGER.info("Awaiting closure from remote");
    }
}
