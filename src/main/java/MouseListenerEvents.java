import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseInputListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseMotionListener;
import com.google.common.base.Stopwatch;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MouseListenerEvents implements NativeMouseInputListener, NativeMouseMotionListener {
    ArrayList<MouseInfoLocal> mousePresses = new ArrayList<>();
    Stopwatch localStopWatch;
    ArrayList<InputInfo> code;

    MouseListenerEvents( ArrayList<InputInfo> code, Stopwatch localStopWatch) {
        this.code = code;
        this.localStopWatch = localStopWatch;
    }

    @Override
    public void nativeMousePressed(NativeMouseEvent event) {
        int clickButton = event.getButton();
        mousePresses.add(new MouseInfoLocal(clickButton,localStopWatch.elapsed(TimeUnit.MILLISECONDS),event.getX(),event.getY(),0));
        localStopWatch.reset().start();


    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent event) {
        String clickButton = String.valueOf(event.getButton());
        for(int i = 0; i < mousePresses.size();i++){
            MouseInfoLocal mouseInfo = mousePresses.get(i);
            if(mouseInfo.inputValue.equals(clickButton)){
                mousePresses.remove(i);
                mouseInfo.totalTimeForAction += mouseInfo.stopwatch.elapsed(TimeUnit.MILLISECONDS);
                System.out.println();
                code.add(mouseInfo);
                break;
            }
        }
    }
    @Override
    public void nativeMouseDragged(NativeMouseEvent event) {
        String buttonClick = String.valueOf((int) (Math.log(event.getModifiers()) / Math.log(2)) - 7);
        for(int i = 0; i < mousePresses.size();i++){
                MouseInfoLocal mouseInfo = mousePresses.get(i);
                if(mouseInfo.inputValue.equals(buttonClick)){
                    long time = mouseInfo.stopwatch.stop().elapsed(TimeUnit.MILLISECONDS);
                    mouseInfo.totalTimeForAction +=  time;
                    mouseInfo.actionAttributes.add(new ActionAttributeData(event.getX(),event.getY(),time));
                    mouseInfo.stopwatch.reset().start();
                    break;
                }
            }
    }
}
