import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseMotionListener;
import com.google.common.base.Stopwatch;
import java.util.ArrayList;

public class MouseListenerEvents implements NativeMouseListener, NativeMouseMotionListener {

    ArrayList<ActionInfo> listOfActions;
    Stopwatch localStopWatch;
    public MouseListenerEvents(ArrayList<ActionInfo> listOfActions, Stopwatch globalStopWatch){
        this.listOfActions = listOfActions;
        localStopWatch = globalStopWatch;
    }

    @Override
    public void nativeMousePressed(NativeMouseEvent nativeEvent) {
        Listeners.addWait(localStopWatch,listOfActions);
        ActionInfo info1 = new ActionInfo(Action.MOUSE_MOVE, new Attribute(new CoordinateInfo(nativeEvent.getX(),nativeEvent.getY())));
        ActionInfo info2 = new ActionInfo(Action.MOUSE_CLICK_PRESSED, new Attribute(nativeEvent.getButton()));
        listOfActions.add(info1);
        listOfActions.add(info2);
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent nativeEvent) {
        Listeners.addWait(localStopWatch,listOfActions);
        ActionInfo info = new ActionInfo(Action.MOUSE_CLICK_RELEASED, new Attribute(nativeEvent.getButton()));
        listOfActions.add(info);
      //TODO The Draggining Event = moving event, then add one Action to be MOUSE_MOVE, delete coordinates variable in Attribute.
    }
    @Override
    public void nativeMouseDragged(NativeMouseEvent nativeEvent) {
        Listeners.addWait(localStopWatch,listOfActions);
        ActionInfo info = new ActionInfo(Action.MOUSE_MOVE,new Attribute(new CoordinateInfo(nativeEvent.getX(),nativeEvent.getY())));
        listOfActions.add(info);
    }

}
