package vazkii.botania.common.achievement;

import btw.achievement.AchievementProvider;
import btw.achievement.event.AchievementEventDispatcher;
import net.minecraft.src.*;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import vazkii.botania.common.Botania;

import java.util.function.Predicate;

import static vazkii.botania.common.achievement.ModAchievements.BOTANIA_PAGE;

public abstract class BotaniaAchievementProvider<T>{
    public static <T> AchievementBuilder<T> getBuilder(Class<? extends AchievementEventDispatcher.AchievementEvent<T>> builderClass) {
        return new AchievementBuilder<>(builderClass);
    }

    public static class AchievementBuilder<T>
            implements AchievementProvider.NameStep<T>,
            AchievementProvider.IconStep<T>,
            AchievementProvider.LocationStep<T>,
            AchievementProvider.ConditionStep<T>,
            AchievementProvider.BuildStep<T> {
        private Class<? extends AchievementEventDispatcher.AchievementEvent<T>> builderClass;
        private ResourceLocation name;
        private ItemStack iconStack;
        private Pair<Integer, Integer> location;
        private Predicate<T> predicate;
        private Achievement<?>[] parents = new Achievement[0];

        public AchievementBuilder(Class<? extends AchievementEventDispatcher.AchievementEvent<T>> builderClass) {
            this.builderClass = builderClass;
        }

        @Override
        public AchievementProvider.IconStep<T> name(ResourceLocation name) {
            this.name = name;
            return this;
        }

        @Override
        public AchievementProvider.LocationStep<T> icon(Item item) {
            return this.icon(new ItemStack(item));
        }

        @Override
        public AchievementProvider.LocationStep<T> icon(Block block) {
            return this.icon(new ItemStack(block));
        }

        @Override
        public AchievementProvider.LocationStep<T> icon(ItemStack stack) {
            this.iconStack = stack;
            return this;
        }

        @Override
        public AchievementProvider.ConditionStep<T> displayLocation(int x, int y) {
            this.location = new ImmutablePair<Integer, Integer>(x, y);
            return this;
        }

        @Override
        public AchievementProvider.BuildStep<T> triggerCondition(Predicate<T> predicate) {
            this.predicate = predicate;
            return this;
        }

        @Override
        public AchievementProvider.BuildStep<T> alwaysTrigger() {
            this.predicate = d -> true;
            return this;
        }

        @Override
        public AchievementProvider.BuildStep<T> parents(Achievement ... parents) {
            this.parents = parents;
            return this;
        }

        @Override
        public Achievement<T> build() {
            return new Achievement<T>(this.name, this.iconStack, this.location.getLeft(), this.location.getRight(), this.builderClass, this.predicate, this.parents).registerAchievement(BOTANIA_PAGE);
        }

        public AchievementProvider.IconStep<T> name(String name) {
            return name(Botania.loc(name));
        }
    }
}
