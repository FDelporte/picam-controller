# Raspberry Pi Camera Controller

Small Java application to control the camera running an a Raspberry Pi as a HDMI camera.

## Controllers

The camera settings can be adapted through different communication channels.

### Websocket

* Connect to the device with a websocket client on URL `ws://localhost:8080/ws/`
* Send the following JSON data with the values of your choice:

```json
{
  "view": {
    "width": 1920,
    "height": 1080
  },
  "zoom": {
    "offsetX": 0.0,
    "offsetY": 0.0,
    "width": 1.0,
    "height": 1.0
  }
}
```

### REST API

```text
http//localhost:8080/api/camera?viewWidth1920&viewHeight=1080&zoomOffsetX=0.0&zoomOffsetY=0.0&zoomWidth=1.0&zoomHeight=1.0
```
