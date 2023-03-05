import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Stopwatch;

import java.util.ArrayList;

 class InputInfo {


      InputInfo(String inputValue, ArrayList<ActionAttributeData> actionAttributes, int totalTimeForAction, long timeAfterAction) {
         this.inputValue = inputValue;
         this.actionAttributes = actionAttributes;
         this.totalTimeForAction = totalTimeForAction;
         this.timeAfterAction = timeAfterAction;
     }
      InputInfo(String inputValue, long timeAfterAction){
          this(inputValue, null,0,timeAfterAction);
     }

     String inputValue;

   ArrayList<ActionAttributeData> actionAttributes;
   int totalTimeForAction;
   long timeAfterAction;
     Stopwatch stopwatch = Stopwatch.createStarted();


     @Override
    public String toString() {
       return String.format("InputInfo [ class: %s, inputValue: %s, code: %s, totalTimeForAction: %d, timeAfterAction: %d", this.getClass().getSimpleName(),inputValue, actionAttributes,totalTimeForAction,timeAfterAction);
    }

}
