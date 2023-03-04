import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.google.common.base.Stopwatch;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class KeyListenerEvents implements NativeKeyListener {

    Stopwatch localStopWatch;
    StringBuilder code;

    ArrayList<KeyInfo> keyPresses = new ArrayList<>();

    String fileName;
    KeyListenerEvents(StringBuilder code, Stopwatch localStopWatch, String fileName){
        this.code = code;
        this.localStopWatch = localStopWatch;
        this.fileName = fileName;
    }


    @Override
    public void nativeKeyPressed(NativeKeyEvent event){
        String keyPressed = NativeKeyEvent.getKeyText(event.getKeyCode()).toLowerCase();
        if(keyPressed.equals("escape")){
            try {
                BufferedWriter b_writer = new BufferedWriter(new FileWriter(String.format("src/main/java/MacroList/%s", fileName)));
                b_writer.write(code.toString());
                b_writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.exit(1);
        }

        boolean isInKeyPresses = false;
        for(int i = 0; i < keyPresses.size(); i++){
            KeyInfo keyInfo = keyPresses.get(i);
            if(keyInfo.keyValue.equals( keyPressed)){
                isInKeyPresses = true;
                break;
            }
        }
        if(!isInKeyPresses){
            keyPresses.add(new KeyInfo(keyPressed,String.format("{%d}",localStopWatch.stop().elapsed(TimeUnit.MILLISECONDS))));
        }


    }
    @Override
    public void nativeKeyReleased(NativeKeyEvent event){
        String keyPressed = NativeKeyEvent.getKeyText(event.getKeyCode()).toLowerCase();
       for(int i = 0; i < keyPresses.size();i++){
           KeyInfo keyInfo = keyPresses.get(i);
           if(keyInfo.keyValue.equals(keyPressed)){
               keyPresses.remove(i);
                code.append(String.format("A{K%s{%d}{%d}}",keyInfo.keyValue,localStopWatch.elapsed(TimeUnit.MILLISECONDS),keyInfo.stopwatch.elapsed(TimeUnit.MILLISECONDS)));
                localStopWatch.reset().start();
               break;
           }
       }
    }
}
