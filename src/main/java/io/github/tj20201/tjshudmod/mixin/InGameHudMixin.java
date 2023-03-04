package io.github.tj20201.tjshudmod.mixin;

import io.github.tj20201.tjshudmod.TJsHUDModClient;
import io.github.tj20201.tjshudmod.config.ConfigGUIData;
import io.wispforest.owo.config.Option;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {
    @Shadow public abstract TextRenderer getTextRenderer();

    @Shadow @Final private MinecraftClient client;

    @Shadow private int scaledWidth;

    @Shadow private int scaledHeight;

    @Inject(at=@At("TAIL"), method="render")
    public void renderElements(MatrixStack matrices, float tickDelta, CallbackInfo ci) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        TextRenderer tr = this.getTextRenderer();
        ArrayList<Object[]> elements = new ArrayList<>();
        for (int i = 0; i < ConfigGUIData.amountOfShowValues; i++) {
            elements.add(new Object[]{null, null});
        }
        for (Option<?> op : TJsHUDModClient.configGUI.allOptions().values()) {
            String optionName = op.key().path()[0];
            if (optionName.startsWith("show")) {
                Boolean isEnabled = (Boolean) TJsHUDModClient.configGUI.getClass().getMethod(optionName).invoke(TJsHUDModClient.configGUI);
                Integer index = (Integer) TJsHUDModClient.configGUI.getClass().getMethod(optionName.replace("show", "order")).invoke(TJsHUDModClient.configGUI);
                elements.set(index-1, new Object[]{optionName, isEnabled});
            }
        }
        int mult = 0;
        for (Object[] element : elements) {
            mult += 1;
            if ((Boolean) element[1]) {
                String value = "0";
                tr.draw(matrices, Text.translatable(("text.config.tjshudconfig.option."+element[0]).replaceFirst("Show", "").replace("Count", "")+": "+value), scaledWidth-160, 15*(mult+1)-15, 0xFFFFFF);
            }
        }
    }
}
