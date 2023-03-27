package TypesOfAttributes;

public class MouseMoveAttribute implements Attribute {
    CoordinateInfo coordinateInfo;
    public MouseMoveAttribute(CoordinateInfo coordinateInfo) {
        this.coordinateInfo = coordinateInfo;
    }

}
