package AbstractSyntaxTree;
import java.util.Vector;

public class MethodDeclList extends ASTNode {
	private Vector list;

	public MethodDeclList(int ln) {
		super(ln);
		list = new Vector();
	}

	public void add(MethodDecl n) {
		list.addElement(n);
	}

	public MethodDecl elementAt(int i)  { 
		return (MethodDecl)list.elementAt(i); 
	}

	public int size() { 
		return list.size(); 
	}
}