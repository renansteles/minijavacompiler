package AbstractSyntaxTree;
import Visitor.Visitor;

public class Print extends Statement {
	public Exp e;

	public Print(Exp ae, int ln) {
		super(ln);
		e=ae; 
	}

	public void accept(Visitor v) {
		v.visit(this);
	}

}