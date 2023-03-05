import java.util.ArrayList;

public class InputInfoDTO {
    public InputInfoDTO(InputInfo inputInfo) {
        this.inputValue = inputInfo.inputValue;
        this.code = inputInfo.actionAttributes;
        this.totalTimeForAction = inputInfo.totalTimeForAction;
        this.timeAfterAction = inputInfo.timeAfterAction;
        this.inputInfoClass = inputInfo.getClass().getSimpleName();
    }


    String inputValue;
    ArrayList<ActionAttributeData> code;
    int totalTimeForAction;
    long timeAfterAction;
    String inputInfoClass;

    @Override
    public String toString() {
        return "InputInfoDTO{" +
                "inputValue='" + inputValue + '\'' +
                ", code=" + code +
                ", totalTimeForAction=" + totalTimeForAction +
                ", timeAfterAction=" + timeAfterAction +
                ", inputInfoClass='" + inputInfoClass + '\'' +
                '}';
    }
}
