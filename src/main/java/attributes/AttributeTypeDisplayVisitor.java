package attributes;

public class AttributeTypeDisplayVisitor implements AttributeTypeVisitor{
    @Override
    public int visit(KeyPressAttribute keyPressAttribute) {
        return keyPressAttribute.buttonType;
    }

    @Override
    public CoordinateInfo visit(MouseMoveAttribute mouseMoveAttribute) {
       return mouseMoveAttribute.coordinateInfo;

    }

    @Override
    public int visit(MousePressAttribute mousePressAttribute) {
       return mousePressAttribute.buttonType;
    }

    @Override
    public long visit(WaitAttribute waitAttribute) {
        return waitAttribute.delay;
    }
}
