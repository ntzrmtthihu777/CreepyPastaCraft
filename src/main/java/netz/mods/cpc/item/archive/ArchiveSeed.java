package netz.mods.cpc.item.archive;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.world.World;
import netz.mods.cpc.entity.monster.EntitySeed;

public class ArchiveSeed extends Archive {

   public ArchiveSeed(int i) {
      super(i);
      this.CPName = "seed";
   }

   public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
      if(par3World.isRemote) {
         return true;
      } else {
         int var11 = par3World.getBlockId(par4, par5, par6);
         par4 += Facing.offsetsXForSide[par7];
         par5 += Facing.offsetsYForSide[par7];
         par6 += Facing.offsetsZForSide[par7];
         double var12 = 0.0D;
         if(par7 == 1 && var11 == Block.fence.blockID || var11 == Block.netherFence.blockID) {
            var12 = 0.5D;
         }

         if(spawnCreature(par3World, par1ItemStack.getItemDamage(), (double)par4 + 0.5D, (double)par5 + var12, (double)par6 + 0.5D) && !par2EntityPlayer.capabilities.isCreativeMode) {
            --par1ItemStack.stackSize;
         }

         return true;
      }
   }

   public static boolean spawnCreature(World par0World, int par1, double par2, double par4, double par6) {
      EntitySeed var8 = new EntitySeed(par0World);
      var8.setLocationAndAngles(par2, par4, par6, par0World.rand.nextFloat() * 360.0F, 0.0F);
      par0World.spawnEntityInWorld(var8);
      var8.playLivingSound();
      return var8 != null;
   }
}
