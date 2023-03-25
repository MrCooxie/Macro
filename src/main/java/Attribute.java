import com.google.common.base.Stopwatch;

import java.util.ArrayList;

public class Attribute {
    String buttonType = null;
    long duration = 0;

    Stopwatch stopwatch = Stopwatch.createUnstarted();
    ArrayList<CoordinateInfo> coordinateAttributes = null;

    public Attribute(String buttonType, ArrayList<CoordinateInfo> coordinateAttributes) {
        stopwatch.start();
        this.buttonType = buttonType;
        this.coordinateAttributes = coordinateAttributes;
    }
    public Attribute(String buttonType){
        stopwatch.start();
        this.buttonType = buttonType;

    }
    public Attribute(long duration){
        this.duration = duration;
    }

}
