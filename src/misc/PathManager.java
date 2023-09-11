package misc;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.entity.SceneObject;
import com.epicbot.api.shared.model.Area;
import com.epicbot.api.shared.model.Tile;
import com.epicbot.api.shared.util.time.Time;
import com.epicbot.api.shared.webwalking.model.WebPath;

public class PathManager {

    public static Area fishingSpot = new Area(
            new Tile[] {
                    new Tile(3240, 3155, 0),
                    new Tile(3245, 3153, 0),
                    new Tile(3242, 3149, 0)
            }
    );

    public static Area treeOakSpot = new Area(3158, 3417, 3171, 3400);

    private static WebPath getWebPath(APIContext ctx, Tile tile) {
        return ctx.webWalking().getPath(tile);
    }

    public static void walkToPath(APIContext ctx, Tile tile) {
        ctx.webWalking().walkPath(getWebPath(ctx, tile));
    }
    public static void walkToNearestBank(APIContext ctx) {
        ctx.webWalking().walkPath(ctx.webWalking().getPathToNearestBank());
    }

    public static void walkToLumbridgeBank(APIContext ctx) {
        SceneObject staircase = ctx.objects().query().named("Staircase").results().nearest();
        ctx.webWalking().walkPath(getWebPath(ctx, new Tile(3206, 3209, 0)));
        if(staircase != null) {
            staircase.interact("Climb-up");

        }
        if (staircase != null && staircase.hasAction("Climb-down")) {
            staircase.interact("Climb-up");
        }
        if (staircase != null && !staircase.hasAction("Climb-up")) {
            walkToNearestBank(ctx);
        }
    }

    public static void walkOutOfLumbridgeCastle(APIContext ctx) {
        SceneObject staircase = ctx.objects().query().named("Staircase").results().nearest();

        ctx.webWalking().walkPath(getWebPath(ctx, new Tile(3206, 3210, 2)));


        if (staircase != null) {
            staircase.interact("Climb-down");
        }
        Time.sleep(1200);
        if (staircase != null && staircase.hasAction("Climb-down")) {
            staircase.interact("Climb-down");
        }

    }
}
