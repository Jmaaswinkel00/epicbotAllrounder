package misc;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.entity.NPC;
import com.epicbot.api.shared.util.time.Time;

public class BankManager {
    private static final NPC banker = APIContext.get().npcs().query().named("Banker").results().nearest();
    public static void depositALlExceptItem(APIContext ctx, String item) {
        if(!PlayerManager.isWalking()) {
            banker.interact("Bank");

            if(ctx.bank().isOpen()) {
                Time.sleep(1200, () -> ctx.bank().depositAllExcept(item));
            }
            if(InventoryManager.onlyContainsRightEquipment(item) && ctx.bank().isOpen()) {
                ctx.bank().close();
            }
        }
    }

    public static void depositAll(APIContext ctx) {
        if(!PlayerManager.isWalking()) {
            banker.interact("Bank");

            if(ctx.bank().isOpen()) {
               ctx.bank().depositInventory();
            }

        }
    }

    public static void withdrawItem(APIContext ctx, String item) {
        banker.interact("Bank");

        if(ctx.bank().isOpen() && hasEquipmentInBank(ctx, item)) {
            ctx.bank().withdraw(1, item);
        }
        if(InventoryManager.hasFishingEquipmentInventory() && ctx.bank().isOpen()) {
            ctx.bank().close();
        }
    }

    public static void openBank(APIContext ctx) {
        while(!ctx.bank().isOpen()) {
            if(!PlayerManager.isWalking() || !PlayerManager.isInteracting()) {
                banker.interact("Bank");
            }
            Time.sleep(1800, 3600);
        }
        System.out.println("Couldn't open bank");
    }

    public static boolean closeBank(APIContext ctx) {
        return ctx.bank().close();
    }

    public static boolean isBankOpen(APIContext ctx) {
        return ctx.bank().isOpen();
    }
    private static boolean hasEquipmentInBank(APIContext ctx, String item) {
        return ctx.bank().contains(item);
    }

}
