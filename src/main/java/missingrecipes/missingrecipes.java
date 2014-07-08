package missingrecipes;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(name = "Missing-Recipes", version = missingrecipes.VERSION, useMetadata = false, modid = "Missing-Recipes", dependencies = "required-after:Forge@[7.7.2.682,);after:Forestry")
public class missingrecipes {
	public static final String VERSION = "@MR_VERSION@";

	@Instance("Missing-Recipes")
	public static missingrecipes instance;

	@Init
	public void initialize(FMLInitializationEvent evt) {

		ArrayList<ItemStack> apatiteOres = OreDictionary.getOres("oreApatite");
		ArrayList<ItemStack> apatiteGems = OreDictionary.getOres("gemApatite");

		if (apatiteOres.size() > 0 && apatiteGems.size() > 0) {
			ItemStack apatiteOre = apatiteOres.get(0);
			ItemStack apatiteGem = apatiteGems.get(0).copy();

			apatiteGem.stackSize = 4;

			FurnaceRecipes.smelting().addSmelting(apatiteOre.getItem().itemID, apatiteOre.getItemDamage(), apatiteGem, 0.5f);
		}
	}
}
