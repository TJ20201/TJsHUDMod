package io.github.tj20201.tjshudmod;

import io.github.tj20201.tjshudmod.config.ConfigGUI;
import io.wispforest.owo.config.OwoConfigCommand;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class TJsHUDModClient implements ClientModInitializer {

    private static KeyBinding configGUIBind;
    // Ignore the errors that the ConfigGUI gives, owo-config generates the class.
    public static final ConfigGUI configGUI = ConfigGUI.createAndLoad();

    public KeyBinding registerKeybind(String id, int defaultKey) {
        return KeyBindingHelper.registerKeyBinding(new KeyBinding("key.tjshudmod."+id, InputUtil.Type.KEYSYM, defaultKey, "category.tjshudmod.main"));
    }

    @Override
    public void onInitializeClient() {
        configGUIBind = registerKeybind("opengui", GLFW.GLFW_KEY_X);
        ClientTickEvents.END_CLIENT_TICK.register(client -> {if (configGUIBind.wasPressed()) {
            configGUI.load();
        }});
    }
}
