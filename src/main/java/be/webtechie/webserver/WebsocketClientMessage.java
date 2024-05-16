package be.webtechie.webserver;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a message received from the WebSocket client.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WebsocketClientMessage {

    @JsonProperty("view")
    private ViewSettings viewSettings;

    @JsonProperty("zoom")
    private ZoomSettings zoomSettings;

    public WebsocketClientMessage() {
        // For JSON mapping
    }

    public ViewSettings getViewSettings() {
        return viewSettings;
    }

    public void setViewSettings(ViewSettings viewSettings) {
        this.viewSettings = viewSettings;
    }

    public ZoomSettings getZoomSettings() {
        return zoomSettings;
    }

    public void setZoomSettings(ZoomSettings zoomSettings) {
        this.zoomSettings = zoomSettings;
    }

    static public class ViewSettings {
        @JsonProperty("width")
        private Integer width;

        @JsonProperty("height")
        private Integer height;

        public ViewSettings() {
            // For JSON mapping
        }

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }
    }

    static public class ZoomSettings {
        @JsonProperty("offsetX")
        private Double offsetX;

        @JsonProperty("offsetY")
        private Double offsetY;

        @JsonProperty("width")
        private Double width;

        @JsonProperty("height")
        private Double height;

        public ZoomSettings() {
            // For JSON mapping
        }

        public Double getOffsetX() {
            return offsetX;
        }

        public void setOffsetX(Double offsetX) {
            this.offsetX = offsetX;
        }

        public Double getOffsetY() {
            return offsetY;
        }

        public void setOffsetY(Double offsetY) {
            this.offsetY = offsetY;
        }

        public Double getWidth() {
            return width;
        }

        public void setWidth(Double width) {
            this.width = width;
        }

        public Double getHeight() {
            return height;
        }

        public void setHeight(Double height) {
            this.height = height;
        }
    }
}
