package be.webtechie.webserver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class WebsocketClientMessageTest {

    @Test
    public void dataReceivedFromClientThroughWebsocketMustBeParsedCorrectly() throws JsonProcessingException {
        var data = "{\"view\": {\"width\": 1920, \"height\": 1080}, " +
                "\"zoom\": {\"offsetX\": 0.4, \"offsetY\": 25.69, \"width\": 0.58, \"height\": 0.18}}";

        ObjectMapper mapper = new ObjectMapper();
        var message = mapper.readValue(data, WebsocketClientMessage.class);

        assertThat(message).isNotNull();
        assertAll(
                () -> assertThat(message.getViewSettings().getWidth()).isEqualTo(1920),
                () -> assertThat(message.getViewSettings().getHeight()).isEqualTo(1080),
                () -> assertThat(message.getZoomSettings().getOffsetX()).isEqualTo(0.4),
                () -> assertThat(message.getZoomSettings().getOffsetY()).isEqualTo(25.69),
                () -> assertThat(message.getZoomSettings().getWidth()).isEqualTo(0.58),
                () -> assertThat(message.getZoomSettings().getHeight()).isEqualTo(0.18)
        );
    }
}
