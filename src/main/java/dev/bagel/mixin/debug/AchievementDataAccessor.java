package dev.bagel.mixin.debug;

import btw.achievement.AchievementData;
import net.minecraft.src.Achievement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(value = AchievementData.class, remap = false)
public interface AchievementDataAccessor {
    @Accessor
    public Map<Achievement, Boolean> getAchievements();
}
