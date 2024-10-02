package mfbt.util;

import mfbt.commands.*;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

public class ModCommands {
  
  public static final DeferredRegister<ArgumentTypeInfo<?, ?>> ROOT_ARGUMENT = DeferredRegister.create(ForgeRegistries.COMMAND_ARGUMENT_TYPES, "mfbt");

  public static void registerCommands(@NotNull CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext buildContext) {

    dispatcher.register(
      LiteralArgumentBuilder.<CommandSourceStack>literal("mfbt")
        .then(VampBonusSPCommand.register()) // To register as /mfbt addVRSP <player>
      );

  }
}
