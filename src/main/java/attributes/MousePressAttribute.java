package attributes;

public class MousePressAttribute implements Attribute{
    int buttonType;
    public MousePressAttribute(int buttonType) {
        this.buttonType = buttonType;
    }

    @Override
    public void accept(AttributeTypeVisitor attributeTypeVisitor) {
        attributeTypeVisitor.visit(this);
    }
}
