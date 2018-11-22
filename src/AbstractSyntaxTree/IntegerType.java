package AbstractSyntaxTree;
import Visitor.Visitor;

public class IntegerType extends Type {
	public IntegerType(int ln) {
		super(ln);
	}
	public void accept(Visitor v) {
		v.visit(this);
	}

}