import com.google.common.base.Stopwatch;

import java.util.ArrayList;

 class InputInfo {


    String inputValue;

   ArrayList<ActionAttributeData> actionAttributes = null;
   int totalTimeForAction = 0;
   long timeAfterAction;
     Stopwatch stopwatch = Stopwatch.createStarted();

     @Override
    public String toString() {
       return String.format("InputInfo [ class: %s, inputValue: %s, code: %s, totalTimeForAction: %d, timeAfterAction: %d", this.getClass().getSimpleName(),inputValue, actionAttributes,totalTimeForAction,timeAfterAction);
    }

}
