package io.github.tj20201.tjshudmod;

import com.mojang.brigadier.ParseResults;
import io.github.tj20201.tjshudmod.config.ConfigGUI;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class TJsHUDModClient implements ClientModInitializer {

    // Ignore any errors that the ConfigGUI may give, this is caused by owo-config generating the class at runtime.
    public static final ConfigGUI configGUI = ConfigGUI.createAndLoad();

    @Override
    public void onInitializeClient() {}
}
