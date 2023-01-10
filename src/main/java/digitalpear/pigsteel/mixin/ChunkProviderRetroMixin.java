package digitalpear.pigsteel.mixin;

import digitalpear.pigsteel.Pigsteel;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(value = ChunkProviderHell.class, remap = false)
public class ChunkProviderRetroMixin {

    @Shadow
    private Random hellRNG;

    @Shadow
    private NoiseGeneratorOctaves field_4169_i;

    @Shadow
    private World worldObj;

    @Inject(method = "populate", at = @At(value = "RETURN"))
    private void populate(IChunkProvider ichunkprovider, int chunkX, int chunkZ, CallbackInfo ci) {
        // Chunk positions in blocks, at the corner of the chunk
        int preX = chunkX * 16;
        int preZ = chunkZ * 16;

        // Noise
        int magicValue = (int)((field_4169_i.func_806_a((double)preX / 2, (double)preZ / 2) / 8.0 + hellRNG.nextDouble() * 4.0 + 4.0) / 3.0);
        if (magicValue < 0) {
            magicValue = 0;
        }

        for (int i = 0; i < magicValue; ++i) {

            // 30% chance for the ore to generate
            if (!Pigsteel.probability(hellRNG, 1)) {
                continue;
            }

            WorldGenerator ore = Pigsteel.pigsteelOreGen;


            int x = preX + hellRNG.nextInt(16) + 8;
            int y = hellRNG.nextInt(256);
            int z = preZ + hellRNG.nextInt(16) + 8;
            ore.func_517_a(1.0, 1.0, 1.0);
            ore.generate(worldObj, hellRNG, x, y, z);
        }
    }
}