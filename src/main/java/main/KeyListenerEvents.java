package main;

import attributes.KeyPressAttribute;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.google.common.base.Stopwatch;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


class KeyListenerEvents implements NativeKeyListener {

    ArrayList<Integer> keysActive = new ArrayList<>();
    ArrayList<ActionInfo> listOfActions;
    Stopwatch localStopWatch;
    String fileName;

    public KeyListenerEvents(ArrayList<ActionInfo> listOfActions, Stopwatch globalStopWatch, String filename) {
        this.listOfActions = listOfActions;
        localStopWatch = globalStopWatch;
        this.fileName = filename;
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeEvent) {
        int buttonType = convertToKeyEvent(NativeKeyEvent.getKeyText(nativeEvent.getKeyCode()).toLowerCase());
        if (buttonType == KeyEvent.VK_ESCAPE) {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            String jsonString = gson.toJson(listOfActions);
            try (BufferedWriter b_writer = new BufferedWriter(new FileWriter("src/main/java/MacroList/" + fileName))) {
                b_writer.write(jsonString);
            } catch (IOException e) {
                throw new RuntimeException();
            }
            Listeners.addWait(localStopWatch, listOfActions);
            System.exit(1);
        }
        //check whether some key is already pressed down, so I don't duplicate keyPressed Events
        for (int i = 0; i < keysActive.size(); i++) {
            if (keysActive.get(i).equals(buttonType)) {
                return;
            }
        }
        Listeners.addWait(localStopWatch, listOfActions);

        ActionInfo info = new ActionInfo(Action.KEYBOARD_KEY_PRESSED, new KeyPressAttribute(buttonType));
        keysActive.add(buttonType);
        listOfActions.add(info);
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeEvent) {
        int buttonType = convertToKeyEvent(NativeKeyEvent.getKeyText(nativeEvent.getKeyCode()).toLowerCase());
        Listeners.addWait(localStopWatch, listOfActions);
        ActionInfo info = new ActionInfo(Action.KEYBOARD_KEY_RELEASED, new KeyPressAttribute(buttonType));
        listOfActions.add(info);
        for (int i = 0; i < keysActive.size(); i++) {
            if (keysActive.get(i) == buttonType) {
                keysActive.remove(i);
                break;
            }
        }
    }

    private int convertToKeyEvent(String key) {
        switch (key) {
            case "a":
                return KeyEvent.VK_A;
            case "b":
                return KeyEvent.VK_B;
            case "c":
                return KeyEvent.VK_C;
            case "d":
                return KeyEvent.VK_D;
            case "e":
                return KeyEvent.VK_E;
            case "f":
                return KeyEvent.VK_F;
            case "g":
                return KeyEvent.VK_G;
            case "h":
                return KeyEvent.VK_H;
            case "i":
                return KeyEvent.VK_I;
            case "j":
                return KeyEvent.VK_J;
            case "k":
                return KeyEvent.VK_K;
            case "l":
                return KeyEvent.VK_L;
            case "m":
                return KeyEvent.VK_M;
            case "n":
                return KeyEvent.VK_N;
            case "o":
                return KeyEvent.VK_O;
            case "p":
                return KeyEvent.VK_P;
            case "q":
                return KeyEvent.VK_Q;
            case "r":
                return KeyEvent.VK_R;
            case "s":
                return KeyEvent.VK_S;
            case "t":
                return KeyEvent.VK_T;
            case "u":
                return KeyEvent.VK_U;
            case "v":
                return KeyEvent.VK_V;
            case "w":
                return KeyEvent.VK_W;
            case "x":
                return KeyEvent.VK_X;
            case "y":
                return KeyEvent.VK_Y;
            case "z":
                return KeyEvent.VK_Z;

            case "0":
                return KeyEvent.VK_0;
            case "1":
                return KeyEvent.VK_1;
            case "2":
                return KeyEvent.VK_2;
            case "3":
                return KeyEvent.VK_3;
            case "4":
                return KeyEvent.VK_4;
            case "5":
                return KeyEvent.VK_5;
            case "6":
                return KeyEvent.VK_6;
            case "7":
                return KeyEvent.VK_7;
            case "8":
                return KeyEvent.VK_8;
            case "9":
                return KeyEvent.VK_9;

            case "escape":
                return KeyEvent.VK_ESCAPE;
        }
        throw new RuntimeException();
    }
}
