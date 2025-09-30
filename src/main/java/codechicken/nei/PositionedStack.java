package codechicken.nei;

import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.src.EnumSkyBlock.Block;

public class PositionedStack {
    public int relx;
    public int rely;
    public ItemStack[] items;
    // compatibility dummy
    public ItemStack item;

    private boolean permutated = false;

    public PositionedStack(Object object, int x, int y, boolean genPerms) {
        items = extractRecipeItems(object);
        relx = x;
        rely = y;

        if (genPerms) {
            generatePermutations();
        } else {
            setPermutationToRender(0);
        }
    }

    public void generatePermutations() {
        if (permutated) return;

        List<ItemStack> stacks = new ArrayList<>();
        for (ItemStack item : items) {
            if (item == null || item.getItem() == null) continue;

            if (item.getItemDamage() == Short.MAX_VALUE) {
                {
                    ItemStack base = new ItemStack(item.getItem(), item.stackSize);
                    base.stackTagCompound = item.stackTagCompound;
                    stacks.add(base);
                }
                continue;
            }

            stacks.add(item.copy());
        }
        items = stacks.toArray(new ItemStack[0]);

        if (items.length == 0) items = new ItemStack[] { new ItemStack(net.minecraft.src.Block.fire) };

        permutated = true;
        setPermutationToRender(0);
    }

    public void setPermutationToRender(int index) {
        this.item = this.items[index].copy();

        if (this.item.getItem() == null) {
            this.item = new ItemStack(net.minecraft.src.Block.fire);
        } else if (this.item.getItemDamage() == 32767) {
            this.item.setItemDamage(0);
        }
    }

    public static ItemStack[] extractRecipeItems(Object obj) {
        if (obj instanceof ItemStack) return new ItemStack[] { (ItemStack) obj };
        if (obj instanceof ItemStack[]) return (ItemStack[]) obj;
        if (obj instanceof List) return ((List<ItemStack>) obj).toArray(new ItemStack[0]);

        throw new ClassCastException(obj + " not an ItemStack, ItemStack[] or List<ItemStack?");
    }

    public boolean contains(ItemStack ingredient) {
        for (ItemStack item : items) if (areStacksSameTypeCrafting(item, ingredient)) return true;

        return false;
    }

    public static boolean areStacksSameTypeCrafting(ItemStack stack1, ItemStack stack2) {
        return stack1 != null && stack2 != null
                && stack1.getItem() == stack2.getItem()
                && (stack1.getItemDamage() == stack2.getItemDamage()
                || stack1.getItemDamage() == 32767
                || stack2.getItemDamage() == 32767
                || stack1.getItem().isDamageable());
    }
}
