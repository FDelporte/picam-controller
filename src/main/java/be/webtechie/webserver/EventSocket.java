package be.webtechie.webserver;

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

    @OnOpen
    public void onWebSocketConnect(Session session) {
        LOGGER.info("Socket Connected: {}", session);
    }

    @OnMessage
    public void onWebSocketText(Session session, String message) throws IOException {
        LOGGER.info("Received TEXT message: {}", message);
        // TODO handle the received message
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
