/* Inventory Stocker
 *  Copyright (c) 2012 Yancarlo Ramsey and CJ Bowman
 *  Licensed as open source with restrictions. Please see attached LICENSE.txt.
 */

package com.kaijin.GenericMod;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import com.kaijin.GenericMod.*;
import net.minecraft.src.*;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import cpw.mods.fml.common.network.*;

public class ServerPacketHandler implements IPacketHandler
{
	/*
	 * Packet format EXAMPLE:
	 * byte 0: Packet Type
	 *     Currently available packet types
	 *         Client:
	 *         0=
	 *             byte 1: x location of TileEntity
	 *             byte 2: y location of TileEntity
	 *             byte 3: z location of TileEntity
	 *             byte 4: boolean request, false = clear snapshot, true = take snapshot
	 *         1=
	 *             byte 1: x location of TileEntity
	 *             byte 2: y location of TileEntity
	 *             byte 3: z location of TileEntity
	 *             byte 4: boolean request, false = not used, true = rotate request
	 *         
	 *         Server:
	 *         0=
	 *             byte 1: x location of TileEntity
	 *             byte 2: y location of TileEntity
	 *             byte 3: z location of TileEntity
	 *             byte 4: boolean information, false = no valid snapshot, true = valid snapshot
	 *         1=@Deprecated
	 *             byte 1: x location of TileEntity
	 *             byte 2: y location of TileEntity
	 *             byte 3: z location of TileEntity
	 *             byte 4: int "metadata", sync client TE rotation and lights with server
	 *             
	 * remaining bytes: data for packet
	 */

	@Override
	public void onPacketData(NetworkManager network, Packet250CustomPayload packet, Player player)
	{

	}
}
