package mypackage.dimension;

import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.COAL;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.CUSTOM;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.DIAMOND;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.DIRT;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.GOLD;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.GRAVEL;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.IRON;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.LAPIS;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.REDSTONE;

import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mypackage.MyMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;

public class MyBiomeBase extends BiomeGenBase {

	public Block baseBlock = Blocks.stone;
	public int grassColor = -1;
	public int foliageColor = -1;
	public float heightBase = 0.2F;
	public float heightVariation = 0.1F;
	
	public MyBiomeBase(int p_i1971_1_) {
		this(p_i1971_1_, Blocks.stone);
	}
	
	public MyBiomeBase(int i, Block base) {
		super(i);
		baseBlock = base;
		this.theBiomeDecorator = new MyBiomeDecorator(baseBlock);
		
		// COPY FROM HERE...
		
		// SETUP CODE (DO NOT EDIT!)
		MyBiomeDecorator decorator = (MyBiomeDecorator)this.theBiomeDecorator;
		this.spawnableMonsterList = new ArrayList();
        this.spawnableCreatureList = new ArrayList();

		// BASICS
		this.topBlock = Blocks.grass;
		this.fillerBlock = Blocks.dirt;
		this.heightBase = 0.0F;
		this.heightVariation = 0.0F;
		this.enableSnow = true;
		this.grassColor = 0x2FB22F;
		this.foliageColor = 0x017901;
		this.waterColorMultiplier = 0x309BF5;
		
		// DECORATIONS
		decorator.treesPerChunk = 0;
		decorator.flowersPerChunk = 0;
		decorator.grassPerChunk = 0;
		decorator.mushroomsPerChunk = 0;
		decorator.bigMushroomsPerChunk = 0;
		decorator.deadBushPerChunk = 0;
		decorator.cactiPerChunk = 0;
		decorator.reedsPerChunk = 0;
		decorator.waterlilyPerChunk = 0;
		decorator.underwaterSandPerChunk = 0;
		decorator.underwaterGravelPerChunk = 0;
		decorator.clayPerChunk = 0;
		decorator.generateLakes = true;

        // ORE SPAWNING
		decorator.addOreSpawn(Blocks.dirt, 32, 20, 0, 256, base, DIRT);
		decorator.addOreSpawn(Blocks.gravel, 32, 10, 0, 256, base, GRAVEL);
		decorator.addOreSpawn(Blocks.coal_ore, 16, 20, 0, 128, base, COAL);
		decorator.addOreSpawn(Blocks.iron_ore, 8, 20, 0, 256, base, IRON);
		decorator.addOreSpawn(Blocks.gold_ore, 8, 2, 0, 32, base, GOLD);
		decorator.addOreSpawn(Blocks.redstone_ore, 7, 8, 0, 16, base, REDSTONE);
		decorator.addOreSpawn(Blocks.diamond_ore, 7, 1, 0, 16, base, DIAMOND);
		decorator.addOreSpawn(Blocks.lapis_ore, 6, 1, 16, 16, base, LAPIS);
		decorator.addOreSpawn(MyMod.makersBlock, 32, 16, 64, 256, base, CUSTOM);

    	// CREATURE SPAWNING
		this.addCreatureSpawn(EntitySheep.class, 12, 4, 4);
		this.addCreatureSpawn(EntityPig.class, 10, 4, 4);
		this.addCreatureSpawn(EntityChicken.class, 10, 4, 4);
		this.addCreatureSpawn(EntityCow.class, 8, 4, 4);
		
		// MONSTER SPAWNING
		this.addMonsterSpawn(EntitySpider.class, 100, 4, 4);
		this.addMonsterSpawn(EntityZombie.class, 100, 4, 4);
		this.addMonsterSpawn(EntitySkeleton.class, 100, 4, 4);
		this.addMonsterSpawn(EntityCreeper.class, 100, 4, 4);
		this.addMonsterSpawn(EntitySlime.class, 100, 4, 4);
		this.addMonsterSpawn(EntityEnderman.class, 10, 1, 4);
		this.addMonsterSpawn(EntityWitch.class, 5, 1, 1);
		
		// WRAP-UP CODE (DO NOT EDIT!)
		this.setHeight(new BiomeGenBase.Height(heightBase, heightVariation));
		this.setTemperatureRainfall(this.enableSnow ? -0.5F : 0.5F, 0.5F);
		
		// ...TO HERE!
		
	}  // <-- DON'T COPY THIS CURLY BRACKET!
	
