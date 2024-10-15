package mfbt.commands;

import com.google.common.collect.Lists;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import java.util.Collection;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.NbtPathArgument;
import net.minecraft.commands.arguments.StringRepresentableArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import org.apache.logging.log4j.core.config.plugins.convert.TypeConverters.StringConverter;
import org.jetbrains.annotations.NotNull;

// Built using LevelUp.java file
public class ModifyForgeCaps {

  public static ArgumentBuilder<CommandSourceStack, ?> register() {

    return Commands.literal("ForgeCaps")
      .requires(ctxt -> ctxt.hasPermission(2))
      .then(Commands.argument("targets"), EntityArgument.players()).then(Commands.argument("path"), new NbtPathArgument())
        .then(Commands.literal("set")).then(Commands.argument("value", new StringRepresentableArgument())
          .executes(
            ctxt -> setFC(ctxt.getSource(), EntityArgument.getPlayers(ctxt, "targets"), (player, path, value) -> {/* Find player, locate path, then set to value */})
          )
        )
        .then(Commands.literal("changeBy")).then(Commands.argument("value", new /*Signed double value of any kind*/())
          .executes(
            ctxt -> cgeFC(ctxt.getSource(), EntityArgument.getPlayers(ctxt, "targets"), (player, path, value) -> {/* Find player, locate path, get current value of the Cap, change by value, then set */})
          )
        )

  }

  private static cgeFC(CommandSourceStack sources, Collection<? extends Player> players, BiConsumer<Player, CommandSourceStack> action) {
    // Uses setFC at the end to apply, cgeFC just has some added code to get and then change before setting.
  }

  private static setFC(CommandSourceStack sources, Collection<? extends Player> players, BiConsumer<Player, CommandSourceStack> action) {
    for (Player player : players) {
      action.accept(player, sources);
    }
    if (players.size() == 1) {
      sources.sendSuccess(() -> Component.literal("Changed " + player.getName().getString() + " ForgeCaps"), true);
    } else {
      sources.sendSuccess(() -> Component.literal("Changed " + players.size() + " player ForgeCaps"), true);
    }
    return players.size();
  }

}