package mypackage.dimension;

import mypackage.MyMod;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class EmeraldWorld extends MyWorldBase {

	@Override
	protected int customID() {
		// TODO Auto-generated method stub
		return MyMod.emeraldWorldID;
	}

	@Override
	protected BiomeGenBase[] customBiomes() {
		// TODO Auto-generated method stub
		BiomeGenBase[]biomes = {
			BiomeGenBase.desert,
			BiomeGenBase.coldBeach,
			BiomeGenBase.icePlains,
			BiomeGenBase.forestHills,
			BiomeGenBase.extremeHills,
			BiomeGenBase.jungleEdge
		};
		return biomes;
	}

	@Override
	protected String customSkyColor() {
		// TODO Auto-generated method stub
		return "#60fdff";
	}

	@Override
	protected String customCloudColor() {
		// TODO Auto-generated method stub
		return "#ffffff";
	}

	@Override
	protected String customFogColor() {
		// TODO Auto-generated method stub
		return "#e0505d";
	}

	@Override
	protected String customSunriseSunsetColor() {
		// TODO Auto-generated method stub
		return "#ddd758";
	}

	@Override
	protected float customDaySpeed() {
		// TODO Auto-generated method stub
		return 0.5F;
	}

	@Override
	protected int customBiomeSize() {
		// TODO Auto-generated method stub
		return 15;
	}

	@Override
	protected Block getBaseBlock() {
		// TODO Auto-generated method stub
		return Blocks.emerald_block;
	}

}
