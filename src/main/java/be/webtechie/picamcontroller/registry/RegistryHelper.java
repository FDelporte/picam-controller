package be.webtechie.picamcontroller.registry;

import be.webtechie.picamcontroller.PiCamController;

import java.util.prefs.Preferences;

public class RegistryHelper {
    private final Preferences prefs;

    public RegistryHelper() {
        prefs = Preferences.userNodeForPackage(PiCamController.class);
    }

    public String get(RegistryKey key) {
        return prefs.get(key.name(), "");
    }

    public void put(RegistryKey key, String value) {
        prefs.put(key.name(), value);
    }
}
