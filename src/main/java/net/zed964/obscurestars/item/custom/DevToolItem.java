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

import java.nio.file.Files;
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
            if (devTool.hasTag()) {
                assert devTool.getTag() != null;
                if (devTool.getTag().contains("obscurestars.position1")) {
                    addNbtForPos(blockClicked, devTool, false);
                    outputSetPos(blockClicked, player, "Position 2");
                }
            } else {
                addNbtForPos(blockClicked, devTool, true);
                outputSetPos(blockClicked, player, "Position 1");
            }
        }
        return super.useOn(pContext);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        if(itemStack.hasTag()) {
            assert itemStack.getTag() != null;
            if((itemStack.getTag().contains("obscurestars.position1")) && (itemStack.getTag().contains("obscurestars.position2"))) {
                if(pPlayer.isShiftKeyDown()) {
                    BlockPos[] tabPos = this.getNbtForPos(itemStack);
                    BlockPos position1 = tabPos[0];
                    BlockPos position2 = tabPos[1];
                    if(itemStack.hasCustomHoverName()) {
                        Component component = itemStack.getDisplayName();
                        String name  = component.getString();
                        name = name.replace("[", "");
                        name = name.replace("]", "");

                        SchematicSaver schematicSaver = new SchematicSaver(position1, position2, name);
                        if(Files.exists(schematicSaver.getPath())) {
                            pPlayer.sendMessage(new TextComponent("The Files was Saved with success !"), pPlayer.getUUID());
                        } else {
                            pPlayer.sendMessage(new TextComponent("The Files was UnSaved !"), pPlayer.getUUID());
                        }
                    } else {
                        SchematicSaver schematicSaver = new SchematicSaver(position1, position2);
                        if(Files.exists(schematicSaver.getPath())) {
                            pPlayer.sendMessage(new TextComponent("The Files was Saved with success !"), pPlayer.getUUID());
                        } else {
                            pPlayer.sendMessage(new TextComponent("The Files was UnSaved !"), pPlayer.getUUID());
                        }
                    }
                }
            }
        }
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

    private BlockPos[] getNbtForPos(ItemStack itemStack) {

        BlockPos[] tabPos = new BlockPos[2];

        String pos1;
        String pos2;

        String xPos1;
        String yPos1;
        String zPos1;

        String xPos2;
        String yPos2;
        String zPos2;

        int pos1Space;
        int pos2Space;

        assert itemStack.getTag() != null;
        pos1 = itemStack.getTag().getString("obscurestars.position1");
        pos2 =  itemStack.getTag().getString("obscurestars.position2");

        pos1 = pos1.substring(13);
        pos1 = pos1.replaceAll(" y = ", " ");
        pos1 = pos1.replaceAll(" z = ", " ");

        pos2 = pos2.substring(13);
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

        tabPos[0] = new BlockPos(convertStringToInt(xPos1, verifNbrNegatif(xPos1)), convertStringToInt(yPos1, verifNbrNegatif(yPos1)), convertStringToInt(zPos1, verifNbrNegatif(zPos1)));
        tabPos[1] = new BlockPos(convertStringToInt(xPos2, verifNbrNegatif(xPos2)), convertStringToInt(yPos2, verifNbrNegatif(yPos2)), convertStringToInt(zPos2, verifNbrNegatif(zPos2)));

        return tabPos;
    }

    private boolean verifNbrNegatif(String pos) {
        String verif = "-";
        return pos.startsWith(verif);
    }

    private int convertStringToInt(String pos, Boolean bool) {
        if(bool) {
            int nbr = -1;
            String test;
            test = pos.replaceAll("-", "");
            return Integer.parseInt(test) * nbr;
        } else {
            return Integer.parseInt(pos);
        }
    }

}

