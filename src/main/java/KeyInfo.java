import com.google.common.base.Stopwatch;

public class KeyInfo extends InputInfo{

    KeyInfo(String keyValue, long timeAfterAction){
        super(keyValue,timeAfterAction);
        inputValue = keyValue;
        this.timeAfterAction = timeAfterAction;
    }
}
