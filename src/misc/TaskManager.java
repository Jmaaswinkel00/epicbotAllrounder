package misc;

import com.epicbot.api.shared.APIContext;
import lombok.Getter;
import skills.Fishing;
import skills.Woodcutting;

import java.util.ArrayList;

@Getter
public class TaskManager {
    private static ArrayList<String> tasksDone;
    private static final APIContext ctx = APIContext.get();
    @Getter
    private static String currentTask;
    private static ArrayList<String> tasksToChooseFrom = new ArrayList<>();

    public static String decideTask() {
        filltasklist();
        return tasksToChooseFrom.get(randomTaskNumber());
    }

    public void setTasksDone(String taskName) {
        tasksDone.add(taskName);
    }

    public static void setCurrentTask(String taskName) {
        currentTask = taskName;
    }

    public void setTasksToChooseFrom(String taskName) {
        tasksToChooseFrom.add(taskName);
    }

    private static void filltasklist() {
        tasksToChooseFrom.add("fishing");
        tasksToChooseFrom.add("woodcutting");
    }
    private static int randomTaskNumber() {
        return (int) (Math.random() * tasksToChooseFrom.toArray().length);
    }

    public static void prepareTask() {
        switch (currentTask) {
            case "fishing":
                prepareFishingTask();
                break;
            case "mining":
                prepareMiningTask();
                break;
            case "woodcutting":
                prepareWoodcuttingTask();
                break;
            case "smithing":
                prepareSmithingTask();
                break;
            default:
                break;
        }
    }

    public static void executeTask() {
        switch (currentTask) {
            case "fishing":
                executeFishingTask();
                break;
            case "mining":
                executeMiningTask();
                break;
            case "woodcutting":
                executeWoodcuttingTask();
                break;
            case "smithing":
                executeSmithingTask();
                break;
            default:
                break;
        }
    }

    //preparing the tasks
    private static void prepareFishingTask() {
        Fishing fishing = new Fishing();
        fishing.prepareFishingTask(ctx);
    }

    private static void prepareMiningTask() {

    }

    private static void prepareWoodcuttingTask() {
        Woodcutting woodcutting = new Woodcutting();
        woodcutting.prepareWoodcuttingTask(ctx);
    }

    private static void prepareSmithingTask() {

    }

    //executing the tasks
    private static void executeFishingTask() {
        Fishing fishing = new Fishing();
        fishing.doFishingTask(ctx);
    }

    private static void executeMiningTask() {

    }

    private static void executeWoodcuttingTask() {
        Woodcutting woodcutting = new Woodcutting();
        woodcutting.doWoodcuttingTask(ctx);
    }

    private static void executeSmithingTask() {

    }
}