	public void genTerrainBlocks(World var1, Random var2, Block[] var3, byte[] var4, int var5, int var6, double var7) 
    {
        boolean flag = true;
        Block block = this.topBlock;
        byte b0 = (byte)(this.field_150604_aj & 255);
        Block block1 = this.fillerBlock;
        int k = -1;
        int l = (int)(var7 / 3.0D + 3.0D + var2.nextDouble() * 0.25D);
        int i1 = var5 & 15;
        int j1 = var6 & 15;
        int k1 = var3.length / 256;

        for (int l1 = 255; l1 >= 0; --l1)
        {
            int i2 = (j1 * 16 + i1) * k1 + l1;

            if (l1 <= 0 + var2.nextInt(5))
            {
                var3[i2] = Blocks.bedrock;
            }
            else
            {
                Block block2 = var3[i2];

                if (block2 != null && block2.getMaterial() != Material.air)
                {
                    if (block2 == baseBlock)
                    {
                        if (k == -1)
                        {
                            if (l <= 0)
                            {
                                block = null;
                                b0 = 0;
                                block1 = baseBlock;
                            }
                            else if (l1 >= 59 && l1 <= 64)
                            {
                                block = this.topBlock;
                                b0 = (byte)(this.field_150604_aj & 255);
                                block1 = this.fillerBlock;
                            }

                            if (l1 < 63 && (block == null || block.getMaterial() == Material.air))
                            {
                                if (this.getFloatTemperature(var5, l1, var6) < 0.15F)
                                {
                                    block = Blocks.ice;
                                    b0 = 0;
                                }
                                else
                                {
                                    block = Blocks.water;
                                    b0 = 0;
                                }
                            }

                            k = l;

                            if (l1 >= 62)
                            {
                                var3[i2] = block;
                                var4[i2] = b0;
                            }
                            else if (l1 < 56 - l)
                            {
                                block = null;
                                block1 = baseBlock;
                                var3[i2] = Blocks.gravel;
                            }
                            else
                            {
                                var3[i2] = block1;
                            }
                        }
                        else if (k > 0)
                        {
                            --k;
                            var3[i2] = block1;

                            if (k == 0 && block1 == Blocks.sand)
                            {
                                k = var2.nextInt(4) + Math.max(0, l1 - 63);
                                block1 = Blocks.sandstone;
                            }
                        }
                    }
                }
                else
                {
                    k = -1;
                }
            }
        }
    }
	
	public void addCreatureSpawn(Class creatureClass, int freq, int minGroupSize, int maxGroupSize) {
		this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(creatureClass, freq, minGroupSize, maxGroupSize));
	}
	
	public void addMonsterSpawn(Class monsterClass, int freq, int minGroupSize, int maxGroupSize) {
		this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(monsterClass, freq, minGroupSize, maxGroupSize));
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
		if (this.foliageColor < 0) {
	        double d0 = (double)MathHelper.clamp_float(this.getFloatTemperature(p_150558_1_, p_150558_2_, p_150558_3_), 0.0F, 1.0F);
	        double d1 = (double)MathHelper.clamp_float(this.getFloatRainfall(), 0.0F, 1.0F);
	        return getModdedBiomeGrassColor(ColorizerGrass.getGrassColor(d0, d1));
		}
		return foliageColor;
    }

	@Override
    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor(int p_150571_1_, int p_150571_2_, int p_150571_3_)
    {
		if (this.grassColor < 0) {
	        double d0 = (double)MathHelper.clamp_float(this.getFloatTemperature(p_150571_1_, p_150571_2_, p_150571_3_), 0.0F, 1.0F);
	        double d1 = (double)MathHelper.clamp_float(this.getFloatRainfall(), 0.0F, 1.0F);
	        return getModdedBiomeFoliageColor(ColorizerFoliage.getFoliageColor(d0, d1));
		}
		return this.grassColor;
    }

}
