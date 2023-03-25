import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.google.common.base.Stopwatch;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


class KeyListenerEvents implements NativeKeyListener {

    ArrayList<String> keysActive = new ArrayList<>();
    ArrayList<ActionInfo> listOfActions;
    Stopwatch localStopWatch;
    public KeyListenerEvents(ArrayList<ActionInfo> listOfActions, Stopwatch globalStopWatch){
        this.listOfActions = listOfActions;
        localStopWatch = globalStopWatch;
    }
    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeEvent) {
        String buttonType = NativeKeyEvent.getKeyText(nativeEvent.getKeyCode()).toLowerCase();
        //check whether some key is already pressed down, so I don't duplicate keyPressed Events
        for(int i = 0; i < keysActive.size();i++){
            if(keysActive.get(i).equals(buttonType)) {
                return;
            }
        }
        addWait();
        ActionInfo info = new ActionInfo(Action.KEYBOARD_KEY_PRESSED, new Attribute(buttonType));
        keysActive.add(buttonType);
        listOfActions.add(info);
    }
    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeEvent) {
        String buttonType = NativeKeyEvent.getKeyText(nativeEvent.getKeyCode()).toLowerCase();
        addWait();
        ActionInfo info = new ActionInfo(Action.KEYBOARD_KEY_RELEASED, new Attribute((NativeKeyEvent.getKeyText(nativeEvent.getKeyCode()).toLowerCase())));
        listOfActions.add(info);
        for(int i = 0; i < keysActive.size(); i++){
            if(keysActive.get(i).equals(buttonType)){
                keysActive.remove(i);
                break;
            }
        }
        printListOfActionInfo();
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
