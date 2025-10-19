package dev.bagel.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import dev.bagel.shim.JsonToNBT;
import dev.bagel.shim.NBTException;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CommandGive.class)
public abstract class CommandGiveMixin extends CommandBase {

    @Inject(method = "processCommand", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/Item;initializeStackOnGiveCommand(Ljava/util/Random;Lnet/minecraft/src/ItemStack;)V"))
    private void processCommandWithNBT(ICommandSender sender, String[] args, CallbackInfo ci, @Local(ordinal = 0) ItemStack stack) {
        if (args.length >= 5)
        {
            String s = func_82360_a(sender, args, 4);

            try
            {
                NBTBase nbtbase = JsonToNBT.func_150315_a(s);

                if (!(nbtbase instanceof NBTTagCompound))
                {
                    notifyAdmins(sender,/* this,*/ "commands.give.tagError", new Object[] {"Not a valid tag"});
                    return;
                }

                stack.setTagCompound((NBTTagCompound)nbtbase);
            }
            catch (NBTException nbtexception)
            {
                notifyAdmins(sender,/* this,*/ "commands.give.tagError", new Object[] {nbtexception.getMessage()});
                return;
            }
        }
    }
}
