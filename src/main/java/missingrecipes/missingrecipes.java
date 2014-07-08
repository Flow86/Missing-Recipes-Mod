package missingrecipes;

import ic2.api.recipe.RecipeInputOreDict;
import ic2.api.recipe.Recipes;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Optional.Method;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(name = "Missing-Recipes", version = missingrecipes.VERSION, useMetadata = false, modid = "Missing-Recipes", dependencies = "required-after:Forge@[7.7.2.682,);after:Forestry;after:IC2")
public class missingrecipes {
	public static final String VERSION = "@MR_VERSION@";

	@Instance("Missing-Recipes")
	public static missingrecipes instance;

	/**
	 * Initialze and add all missing recipes.
	 * 
	 * @param evt
	 */
	@Mod.EventHandler
	public void initialize(FMLInitializationEvent evt) {
		if (Loader.isModLoaded("Forestry"))
			addRecipesForestry();
		if (Loader.isModLoaded("IC2"))
			addRecipesIC2();
	}

	/**
	 * Forestry Missing Recipes
	 */
	@Method(modid = "Forestry")
	public void addRecipesForestry() {
		ArrayList<ItemStack> apatiteOres = OreDictionary.getOres("oreApatite");
		ArrayList<ItemStack> apatiteGems = OreDictionary.getOres("gemApatite");

		if (apatiteOres.size() > 0 && apatiteGems.size() > 0) {
			ItemStack apatiteOre = apatiteOres.get(0);
			ItemStack apatiteGem = apatiteGems.get(0).copy();

			apatiteGem.stackSize = 4;

			GameRegistry.addSmelting(Block.getBlockFromItem(apatiteOre.getItem()), apatiteGem, 0.5f);
		}
	}

	/**
	 * IC2 Missing Recipes
	 */
	@Method(modid = "IC2")
	public void addRecipesIC2() {
		// diamonds
		Recipes.macerator.addRecipe(new RecipeInputOreDict("oreDiamond", 1), null, new ItemStack(Items.diamond, 2));

		// coal ore -> 2
		// lapis ore -> 8
		// emerald -> 2
		// redstone -> 8
		// Sulfur -> 6
		// saltpeter -> 4
	}
}
