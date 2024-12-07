package mfbt.util.compat.vamprism;

import com.mojang.brigadier.context.CommandContext;
import de.teamlapen.vampirism.api.VampirismCapabilities;
import de.teamlapen.vampirism.entity.factions.FactionPlayerHandler;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// Built using FactionPlayerHandler
public class MfbtVampirismHandler implements ISyncable.ISyncableEntityCapabilityInst, IFactionPlayerHandler {

  public static final Logger LOGGER = LogManager.getLogger("MFBT-Vampirism");

  public boolean addBonusSP(CommandContext<CommandSourceStack> context) {
    // Replace block with code to get the Player's faction and lord level
    if (lord_level == 5) {
      this.getCurrentFactionPlayer().ifPresent(player -> player.getSkillHandler().addSkillPoints((int) player.getBonusSP(player.getCurrentFaction())));
    } else {
      return false;
    }
  }

  public static @NotNull MfbtVampirismHandler get(@NotNull Player player) {
    return (MfbtVampirismHandler) player.getCapability(VampirismCapabilities.FACTION_HANDLER_PLAYER, null).orElseThrow(() -> new IllegalStateException("Cannot get Vampirism data from EntityPlayer " + player));
  }

  public int getBonusSP(String faction) {
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
