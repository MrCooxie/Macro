import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Player {

    Player(String fileName){
        play(fileName);
    }
    private void play(String fileName){
        ArrayList<InputInfoDTO> Actions;
        Gson gson = new Gson();
        try(JsonReader jsonReader = gson.newJsonReader(new FileReader("src/main/java/MacroList/" + fileName))){
            Actions = readActionsArray(jsonReader);
            System.out.println(Actions);
        } catch (IOException e){
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }
    private ArrayList<InputInfoDTO> readActionsArray(JsonReader jsonReader) throws IOException {
        ArrayList<InputInfoDTO> code = new ArrayList<>();
        jsonReader.beginArray();
        while(jsonReader.hasNext()){
            code.add(readMessage(jsonReader));
        }
        jsonReader.endArray();
        return code;
    }
    private InputInfoDTO readMessage(JsonReader jsonReader) throws IOException {
        String inputValue = null;
        int totalTimeForAction = -1;
        int timeAfterAction = -1;
        ArrayList<ActionAttributeData> actionAttributeData = null;

        jsonReader.beginObject();
        System.out.println(jsonReader.peek());
        while (jsonReader.hasNext()){
            String name = jsonReader.nextName();
            switch (name) {
                case "inputValue":
                    inputValue = jsonReader.nextString();
                    break;
                case "code":
                    if(jsonReader.peek() != JsonToken.NULL){
                        actionAttributeData = readActionAttributeArray(jsonReader);
                    }
                    break;
                case "totalTimeForAction":
                    totalTimeForAction = jsonReader.nextInt();
                    break;
                case "timeAfterAction":
                    timeAfterAction = jsonReader.nextInt();
                default:
                    jsonReader.skipValue();
                    break;
            }
        }
        jsonReader.endObject();
        InputInfo inputInfo = new InputInfo(inputValue,actionAttributeData,totalTimeForAction,timeAfterAction);
        System.out.println("Hello");
        return new InputInfoDTO(inputInfo);
    }
    private ArrayList<ActionAttributeData> readActionAttributeArray(JsonReader jsonReader) throws IOException {
        ArrayList<ActionAttributeData> code = new ArrayList<>();
        jsonReader.beginArray();
        while(jsonReader.hasNext()){
            code.add(readActionAttribute(jsonReader));
        }
        jsonReader.endArray();
        return code;
    }
    private ActionAttributeData readActionAttribute(JsonReader jsonReader) throws IOException {
        int XCoordinate = -1;
        int YCoordinate = -1;
        long Time = -1;
        jsonReader.beginObject();
        while ( jsonReader.hasNext()) {
            String name = jsonReader.nextName();
            switch (name) {
                case "XCoordinate":
                    XCoordinate = jsonReader.nextInt();
                    break;
                case "YCoordinate":
                    YCoordinate = jsonReader.nextInt();
                    break;
                case "time": //TODO replace with "Time" later
                    Time = jsonReader.nextLong();
                    break;
                default:
                    jsonReader.skipValue();
            }
        }
            jsonReader.endObject();
            return new ActionAttributeData(XCoordinate,YCoordinate,Time);
    }
}
