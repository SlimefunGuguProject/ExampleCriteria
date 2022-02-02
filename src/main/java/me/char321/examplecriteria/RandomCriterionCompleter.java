package me.char321.examplecriteria;

import me.char321.sfadvancements.SFAdvancements;
import me.char321.sfadvancements.api.criteria.Criterion;
import me.char321.sfadvancements.core.criteria.completer.CriterionCompleter;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class RandomCriterionCompleter implements CriterionCompleter, Listener {
    private final Set<RandomCriterion> criteria = new HashSet<>();

    /**
     * The event handler that performs the criteria.
     */
    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        for (RandomCriterion criterion : criteria) {
            if (ThreadLocalRandom.current().nextDouble() < criterion.getChance()) {
                criterion.perform(e.getEntity());
            }
        }
    }

    /**
     * Override this method to store the incoming criterion.
     * This method is called whenever a criterion of type getCriterionClass() is registered. (on SFA startup)
     * This is assuming you call CriterionCompleter#register during server startup/before the advancements are loaded.
     *
     * @param criterion a criterion being registered
     */
    @Override
    public void register(Criterion criterion) {
        criteria.add((RandomCriterion) criterion);
    }

    /**
     * this should probably have a different name from the criteria register
     * This is where you can register your listeners or other method of detection.
     */
    @Override
    public void register() {
        CriterionCompleter.super.register();
        Bukkit.getPluginManager().registerEvents(this, ExampleCriteria.getInstance());
    }

    /**
     * This should return the criterion class that this advancement completes.
     * It determines what type of criteria will be passed into register(Criterion).
     * criterioncompleter should probably be generic but for now this is how it is
     *
     * @return the class of the criterion that this completes
     */
    @Override
    public Class<? extends Criterion> getCriterionClass() {
        return RandomCriterion.class;
    }
}
