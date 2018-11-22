package AbstractSyntaxTree;

import TypeCheck.CreateTable;
import Visitor.Visitor;


public class Formal extends ASTNode{
	public Type t;
	public Identifier i;

	public Formal(Type at, Identifier ai, int ln) {
		super(ln);
		t=at; i=ai;
	}

	public void accept(Visitor v) {
		v.visit(this);
	}

	public void accept(CreateTable criarTabela) {
		// TODO Auto-generated method stub
		criarTabela.visit(this);
	}


}