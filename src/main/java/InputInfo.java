import com.google.common.base.Stopwatch;

import java.util.ArrayList;

abstract class InputInfo {
   int totalTimeForAction = 0;

   Stopwatch stopwatch = Stopwatch.createStarted();
   long timeAfterAction;
   public abstract String toString();
}
