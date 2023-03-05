import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.google.common.base.Stopwatch;

import java.util.ArrayList;

public class Recorder {
    public static void main(String[] args){
        Player player = new Player("Test");
        /*try {
            GlobalScreen.registerNativeHook();
        } catch(NativeHookException exception){
            System.err.println("There was an error registering the events");
            System.err.println(exception.getMessage());
            System.exit(1);
        }
        Stopwatch globalStopWatch = Stopwatch.createStarted();
        ArrayList<InputInfo> code = new ArrayList<>();

        KeyListenerEvents keyListenerEvents = new KeyListenerEvents(code,globalStopWatch, "Test");
        MouseListenerEvents mouseListenerEvent = new MouseListenerEvents(code, globalStopWatch);

        GlobalScreen.addNativeMouseListener(mouseListenerEvent);
        GlobalScreen.addNativeMouseMotionListener(mouseListenerEvent);
        GlobalScreen.addNativeKeyListener(keyListenerEvents);*/
    }
}
