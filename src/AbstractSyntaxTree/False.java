package AbstractSyntaxTree;
import Visitor.Visitor;

public class False extends Exp {
	public False(int ln) {
		super(ln);
	}
	public void accept(Visitor v) {
		v.visit(this);
	}

}