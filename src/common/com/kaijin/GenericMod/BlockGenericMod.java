/* Inventory Stocker
 *  Copyright (c) 2012 Yancarlo Ramsey and CJ Bowman
 *  Licensed as open source with restrictions. Please see attached LICENSE.txt.
 */

package com.kaijin.GenericMod;

import java.util.*;

import net.minecraft.src.Block;
import net.minecraft.src.BlockContainer;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.MathHelper;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import cpw.mods.fml.common.network.NetworkRegistry;


public class BlockGenericMod extends Block
{
	public BlockGenericMod(int i, int j, Material material)
	{
		super(i, j, material);
	}

	/**
	 * This adds the meta data blocks to the creative inventory specified by the main
	 * block definition in the main mod file
	 */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int var4 = 0; var4 < 3; ++var4)
        {
            par3List.add(new ItemStack(par1, 1, var4));
        }
    }
	
	@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		if (Utils.isDebug()) System.out.println("BlockInventoryStocker.createTileEntity");
		switch (metadata)
		{
		case 0:
			return new TEGenericMod1();

		case 1:
			return new TEGenericMod2();

		case 2:
			return new TEGenericMod3();

		default:
			return null;
		}
	}

	public String getTextureFile()
	{
		return GenericMod.proxy.BLOCK_PNG;
	}

	public int idDropped(int var1, Random var2, int var3)
	{
		if (Utils.isDebug()) System.out.println("BlockChargingBench.idDropped");
		return this.blockID;
	}

	protected int damageDropped(int var1)
	{
		if (Utils.isDebug()) System.out.println("BlockChargingBench.damageDropped");
		return var1;
	}

	public int getBlockTextureFromSide(int i)
	{
		switch (i)
		{
		case 0: // Bottom
			return 16;

		case 1: // Top
			return 0;

		case 2: // North
			return 16;

		case 3: // South
			return 16;

		default: // 4-5 West-East
			return 16;
		}
	}

	@SideOnly(Side.CLIENT)
	public int getBlockTexture(IBlockAccess blocks, int x, int y, int z, int i)
	{
		return 0;
	}

	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving par5EntityLiving)
	{

	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int par6, float par7, float par8, float par9)
	{
		if(world.isRemote)
		{
			// Prevent GUI pop-up and handle block rotation
			if (entityplayer.isSneaking())
			{
				if (Utils.isDebug()) System.out.println("Block.world.isRemote.isSneaking");
				// Prevent GUI popup when sneaking
				// This allows you to sneak place things directly on the inventory stocker
				return false;
			}
		}
		else if (GenericMod.proxy.isServer())
		{
			// Prevent GUI pop-up and handle block rotation
			if (entityplayer.isSneaking())
			{
				//prevent GUI popup when sneaking and your hand is empty
				return false;
			}

			//If we got here, we're not sneaking, time to get to work opening the GUI
			// Duplicate part of onNeighborBlockChange to ensure status is up-to-date before GUI opens
			entityplayer.openGui(GenericMod.instance, 1, world, x, y, z);
			return true;
		}
		if (Utils.isDebug()) System.out.println("Block.onBlockActivated.fallthrough-should not happen?");
		return true;
	}

	public TEGenericMod getBlockEntity(int var1)
	{
		if (Utils.isDebug()) System.out.println("BlockChargingBench.getBlockEntity.var1: "+var1);
		switch (var1)
		{
		case 0:
			return new TEGenericMod1();

		case 1:
			return new TEGenericMod2();

		case 2:
			return new TEGenericMod3();

		default:
			return null;
		}
	}
	@Override
	public boolean canProvidePower()
	{
		return false; // Old means of causing visual RedPower wire connections.
	}

	@Override
	public boolean canConnectRedstone(IBlockAccess world, int X, int Y, int Z, int direction)
	{
		return true; // Will appear to connect to RedPower wires and such.
		// Currently still causes redstone dust to appear to connect in some cases where it shouldn't; Not our fault.
	}

	@Override
	public boolean hasTileEntity(int metadata)
	{
		return true;
	}

	/**
	 * This is called when something changes near our block, we use it to detect placement of pipes/tubes
	 * so we can update our textures. We also use it to verify the attached storage inventory hasn't been
	 * removed or changed.
	 */
	public void onNeighborBlockChange(World world, int x, int y, int z, int blockID)
	{
		super.onNeighborBlockChange(world, x, y, z, blockID);
	}

	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int par1)
	{
		preDestroyBlock(world, x, y, z);
		if (Utils.isDebug()) System.out.println("BlockInventoryStocker.onBlockDestroyedByPlayer");
		super.onBlockDestroyedByPlayer(world, x, y, z, par1);
	}

	public static void dropItems(World world, ItemStack stack, int i, int j, int k)
	{
		float f1 = 0.7F;
		double d = (double)(world.rand.nextFloat() * f1) + (double)(1.0F - f1) * 0.5D;
		double d1 = (double)(world.rand.nextFloat() * f1) + (double)(1.0F - f1) * 0.5D;
		double d2 = (double)(world.rand.nextFloat() * f1) + (double)(1.0F - f1) * 0.5D;
		EntityItem entityitem = new EntityItem(world, (double) i + d,
				(double) j + d1, (double) k + d2, stack);
		entityitem.delayBeforeCanPickup = 10;
		world.spawnEntityInWorld(entityitem);
	}

	public static void dropItems(World world, IInventory inventory, int i, int j, int k)
	{
		for (int l = 0; l < inventory.getSizeInventory(); ++l)
		{
			ItemStack items = inventory.getStackInSlot(l);

			if (items != null && items.stackSize > 0)
			{
				dropItems(world, inventory.getStackInSlot(l).copy(), i, j, k);
			}
		}
	}

	public static void preDestroyBlock(World world, int i, int j, int k)
	{
		TileEntity tile = world.getBlockTileEntity(i, j, k);

		if (tile instanceof IInventory && !GenericMod.proxy.isClient())
		{
			dropItems(world, (IInventory) tile, i, j, k);
			tile.invalidate();
		}
	}
}
