public class KeyInfo extends InputInfo{
    String keyValue;
    KeyInfo(String keyValue, long timeAfterAction){
        this.keyValue = keyValue;
        this.timeAfterAction = timeAfterAction;
    }
    public String toString(){
        return String.format("{Input Value:%s}; {TotalTimeForAction: %d}; {TimeAfterAction: %d}", keyValue,totalTimeForAction, timeAfterAction);
    }
}
