package mfbt.commands;

import com.google.common.collect.Lists;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import de.teamlapen.vampirism.entity.factions.FactionPlayerHandler;
import java.util.Collection;
import mfbt.util.mfbtVampirismHandler;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import org.apache.logging.log4j.core.config.plugins.convert.TypeConverters.StringConverter;
import org.jetbrains.annotations.NotNull;

// Pulling from de.teamlapen.vampirism.command.LevelUpCommand
public class VampBonusSPCommand {

  public static ArgumentBuilder<CommandSourceStack, ?> register() {

    return Commands.literal("addVSP")
      .requires(context -> context.hasPermission(2))
      .executes(context -> addVSP(context, Lists.newArrayList(context.getSource().getPlayerOrException()))).then(Commands.argument("player", EntityArgument.entities())
        .executes(context -> addVSP(context, EntityArgument.getPlayers(context, "player"))));
  
  }

  private static int addVSP(@NotNull CommandContext<CommandSourceStack> context, @NotNull Collection<ServerPlayer> players) {

    for (ServerPlayer player : players) {
      // Am pretty confident these problems resolve with line 6 import
      FactionPlayerHandler handler = FactionPlayerHandler.get(player);
      mfbtVampirismHandler handlerMFBT = mfbtVampirismHandler.addBonusSP();
      if (handler.getCurrentLevel() == handler.getCurrentFaction().getHighestReachableLevel()) {
        if (handlerMFBT.addBonusSP(handler.getCurrentFaction())) {
          context.getSource().sendSuccess(() -> Component.translatable("Added %s points to %s.", (String) handlerMFBT.getBonusSP(handler.getCurrentFaction()), players.size() > 1 ? player.getDisplayName() : "Player"), true);
        } else {
          context.getSource().sendFailure(Component.translatable("Failed to add SP to %s.", players.size() > 1 ? player.getDisplayName() : "Player"));
        }
      } else {
        context.getSource().sendFailure(Component.translatable("%s is not at the highest lord level.", players.size() > 1 ? player.getDisplayName() : "Player"));
      }
    }

    return 0;

  }

}