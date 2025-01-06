# Raspberry Pi Camera Controller

Small Java application to control the camera running an a Raspberry Pi as an HDMI camera by executing the
`libcamera-hello`
application [included in Raspberry Pi OS](https://www.raspberrypi.com/documentation/computers/camera_software.html).

## REST API Controller

The camera settings can be adapted through an API.

```text
http//localhost:8080/api/camera?viewWidth=1920&viewHeight=1080&zoomOffsetX=0.0&zoomOffsetY=0.0&zoomWidth=1.0&zoomHeight=1.0
```

* viewWidth: 1 - 1920
* viewHeight: 1 - 1080
* zoomOffsetX: Percentage of the available width and heights as a decimal between 0 and 1
* zoomOffsetY: Percentage of the available width and heights as a decimal between 0 and 1
* zoomWidth: Percentage of the available width and heights as a decimal between 0 and 1
* zoomHeight: Percentage of the available width and heights as a decimal between 0 and 1

## Run on Raspberry Pi Zero 1 W

### Configure WiFi (if needed)

NOTE: This type of board requires a 2.4 Ghz access point!

```bash
sudo raspi-config
```

### Install Java

```bash
wget https://cdn.azul.com/zulu-embedded/bin/zulu11.76.21-ca-jdk11.0.25-linux_aarch32hf.tar.gz
tar -xzvf zulu11.76.21-ca-jdk11.0.25-linux_aarch32hf.tar.gz
rm zulu11.76.21-ca-jdk11.0.25-linux_aarch32hf.tar.gz
zulu11.76.21-ca-jdk11.0.25-linux_aarch32hf/bin/java -version
```

Should output:

```bash
openjdk version "11.0.25" 2024-10-15 LTS
OpenJDK Runtime Environment Zulu11.76+21-CA (build 11.0.25+9-LTS)
OpenJDK Client VM Zulu11.76+21-CA (build 11.0.25+9-LTS, mixed mode)
```

### Startup Script

```bash
sudo nano /etc/systemd/system/camera.start.service

[Unit]
Description=Start the camera control app

[Service]
Type=simple
ExecStartPre=/bin/sleep 30
ExecStart=/home/pi/zulu11.76.21-ca-jdk11.0.25-linux_aarch32hf/bin/java -jar /home/pi/picam-controller/picam-controller.jar &

[Install]
WantedBy=multi-user.target

sudo chmod 644 /etc/systemd/system/camera.start.service
sudo systemctl enable camera.start.service
sudo systemctl start camera.start.service
sync
sudo reboot
```

