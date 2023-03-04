import com.google.common.base.Stopwatch;
public class MouseInfo {
    int clickValue;
    StringBuilder code;
    Stopwatch stopwatch = Stopwatch.createStarted();
    int totalTimeForAction = 0;
    MouseInfo(int clickValue, String time, String code){
        this.clickValue = clickValue;
        this.code = new StringBuilder("M" + clickValue + time + "[" + code);
    }
    @Override
    public String toString(){
        return String.format("{Click Value:%d}; {Code: %s};", clickValue,code.toString());
    }
}
