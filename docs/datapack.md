# Datapack

Minecraft's Datapacks use a json format to designate and define data objects.
Fyre uses these to define advancements and custom recipes.

---

- [Datapack](#datapack)
	- [Advancements](#advancements)
	- [Recipes](#recipes)

---

## Advancements

Advancements act like achievements to guide the player through the new
progression system, in order to do this the original minecraft advancements are
disabled inside the server configuration to ensure only custom advancements are
loaded

`/spigot.yml`

```yml
advancements:
  disable-saving: false
  disabled:
  - minecraft:adventure/adventuring_time
  - minecraft:adventure/arbalistic
  - minecraft:adventure/bullseye
  - minecraft:adventure/hero_of_the_village
  - minecraft:adventure/honey_block_slide
  - minecraft:adventure/kill_all_mobs
  - minecraft:adventure/kill_a_mob
  - minecraft:adventure/ol_betsy
  - minecraft:adventure/root
  - minecraft:adventure/shoot_arrow
  - minecraft:adventure/sleep_in_bed
  - minecraft:adventure/sniper_duel
  - minecraft:adventure/summon_iron_golem
  - minecraft:adventure/throw_trident
  - minecraft:adventure/totem_of_undying
  - minecraft:adventure/trade
  - minecraft:adventure/two_birds_one_arrow
  - minecraft:adventure/very_very_frightening
  - minecraft:adventure/voluntary_exile
  - minecraft:adventure/whos_the_pillager_now
  - minecraft:end/dragon_breath
  - minecraft:end/dragon_egg
  - minecraft:end/elytra
  - minecraft:end/enter_end_gateway
  - minecraft:end/find_end_city
  - minecraft:end/kill_dragon
  - minecraft:end/levitate
  - minecraft:end/respawn_dragon
  - minecraft:end/root
  - minecraft:husbandry/balanced_diet
  - minecraft:husbandry/bred_all_animals
  - minecraft:husbandry/breed_an_animal
  - minecraft:husbandry/break_diamond_hoe
  - minecraft:husbandry/complete_catalogue
  - minecraft:husbandry/fishy_business
  - minecraft:husbandry/obtain_netherite_hoe
  - minecraft:husbandry/plant_seed
  - minecraft:husbandry/root
  - minecraft:husbandry/safely_harvest_honey
  - minecraft:husbandry/silk_touch_nest
  - minecraft:husbandry/tactical_fishing
  - minecraft:husbandry/tame_an_animal
  - minecraft:nether/all_effects
  - minecraft:nether/all_potions
  - minecraft:nether/brew_potion
  - minecraft:nether/charge_respawn_anchor
  - minecraft:nether/create_beacon
  - minecraft:nether/create_full_beacon
  - minecraft:nether/distract_piglin
  - minecraft:nether/explore_nether
  - minecraft:nether/fast_travel
  - minecraft:nether/find_bastion
  - minecraft:nether/find_fortress
  - minecraft:nether/get_wither_skull
  - minecraft:nether/loot_bastion
  - minecraft:nether/netherite_armor
  - minecraft:nether/obtain_ancient_debris
  - minecraft:nether/obtain_blaze_rod
  - minecraft:nether/obtain_crying_obsidian
  - minecraft:nether/return_to_sender
  - minecraft:nether/root
  - minecraft:nether/summon_wither
  - minecraft:nether/uneasy_alliance
  - minecraft:nether/use_lodestone
  - minecraft:story/cure_zombie_villager
  - minecraft:story/deflect_arrow
  - minecraft:story/enchant_item
  - minecraft:story/enter_the_end
  - minecraft:story/enter_the_nether
  - minecraft:story/follow_ender_eye
  - minecraft:story/form_obsidian
  - minecraft:story/iron_tools
  - minecraft:story/lava_bucket
  - minecraft:story/mine_diamond
  - minecraft:story/mine_stone
  - minecraft:story/obtain_armor
  - minecraft:story/root
  - minecraft:story/shiny_gear
  - minecraft:story/smelt_iron
  - minecraft:story/upgrade_tools
```

With the original advancements stopped, we then [create custom
advancements](https://github.com/the-dwellers/Fyre/tree/master/datapack/Fyre/data/fyre/advancements).
They follow the [advancement json
format](https://minecraft.gamepedia.com/Advancements#JSON_Format)

## Recipes

Due to the nature of Fyre's progression system, by default players cannot craft
all items, unlike in the original minecraft game. This is enforced by using [the
gamerule to disable crafting of unknown
recipes](https://minecraft.gamepedia.com/Commands/gamerule)
*(doLimitedCrafting)*

Even though this gamerule is set, players normally unlock recipes as they
perform actions throughout minecraft, such as entering water causing them to
learn the boat recipe.

To disable this functionality, we use [dummy files to overwrite the vanilla
datapack](https://github.com/the-dwellers/Fyre/tree/master/datapack/Fyre/data/minecraft/advancements/recipes)
and disable these *hidden* advancements that causes the player to be rewarded
with the recipes.

In the end, the result is that the player cannot craft recipes they've not
unlocked, and they can only be unlocked by progressing through Fyre.
