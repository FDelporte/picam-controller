package be.webtechie.picamcontroller.registry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestRegistry {

    @Test
    void shouldChangeValueInRegistry() {
        RegistryHelper registryHelper = new RegistryHelper();
        registryHelper.put(RegistryKey.VIEW_WIDTH, "1000");
        assertEquals("1000", registryHelper.get(RegistryKey.VIEW_WIDTH));
        registryHelper.put(RegistryKey.VIEW_WIDTH, "1920");
        assertEquals("1920", registryHelper.get(RegistryKey.VIEW_WIDTH));
    }
}
