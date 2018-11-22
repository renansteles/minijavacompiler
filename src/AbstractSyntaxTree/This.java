package AbstractSyntaxTree;
import Visitor.Visitor;

public class This extends Exp {
	public This(int ln) {
		super(ln);
	}
	public void accept(Visitor v) {
		v.visit(this);
	}

}