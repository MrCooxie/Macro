import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;

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
        KeyListenerEvents keyListenerEvents = new KeyListenerEvents(arrayList);
        MouseListenerEvents mouseListenerEvent = new MouseListenerEvents();

        GlobalScreen.addNativeMouseListener(mouseListenerEvent);
        GlobalScreen.addNativeMouseMotionListener(mouseListenerEvent);
        GlobalScreen.addNativeKeyListener(keyListenerEvents);
    }
}
