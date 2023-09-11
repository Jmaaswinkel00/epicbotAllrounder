package misc;

import com.epicbot.api.shared.APIContext;

public class SkillsManager {

    public static int checkFishingLevel(APIContext ctx) {
        return ctx.skills().fishing().getCurrentLevel();
    }
}
