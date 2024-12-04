package mfbt.util.compat;

import de.teamlapen.vampirism.api.entity.factions.IFactionPlayerHandler;
import de.teamlapen.vampirism.api.VampirismCapabilities;
import net.minecraft.world.entity.LivingEntity;

public class Vampirism {

  // Built using Create's Github for Curios
  private static Optional<Map<String, IFactionPlayerHandler>> getVampirism)LivingEntity entity) {
    return entity.getCapability(VampirismCapabilities.FACTION_HANDLER_PLAYER).map(IFactionPlayerHandler::)
  }

}
