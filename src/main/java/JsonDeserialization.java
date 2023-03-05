import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


class JsonDeserialization {


    static InputInfoDTO[] deserialize(String fileName) {
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            String jsonString = Files.readString(Path.of("src/main/java/MacroList/" + fileName));
            InputInfoDTO[] Actions = gson.fromJson(jsonString, InputInfoDTO[].class);
            return Actions;

        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    return null;
    }
}
