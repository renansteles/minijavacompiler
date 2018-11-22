package TypeCheck;

import AbstractSyntaxTree.*;

public interface InterfaceTableVisitor {
	  public void visit(Program n);
	  public void visit(MainClass n);
	  public void visit(ClassDeclSimple n);
	  public void visit(ClassDeclExtends n);
	  public void visit(VarDecl n);
	  public void visit(MethodDecl n);
	  public void visit(Formal n);
	  public void visit(Identifier n);
}