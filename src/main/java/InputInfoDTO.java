import java.util.ArrayList;

public class InputInfoDTO {
    public InputInfoDTO(InputInfo inputInfo) {
        this.inputValue = inputInfo.inputValue;
        this.code = inputInfo.actionAttributes;
        this.totalTimeForAction = inputInfo.totalTimeForAction;
        this.timeAfterAction = inputInfo.timeAfterAction;
    }
    String inputValue;
    ArrayList<ActionAttributeData> code;
    int totalTimeForAction;
    long timeAfterAction;
}
