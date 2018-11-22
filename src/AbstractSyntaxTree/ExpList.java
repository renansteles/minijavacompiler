package AbstractSyntaxTree;

import java.util.Vector;

public class ExpList extends ASTNode{
	private Vector list;

	public ExpList(int ln) {
		super(ln);
		list = new Vector();
	}

	public void add(Exp n) {
		list.addElement(n);
	}

	public Exp elementAt(int i)  { 
		return (Exp)list.elementAt(i); 
	}

	public int size() { 
		return list.size(); 
	}
}