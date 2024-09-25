package mfbt.util;

import mfbt.commands.*;
import com.google.common.collect.Lists;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import java.util.List;

import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

public class ModCommands {
  
  public static final DeferredRegister<ArgumentTypeInfo<?, ?>> ROOT_ARGUMENT = DeferredRegister.create(ForgeRegistries.COMMAND_ARGUMENT_TYPES, "mfbt");

  public static void registerCommands(@NotNull CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext buildContext) {
    List<String> mfbt = Lists.newArrayList("mfbt");

    for (String s : mfbt) {
      dispatcher.register(
        LiteralArgumentBuilder.<CommandSourceStack>literal(s)
          .then(VampBonusSPCommand.register())
      );
    }
  }
}
