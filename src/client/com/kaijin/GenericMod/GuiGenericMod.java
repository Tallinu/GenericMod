/* Inventory Stocker
 *  Copyright (c) 2012 Yancarlo Ramsey and CJ Bowman
 *  Licensed as open source with restrictions. Please see attached LICENSE.txt.
 */

package com.kaijin.GenericMod;

import org.lwjgl.opengl.GL11;

import com.kaijin.GenericMod.*;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.*;

@SideOnly(Side.CLIENT)
public class GuiGenericMod extends GuiContainer
{
	private TEGenericMod tile;
	// last button clicked
	private GuiButton selectedButton = null;

	// define button class wide
	private GuiButton button = null;

	public GuiGenericMod(InventoryPlayer inventoryplayer, TEGenericMod tile)
	{
		super(new ContainerGenericMod(inventoryplayer, tile));
		this.tile = tile;
		xSize = 176;
		ySize = 168;
		button = new GuiButton(0, 0, 0, 40, 20, "");
	}

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of the items)
	 */
	protected void drawGuiContainerForegroundLayer()
	{
		this.fontRenderer.drawString("Input", 8, 6, 4210752);
		this.fontRenderer.drawString(this.tile.getInvName(), 68, 6, 4210752);
		this.fontRenderer.drawString("Output", 116, 6, 4210752);
		this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}

	/**
	 * Draw the background layer for the GuiContainer (everything behind the items)
	 */
	protected void drawGuiContainerBackgroundLayer(float par1, int mouseX, int mouseY)
	{
		int GuiTex = this.mc.renderEngine.getTexture("/com/kaijin/GenericMod/textures/GenericMod.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(GuiTex);
		int XOffset = (width - xSize) / 2; // X offset = Half the difference between screen width and GUI width
		int YOffset = (height - ySize) / 2; // Y offset = half the difference between screen height and GUI height
		this.drawTexturedModalRect(XOffset, YOffset, 0, 0, xSize, ySize);

		//GuiButton(int ID, int XOffset, int YOffset, int Width, int Height, string Text)
		//button definition is the full one with width and height
		//defining button below, setting it look enabled and drawing it
		//If you make changes to the button state, you must call .drawButton(mc, XOffset, YOffset)
	}

	//Copied mouseClicked function to get our button to make the "click" noise when clicked
	protected void mouseClicked(int par1, int par2, int par3)
	{
		if (par3 == 0)
		{
			if (button.mousePressed(this.mc, par1, par2))
			{
				this.selectedButton = button;
				this.mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
				this.actionPerformed(button);
			}
		}
		super.mouseClicked(par1, par2, par3);
	}

	/*
	 * This function actually handles what happens when you click on a button, by ID
	 */
	public void actionPerformed(GuiButton button)
	{
		if (!button.enabled)
		{
			return;
		}
		if (button.id == 0)
		{

		}
	}
}
