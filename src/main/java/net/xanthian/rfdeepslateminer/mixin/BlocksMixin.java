package net.xanthian.rfdeepslateminer.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(Blocks.class)
public class BlocksMixin {

	/**
	 * @author Xanthian
	 **/

	@Redirect(
			method = "<clinit>",
			slice = @Slice(
					from = @At(
							value = "CONSTANT",
							args = "stringValue=reinforced_deepslate")),
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/block/AbstractBlock$Settings;strength(FF)Lnet/minecraft/block/AbstractBlock$Settings;",
					ordinal = 0))

	private static AbstractBlock.Settings rfdsm$redirectRequiresTool(AbstractBlock.Settings settings, float hardness, float resistance) {
		return AbstractBlock.Settings.of(Material.STONE, MapColor.DEEPSLATE_GRAY)
				.sounds(BlockSoundGroup.DEEPSLATE)
				.requiresTool()
				.strength(50.0f, 1200.0f);
	}
}