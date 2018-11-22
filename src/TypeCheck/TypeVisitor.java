package TypeCheck;

import java.util.HashMap;
import java.util.Map;

import AbstractSyntaxTree.*;
import Visitor.Visitor;
import types.*;




public class TypeVisitor implements Visitor{
	private Classe lastClass;
	private Metodo lastMetodo;
	private table table;
	
	
	public TypeVisitor(table t) {
		table = t;
	}
	public table gettable(){
		return table;
	}

	public void visit(Return n) {
		// TODO Auto-generated method stub
	}

	public void visit(Program n) {
		n.m.accept(this);
		for(int i = 0; i<n.cl.size();i++){
			n.cl.elementAt(i).accept(this);
		}
		
	}

	public void visit(MainClass n) {
		// TODO Auto-generated method stub
	    n.i1.accept(this);
	    n.i2.accept(this);
	    n.s.accept(this);
	}

	public void visit(ClassDeclSimple n) {
		lastClass = this.table.get_class(n.i.s);
		n.i.accept(this);
		for(int i =0; i< n.vl.size();i++){
			n.vl.elementAt(i).accept(this);
		}
		for(int j=0;j< n.ml.size();j++){
			n.ml.elementAt(j).accept(this);
		}
		lastClass = null;
		
		// TODO Auto-generated method stub
		
	}

	// n.j -> Pai
	public void visit(ClassDeclExtends n) {
		// TODO Auto-generated method stub
		this.table.get_class(n.i.s).setPai(this.table.get_class(n.j.s));
		lastClass = this.table.get_class(n.i.s);
		n.i.accept(this);
		for(int i =0; i< n.vl.size();i++){
			n.vl.elementAt(i).accept(this);
		}
		for(int j=0;j< n.ml.size();j++){
			n.ml.elementAt(j).accept(this);
		}
		lastClass = null;
	}

	public void visit(VarDecl n) {
		n.t.accept(this);
		n.i.accept(this);
	}

	public void visit(MethodDecl n) {
		this.lastMetodo = this.lastClass.getMethods(n.i.s);
		n.t.accept(this);
		n.i.accept(this);
		for(int i= 0;i<n.fl.size();i++){
			n.fl.elementAt(i).accept(this);
		}
		for(int k = 0; k<n.vl.size();k++){
			VarDecl v = n.vl.elementAt(k);
			v.accept(this);
		}
		for(int j=0;j<n.sl.size();j++){
			n.sl.elementAt(j).accept(this);
		}
		
		n.e.accept(this);
		
		lastMetodo = null;
	}
		
	//Type base
	public void visit(IntegerLiteral n) {
		// TODO Auto-generated method stub
		n.tipo = Tipo.INTEGER;
	}

	public void visit(True n) {
		// TODO Auto-generated method stub
		n.tipo = Tipo.BOOLEAN;
	}

	public void visit(False n) {
		// TODO Auto-generated method stub
		n.tipo = Tipo.BOOLEAN;
	}
	
	//New 
	public void visit(NewArray n) {
		// TODO Auto-generated method stub
		n.e.accept(this);
		if(!n.e.tipo.equals(Tipo.INTEGER)){
			throw new IllegalArgumentException("Expression must be int na line"+n.e.line_number);
		}
		n.tipo = Tipo.INTARRAY;
		
	}

	public void visit(NewObject n) {
		// TODO Auto-generated method stub
		n.i.accept(this);
		Classe c;
		c = table.get_class(n.i.s);
		if(c == null){
			throw new IllegalArgumentException("Objeto inexistente na linha: "+n.line_number);
		}
		n.tipo = new ClassTipo(c.getDadClass(),c.getKey());
	}
	
	public void visit(If n) {
		// TODO Auto-generated method stub
		n.e.accept(this);
		if(!(n.e.tipo.equals(Tipo.BOOLEAN))){
			throw new IllegalArgumentException("Expression must be boolean na line: "+n.e.line_number);
		}
		n.s1.accept(this);
		n.s2.accept(this);
		n.tipo = n.e.tipo;
	}

	public void visit(While n) {
		// TODO Auto-generated method stub
		n.e.accept(this);
		if(!(n.e.tipo.equals(Tipo.BOOLEAN))){
			throw new IllegalArgumentException("A expressão deve ser Boolean na linha: "+n.e.line_number);
		}
		n.s.accept(this);
		n.tipo = n.e.tipo;
	}
	
	public void visit(And n) {
		// TODO Auto-generated method stub
		n.e1.accept(this);
		n.e2.accept(this);
		if(!n.e1.tipo.equals(Tipo.BOOLEAN)){
			if(!n.e2.tipo.equals(Tipo.BOOLEAN)){
				throw new IllegalArgumentException("Expression must be boolean na line: "+n.line_number);
			}
		}
		n.tipo = n.e1.tipo;
	}
	
