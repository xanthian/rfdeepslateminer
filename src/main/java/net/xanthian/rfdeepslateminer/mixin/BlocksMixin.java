package net.xanthian.rfdeepslateminer.mixin;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

import org.spongepowered.asm.mixin.Debug;
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
					target = "Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;strength(FF)Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;",
					ordinal = 0))

	private static BlockBehaviour.Properties rfdsm$redirectRequiresTool(BlockBehaviour.Properties instance, float pDestroyTime, float pExplosionResistance) {
		return instance
				.mapColor(MapColor.DEEPSLATE)
				.instrument(NoteBlockInstrument.BASEDRUM)
				.sound(SoundType.DEEPSLATE)
				.requiresCorrectToolForDrops()
				.strength(50.0f, 1200.0f);

	}
}