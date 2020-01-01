package infstudio.realnetwork.block;

import infstudio.realnetwork.RealNetwork;
import infstudio.realnetwork.core.NetWork;
import infstudio.realnetwork.tileentity.TileEntityWireBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockWireBase extends Block {

    private String name;

    public BlockWireBase() {
        super(Material.ROCK);
        setCreativeTab(RealNetwork.rnTab);
    }

    public void setName(String name) {
        this.name = name;
        setRegistryName(RealNetwork.MODID, name);
        setUnlocalizedName(name);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        TileEntityWireBase tile = (TileEntityWireBase) worldIn.getTileEntity(pos);
        if (!worldIn.isRemote) playerIn.sendMessage(new TextComponentString("I="+tile.getI()+"A"));
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        NetWork netWork = new NetWork(worldIn, pos);
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);

    }

    @Override
    public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
        NetWork netWork = new NetWork(worldIn, pos);
        super.onBlockDestroyedByPlayer(worldIn, pos, state);
    }

    @Override
    public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosionIn) {
        NetWork netWork = new NetWork(worldIn, pos);
        super.onBlockDestroyedByExplosion(worldIn, pos, explosionIn);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityWireBase(1e-6);
    }

}