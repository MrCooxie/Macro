import com.google.common.base.Stopwatch;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

class Listeners {
    private Listeners(){

    }
    public static void printListOfActionInfo(ArrayList<ActionInfo> listOfActions) {
        for (int i = 0; i < listOfActions.size(); i++) {
            Action action = listOfActions.get(i).action;
            Attribute attribute = listOfActions.get(i).attribute;
            System.out.printf("ActionType:%s; ButtonType:%s; Duration:%d\n", action.toString(), attribute.buttonType, attribute.duration);
        }
    }
    public static void printListOfActionInfo(ActionInfo[] listOfActions) {
        for (int i = 0; i < listOfActions.length; i++) {
            Action action = listOfActions[i].action;
            Attribute attribute = listOfActions[i].attribute;
            System.out.printf("ActionType:%s; ButtonType:%s; Duration:%d\n", action.toString(), attribute.buttonType, attribute.duration);
        }
    }
    public static void addWait(Stopwatch localStopWatch, ArrayList<ActionInfo> listOfActions){
        ActionInfo info = new ActionInfo(Action.WAIT, new Attribute(localStopWatch.stop().elapsed(TimeUnit.MILLISECONDS)));
        listOfActions.add(info);
        localStopWatch.reset().start();
    }
}
