package main;

import attributes.Attribute;
import attributes.AttributeTypeDisplayVisitor;
import attributes.AttributeTypeVisitor;
import attributes.WaitAttribute;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.google.common.base.Stopwatch;

import java.util.ArrayList;

public class Recorder {
    public static void main(String[] args){

        Attribute attribute = new WaitAttribute(2);
        attribute.accept(new AttributeTypeDisplayVisitor());

      try {
            GlobalScreen.registerNativeHook();
        } catch(NativeHookException exception){
            System.err.println("There was an error registering the events");
            System.err.println(exception.getMessage());
            System.exit(1);
        }
        ArrayList<ActionInfo> arrayList = new ArrayList<>();
        Stopwatch stopwatch = Stopwatch.createStarted();
        KeyListenerEvents keyListenerEvents = new KeyListenerEvents(arrayList, stopwatch, "Test");
        MouseListenerEvents mouseListenerEvent = new MouseListenerEvents(arrayList,stopwatch);

        GlobalScreen.addNativeMouseListener(mouseListenerEvent);
        GlobalScreen.addNativeMouseMotionListener(mouseListenerEvent);
        GlobalScreen.addNativeKeyListener(keyListenerEvents);
       // Main.Player.play("C:\\Users\\user\\Documents\\AllProgrammingProjects\\Java Programs\\Macro\\src\\main\\java\\MacroList\\Test");
    }
}
