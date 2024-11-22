package mfbt.commands;

import com.google.common.collect.Lists;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import java.util.Collections;
import java.util.List;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.NbtPathArgument;
import net.minecraft.commands.arguments.NbtTagArgument;
import net.minecraft.commands.arguments.NbtPathArgument.NbtPath;
import net.minecraft.nbt.NumericTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import org.apache.logging.log4j.core.config.plugins.convert.TypeConverters.StringConverter;
import org.jetbrains.annotations.NotNull;

// Built using LevelUp.java file
public class ModifyPlayerData {

  public static ArgumentBuilder<CommandSourceStack, ?> register() {

    return Commands.literal("modifyPD")
    // Find deobfuscated form of /data command (Saved Games\MultiMC\libraries\net\minecraft\client\1.20.1-20230612.114412\client-1.20.1-20230612.114412-srg.jar \net\minecraft\server\commands\data\DataCommands.class)
      .requires(ctxt -> ctxt.hasPermission(2))
      .then(Commands.argument("player"), EntityArgument.players()).then(Commands.argument("targetPath"), new NbtPathArgument()).then(Commands.literal("set")).then(Commands.argument("value", new NbtTagArgument())
      .executes((ctxt) -> {
        List<Tag> rewrite = Collections.singletonList(NbtTagArgument.getArgument(ctxt, "value"));
        setFC(ctxt, EntityArgument.getPlayers(ctxt, "player"), NbtPathArgument.getArgument(ctxt, "targetPath"), rewrite);
      })
    );
  }

  private static int setFC(CommandSourceStack sources, ServerPlayer players, DataManipulator targetPath, List<Tag> value) {
    for (Player player : players) {
      // Will need to design custom accessor based off Player NBT Modifier mod to access players
    }
    if (players.size() == 1) {
      sources.sendSuccess(() -> Component.m_237110_("mfbt.commands.mpd_success_single"), /* Player with title */)
    } else {
      sources.sendSuccess(() -> Component.m_237110_("mfbt.commands.mpd_success_multiple"), players.size())
    }
  }

}