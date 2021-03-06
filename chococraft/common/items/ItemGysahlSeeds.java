package chococraft.common.items;

import chococraft.common.Constants;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSeeds;

public class ItemGysahlSeeds extends ItemSeeds
{
	public ItemGysahlSeeds(int itemId, int blockType, int soilBlockID)
	{
		super(itemId, blockType, soilBlockID);
		this.setCreativeTab(CreativeTabs.tabMaterials);
		this.setUnlocalizedName(Constants.KEY_GY_SEEDS);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		this.itemIcon = iconRegister.registerIcon(Constants.TCC_MODID + ":" + Constants.KEY_GY_SEEDS);
	}
}
