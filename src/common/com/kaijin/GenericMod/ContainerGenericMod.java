/* Inventory Stocker
 *  Copyright (c) 2012 Yancarlo Ramsey and CJ Bowman
 *  Licensed as open source with restrictions. Please see attached LICENSE.txt.
 */

package com.kaijin.GenericMod;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import com.kaijin.GenericMod.*;
import cpw.mods.fml.common.network.Player;
import net.minecraft.src.*;

public class ContainerGenericMod extends Container
{
	public TEGenericMod tile;

	public ContainerGenericMod(InventoryPlayer inventoryplayer, TEGenericMod tile)
	{

	}

	public boolean canInteractWith(EntityPlayer entityplayer)
	{
		return this.tile.isUseableByPlayer(entityplayer);
	}
}
