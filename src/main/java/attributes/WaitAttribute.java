package attributes;

public class WaitAttribute implements Attribute{
    long delay;
    public WaitAttribute(long delay) {
        this.delay = delay;
    }

    @Override
    public void accept(AttributeTypeVisitor attributeTypeVisitor) {
        attributeTypeVisitor.visit(this);
    }
}
