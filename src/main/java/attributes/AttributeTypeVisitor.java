package attributes;

public interface AttributeTypeVisitor {
    int visit(KeyPressAttribute keyPressAttribute);
    CoordinateInfo visit(MouseMoveAttribute mouseMoveAttribute);
    int visit(MousePressAttribute mousePressAttribute);
    long visit(WaitAttribute waitAttribute);
}
