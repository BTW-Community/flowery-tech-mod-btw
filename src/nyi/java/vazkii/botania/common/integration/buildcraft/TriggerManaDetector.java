package vazkii.botania.common.integration.buildcraft;

import net.minecraft.src.IconRegister;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.StatCollector;
import net.minecraft.src.World;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.Botania;
import vazkii.botania.common.lib.LibTriggerNames;
import buildcraft.api.statements.IStatementContainer;
import buildcraft.api.statements.IStatementParameter;
import buildcraft.api.statements.ITriggerInternal;

public class TriggerManaDetector extends StatementBase implements ITriggerInternal {

	@Override
	public String getUniqueTag() {
		return "botania:manaDetector";
	}

	@Override
	public void registerIcons(IconRegister iconRegister) {
		icon = IconHelper.forName(iconRegister, "triggers/manaDetector");
	}

	@Override
	public String getDescription() {
		return StatCollector.translateToLocal(LibTriggerNames.TRIGGER_MANA_DETECTOR);
	}

	@Override
	public boolean isTriggerActive(IStatementContainer source, IStatementParameter[] parameters) {
		World world = source.getTile().getWorldObj();
		int x = source.getTile().xCoord, y = source.getTile().yCoord, z = source.getTile().zCoord;

		boolean output = world.getEntitiesWithinAABB(IManaBurst.class, AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1, z + 1)).size() != 0;

		if(output) for(int i = 0; i < 4; i++)
			Botania.proxy.sparkleFX(world, x + Math.random(), y + Math.random(), z + Math.random(), 1F, 0.2F, 0.2F, 0.7F + 0.5F * (float) Math.random(), 5);

		return output;
	}

}
