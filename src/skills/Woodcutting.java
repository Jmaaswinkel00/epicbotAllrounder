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
    private static SceneObject tree = APIContext.get().objects().query().named("Oak tree").results().nearest();
    private static final Area woodcuttingArea = PathManager.treeOakSpot;
    public static void doWoodcuttingTask(APIContext ctx) {

        if(InventoryManager.isInventoryFull()) {
            if(PlayerManager.isNotAtBank(ctx)) {
                PathManager.walkToNearestBank(ctx);
            } else {
                BankManager.depositALlExceptItem(ctx, "Bronze axe");
            }
        } else {
            if(!InventoryManager.hasWoodcuttingEquipmentInventory()) {
                if(PlayerManager.isNotAtBank(ctx)) {
                    PathManager.walkToNearestBank(ctx);
                } else {
                    BankManager.withdrawItem(ctx, "Bronze axe");
                }
            }
            if(!isInWoodcuttingArea(ctx)) {
                System.out.println("lopen");
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

    public static void prepareWoodcuttingTask(APIContext ctx) {
        if (!InventoryManager.hasWoodcuttingEquipmentInventory()) {

            if (PlayerManager.isNotAtBank(ctx)) {
                PathManager.walkToNearestBank(ctx);
            }
            if(!BankManager.isBankOpen(ctx)) {
                BankManager.openBank(ctx);
            }
            BankManager.withdrawItem(ctx, "Bronze axe");

        }

        if(!InventoryManager.isInventoryEmptyExcept("Bronze axe")) {
            if (PlayerManager.isNotAtBank(ctx)) {
                PathManager.walkToNearestBank(ctx);
            }
            if(!BankManager.isBankOpen(ctx)) {
                System.out.println("test");
                BankManager.openBank(ctx);
            }
            while (ctx.bank().isOpen()) {
                BankManager.depositALlExceptItem(ctx, "Bronze axe");
                Time.sleep(1200, 2400);
                if (InventoryManager.isInventoryEmpty()) {
                    if (BankManager.closeBank(ctx)) {
                        return;
                    }
                }
            }
        }
    }

    private static boolean isInWoodcuttingArea(APIContext ctx) {
        return woodcuttingArea.contains(ctx.localPlayer().getLocation());
    }
}
