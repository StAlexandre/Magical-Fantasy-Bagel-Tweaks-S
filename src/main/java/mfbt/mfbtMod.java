package mfbt;

import mfbt.util.ModCommands;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

@Mod("mfbt")
public class mfbtMod {

  public static final Logger LOGGER = LogManager.getLogger("Magical Fantasy Bagel Tweaks");
  public static mfbtMod instance;

  public mfbtMod() {

    instance = this;

    MinecraftForge.EVENT_BUS.addListener(this::onCommandsRegister);

    CompatHandler.initialize();

  }


  public void onCommandsRegister(@NotNull RegisterCommandsEvent event) {
    ModCommands.registerCommands(event.getDispatcher(), event.getBuildContext());
    // Imports commands here
  }

}