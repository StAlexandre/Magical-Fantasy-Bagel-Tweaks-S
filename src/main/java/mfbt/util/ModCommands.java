package mfbt.util;

import mfbt.commands.*;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import mfbt.util.ModCommands;
import mfbt.util.CompatHandler;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

public class ModCommands {
  
  public static final DeferredRegister<ArgumentTypeInfo<?, ?>> ROOT_ARGUMENT = DeferredRegister.create(ForgeRegistries.COMMAND_ARGUMENT_TYPES, "MFBT");

  public static void registerCommands(@NotNull CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext buildContext) {

    dispatcher.register(
      LiteralArgumentBuilder.<CommandSourceStack>literal("MFBT")
        .then(VampBonusSPCommand.register()) // To register as /MFBT addVRSP <player>, needs conditional to see if vampirism is provided before running this. If not, skip
      );

  }

}
