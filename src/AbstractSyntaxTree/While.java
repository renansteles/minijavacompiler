package AbstractSyntaxTree;
import Visitor.Visitor;

public class While extends Statement {
	public Exp e;
	public Statement s;

	public While(Exp ae, Statement as, int ln) {
		super(ln);
		e=ae; s=as; 
	}

	public void accept(Visitor v) {
		v.visit(this);
	}
}
