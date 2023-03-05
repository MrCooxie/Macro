import java.awt.*;
import java.awt.event.InputEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player {
    InputInfoDTO[] Actions;
    Player(InputInfoDTO[] Actions){
        this.Actions = Actions;
    }
    public void play(){
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
        for(int i = 0; i < Actions.length; i++){
            if(Actions[i].inputInfoClass.equals("MouseInfoLocal")){
                ArrayList<ActionAttributeData> attributes = Actions[i].code;
                int inputValue = Integer.parseInt(Actions[i].inputValue);
                robot.delay((int)Actions[i].timeAfterAction);
                for(int j = 0; j < attributes.size(); j++){
                    ActionAttributeData temp = attributes.get(i);
                    robot.mouseMove(temp.XCoordinate,temp.YCoordinate);
                    robot.mousePress(InputEvent.getMaskForButton(inputValue));
                    if(attributes.size() == 1) {
                        robot.delay(Actions[i].totalTimeForAction);
                    }
                }
                robot.mouseRelease(InputEvent.getMaskForButton(inputValue));
            }
        }
    }
}
