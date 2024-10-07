package mfbt.util;

import java.util.Map;
import mfbt.mfbtMod;
import mfbt.commands.VampBonusSPCommand;
import net.minecraftforge.fml.ModList;

public class CompatHandler {

  public final static String VAMPIRISM = "vampirism";

  private static final Map<String, ModData> MODS = Map.of(
    VAMPIRISM, new ModData(Vampirism::initialize);
  )

  public static void initialize() {
    MODS.forEach((modId, data) -> {
      if (ModList.get().isLoaded(modId)) {
        data.initialize.run();
        data.isLoaded = true;
        mfbtMod.LOG.info("Loaded [{}] compatibility", modId);
      }
    });
  }

  public static boolean isModLoaded(final String modId) {
    return MODS.get(modId).isLoaded;
  }

  private static class ModData {

    public final Runnable initialize;
    
    public boolean isLoaded;

    public ModData(final Runnable initialize) {
      this.initialize = initialize;
    }

  }

}
