import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.GameType;
import com.epicbot.api.shared.script.LoopScript;
import com.epicbot.api.shared.script.ScriptManifest;
import com.epicbot.api.shared.util.paint.frame.PaintFrame;
import com.epicbot.api.shared.util.time.Time;
import constants.TaskState;
import misc.TaskManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static kotlin.random.RandomKt.Random;

@ScriptManifest(name = "Flubby allrounder", gameType = GameType.OS)
public class Main extends LoopScript {
    private long startTime;
    boolean isMember;
    TaskState taskState = TaskState.CHOOSING_TASK;
    @Override
    public boolean onStart(String... strings) {
        startTime = System.currentTimeMillis();
        isMember = getAPIContext().localPlayer().hasMembership();
        return true;
    }

    @Override
    protected int loop() {

        switch (taskState) {
            case CHOOSING_TASK:
                TaskManager.setCurrentTask(TaskManager.decideTask());
                taskState = TaskState.TASK_CHOSEN;
                break;
            case TASK_CHOSEN:
                //inventaris legen, en klaarmaken voor task (juiste items pakken)
                TaskManager.prepareTask();
                taskState = TaskState.EXECUTING_TASK;
                break;

            case EXECUTING_TASK:
                TaskManager.executeTask();
                break;
        }
        return 1800;
    }

    @Override
    protected void onPaint(Graphics2D g, APIContext ctx) {
        PaintFrame frame = new PaintFrame("Flubby Allrounder");
        frame.addLine("Activity", TaskManager.getCurrentTask());
        frame.addLine("STATE", taskState);
        frame.addLine("Runtime: ", Time.getFormattedRuntime(startTime));
        frame.draw(g, 0, 170, ctx);
    }
}