package skills;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.model.Area;
import com.epicbot.api.shared.model.Tile;
import com.epicbot.api.shared.query.NPCQueryBuilder;
import misc.*;

public class Fishing {
    private final NPCQueryBuilder fishingSpot = APIContext.get().npcs().query();
    private final Area fishingSpotArea = PathManager.fishingSpot;

    public void doFishingTask(APIContext ctx) {
        if(InventoryManager.isInventoryFull() && InventoryManager.onlyContainsRightEquipment("Small fishing net")) {
            if(PlayerManager.isNotAtBank(ctx)) {
                PathManager.walkToLumbridgeBank(ctx);
            } else {
                BankManager.depositALlExceptItem(ctx, "Small fishing net");
                if(InventoryManager.onlyContainsRightEquipment("Small fishing net")) {
                    PathManager.walkOutOfLumbridgeCastle(ctx);
                }
            }
        } else {
            if(!InventoryManager.hasFishingEquipmentInventory()) {
                if(PlayerManager.isNotAtBank(ctx)) {
                    PathManager.walkToNearestBank(ctx);
                } else {
                    BankManager.withdrawItem(ctx, "Small fishing net");
                }
            }
            if(!isNearFishingSpot(ctx) && InventoryManager.onlyContainsRightEquipment("Small fishing net")) {
                PathManager.walkToPath(ctx, new Tile(3240, 3155, 0));
            }
            if(!PlayerManager.isInteracting() && !PlayerManager.isWalking()) {
                fishingSpot.results().nearest().interact("Net");
                ctx.mouse().moveOffScreen();
            }
        }
    }

    public void prepareFishingTask(APIContext ctx) {

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
