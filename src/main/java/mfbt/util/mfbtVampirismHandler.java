package mfbt.util;

import de.teamlapen.vampirism.api.entity.factions.IPlayableFaction;
import de.teamlapen.vampirism.entity.factions.FactionPlayerHandler;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

public class mfbtVampirismHandler {

  public static final Logger LOGGER = LogManager.getLogger("mfbt-Vampirism");

  // Prettu sure this is fine thanks to import 4
  public boolean addBonusSP(IPlayableFaction<?> faction) {
    FactionPlayerHandler entity = FactionPlayerHandler.get(this);
    entity.getCurrentFactionPlayer().ifPresent(player -> {
      player.getSkillHandler().addSkillPoints(this.getBonusSP(faction));
    });
    return true;
  }

  private int getBonusSP(String faction) {
    switch (faction) {
      case "werewolf":
        return 24;
      case "hunter":
        return 17;
      case "vampire":
        return 20;
      default:
        return 0;
    }
  }

}
