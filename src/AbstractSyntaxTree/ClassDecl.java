package AbstractSyntaxTree;

import TypeCheck.CreateTable;
import Visitor.Visitor;


public abstract class ClassDecl extends ASTNode{
	public ClassDecl(int ln) {
		super(ln);
	}
	public abstract void accept(Visitor v);
	public abstract void accept(CreateTable v);
}