package mypackage;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import mypackage.dimension.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler; // used in 1.6.2
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid="MyModID", name="My Mod", version="1.0.0")
public class MyMod {
	public static int varCount = 5;
	public static int armorCount = 5;
	public static HashMap<String, Integer> armorMap = new HashMap<String, Integer>();

	// AUTHOR NAME:
	//Sumedh Chekka
	//emerald

	@Instance(value = "1")
	public static MyMod instance;
	@SidedProxy(clientSide="mypackage.client.ClientProxy", serverSide="mypackage.CommonProxy")
	public static CommonProxy proxy;

	// MATERIALS
	public static ArmorMaterial crackedEmeraldArmor = addArmorMaterial(999, new int[]{99, 99, 99, 99},99 );
	public static ToolMaterial crackedEmeraldTool = addToolMaterial(3, 9999, 999.0F, 999999F, 64);
	
	// NEW ITEMS
	public static MyFood goldenCheese = new MyFood(50, 1.0F, true);
	public static MySword crackedEmeraldSword = new MySword(crackedEmeraldTool);
	public static MyItem emeraldCube = new MyItem(CreativeTabs.tabMaterials);
	public static MyItem makersIngot = new MyItem(CreativeTabs.tabMaterials);
	public static MyItem makersMineral = new MyItem(CreativeTabs.tabMaterials);
	
	// NEW BLOCKS
	public static MyBlock crackedEmerald = new MyBlock(CreativeTabs.tabBlock, Material.rock); 
	public static MyBlock makersOre = new MyBlock(CreativeTabs.tabBlock, Material.rock);
	public static MyBlock makersBlock = new MyBlock(CreativeTabs.tabBlock, Material.iron);
	
	// NEW ARMOR
	public static MyBoots crackedEmeraldBoots = new MyBoots(crackedEmeraldArmor);
	public static MyLeggings crackedEmeraldLeggings = new MyLeggings(crackedEmeraldArmor);
	public static MyChestplate crackedEmeraldChestplate = new MyChestplate(crackedEmeraldArmor);
	public static MyHelmet crackedEmeraldHelmet = new MyHelmet(crackedEmeraldArmor);

	// NEW THROWABLE
	public static MyThrowableItem makersThrowable = new MyThrowableItem(CreativeTabs.tabMisc);
	
	// BIOMES AND DIMENSIONS
	public static int biomeID = 50;
	public static int customWorldID = 2;
	public static MyTeleporterItem tpHome = new MyTeleporterItem(0);
	public static MyTeleporterItem tpCustom = new MyTeleporterItem(customWorldID);
	public static CustomBiome theCustomBiome = new CustomBiome(biomeID++, Blocks.stone);
	public static int emeraldWorldID = 3;
	public static MyTeleporterItem tpEmeraldm = new MyTeleporterItem(emeraldWorldID);
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		// BLOCK QUALITIES
		crackedEmerald.setHarvestLevel("pickaxe", 3);
		crackedEmerald.setHardness(25F);
		crackedEmerald.setResistance(6000F);
		crackedEmerald.addItemDropped(Items.ender_eye, 64);
		
		makersBlock.setHarvestLevel("pickaxe", 1);
		makersBlock.setHardness(5.0F);
		makersBlock.setResistance(50.0F);
		makersBlock.addBlockDropped(makersBlock);

		makersOre.setHarvestLevel("pickaxe", 1);
		makersOre.setHardness(3.0F);
		makersOre.setResistance(15.0F);
		makersOre.addItemDropped(makersMineral,2,4);
		
		// POTION EFFECTS FOR FOOD
		
		// BUILD DIMENSIONS
		buildDimension(emeraldWorldID, EmeraldWorld.class);
		buildDimension(customWorldID, CustomWorld.class);
		
		// REGISTRATION
		registerEverything();
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		proxy.registerRenderers();
		
		GameRegistry.registerWorldGenerator(new MyGenerator(), 1000);
		
