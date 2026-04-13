package vazkii.botania.client.model;

import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class CustomModelRenderer extends ModelRenderer {
    public int lensType = -1;
    public CustomModelRenderer(ModelBase par1ModelBase, String par2Str) {
        super(par1ModelBase, par2Str);
    }

    public CustomModelRenderer(ModelBase par1ModelBase) {
        super(par1ModelBase);
    }

    public CustomModelRenderer(ModelBase par1ModelBase, int par2, int par3) {
        super(par1ModelBase, par2, par3);
    }
}
