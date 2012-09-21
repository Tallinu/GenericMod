/* Inventory Stocker
 *  Copyright (c) 2012 Yancarlo Ramsey and CJ Bowman
 *  Licensed as open source with restrictions. Please see attached LICENSE.txt.
 */

package com.kaijin.GenericMod;

import java.io.File;

import com.kaijin.GenericMod.CommonProxy;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.src.*;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.Configuration;

public class ClientProxy extends CommonProxy
{
	@Override
	public void load()
	{
		MinecraftForgeClient.preloadTexture(GenericMod.proxy.BLOCK_PNG);
	}
}
