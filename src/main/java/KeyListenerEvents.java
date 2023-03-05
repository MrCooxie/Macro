import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.google.common.base.Stopwatch;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class KeyListenerEvents implements NativeKeyListener {

    Stopwatch localStopWatch;
    ArrayList<InputInfo> code;

    ArrayList<KeyInfo> keyPresses = new ArrayList<>();

    String fileName;
    KeyListenerEvents( ArrayList<InputInfo> code, Stopwatch localStopWatch, String fileName){
        this.code = code;
        this.localStopWatch = localStopWatch;
        this.fileName = fileName;
    }


    @Override
    public void nativeKeyPressed(NativeKeyEvent event){
        String keyPressed = NativeKeyEvent.getKeyText(event.getKeyCode()).toLowerCase();
        if(keyPressed.equals("escape")){
            ArrayList<InputInfoDTO> codeDTO = new ArrayList<>();
            for(int i = 0; i< code.size();i++){
                codeDTO.add(new InputInfoDTO(code.get(i)));
            }
                GsonBuilder builder = new GsonBuilder();
                builder.setPrettyPrinting();
                Gson gson = builder.create();
                String jsonString = gson.toJson(codeDTO);
                System.out.println(jsonString);
                InputInfoDTO[] infoDTOS = gson.fromJson(jsonString, InputInfoDTO[].class);
                try(BufferedWriter b_writer = new BufferedWriter(new FileWriter("src/main/java/MacroList/" + fileName))){
                b_writer.write(jsonString);
            } catch (IOException e){
                throw new RuntimeException();
            }
            System.exit(1);
        }

        boolean isInKeyPresses = false;
        for(int i = 0; i < keyPresses.size(); i++){
            KeyInfo keyInfo = keyPresses.get(i);
            if(keyInfo.inputValue.equals(keyPressed)){
                isInKeyPresses = true;
                break;
            }
        }
        if(!isInKeyPresses){
            keyPresses.add(new KeyInfo(keyPressed,localStopWatch.stop().elapsed(TimeUnit.MILLISECONDS)));
        }

    }
    @Override
    public void nativeKeyReleased(NativeKeyEvent event){
        String keyPressed = NativeKeyEvent.getKeyText(event.getKeyCode()).toLowerCase();
       for(int i = 0; i < keyPresses.size();i++){
           KeyInfo keyInfo = keyPresses.get(i);
           if(keyInfo.inputValue.equals(keyPressed)){
               keyPresses.remove(i);
               keyInfo.totalTimeForAction += keyInfo.stopwatch.elapsed(TimeUnit.MILLISECONDS);
                code.add(keyInfo);
                localStopWatch.reset().start();
               break;
           }
       }
        System.out.println(code);
    }
}
