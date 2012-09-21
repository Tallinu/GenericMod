package com.kaijin.GenericMod;

import net.minecraft.src.Container;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.TileEntity;

public class TEGenericMod extends TileEntity implements IInventory
{

	public TEGenericMod(int i)
	{

	}

	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public ItemStack getStackInSlot(int var1) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ItemStack decrStackSize(int var1, int var2) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ItemStack getStackInSlotOnClosing(int var1) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setInventorySlotContents(int var1, ItemStack var2) {
		// TODO Auto-generated method stub

	}
	@Override
	public String getInvName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean isUseableByPlayer(EntityPlayer var1) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void openChest() {
		// TODO Auto-generated method stub

	}
	@Override
	public void closeChest() {
		// TODO Auto-generated method stub

	}
}
