package io.github.tj20201.tjshudmod.config;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import io.wispforest.owo.config.annotation.RangeConstraint;

@Modmenu(modId = "tjshudmod")
@Config(name = "tjshudconfig", wrapperName = "ConfigGUI")
public class ConfigGUIModel {
    public boolean showRenderedPlayersCount = true;
    @RangeConstraint(min = 1, max = ConfigGUIData.amountOfShowValues) public int orderRenderedPlayersCount = 3;
    public boolean showLightLevel = true;
    @RangeConstraint(min = 1, max = ConfigGUIData.amountOfShowValues) public int orderLightLevel = 4;
    public boolean showCoordinates = true;
    @RangeConstraint(min = 1, max = ConfigGUIData.amountOfShowValues) public int orderCoordinates = 1;
    public boolean showWorldTimeAndDay = true;
    @RangeConstraint(min = 1, max = ConfigGUIData.amountOfShowValues) public int orderWorldTimeAndDay = 2;
}
