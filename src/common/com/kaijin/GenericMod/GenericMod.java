package com.kaijin.GenericMod;

import java.io.File;

import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;

import com.kaijin.GenericMod.*;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "GenericMod", name="Generic Mod", version="VERSION HERE", dependencies = "required-after:Forge@[4.1.1.251,)")
@NetworkMod(clientSideRequired = true, serverSideRequired = false,
clientPacketHandlerSpec = @SidedPacketHandler(channels = {"GenericMod"}, packetHandler = ClientPacketHandler.class),
serverPacketHandlerSpec = @SidedPacketHandler(channels = ("GenericMod"), packetHandler = ServerPacketHandler.class))
public class GenericMod
{
	@SidedProxy(clientSide = "com.kaijin.GenericMod.ClientProxy", serverSide = "com.kaijin.GenericMod.CommonProxy")
	public static CommonProxy proxy; //This object will be populated with the class that you choose for the environment

	@Instance("GenericMod")
	public static GenericMod instance; //The instance of the mod that will be defined, populated, and callable

	static int GenericModBlockID;
	static public boolean isDebugging;

	@PreInit
	public static void preInit(FMLPreInitializationEvent event)
	{
		try
		{

			Configuration configuration = new Configuration(event.getSuggestedConfigurationFile());
			configuration.load();
			GenericModBlockID = configuration.getOrCreateBlockIdProperty("GenericMod", 2491).getInt();
			isDebugging = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("debug", configuration.CATEGORY_GENERAL, false).value));
			configuration.save();
		}
		catch (Exception var1)
		{
			System.out.println("[GenericMod] Error while trying to access configuration!");
			throw new RuntimeException(var1);
		}

	}

	@Init
	public void load(FMLInitializationEvent event)
	{
		GenericMod = new BlockGenericMod(GenericModBlockID, 0, Material.ground).setHardness(0.75F).setResistance(5F).setStepSound(Block.soundStoneFootstep).setBlockName("InventoryStocker").setCreativeTab(CreativeTabs.tabBlock);
		LanguageRegistry.addName(GenericMod, "Generic Mod");
		GameRegistry.registerBlock(GenericMod, ItemGenericMod.class);

		GameRegistry.registerTileEntity(TEGenericMod1.class, "Generic Mod MK1");
		GameRegistry.registerTileEntity(TEGenericMod2.class, "Generic Mod MK2");
		GameRegistry.registerTileEntity(TEGenericMod3.class, "Generic Mod MK3");

		LanguageRegistry.instance().addStringLocalization("blockGenericMod1.name", "Generic Mod Mk1");
		LanguageRegistry.instance().addStringLocalization("blockGenericMod2.name", "Generic Mod Mk2");
		LanguageRegistry.instance().addStringLocalization("blockGenericMod3.name", "Generic Mod Mk3");

		NetworkRegistry.instance().registerGuiHandler(this.instance, proxy);

		proxy.load();
		if (proxy.isServer())
		{
			FMLLog.getLogger().info ("GenericMod loaded.");
		}
		if (isDebugging)
		{
			FMLLog.getLogger().info("GenericMod debugging enabled.");
		}
	}

	@PostInit
	public void modsLoaded(FMLPostInitializationEvent event)
	{
		if (Utils.isDebug()) System.out.println("GenericMod.modsLoaded");
		GameRegistry.addRecipe(new ItemStack(GenericMod, 16, 0), new Object[] {"XX ", "XX ", "X  ", 'X', Block.dirt}); // Meta 0
		GameRegistry.addRecipe(new ItemStack(GenericMod, 16, 1), new Object[] {"XX ", "XX ", "XX ", 'X', Block.dirt}); // Meta 1
		GameRegistry.addRecipe(new ItemStack(GenericMod, 16, 2), new Object[] {"XX ", "XX ", "XXX", 'X', Block.dirt}); // Meta 2
	}
	public static Block GenericMod; 
}
