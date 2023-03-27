package attributes;

public class MouseMoveAttribute implements Attribute {
    CoordinateInfo coordinateInfo;
    public MouseMoveAttribute(CoordinateInfo coordinateInfo) {
        this.coordinateInfo = coordinateInfo;
    }

    @Override
    public void accept(AttributeTypeVisitor attributeTypeVisitor) {
        attributeTypeVisitor.visit(this);
    }
}
