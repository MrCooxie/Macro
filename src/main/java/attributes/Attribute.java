package attributes;

public interface Attribute {
     void accept(AttributeTypeVisitor attributeTypeVisitor);
}
