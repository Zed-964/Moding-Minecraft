package net.zed964.obscurestars.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.zed964.obscurestars.item.ModItems;
import net.zed964.obscurestars.schematic.SchematicSaver;
import net.zed964.obscurestars.util.InventoryUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DevToolItem extends Item {

    public DevToolItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext pContext) {
        if (pContext.getLevel().isClientSide()) {
            Player player = pContext.getPlayer();
            BlockPos blockClicked = pContext.getClickedPos();
            assert player != null;
            ItemStack devTool = player.getInventory().getItem(InventoryUtil.getFirstInventoryIndex(player, ModItems.DEV_TOOL.get()));
            if (player.isShiftKeyDown()) {
                addNbtForPos(blockClicked, devTool, false);
                outputSetPos(blockClicked, player, "Position 2");

            } else {
                addNbtForPos(blockClicked, devTool, true);
                outputSetPos(blockClicked, player, "Position 1");
            }
        }
        return super.useOn(pContext);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        if(pStack.hasTag()) {
            assert pStack.getTag() != null;
            String toolTips = "";
            if (pStack.getTag().contains("obscurestars.position1")) {
                toolTips = "Position 1 : ";
                toolTips =  toolTips + pStack.getTag().getString("obscurestars.position1");
            }
            if (pStack.getTag().contains("obscurestars.position2")) {
                toolTips = toolTips + "\n" + "Position 2 : ";
                toolTips = toolTips + pStack.getTag().getString("obscurestars.position2");
            }
            pTooltipComponents.add(new TextComponent(toolTips));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    }

    public void outputSetPos(BlockPos blockPos, Player player, String posNum) {
        player.sendMessage(new TextComponent("Set To : x = " + blockPos.getX() + " y = " + blockPos.getY() + " z = " + blockPos.getZ() + " For : " + posNum), player.getUUID());
    }

    private void addNbtForPos(BlockPos blockPos, ItemStack itemStack, Boolean pos1) {
        CompoundTag nbtData;
        if (itemStack.hasTag()) {
            nbtData = itemStack.getTag();
        } else {
            nbtData = new CompoundTag();
        }
        assert nbtData != null;
        if (pos1) {
            nbtData.putString("obscurestars.position1", "Set To : x = " + blockPos.getX() + " y = " + blockPos.getY() + " z = " + blockPos.getZ());
        } else {
            nbtData.putString("obscurestars.position2", "Set To : x = " + blockPos.getX() + " y = " + blockPos.getY() + " z = " + blockPos.getZ());
        }
        itemStack.setTag(nbtData);
    }

    public void getNbtForPos(ItemStack itemStack) {
        if(itemStack.hasTag()) {
            String pos1;
            String pos2;

            String xPos1;
            String yPos1;
            String zPos1;

            String xPos2;
            String yPos2;
            String zPos2;

            int positionX1;
            int positionY1;
            int positionZ1;

            int positionX2;
            int positionY2;
            int positionZ2;

            int pos1Space;
            int pos2Space;

            assert itemStack.getTag() != null;
            pos1 = itemStack.getTag().getString("obscurestars.position1");
            pos2 =  itemStack.getTag().getString("obscurestars.position2");

            pos1 = pos1.replaceAll("Set to : x = ", "");
            pos1 = pos1.replaceAll(" y = ", " ");
            pos1 = pos1.replaceAll(" z = ", " ");

            pos2 = pos2.replaceAll("Set to : x = ", "");
            pos2 = pos2.replaceAll(" y = ", " ");
            pos2 = pos2.replaceAll(" z = ", " ");

            pos1Space = pos1.indexOf(" ");
            xPos1 = pos1.substring(0, pos1Space);
            pos1 = pos1.replaceAll(xPos1 + " ", "");

            pos2Space = pos2.indexOf(" ");
            xPos2 = pos2.substring(0, pos2Space);
            pos2 = pos2.replaceAll(xPos2 + " ", "");

            pos1Space = pos1.indexOf(" ");
            yPos1 = pos1.substring(0, pos1Space);
            pos1 = pos1.replaceAll(yPos1 + " ", "");

            pos2Space = pos2.indexOf(" ");
            yPos2 = pos2.substring(0, pos2Space);
            pos2 = pos2.replaceAll(yPos2 + " ", "");

            zPos1 = pos1;
            zPos2 = pos2;

            positionX1 = Integer.parseInt(xPos1);
            positionX2 = Integer.parseInt(xPos2);

            positionY1 = Integer.parseInt(yPos1);
            positionY2 = Integer.parseInt(yPos2);

            positionZ1 = Integer.parseInt(zPos1);
            positionZ2 = Integer.parseInt(zPos2);

            BlockPos blockPos1 = new BlockPos(positionX1, positionY1, positionZ1);
            BlockPos blockPos2 = new BlockPos(positionX2, positionY2, positionZ2);

            SchematicSaver schematicSaver = new SchematicSaver(blockPos1, blockPos2);
        }
    }
}

