package Main;

import Main.Action;
import Main.ActionInfo;
import TypesOfAttributes.Attribute;
import TypesOfAttributes.MouseMoveAttribute;
import TypesOfAttributes.WaitAttribute;
import com.google.common.base.Stopwatch;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

class Listeners {
    private Listeners(){

    }
    public static void addWait(Stopwatch localStopWatch, ArrayList<ActionInfo> listOfActions){
        ActionInfo info = new ActionInfo(Action.WAIT, new WaitAttribute(localStopWatch.stop().elapsed(TimeUnit.MILLISECONDS)));
        listOfActions.add(info);
        localStopWatch.reset().start();
    }
}
