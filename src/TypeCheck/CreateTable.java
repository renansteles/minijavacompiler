package TypeCheck;


import AbstractSyntaxTree.*;
import types.*;


public class CreateTable implements InterfaceTableVisitor{
	private Classe lastClass;
	private Metodo lastMethod;
	private table table;
	private int offset = 4;
	
	
	public CreateTable(table t){
		this.table = t;
	}
	public table getTable(){
		return table;
	}
	public void visit(Program n) {
		n.m.accept(this);
		for(int i = 0; i<n.cl.size();i++){
			n.cl.elementAt(i).accept(this);
		}
		
	}

	public void visit(MainClass n) {
	    n.i1.accept(this);
	    n.i2.accept(this);
	}

	public void visit(ClassDeclSimple n) {
		if(!(this.table.add_class(n.i.s, null))){
			throw new IllegalArgumentException("A Classe: "+n.i.s + "Já existe.");
		}
		lastClass = this.table.get_class(n.i.s);
		n.i.accept(this);
		for(int i =0; i< n.vl.size();i++){
			lastClass.addGlobals(n.vl.elementAt(i).i.s, new variable(n.vl.elementAt(i).i.s,n.vl.elementAt(i).t,offset*(i+1)));
			n.vl.elementAt(i).accept(this);
		}
		for(int j=0;j< n.ml.size();j++){
			lastClass.addMethods(n.ml.elementAt(j).i.s, new Metodo(n.ml.elementAt(j).i.s,n.ml.elementAt(j).t));
			n.ml.elementAt(j).accept(this);
		}
		lastClass = null;
		
	}

	public void visit(ClassDeclExtends n) {
		n.i.accept(this);
		n.j.accept(this);
		if(!(this.table.add_class(n.i.s, this.table.get_class(n.j.s)))){
			throw new IllegalArgumentException("A Classe: "+n.i.s + "Já existe.");
		}
		lastClass = this.table.get_class(n.i.s);
		for(int i =0; i< n.vl.size();i++){
			lastClass.addGlobals(n.vl.elementAt(i).i.s, new variable(n.vl.elementAt(i).i.s,n.vl.elementAt(i).t,offset*(i+1)));
			n.vl.elementAt(i).accept(this);
		}
		for(int j=0;j< n.ml.size();j++){
			lastClass.addMethods(n.ml.elementAt(j).i.s, new Metodo(n.ml.elementAt(j).i.s,n.ml.elementAt(j).t));
			offset = (j+1)*4;
			n.ml.elementAt(j).accept(this);
		}
		offset = 4;
		lastClass = null;		

	}

	public void visit(VarDecl n) {
		n.i.accept(this);
		if(lastMethod ==null) this.lastClass.addGlobals(n.i.s, new variable(n.i.s,n.t,0));
		else this.lastMethod.adicionar_locais(n.i.s, new variable(n.i.s,n.t,offset));
	}

	public void visit(MethodDecl n) {
		this.lastClass.addMethods(n.i.s, new Metodo(n.i.s,n.t));
		this.lastMethod = this.lastClass.getMethods(n.i.s);
		
		n.i.accept(this);
		for(int i= 0;i<n.fl.size();i++){
			this.lastMethod.adicionar_parametros(new variable(n.fl.elementAt(i).i.s,n.fl.elementAt(i).t,offset*(i+1)+4));
			n.fl.elementAt(i).accept(this);
		}
		for(int k = 0; k<n.vl.size();k++){
			VarDecl v = n.vl.elementAt(k);
			this.lastMethod.adicionar_locais(v.i.s, new variable(v.i.s,v.t,offset*(k+1)));
			v.accept(this);
		}
		for(int j=0;j<n.sl.size();j++){
		}
	}
	
	public void visit(Formal n){
		
	}
	public void visit(Identifier n) {
	}
	
}