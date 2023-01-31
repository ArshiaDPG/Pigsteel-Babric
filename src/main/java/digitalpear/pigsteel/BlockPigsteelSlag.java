package digitalpear.pigsteel;

import net.minecraft.src.Block;
import net.minecraft.src.Material;

import java.util.Random;

public class BlockPigsteelSlag extends Block {

    public BlockPigsteelSlag(int i) {
        super(i, Material.rock);
    }

    public int idDropped(int i, Random random) {
        return Pigsteel.pigsteelChunk.itemID;
    }

    protected int damageDropped(int i) {
        return 4;
    }

    public int quantityDropped(int metadata, Random random) {
        return 1 + random.nextInt(2);
    }
}
