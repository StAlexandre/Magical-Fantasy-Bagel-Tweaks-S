package mfbt.commands;

import com.google.common.collect.Lists;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import java.util.Collection;
import mfbt.util.mfbtVampirismHandler;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import org.apache.logging.log4j.core.config.plugins.convert.TypeConverters.StringConverter;
import org.jetbrains.annotations.NotNull;

// Built using LevelUp.java file
public class VampBonusSPCommand {

  public static ArgumentBuilder<CommandSourceStack, ?> register() {

    return Commands.literal("addVRSP") // Stands for Vampirism Ruler Skill Points, should be "addVRSP <player>", should also accept @s
      .requires(context -> context.hasPermission(2))
      .executes(context -> addVRSP(context, Lists.newArrayList(context.getSource().getPlayerOrException()))).then(Commands.argument("player", EntityArgument.entities())
        .executes(context -> addVRSP(context, EntityArgument.getPlayers(context, "player"))));
  
  }

  private static int addVRSP(@NotNull CommandContext<CommandSourceStack> context, @NotNull Collection<ServerPlayer> players) {

    for (ServerPlayer p : players) {
      mfbtVampirismHandler h = mfbtVampirismHandler.get(p);
      if (h.getLordLevel() == 5) {
        context.getSource().sendSuccess(() -> Component.translatable("command.mfbt.breaker_challenges.ruler.success", h.getBonusSP(h.getCurrentFaction()), players.size() > 1 ? p.getDisplayName() : "Player"), true);
      } else {
        context.getSource().sendFailure(Component.translatable("command.mfbt.breaker_challenges.ruler.fail", players.size() > 1 ? p.getDisplayName() : "Player"));
      }

    return 0;

  }

}