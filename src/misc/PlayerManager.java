package misc;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.methods.ILocalPlayerAPI;

public class PlayerManager {
    private static final ILocalPlayerAPI localPlayer = APIContext.get().localPlayer();



    public static boolean isInteracting() {
        return localPlayer.isAnimating();
    }

    public static boolean isWalking() {
        return localPlayer.isMoving();
    }

    public static boolean isNotAtBank(APIContext ctx) {
        return ctx.npcs().query().named("Banker").results().isEmpty();
    }

}
