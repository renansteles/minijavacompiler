package AbstractSyntaxTree;
import Visitor.Visitor;

public class BooleanType  extends Type {
	public BooleanType(int ln) {
		super(ln);
	}
	public void accept(Visitor v) {
		v.visit(this);
	}

}