package AbstractSyntaxTree;
import Visitor.Visitor;

public class Return extends Statement {
	public Exp e;

	public Return(Exp re, int ln) {
		super(ln);
		e=re; 
	}

	public void accept(Visitor v) {
		v.visit(this);
	}

}
