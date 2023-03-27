package main;

import attributes.Attribute;
import attributes.AttributeTypeDisplayVisitor;
import attributes.WaitAttribute;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class Player {
    private Player(){

    }
    public static void play(String fileName){

        Robot robot;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        ActionInfo[] actions = deserialize(fileName);
       for(int i = 0; i < actions.length;i++){
           ActionInfo action = actions[i];
           switch(action.action){
               case WAIT ->{
                   Attribute attribute = action.attribute;
                   long delay = attribute.accept(new AttributeTypeDisplayVisitor());
               }
               case MOUSE_CLICK_PRESSED -> robot.mousePress(InputEvent.getMaskForButton(action.attribute.buttonType));
               case MOUSE_CLICK_RELEASED -> robot.mouseRelease(InputEvent.getMaskForButton(action.attribute.buttonType));
               case MOUSE_MOVE -> robot.mouseMove(action.attribute.coordinates.X_Coordinate, action.attribute.coordinates.Y_Coordinate);
               case KEYBOARD_KEY_PRESSED -> robot.keyPress(action.attribute.buttonType);
               case KEYBOARD_KEY_RELEASED -> robot.keyRelease(action.attribute.buttonType);
           }

       }
    }
    private static ActionInfo[] deserialize(String fileName){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        String jsonString;
        try {
            jsonString = Files.readString(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ActionInfo[] actions = gson.fromJson(jsonString,ActionInfo[].class);
        Listeners.printListOfActionInfo(actions);
        return actions;
    }
}
