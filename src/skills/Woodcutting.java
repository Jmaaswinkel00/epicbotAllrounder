package skills;

import com.epicbot.api.os.model.game.WidgetID;
import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.entity.SceneObject;
import com.epicbot.api.shared.model.Area;
import com.epicbot.api.shared.model.Tile;
import com.epicbot.api.shared.util.time.Time;
import misc.*;

import java.nio.file.Path;

public class Woodcutting {
    private final Area woodcuttingArea = PathManager.treeOakSpot;
    private final String axe = "Bronze axe";
    public void doWoodcuttingTask(APIContext ctx) {

        if(InventoryManager.isInventoryFull()) {
            if(PlayerManager.isNotAtBank(ctx)) {
                PathManager.walkToNearestBank(ctx);
            } else {
                BankManager.depositALlExceptItem(ctx, axe);
            }
        } else {
            if(!InventoryManager.hasWoodcuttingEquipmentInventory(axe)) {
                if(PlayerManager.isNotAtBank(ctx)) {
                    PathManager.walkToNearestBank(ctx);
                } else {
                    BankManager.withdrawItem(ctx, axe);
                }
            }
            if(!isInWoodcuttingArea(ctx)) {
                PathManager.walkToPath(ctx, woodcuttingArea.getRandomTile());
            }
            if(!PlayerManager.isInteracting() && !PlayerManager.isWalking()) {
                SceneObject tree = APIContext.get().objects().query().named("Oak tree").results().nearest();
                if(tree != null && tree.hasAction("Chop down")) {
                    Time.sleep(1800, () -> tree.interact("Chop down"));
                    ctx.mouse().moveOffScreen();
                }
            }
        }
    }

    public void prepareWoodcuttingTask(APIContext ctx) {
        if (!InventoryManager.hasWoodcuttingEquipmentInventory(axe)) {
            if (PlayerManager.isNotAtBank(ctx)) {
                PathManager.walkToNearestBank(ctx);
            }
            if(!BankManager.isBankOpen(ctx)) {
                BankManager.openBank(ctx);
            }
            BankManager.withdrawItem(ctx, axe);
        }

        if(!InventoryManager.isInventoryEmptyExcept(axe)) {
            if (PlayerManager.isNotAtBank(ctx)) {
                PathManager.walkToNearestBank(ctx);
            }
            if(!BankManager.isBankOpen(ctx)) {
                BankManager.openBank(ctx);
            }
            while (ctx.bank().isOpen()) {
                BankManager.depositALlExceptItem(ctx, axe);
                Time.sleep(1200, 2400);
                if (InventoryManager.isInventoryEmpty()) {
                    if (BankManager.closeBank(ctx)) {
                        return;
                    }
                }
            }
        }
    }

    private boolean isInWoodcuttingArea(APIContext ctx) {
        return woodcuttingArea.contains(ctx.localPlayer().getLocation());
    }
}
