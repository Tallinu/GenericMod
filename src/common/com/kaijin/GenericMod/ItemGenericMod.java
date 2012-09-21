package com.kaijin.GenericMod;

import net.minecraft.src.*;

public class ItemGenericMod extends ItemBlock
{
	public ItemGenericMod(int var1)
	{
		super(var1);
		if (Utils.isDebug()) System.out.println("ItemGenericMod");
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	public int getMetadata(int var1)
	{
		if (Utils.isDebug()) System.out.println("ItemGenericMod.getMetadata");
		return var1;
	}

	public String getItemNameIS(ItemStack var1)
	{
		if (Utils.isDebug()) System.out.println("ItemGenericMod.getItemNameIS");
		int var2 = var1.getItemDamage();

		switch (var2)
		{
		case 0:
			return "blockGenericMod1";

		case 1:
			return "blockGenericMod2";

		case 2:
			return "blockGenericMod3";

		default:
			return null;
		}
	}
}
