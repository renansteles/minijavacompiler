package AbstractSyntaxTree;
import Visitor.Visitor;
public abstract class Type extends ASTNode {
    public Type(int ln) {
        super(ln);
    }
    public abstract void accept(Visitor v);
}