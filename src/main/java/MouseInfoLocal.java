import java.util.ArrayList;

public class MouseInfoLocal extends InputInfo {
    MouseInfoLocal(int clickValue, long timeAfterAction, int X, int Y, long T){
        super(String.valueOf(clickValue),timeAfterAction);
        this.actionAttributes = new ArrayList<>();
        this.actionAttributes.add(new ActionAttributeData(X,Y,T));
        this.timeAfterAction = timeAfterAction;
    }
}