	public void visit(Not n) {
		// TODO Auto-generated method stub
		n.e.accept(this);
		if(!n.e.tipo.equals(Tipo.BOOLEAN)){
			throw new IllegalArgumentException("Expression must be boolean na line: "+n.line_number);
		}
		n.tipo = n.e.tipo;
	}
	

	public void visit(LessThan n) {
		// TODO Auto-generated method stub
		n.e1.accept(this);
		n.e2.accept(this);
		if(!n.e1.tipo.equals(Tipo.INTEGER) && !n.e1.tipo.equals(Tipo.INTARRAY)){
			if(!n.e2.tipo.equals(Tipo.INTEGER) && !n.e2.tipo.equals(Tipo.INTARRAY)){
				throw new IllegalArgumentException("A expressão deve ser int na linha: "+n.line_number);
			}
		}
		n.tipo = Tipo.BOOLEAN;
	}

	public void visit(Plus n) {
		// TODO Auto-generated method stub
		n.e1.accept(this);
		n.e2.accept(this);
		if(!n.e1.tipo.equals(Tipo.INTEGER) && !n.e1.tipo.equals(Tipo.INTARRAY)){
			if(!n.e2.tipo.equals(Tipo.INTEGER) && !n.e2.tipo.equals(Tipo.INTARRAY)){
				throw new IllegalArgumentException("Expression must be int na line: "+n.line_number);
			}
		}
		n.tipo = n.e1.tipo;
	}

	public void visit(Minus n) {
		// TODO Auto-generated method stub
		n.e1.accept(this);
		n.e2.accept(this);
		if(!n.e1.tipo.equals(Tipo.INTEGER) && !n.e1.tipo.equals(Tipo.INTARRAY)){
			if(!n.e2.tipo.equals(Tipo.INTEGER) && !n.e2.tipo.equals(Tipo.INTARRAY)){
				throw new IllegalArgumentException("Expression must be int na line: "+n.line_number);
			}
		}
		n.tipo = n.e1.tipo;
	}

	public void visit(Times n) {
		// TODO Auto-generated method stub
		n.e1.accept(this);
		n.e2.accept(this);
		if(!n.e1.tipo.equals(Tipo.INTEGER) && !n.e1.tipo.equals(Tipo.INTARRAY)){
			if(!n.e2.tipo.equals(Tipo.INTEGER) && !n.e2.tipo.equals(Tipo.INTARRAY)){
				throw new IllegalArgumentException("A expressão deve ser int na linha: "+n.line_number);
			}
		}
		n.tipo = n.e1.tipo;
	}
	
	public void visit(ArrayLookup n) {
		// TODO Auto-generated method stub
		n.e1.accept(this);
		if( !(n.e1.tipo.equals(Tipo.INTARRAY))&& !(n.e1 instanceof IdentifierExp)){
			throw new IllegalArgumentException("err in "+n.e1.line_number+" expected an array");
		}
		n.e2.accept(this);
		if(!(n.e2.tipo.equals(Tipo.INTEGER))){
			throw new IllegalArgumentException("The expression of the array must be integer in the line "+n.e2.line_number);
		}
		n.tipo = Tipo.INTARRAY;
	}

	public void visit(ArrayLength n) {
		// TODO Auto-generated method stub
		n.e.accept(this);
		if(!n.e.tipo.equals(Tipo.INTARRAY) && !(n.e instanceof IdentifierExp)){
			throw new IllegalArgumentException("erro in line "+n.e.line_number+" expected array");
		}
		n.tipo = Tipo.INTEGER;
	}


	public void visit(Assign n) {
		// TODO Auto-generated method stub
		n.i.accept(this);
		n.e.accept(this);		
		variable v = null;
		if(lastMetodo != null){
			v = lastMetodo.pegar_parametro(n.i.s);
			if(v==null) v=lastMetodo.getLocais().get(n.i.s);
		}
		if(v==null){
			v=lastClass.getGlobais().get(n.i.s);
		}
		v.getType().accept(this); // Necessário nos caso de ser um Objeto
		if(v.getType() instanceof IntegerType){
			n.i.tipo = Tipo.INTEGER;
		}else if(v.getType() instanceof BooleanType){
			n.i.tipo = Tipo.BOOLEAN;
		}else if(v.getType() instanceof IntArrayType){
			n.i.tipo = Tipo.INTARRAY;
		}
		else{
			n.i.tipo = v.getType().tipo;
		}
		if((n.e.tipo instanceof ClassTipo)){

			if(!((n.e.tipo.igual(n.i.tipo))||(n.e.tipo.insta(n.i.tipo)))){
				throw new IllegalArgumentException("The object can not be instantiated on the line : "+n.line_number);
			}
		}else if(n.e.tipo.equals(Tipo.INTARRAY)){
			if(n.i.tipo.equals(Tipo.INTEGER)){
			}else if(n.i.tipo.equals(Tipo.INTARRAY)){
				
			}
			else if(!n.i.tipo.equals(Tipo.INTEGER)){
				throw new IllegalArgumentException("Var are not integer in line : "+n.line_number);
			}else{// Err
				throw new IllegalArgumentException("Incorrect type assignment on line : "+n.e.tipo + "  "+n.i.tipo);
			}
		}
		else{
			if(!n.i.tipo.equals(n.e.tipo)){
				throw new IllegalArgumentException("Atribuição incorreta de tipos na linha: "+n.line_number);
			}
		}
		
		n.tipo = n.i.tipo;
	}

