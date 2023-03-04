import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseInputListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseMotionListener;
import com.google.common.base.Stopwatch;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MouseListenerEvents implements NativeMouseInputListener, NativeMouseMotionListener {
    ArrayList<MouseInfo> mousePresses = new ArrayList<>();
    Stopwatch localStopWatch;
    StringBuilder code;

    MouseListenerEvents(StringBuilder code, Stopwatch localStopWatch) {
        this.code = code;
        this.localStopWatch = localStopWatch;
    }

    @Override
    public void nativeMousePressed(NativeMouseEvent event) {
        int clickButton = event.getButton();
        mousePresses.add(new MouseInfo(clickButton,String.format("{%d}",localStopWatch.elapsed(TimeUnit.MILLISECONDS)),String.format("(X:%d; Y:%d; T:%d)",event.getX(),event.getY(), 0)));
        localStopWatch.reset().start();


    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent event) {
        int clickButton = event.getButton();
        for(int i = 0; i < mousePresses.size();i++){
            MouseInfo mouseInfo = mousePresses.get(i);
            if(mouseInfo.clickValue == clickButton){
                mousePresses.remove(i);
                mouseInfo.totalTimeForAction += mouseInfo.stopwatch.elapsed(TimeUnit.MILLISECONDS);
                code.append(String.format("A{%s]{%d}}",mouseInfo.code,mouseInfo.totalTimeForAction));
                break;
            }
        }
    }

    @Override
    public void nativeMouseDragged(NativeMouseEvent event) {
        int buttonClick = (int) (Math.log(event.getModifiers()) / Math.log(2)) - 7;
        for(int i = 0; i < mousePresses.size();i++){
                MouseInfo mouseInfo = mousePresses.get(i);
                if(mouseInfo.clickValue == buttonClick){
                    long time = mouseInfo.stopwatch.stop().elapsed(TimeUnit.MILLISECONDS);
                    mouseInfo.totalTimeForAction +=  time;
                    mouseInfo.code.append(String.format(",(X:%d; Y:%d; T:%d)",event.getX(),event.getY(),time));
                    mouseInfo.stopwatch.reset().start();
                    break;
                }
            }
    }
}
