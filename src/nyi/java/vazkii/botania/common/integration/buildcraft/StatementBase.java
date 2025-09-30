package vazkii.botania.common.integration.buildcraft;

import net.minecraft.src.Icon;
import buildcraft.api.statements.IStatement;
import buildcraft.api.statements.IStatementParameter;

public abstract class StatementBase implements IStatement {
	protected Icon icon;

	@Override
	public Icon getIcon() {
		return icon;
	}

	@Override
	public int maxParameters() {
		return 0;
	}

	@Override
	public int minParameters() {
		return 0;
	}

	@Override
	public IStatementParameter createParameter(int index) {
		return null;
	}

	@Override
	public IStatement rotateLeft() {
		return this;
	}
}
