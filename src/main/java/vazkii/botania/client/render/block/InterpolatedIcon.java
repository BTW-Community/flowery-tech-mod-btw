/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Aug 15, 2015, 5:11:16 PM (GMT)]
 */
package vazkii.botania.client.render.block;

import java.lang.reflect.Field;

import net.minecraft.src.TextureAtlasSprite;
import net.minecraft.src.TextureUtil;
import net.minecraft.src.AnimationMetadataSection;
import vazkii.botania.common.lib.LibObfuscation;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

// This is all vanilla code from 1.8, thanks to ganymedes01 porting it to 1.7 :D
@Environment(EnvType.CLIENT)
public class InterpolatedIcon extends TextureAtlasSprite {

	protected int[][] interpolatedFrameData;

	public InterpolatedIcon(String name) {
		super(name);
	}

	@Override
	public void updateAnimation() {
		super.updateAnimation();
		try {
			updateAnimationInterpolated();
		} catch(Exception e) {
			// NO-OP
		}
	}

	private void updateAnimationInterpolated() throws IllegalArgumentException, IllegalAccessException {
		AnimationMetadataSection animationMetadata = this.getAnimationMetadata();

		double d0 = 1.0D - tickCounter / (double) animationMetadata.getFrameTimeSingle(frameCounter);
		int i = animationMetadata.getFrameIndex(frameCounter);
		int j = animationMetadata.getFrameCount() == 0 ? framesTextureData.size() : animationMetadata.getFrameCount();
		int k = animationMetadata.getFrameIndex((frameCounter + 1) % j);

		if(i != k && k >= 0 && k < framesTextureData.size()) {
			int[][] aint = (int[][]) framesTextureData.get(i);
			int[][] aint1 = (int[][]) framesTextureData.get(k);

			if(interpolatedFrameData == null || interpolatedFrameData.length != aint.length)
				interpolatedFrameData = new int[aint.length][];

			for(int l = 0; l < aint.length; l++) {
				if (interpolatedFrameData[l] == null)
					interpolatedFrameData[l] = new int[aint[l].length];

				if(l < aint1.length && aint1[l].length == aint[l].length)
					for (int i1 = 0; i1 < aint[l].length; ++i1) {
						int j1 = aint[l][i1];
						int k1 = aint1[l][i1];
						int l1 = (int) (((j1 & 16711680) >> 16) * d0 + ((k1 & 16711680) >> 16) * (1.0D - d0));
						int i2 = (int) (((j1 & 65280) >> 8) * d0 + ((k1 & 65280) >> 8) * (1.0D - d0));
						int j2 = (int) ((j1 & 255) * d0 + (k1 & 255) * (1.0D - d0));
						interpolatedFrameData[l][i1] = j1 & -16777216 | l1 << 16 | i2 << 8 | j2;
					}
			}

			uploadTextureMipmap(interpolatedFrameData, width, height, originX, originY);
		}
	}
	//todofix all of this
	public static void uploadTextureMipmap(int[][] p_147955_0_, int p_147955_1_, int p_147955_2_, int p_147955_3_, int p_147955_4_)
	{
		for (int i1 = 0; i1 < p_147955_0_.length; ++i1)
		{
			int[] aint1 = p_147955_0_[i1];
			TextureUtil.uploadTextureSub(aint1, i1, p_147955_1_ >> i1, p_147955_2_ >> i1, p_147955_3_ >> i1, false/* ????? p_147955_4_ >> i1*/, p_147955_0_.length > 1);
		}
	}
}