		// ITEM STACKS
		ItemStack oneStick = new ItemStack(Items.stick); 
		ItemStack onecrackedEmerald = new ItemStack(crackedEmerald);
		ItemStack onecrackedEmeraldSword = new ItemStack(crackedEmerald);
		ItemStack oneDimond = new ItemStack(Items.diamond, 64);
		ItemStack oneDirt = new ItemStack(Blocks.dirt);
		ItemStack oneGravel = new ItemStack(Blocks.gravel);
		ItemStack oneMakersMineral = new ItemStack(makersMineral);
		ItemStack oneMakersIngot = new ItemStack(makersIngot);
		ItemStack nineMakersIngot = new ItemStack(makersIngot, 9);
		ItemStack oneMakersOre = new ItemStack(makersOre);
		ItemStack oneMakersBlock = new ItemStack(makersBlock);
		ItemStack fourMakersThrowable = new ItemStack(makersThrowable, 4);
		ItemStack oneGunpowder = new ItemStack(Items.gunpowder);
		
		// NEW RECIPES
		GameRegistry.addRecipe(onecrackedEmeraldSword, " c ", " c ", " s ",'c', onecrackedEmeraldSword, 's',oneStick);
		GameRegistry.addShapelessRecipe(oneDimond, oneDirt, oneGravel);
		GameRegistry.addRecipe(oneMakersBlock, "iii", "iii", "iii", 'i', oneMakersIngot);
		GameRegistry.addRecipe(fourMakersThrowable, " a ", "aba", " a ", 'a', oneMakersIngot, 'b', oneGunpowder);
		GameRegistry.addShapelessRecipe(nineMakersIngot, oneMakersBlock);
		GameRegistry.addSmelting(oneMakersOre, oneMakersIngot, 1.0F);
		
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}
	
	public static ToolMaterial addToolMaterial(int harvest_level, int durability, float efficiency, float damage, int enchantability) {
		return EnumHelper.addToolMaterial(MyMod.class.getDeclaredFields()[MyMod.varCount++].getName(),harvest_level,durability,efficiency,damage,enchantability);
	}

	public static ArmorMaterial addArmorMaterial(int durability, int[] damage_reductions, int enchantability) {
		armorMap.put(MyMod.class.getDeclaredFields()[MyMod.varCount].getName(), MyMod.armorCount++);
		return EnumHelper.addArmorMaterial(MyMod.class.getDeclaredFields()[MyMod.varCount++].getName(),durability,damage_reductions,enchantability);
	}
	
	public static String getNextName() {
		Field f;
		while (true) {
			f = MyMod.class.getDeclaredFields()[MyMod.varCount++];
			Class c = f.getType();
			if (	c == MyAxe.class || c == MyBoots.class || c == MyChestplate.class || 
					c == MyFood.class || c == MyHelmet.class || c == MyHoe.class || 
					c == MyItem.class || c == MyLeggings.class || c == MyPickaxe.class || 
					c == MySpade.class || c == MySword.class || c == MyThrowableItem.class || 
					c == MyTeleporterItem.class || c == MyBlock.class) break;
		}
		return f.getName();
	}
	
	private void registerEverything() {
		MinecraftForge.EVENT_BUS.register(new MyForgeEvents());
		FMLCommonHandler.instance().bus().register(new MyWorldEvents());
		int entityID = 0;
		EntityRegistry.registerModEntity(MyThrowableEntity.class, "MyThrowableEntity", EntityRegistry.findGlobalUniqueEntityId(), this, 80, 1, true);
		try {
			for (Field f : this.getClass().getDeclaredFields()) {
				Class c = f.getType();
				if (	c == MyAxe.class || c == MyBoots.class || c == MyChestplate.class || 
						c == MyFood.class || c == MyHelmet.class || c == MyHoe.class || 
						c == MyItem.class || c == MyLeggings.class || c == MyPickaxe.class || 
						c == MySpade.class || c == MySword.class || c == MyThrowableItem.class || 
						c == MyTeleporterItem.class) {
					Item item = (Item)f.get(this);
					GameRegistry.registerItem(item, item.getUnlocalizedName());
				}
				if (c == MyBlock.class) {
					Block block = (Block)f.get(this);
					GameRegistry.registerBlock(block, block.getUnlocalizedName());
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	private void buildDimension(int dimID, Class<? extends WorldProvider> dimClass) {
		DimensionManager.registerProviderType(dimID, dimClass, false);
		DimensionManager.registerDimension(dimID, dimID);
	}
}
