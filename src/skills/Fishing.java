package skills;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.model.Area;
import com.epicbot.api.shared.model.Tile;
import com.epicbot.api.shared.query.NPCQueryBuilder;
import com.epicbot.api.shared.util.time.Time;
import misc.*;

public class Fishing {
    private final NPCQueryBuilder fishingSpot = APIContext.get().npcs().query();
    private final Area fishingSpotArea = PathManager.fishingSpot;
    private final String fishingTool = "Small fishing net";

    public void doFishingTask(APIContext ctx) {
        if(InventoryManager.isInventoryFull() && InventoryManager.onlyContainsRightEquipment(fishingTool)) {
            if(PlayerManager.isNotAtBank(ctx)) {
                PathManager.walkToLumbridgeBank(ctx);
            } else {
                BankManager.depositALlExceptItem(ctx, fishingTool);
                if(InventoryManager.onlyContainsRightEquipment(fishingTool)) {
                    PathManager.walkOutOfLumbridgeCastle(ctx);
                }
            }
        } else {
            if(!InventoryManager.hasFishingEquipmentInventory()) {
                if(PlayerManager.isNotAtBank(ctx)) {
                    PathManager.walkToNearestBank(ctx);
                } else {
                    BankManager.withdrawItem(ctx, fishingTool);
                }
            }
            if(!isNearFishingSpot(ctx) && InventoryManager.onlyContainsRightEquipment(fishingTool)) {
                PathManager.walkToPath(ctx, new Tile(3240, 3155, 0));
            }
            if(!PlayerManager.isInteracting() && !PlayerManager.isWalking()) {
                fishingSpot.results().nearest().interact("Net");
                ctx.mouse().moveOffScreen();
            }
        }
    }

    public void prepareFishingTask(APIContext ctx) {
        if (!InventoryManager.hasFishingEquipmentInventory(fishingTool)) {
            if (PlayerManager.isNotAtBank(ctx)) {
                PathManager.walkToNearestBank(ctx);
            }
            if(!BankManager.isBankOpen(ctx)) {
                BankManager.openBank(ctx);
            }
            BankManager.withdrawItem(ctx, fishingTool);
        }

        if(!InventoryManager.isInventoryEmptyExcept(fishingTool)) {
            if (PlayerManager.isNotAtBank(ctx)) {
                PathManager.walkToNearestBank(ctx);
            }
            if(!BankManager.isBankOpen(ctx)) {
                BankManager.openBank(ctx);
            }
            while (ctx.bank().isOpen()) {
                BankManager.depositALlExceptItem(ctx, fishingTool);
                Time.sleep(1200, 2400);
                if (InventoryManager.isInventoryEmpty()) {
                    if (BankManager.closeBank(ctx)) {
                        return;
                    }
                }
            }
        }
    }

    public boolean isNearFishingSpot(APIContext ctx) {
        if(fishingSpotArea.contains(ctx.localPlayer().getLocation()) || !fishingSpot.named("Fishing spot").results().isEmpty()) {
            return true;
        }

        return false;
    }

    public boolean isAtFishingSpot(APIContext ctx) {
        return false;
    }
}
