import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseMotionListener;
import com.google.common.base.Stopwatch;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MouseListenerEvents implements NativeMouseListener, NativeMouseMotionListener {

    ArrayList<ActionInfo> listOfActions;
    Stopwatch localStopWatch;
    public MouseListenerEvents(ArrayList<ActionInfo> listOfActions, Stopwatch globalStopWatch){
        this.listOfActions = listOfActions;
        localStopWatch = globalStopWatch;
    }

    @Override
    public void nativeMousePressed(NativeMouseEvent nativeEvent) {
        addWait();
        ArrayList<CoordinateInfo> coordinateInfos = new ArrayList<>();
        ActionInfo info = new ActionInfo(Action.MOUSE_CLICK_PRESSED, new Attribute(String.valueOf(nativeEvent.getButton()),coordinateInfos));
        listOfActions.add(info);
        printListOfActionInfo();
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent nativeEvent) {
        addWait();
      //TODO The Draggining Event = moving event, then add one Action to be MOUSE_MOVE, delete coordinates variable in Attribute.
    }
    @Override
    public void nativeMouseDragged(NativeMouseEvent nativeEvent) {
    }
    public void addWait(){
        ActionInfo info = new ActionInfo(Action.WAIT, new Attribute(localStopWatch.stop().elapsed(TimeUnit.MILLISECONDS)));
        listOfActions.add(info);
        localStopWatch.reset().start();
    }
    public void printListOfActionInfo(){
        for(int i = 0; i < listOfActions.size();i++){
            Action action = listOfActions.get(i).action;
            Attribute attribute = listOfActions.get(i).attribute;
            System.out.printf("ActionType:%s; ButtonType:%s; Duration:%d\n",action.toString(),attribute.buttonType, attribute.duration);
        }
    }

}
