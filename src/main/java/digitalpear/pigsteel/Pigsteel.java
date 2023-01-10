package digitalpear.pigsteel;

import net.fabricmc.api.ModInitializer;
import net.minecraft.src.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.BlockHelper;
import turniplabs.halplibe.helper.RecipeHelper;
import turniplabs.halplibe.helper.TextureHelper;

import java.util.Random;


public class Pigsteel implements ModInitializer {
    public static final String MOD_ID = "pigsteel";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    static int baseID = 900;

    public static String name(String name) {
        return Pigsteel.MOD_ID + "." + name;
    }
    public static boolean probability(Random rand, double percent) {
        return percent > 0 && rand.nextInt(100) <= percent;
    }

    public static final Block pigsteelSlag = BlockHelper.createBlock(
            new BlockPigsteelSlag(baseID + 1),
            name("slag.pigsteel"),
            31, 0, 31, 1,
            Block.soundStoneFootstep,
            3f,
            4f,
            0);

    public static final Block pigsteelPlates = BlockHelper.createBlock(
            new BlockPigsteelSlag(baseID + 2),
            name("plates.pigsteel"),
            31, 2,
            Block.soundMetalFootstep,
            Block.blockIron.getHardness() + 2,
            12.0F,
            0);

    public static final Item pigsteelChunk = new Item(baseID + 3)
            .setIconCoord(0, 14)
            .setItemName(name("chunk.pigsteel"));


    //Values of pigsteel generating
    public static final WorldGenerator pigsteelOreGen = new WorldGenMinable(pigsteelSlag.blockID, 5, false);

    

    @Override
    public void onInitialize() {
        TextureHelper.addTextureToItems(MOD_ID, "pigsteelChunk.png", 0, 14);
        TextureHelper.addTextureToTerrain(MOD_ID, "pigsteelSlagEnd.png", 31, 0);
        TextureHelper.addTextureToTerrain(MOD_ID, "pigsteelSlagSide.png", 31, 1);
        TextureHelper.addTextureToTerrain(MOD_ID, "pigsteelPlates.png", 31, 2);


        RecipeHelper.smeltingManager.addSmelting(Pigsteel.pigsteelChunk.itemID, new ItemStack(Item.ingotIron));
        RecipeHelper.smeltingManager.addSmelting(pigsteelSlag.blockID, new ItemStack(Item.ingotIron));
        RecipeHelper.blastingManager.addSmelting(pigsteelSlag.blockID, new ItemStack(Block.blockIron));

        RecipeHelper.Crafting.createRecipe(Pigsteel.pigsteelPlates, 1, new Object[]{"AA", "AA", 'A', Pigsteel.pigsteelChunk});
        RecipeHelper.Crafting.createShapelessRecipe(Pigsteel.pigsteelChunk, 4, new Object[]{new ItemStack(Pigsteel.pigsteelPlates, 1)});

        LOGGER.info(MOD_ID + " initialized.");
    }
}
