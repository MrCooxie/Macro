import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.google.common.base.Stopwatch;

import java.util.ArrayList;

public class Recorder {
    public static void main(String[] args){


       try {
            GlobalScreen.registerNativeHook();
        } catch(NativeHookException exception){
            System.err.println("There was an error registering the events");
            System.err.println(exception.getMessage());
            System.exit(1);
        }
        ArrayList<ActionInfo> arrayList = new ArrayList<>();
        Stopwatch stopwatch = Stopwatch.createStarted();
        KeyListenerEvents keyListenerEvents = new KeyListenerEvents(arrayList, stopwatch);
        MouseListenerEvents mouseListenerEvent = new MouseListenerEvents(arrayList,stopwatch);

        GlobalScreen.addNativeMouseListener(mouseListenerEvent);
        GlobalScreen.addNativeMouseMotionListener(mouseListenerEvent);
        GlobalScreen.addNativeKeyListener(keyListenerEvents);
    }

}
