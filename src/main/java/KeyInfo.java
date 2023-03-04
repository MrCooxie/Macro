import com.google.common.base.Stopwatch;

public class KeyInfo {

    String keyValue;
    StringBuilder code;
    Stopwatch stopwatch = Stopwatch.createStarted();
    KeyInfo(String keyValue, String time){
        this.keyValue = keyValue;
        this.code = new StringBuilder("K" + keyValue + time);
    }
    @Override
    public String toString(){
        return String.format("{Click Value:%s}; {Code: %s};", keyValue,code.toString());
    }
}
