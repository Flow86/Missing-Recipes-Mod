/** 
 * Copyright (C) 2011-2014 Flow86
 * 
 * Missing-Recipes is open-source.
 *
 * It is distributed under the terms of my Open Source License. 
 * It grants rights to read, modify, compile or run the code. 
 * It does *NOT* grant the right to redistribute this software or its 
 * modifications in any form, binary or source, except if expressively
 * granted by the copyright holder.
 */

package missingrecipes;

import ic2.api.recipe.RecipeInputOreDict;
import ic2.api.recipe.Recipes;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Optional.Method;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(name = "Missing-Recipes", version = missingrecipes.VERSION, useMetadata = false, modid = "Missing-Recipes", dependencies = "required-after:Forge@[10.12.2.1147,);after:Forestry@[2.4.1.27,);after:IC2@[2.1.477,)", acceptableRemoteVersions = "*")
public class missingrecipes {
	public static final String VERSION = "$version";

	public static Logger logger = null;

	@Instance("Missing-Recipes")
	public static missingrecipes instance;

	/**
	 * Initialize logger.
	 * 
	 * @param evt
	 */
	@Mod.EventHandler
	public void prepInitialize(FMLPreInitializationEvent evt) {
		logger = evt.getModLog();
	}

	/**
	 * Initialize and add all missing recipes.
	 * 
	 * @param evt
	 */
	@Mod.EventHandler
	public void postInitialize(FMLPostInitializationEvent evt) {
		logger.info("Initializing Missing-Recipes " + VERSION);

		logger.info("Now adding general missing recipes...");
		addRecipesGeneral();

		if (Loader.isModLoaded("Forestry")) {
			logger.info("Found Forestry, so now adding specific missing recipes...");
			addRecipesForestry();
		}
		if (Loader.isModLoaded("IC2")) {
			logger.info("Found IC2, so now adding specific missing recipes...");
			addRecipesIC2();
		}
	}

	/**
	 * gets the first entry from the ore dictionary
	 * 
	 * @param ore
	 *            the ore to search for
	 * 
	 * @return null if not found, dictionary entry otherwise
	 */
	ItemStack getFromOreDictionary(String ore) {
		ArrayList<ItemStack> ores = OreDictionary.getOres(ore);
		if (ores.size() > 0)
			return ores.get(0);

		logger.warn("Unable to find ore with name \"" + ore + "\" in ore dictionary.");
		return null;
	}

	/**
	 * Generally Missing Recipes
	 */
	public void addRecipesGeneral() {
		// apatite ore -> 4
		ItemStack apatiteOre = getFromOreDictionary("oreApatite");
		ItemStack apatiteGem = getFromOreDictionary("gemApatite");

		if (apatiteOre != null && apatiteGem != null) {
			logger.info("Adding Furnace-Recipe: apatiteOre,1 -> apatiteGem,4");
			GameRegistry.addSmelting(Block.getBlockFromItem(apatiteOre.getItem()), new ItemStack(apatiteGem.getItem(), 4), 0.5f);
		}
	}

	/**
	 * Forestry Missing Recipes
	 */
	@Method(modid = "Forestry")
	public void addRecipesForestry() {
	}

	/**
	 * IC2 Missing Recipes
	 */
	@Method(modid = "IC2")
	public void addRecipesIC2() {

		// split dense ores in normal ores
		if (Loader.isModLoaded("denseores")) {
			for (String denseOreName : OreDictionary.getOreNames()) {
				if (denseOreName.startsWith("denseore")) {
					String normalOreName = denseOreName.substring(5);
					ItemStack normalOre = getFromOreDictionary(normalOreName);
					logger.info("Adding Macerator-Recipe: " + denseOreName + ",1 -> " + normalOre + ",4");
					Recipes.macerator.addRecipe(new RecipeInputOreDict(denseOreName, 1), null, new ItemStack(normalOre.getItem(), 4));
				}
			}
		}

		// nether quartz ore -> 4
		ItemStack quartzGem = getFromOreDictionary("gemQuartz");
		if (quartzGem != null) {
			logger.info("Adding Macerator-Recipe: quartzOre,1 -> quartzGem,4");
			Recipes.macerator.addRecipe(new RecipeInputOreDict("oreQuartz", 1), null, new ItemStack(quartzGem.getItem(), 4));
		}

		// apatite ore -> 6
		ItemStack apatiteGem = getFromOreDictionary("gemApatite");
		if (apatiteGem != null) {
			logger.info("Adding Macerator-Recipe: apatiteOre,1 -> apatiteGem,6");
			Recipes.macerator.addRecipe(new RecipeInputOreDict("oreApatite", 1), null, new ItemStack(apatiteGem.getItem(), 6));
		}

		// diamond ore -> 2 diamond
		ItemStack diamondGem = getFromOreDictionary("gemDiamond");
		if (diamondGem != null) {
			logger.info("Adding Macerator-Recipe: diamondOre,1 -> diamondGem,2");
			Recipes.macerator.addRecipe(new RecipeInputOreDict("oreDiamond", 1), null, new ItemStack(diamondGem.getItem(), 2));
		}

		// coal ore -> 2
		logger.info("Adding Macerator-Recipe: coalOre,1 -> coal,2");
		Recipes.macerator.addRecipe(new RecipeInputOreDict("oreCoal", 1), null, new ItemStack(Items.coal, 2));

		// lapis ore -> 8
		ItemStack lapisGem = getFromOreDictionary("gemLapis");
		if (lapisGem != null) {
			logger.info("Adding Macerator-Recipe: lapisOre,1 -> lapisGem,8");
			Recipes.macerator.addRecipe(new RecipeInputOreDict("oreLapis", 1), null, new ItemStack(lapisGem.getItem(), 8));
		}

		// emerald -> 2
		ItemStack emeraldGem = getFromOreDictionary("gemEmerald");
		if (emeraldGem != null) {
			logger.info("Adding Macerator-Recipe: emeraldOre,1 -> emeraldGem,2");
			Recipes.macerator.addRecipe(new RecipeInputOreDict("oreEmerald", 1), null, new ItemStack(emeraldGem.getItem(), 2));
		}

		// redstone -> 8
		ItemStack redstoneDust = getFromOreDictionary("dustRedstone");
		if (redstoneDust != null) {
			logger.info("Adding Macerator-Recipe: redstoneOre,1 -> redstoneDust,8");
			Recipes.macerator.addRecipe(new RecipeInputOreDict("oreRedstone", 1), null, new ItemStack(redstoneDust.getItem(), 8));
		}

		// sulfur -> 6
		ItemStack sulfurDust = getFromOreDictionary("dustSulfur");
		if (sulfurDust != null) {
			logger.info("Adding Macerator-Recipe: sulfurOre,1 -> sulfurDust,6");
			Recipes.macerator.addRecipe(new RecipeInputOreDict("oreSulfur", 1), null, new ItemStack(sulfurDust.getItem(), 6));
		}

		// saltpeter -> 4
		ItemStack saltpeterDust = getFromOreDictionary("dustSaltpeter");
		if (saltpeterDust != null) {
			logger.info("Adding Macerator-Recipe: saltpeterOre,1 -> saltpeterDust,4");
			Recipes.macerator.addRecipe(new RecipeInputOreDict("oreSaltpeter", 1), null, new ItemStack(saltpeterDust.getItem(), 4));
		}
	}
}
