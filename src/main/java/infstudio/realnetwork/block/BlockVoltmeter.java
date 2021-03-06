package infstudio.realnetwork.block;

import infstudio.realnetwork.tileentity.TileEntityVoltmeter;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class BlockVoltmeter extends BlockResistance {

    public BlockVoltmeter() {
        super();
        setName("voltmeter");
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityVoltmeter(1e3);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            TileEntityVoltmeter tile = (TileEntityVoltmeter)worldIn.getTileEntity(pos);
            playerIn.sendMessage(new TextComponentString("U="+String.format("%.2f", tile.getU())+"V"));
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }

}
