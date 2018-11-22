package AbstractSyntaxTree;
import Visitor.Visitor;
import types.AbsTipo;

public class ASTNode {
	public int line_number;
	public AbsTipo tipo;


	// Constructor
	public ASTNode(int ln) {
		line_number = ln;
	}

}