package vazkii.botania.common.achievement;

import btw.achievement.AchievementProvider;
import btw.achievement.event.AchievementEventDispatcher;
import net.minecraft.src.Achievement;
import net.minecraft.src.ItemStack;
import vazkii.botania.common.Botania;

import java.util.function.Predicate;

import static vazkii.botania.common.achievement.ModAchievements.BOTANIA_PAGE;

public abstract class BotaniaAchievementProvider<T>{
    public static <T> Builder<T> getBuilder(Class<? extends AchievementEventDispatcher.AchievementEvent<T>> builderClass) {
        return new Builder<>(builderClass);
    }

    public static class Builder<T> extends AchievementProvider.AchievementBuilder<T> {
        private Builder(Class<? extends AchievementEventDispatcher.AchievementEvent<T>> builderClass) {
            super(builderClass);
        }

        public AchievementProvider.IconStep<T> name(String name) {
            return super.name(Botania.loc(name));
        }

        @Override
        public Achievement<T> build() {
            return super.build().registerAchievement(BOTANIA_PAGE);
        }
    }
}
