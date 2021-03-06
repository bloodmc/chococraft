package chococraft.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import chococraft.common.entities.colours.EntityChocoboPurple;
import chococraft.common.items.BlockGysahlStem;

public class ChocoCraftEventCommon
{
	@ForgeSubscribe
	public void onUseBonemeal(BonemealEvent event)
	{
		if (event.ID == ModChocoCraft.gysahlStemBlock.blockID)
		{
			if (((BlockGysahlStem)ModChocoCraft.gysahlStemBlock).onBonemealUse(event.world, event.X, event.Y, event.Z))
			{
				event.setResult(Result.ALLOW);
			}
		}
	}
    
    @ForgeSubscribe
    public void onBurnDamage(LivingAttackEvent event)
    {
    	if(event.entity instanceof EntityPlayer)
    	{
    		EntityPlayer player = (EntityPlayer)event.entity;
    		if(event.source.equals(DamageSource.lava) 
    				|| event.source.equals(DamageSource.inFire) 
    				|| event.source.equals(DamageSource.onFire))
    		{
    			if(player.ridingEntity instanceof EntityChocoboPurple)
    			{
    				event.setCanceled(true);
    			}
    		}
    	}
    }
}
