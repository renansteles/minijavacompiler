package AbstractSyntaxTree;
import Visitor.Visitor;

public class Not extends Exp {
	  public Exp e;
	  
	  public Not(Exp ae, int ln) {
	    super(ln);
	    e=ae; 
	  }

	  public void accept(Visitor v) {
	    v.visit(this);
	  }
	  
	}