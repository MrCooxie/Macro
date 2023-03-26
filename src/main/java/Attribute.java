import com.google.common.base.Stopwatch;
public class Attribute {
    int buttonType;
    long duration;
    CoordinateInfo coordinates;
    public Attribute(int buttonType, long duration, CoordinateInfo coordinates){
        this.buttonType = buttonType;
        this.duration = duration;
        this.coordinates = coordinates;

    }
    public Attribute(long duration){
        this(-1,duration,null);
    }
    public Attribute(int buttonType){
        this(buttonType,0,null);
    }
    public Attribute(CoordinateInfo coordinates){
        this(-1,0,coordinates);
    }

}
