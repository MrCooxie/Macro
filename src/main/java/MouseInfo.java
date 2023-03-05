import java.util.ArrayList;

public class MouseInfo extends InputInfo {
    MouseInfo(int clickValue, long timeAfterAction, int X, int Y, long T){
        this.actionAttributes = new ArrayList<>();
        inputValue = String.valueOf(clickValue);
        this.actionAttributes.add(new ActionAttributeData(X,Y,T));
        this.timeAfterAction = timeAfterAction;
    }
}
