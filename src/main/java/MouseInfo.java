import com.google.common.base.Stopwatch;

import java.util.ArrayList;

public class MouseInfo extends InputInfo {
    int clickValue;
    ArrayList<String> code = new ArrayList<>();
    MouseInfo(int clickValue, long timeAfterAction, String code){
        this.clickValue = clickValue;
        this.code.add(code);
        this.timeAfterAction = timeAfterAction;
    }
    public String toString(){
        return String.format("{Input Value:%d}; {Code: %s}; {TotalTimeForAction: %d}; {TimeAfterAction: %d}", clickValue,code, totalTimeForAction, timeAfterAction);
    }
}
