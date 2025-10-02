package baubles.common;

import baubles.api.BaubleType;
import baubles.api.expanded.BaubleExpandedSlots;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.List;

public class ItemDebugger extends BaubleItemBase {

    private Icon[] icons;

	public ItemDebugger(int id) {
		super(id);
		this.setHasSubtypes(true);
        if (BaublesConfig.hideDebugItem){
		    setCreativeTab(null);
        }
	}

	@Environment(EnvType.CLIENT)
	@Override
	public void registerIcons(IconRegister ir) {
		icons = new Icon[BaubleExpandedSlots.getCurrentlyRegisteredTypes().size()];
		for(int i = 0; i < icons.length; i++) {
		  icons[i] = ir.registerIcon("baubles:empty_bauble_slot_" + BaubleExpandedSlots.getCurrentlyRegisteredTypes().get(i));
		}
	}

    @Override
    public Icon getIconFromDamage(int meta) {
        return icons[meta >= icons.length ? 0 : meta];
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public void getSubItems(int item, CreativeTabs tab, List list) {
		for(int i = 0; i < icons.length; i++) {
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public String[] getBaubleTypes(ItemStack itemStack) {
	  String type;
	  int meta = itemStack.getItemDamage();
	  if(meta <= 0 || meta > icons.length) {
		 type = BaubleExpandedSlots.unknownType;
	  } else {
		 type = BaubleExpandedSlots.getCurrentlyRegisteredTypes().get(meta);
	  }
	  return new String[] {type};
	}

	@Override
	public BaubleType getBaubleType(ItemStack itemStack) {
	  return null;
	}

	public Icon getBackgroundIconForSlotType(String type) {
		if(type != null && BaubleExpandedSlots.isTypeRegistered(type)) {
			return icons[BaubleExpandedSlots.getIndexOfTypeInRegisteredTypes(type)];
		} else {
			return icons[0];
		}
	}

}
