package me.char321.examplecriteria;

import me.char321.sfadvancements.api.criteria.Criterion;

public class RandomCriterion extends Criterion {
    private final double chance;

    public RandomCriterion(String id, int count, String name, double chance) {
        super(id, count, name);
        this.chance = chance;
    }

    public double getChance() {
        return chance;
    }
}
