package attributes;

public class KeyPressAttribute implements Attribute{
    int buttonType;
    public KeyPressAttribute(int buttonType) {
        this.buttonType = buttonType;
    }

    @Override
    public void accept(AttributeTypeVisitor attributeTypeVisitor) {
        attributeTypeVisitor.visit(this);
    }
}
