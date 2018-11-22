package AbstractSyntaxTree;
import TypeCheck.CreateTable;
import Visitor.Visitor;

public class Identifier extends ASTNode {
	public String s;

	public Identifier(String as, int ln) { 
		super(ln);
		s=as;
	}

	public void accept(Visitor v) {
		v.visit(this);
	}

	public String toString(){
		return s;
	}

	public void accept(CreateTable v) {
		// TODO Auto-generated method stub
		v.visit(this);
	}
}