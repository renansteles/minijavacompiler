package AbstractSyntaxTree;
import Visitor.Visitor;

public class True extends Exp {
	public True(int ln) {
		super(ln);
	}
	public void accept(Visitor v) {
		v.visit(this);
	}

}