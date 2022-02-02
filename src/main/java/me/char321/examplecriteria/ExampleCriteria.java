package me.char321.examplecriteria;

import me.char321.sfadvancements.SFAdvancements;
import me.char321.sfadvancements.api.criteria.CriteriaTypes;
import org.bukkit.plugin.java.JavaPlugin;

public final class ExampleCriteria extends JavaPlugin {
    private static ExampleCriteria instance;
    @Override
    public void onEnable() {
        instance = this;

        // make sure to call this for your completers during server startup
        new RandomCriterionCompleter().register();

        /*
          also this if you want your criteria to be configurable
          String type is the criteria.type in the config
          (config.getString("type") should return type)

          the second parameter is a function from ConfigurationSection to Criterion, so you can customize
          how you deserialize configured criteria
         */
        CriteriaTypes.putType("random", config -> {
            String id = config.getName();

            // optional
            int amount = config.getInt("amount");
            if (amount == 0) { // if no amount is specified
                amount = 1;
            }

            // this is boilerplate, should probably be somewhere else
            String name = config.getString("name");
            if(name == null) {
                name = id;
            }

            // this is where you can add custom configurability
            double chance = config.getDouble("chance");
            // if you want to get itemstacks, you can use ConfigUtils.getItem(config, path)

            // return a criterion from the config
            return new RandomCriterion(id, amount, name, chance);
        });
    }

    @Override
    public void onDisable() {

    }

    public static ExampleCriteria getInstance() {
        return instance;
    }
}