	public void visit(ArrayAssign n) {
		// TODO Auto-generated method stub
		n.i.accept(this);
		n.e1.accept(this);
		n.e2.accept(this);
		variable v = null;
		if(lastMetodo != null){
			v = lastMetodo.pegar_parametro(n.i.s);
			if(v==null) v=lastMetodo.getLocais().get(n.i.s);
		}if(v==null){
			v=lastClass.getGlobais().get(n.i.s);
		}
		// System.out.println("ARRAYASSIGN: "+n.e1.tipo +"\t"+ n.e2.tipo);
		if(!n.e1.tipo.equals(Tipo.INTEGER)){
			throw new IllegalArgumentException("Expected integer value in Colcheite: "+n.line_number);
		}
		if(!n.e2.tipo.equals(Tipo.INTEGER) && !n.e2.tipo.equals(Tipo.INTARRAY)){
			throw new IllegalArgumentException("Expected integer value in: "+n.line_number);
		}
		if(!(v.getType() instanceof IntArrayType)){
			throw new IllegalArgumentException("Expected array  in: "+n.line_number);
		}
		n.i.tipo = Tipo.INTARRAY;
		n.tipo = Tipo.INTARRAY;
	}
	

	public void visit(Print n) {
		// TODO Auto-generated method stub
		n.e.accept(this);
	}
	
	public void visit(Block n) {
		// TODO Auto-generated method stub
		for ( int i = 0; i < n.sl.size(); i++ ) {
	        n.sl.elementAt(i).accept(this);
	    }
	}	
	
	
	public void visit(Call n) {
		// TODO Auto-generated method stub
		n.e.accept(this);
		n.i.accept(this);
		// System.out.println("GHGH: "+n.e.tipo+n.line_number);
		ClassTipo c = (ClassTipo)n.e.tipo;
		Metodo m = table.get_methods(n.i.s, c.getKey());
		for ( int i = 0; i < n.el.size(); i++ ) {
	        n.el.elementAt(i).accept(this);
	    }

		if(m.getTipo() instanceof IntegerType){
			n.i.tipo = Tipo.INTEGER;
		}else if(m.getTipo() instanceof BooleanType){
			n.i.tipo = Tipo.BOOLEAN;
		}else if(m.getTipo() instanceof IdentifierType){
			m.getTipo().accept(this);
			n.i.tipo = m.getTipo().tipo;
		}
		n.tipo = n.i.tipo;
	}


	public void visit(IdentifierExp n) {
		// TODO Auto-generated method stub
		variable v = null;
		if(lastMetodo != null){
			v = lastMetodo.pegar_parametro(n.s);
			if(v==null) v=lastMetodo.getLocais().get(n.s);
		}
		if(v==null){
			v=lastClass.getGlobais().get(n.s);
		}
		v.getType().accept(this);
		if(v.getType() instanceof IntegerType){
			n.tipo = Tipo.INTEGER;
		}else if(v.getType() instanceof BooleanType){
			n.tipo = Tipo.BOOLEAN;
		}else if(v.getType() instanceof IdentifierType){
			v.getType().accept(this);
			n.tipo = v.getType().tipo;
		}else if(v.getType().tipo.equals(Tipo.INTARRAY)){
			n.tipo = Tipo.INTARRAY;
		}
		
	}

	public void visit(This n) {
		// TODO Auto-generated method stub
		n.tipo = new ClassTipo(lastClass.getDadClass(), lastClass.getKey());
	}

	
	public void visit(Identifier n) {
		// TODO Auto-generated method stub
		
	}
	public void visit(Formal n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(IntArrayType n) {
		// TODO Auto-generated method stub
		n.tipo = Tipo.INTARRAY;
	}

	public void visit(BooleanType n) {
		// TODO Auto-generated method stub
	}

	public void visit(IntegerType n) {
		// TODO Auto-generated method stub
	}

	public void visit(IdentifierType n) {
		// TODO Auto-generated method stub
		Classe c = table.get_class(n.s);
//		// System.out.println("IDENTIFIERTYPE: "+n.line_number +"\t"+ n.s);
		n.tipo = new ClassTipo(c.getDadClass(),c.getKey());
	}

	// fim não necessários

	
	
	
	@Override
	public String toString(){
		String result = "";
		result += "Classes:\n";
		for(String s : table.classes.keySet()){
			result += s+"\n";
			Classe c = table.classes.get(s);
			if(c.getDadClass() != null){
				result += "\tExtends\t"+c.getDadClass()+"\n";
			}
			result +=c.getOffset();
			result += c.toString();
		}
		return result;
	}

}