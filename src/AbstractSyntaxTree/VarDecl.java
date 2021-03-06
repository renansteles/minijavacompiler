package AbstractSyntaxTree;
import TypeCheck.CreateTable;
import Visitor.Visitor;

public class VarDecl extends ASTNode {
	public Type t;
	public Identifier i;

	public VarDecl(Type at, Identifier ai, int ln) {
		super(ln);
		t=at; i=ai;
	}

	public void accept(Visitor v) {
		v.visit(this);
	}

	public void accept(CreateTable criarTabela) {
		criarTabela.visit(this);
	}
}