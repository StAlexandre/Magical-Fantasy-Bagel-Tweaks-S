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
            ctxt -> setFC(ctxt.getSource(), EntityArgument.getPlayers(ctxt, "targets"), /* Take the rest of the arguments to find the location, and set */)
          )
        )
        .then(Commands.literal("add"))
  
  }