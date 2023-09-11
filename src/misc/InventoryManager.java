package misc;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.methods.IInventoryAPI;
import com.epicbot.api.shared.methods.ILocalPlayerAPI;

public class InventoryManager {
    private static final IInventoryAPI inventory = APIContext.get().inventory();
    public static boolean isInventoryFull() {
        return inventory.isFull();
    }

    public static boolean onlyContainsRightEquipment(String item) {
        return inventory.onlyContains(item);
    }

    public static boolean hasFishingEquipmentInventory() {
        return inventory.contains("Small fishing net");
    }

    public static boolean hasWoodcuttingEquipmentInventory() {
        return inventory.contains("Bronze axe");
    }

    public static boolean isInventoryEmpty() {
        return inventory.isEmpty();
    }
    public static boolean isInventoryEmptyExcept(String... item) {
        return inventory.onlyContains(item);
    }
}
