package missingrecipes;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(name = "Missing-Recipes", version = missingrecipes.VERSION, useMetadata = false, modid = "Missing-Recipes", dependencies = "required-after:Forge@[7.7.2.682,);after:Forestry;after:IC2")
public class missingrecipes {
	public static final String VERSION = "@MR_VERSION@";

	@Instance("Missing-Recipes")
	public static missingrecipes instance;

	@Mod.EventHandler
	public void initialize(FMLInitializationEvent evt) {

		// forestry
		ArrayList<ItemStack> apatiteOres = OreDictionary.getOres("oreApatite");
		ArrayList<ItemStack> apatiteGems = OreDictionary.getOres("gemApatite");

		if (apatiteOres.size() > 0 && apatiteGems.size() > 0) {
			ItemStack apatiteOre = apatiteOres.get(0);
			ItemStack apatiteGem = apatiteGems.get(0).copy();

			apatiteGem.stackSize = 4;

			GameRegistry.addSmelting(Block.getBlockFromItem(apatiteOre.getItem()), apatiteGem, 0.5f);
		}

		// diamonds
		// ic2
		// GameRegistry.addSmelting(Block.getBlockFromItem(apatiteOre.getItem()), apatiteGem, 0.5f);

	}
}
