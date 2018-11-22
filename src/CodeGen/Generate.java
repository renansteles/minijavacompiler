package CodeGen;
import TypeCheck.*;
import Visitor.*;
import types.*;

import java.util.ArrayList;
import java.util.Stack;

import AbstractSyntaxTree.*;




public class Generate implements Visitor {
	int indexlabel = 0;
	table table;
	Classe lastClass;
	Metodo lastMethod;
	ASTNode last;
	
	public Generate(table t){
		this.table = t;
	}
	public void visit(Return n) {
		
	}

	public void visit(Program n) {
		n.m.accept(this);
		for(int i = 0; i<n.cl.size();i++){
			n.cl.elementAt(i).accept(this);
		}
	}

	public void visit(MainClass n) {
		System.out.println("\t.text");
		System.out.println("\t.global asm_main\n");
		System.out.println("asm_main:");
		n.s.accept(this);
		System.out.println("ret");
	}

	public void visit(ClassDeclSimple n) {
		lastClass = this.table.get_class(n.i.s);
		Classe pai = lastClass.getDadClass();
		vtable(pai);
		if(lastClass.getGlobais().size() >0){
			System.out.println("\t.data");
			System.out.println("\tglob_"+lastClass.getKey()+":");
				for(variable v : lastClass.getGlobais().values()){
					System.out.println("\tdd\t "+v.getKey());
				}
			System.out.println("\t.code");
		}for(int i =0;i< n.ml.size();i++){
			n.ml.elementAt(i).accept(this);
		}
		lastClass = null;
	}

	public void visit(ClassDeclExtends n) {
		lastClass = this.table.get_class(n.i.s);
		Classe pai = lastClass.getDadClass();
		vtable(pai);
		System.out.println("\t.code");
		if(lastClass.getGlobais().size() >0){
		System.out.println("\t.data");
		System.out.println("\tglob_"+lastClass.getKey()+":");
			for(variable v : lastClass.getGlobais().values()){
				System.out.println("\tdd\t "+v.getKey());
			}
			System.out.println("\t.code");
		}
		for(int i =0;i< n.ml.size();i++){
			n.ml.elementAt(i).accept(this);
		}
		lastClass = null;
	}

	public void visit(VarDecl n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(MethodDecl n) {
		// TODO Auto-generated method stub
		this.lastMethod = this.lastClass.getMethods(n.i.s);
		prologo();
		System.out.println("sub esp,"+(lastMethod.getLocais().size()*4));
		
		for(int j=0;j<n.sl.size();j++){
			n.sl.elementAt(j).accept(this);
		}
		epilogo();
		this.lastMethod = null;
	}

	public void visit(Formal n) {
		// TODO Auto-generated method stub
	}

	public void visit(IntArrayType n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(BooleanType n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(IntegerType n) {
		// TODO Auto-generated method stub
	}

	public void visit(IdentifierType n) {
		// TODO Auto-generated method stub

	}

	public void visit(Block n) {
		// TODO Auto-generated method stub
		for ( int i = 0; i < n.sl.size(); i++ ) {
	        n.sl.elementAt(i).accept(this);
	    }
	}

	public void visit(If n) {
		// TODO Auto-generated method stub
		indexlabel++;
		last = n;
		n.e.accept(this);
		
		n.s1.accept(this);
		System.out.println("jmp done"+indexlabel);
		System.out.print("else"+indexlabel+":\t");
		n.s2.accept(this);
		System.out.println("done"+indexlabel+":");
		last = null;
				
	}
	
	public void visit(While n) {
		// TODO Auto-generated method stub
		
		indexlabel++;
		last = n;
		System.out.print("test"+indexlabel+":\t"); 
		n.e.accept(this);
		n.s.accept(this);
		System.out.println("jmp test"+indexlabel);
		System.out.println("done"+indexlabel+":");
		last = null;
	}

	public void visit(Print n) {
		// TODO Auto-generated method stub
		if(n.e instanceof IntegerLiteral){
			System.out.print("mov eax,");
		}
		if(n.e instanceof IdentifierExp){
			System.out.print("mov eax,");
		}
		n.e.accept(this);
		System.out.println("push eax");
		System.out.println("call put");
		System.out.println("add esp,4");
		System.out.println("mov ecx,[esp]");
	}

	public void visit(Assign n) {
		// TODO Auto-generated method stub
		last = n;
		if(n.e instanceof IntegerLiteral){
			System.out.print("mov eax,");
		}
		n.e.accept(this);
		variable v = null;
		for(variable va:lastClass.getGlobais().values()){
			if(va.getKey().equals(n.i.s)){
				v = va;
				System.out.println("mov eax,[ecx+"+v.getOffset()+"]");
				break;
			}
		}
		if(v == null){
			for(variable va:lastMethod.getLocais().values()){
				if(va.getKey().equals(n.i.s)){
					v = va;
					System.out.println("mov eax,[ebp-"+v.getOffset()+"]");
					break;
				}
			}
		}
		if(v==null){
			for(variable va:lastMethod.getParametros()){
				if(va.getKey().equals(n.i.s)){
					v = va;
					System.out.println("mov eax,[ebp+"+v.getOffset()+"]");
					break;
				}	
			}
		}
		
		last = null;
		
	}

	public void visit(ArrayAssign n) {
		// TODO Auto-generated method stub
		if(n.e2 instanceof IntegerLiteral){
			System.out.print("mov eax,");
		}
		n.e2.accept(this);
		System.out.println("push eax");
		n.e1.accept(this);
		System.out.println("push eax");
		System.out.println("pop edx");
		System.out.println("pop eax");
		variable v = null;
		for(variable va:lastClass.getGlobais().values()){
			if(va.getKey().equals(n.i.s)){
				v = va;
				System.out.println("mov ecx,[ecx+"+v.getOffset()+"]");
				break;
			}
		}
		if(v == null){
			for(variable va:lastMethod.getLocais().values()){
				if(va.getKey().equals(n.i.s)){
					v = va;
					System.out.println("mov ecx,[ebp-"+v.getOffset()+"]");
					break;
				}
			}
		}
		if(v==null){
			for(variable va:lastMethod.getParametros()){
				if(va.getKey().equals(n.i.s)){
					v = va;
					System.out.println("mov ecx,[ebp+"+v.getOffset()+"]");
					break;
				}	
			}
		}
		System.out.println("shl edx,2");
		System.out.println("add edx,ecx");
		System.out.println("mov [edx],eax");
		System.out.println("mov ecx,[esp]");
		
	}

	public void visit(And n) {
		// TODO Auto-generated method stub
		n.e1.accept(this);
		System.out.println("cmp eax, 0");
		if(last instanceof If){
			System.out.println("je else"+indexlabel);
		}else if(last instanceof While){
			System.out.println("je done"+indexlabel);
		}
		n.e2.accept(this);
		if(last instanceof If){
			System.out.println("je else"+indexlabel);
		}else if(last instanceof While){
			System.out.println("je done"+indexlabel);
		}

	}


	public void visit(LessThan n) {
		// TODO Auto-generated method stub
		System.out.print("mov eax,");
		n.e1.accept(this);
		System.out.print("mov edx,");
		n.e2.accept(this);
		if(last instanceof Not){
			System.out.println("cmp edx,eax");
		}else{
			System.out.println("cmp eax,edx");
		}
		if(last instanceof If){
			System.out.println("jge else"+indexlabel);
		}else if(last instanceof While){
			System.out.println("jge done"+indexlabel);
		}
	}

	public void visit(Plus n) {
		// TODO Auto-generated method stub
		System.out.print("mov eax,");
		n.e1.accept(this);
		System.out.print("mov edx,");
		n.e2.accept(this);
		System.out.println("add eax,edx");
	}

	public void visit(Minus n) {
		// TODO Auto-generated method stub
		System.out.print("mov eax,");
		n.e1.accept(this);
		System.out.print("mov edx,");
		n.e2.accept(this);
		System.out.println("sub eax,edx");
	}

	public void visit(Times n) {
		// TODO Auto-generated method stub
		last = n;
			System.out.print("mov eax,");
		n.e1.accept(this);
		if(n.e2 instanceof IntegerLiteral){
			System.out.print("mov edx,");
		}
		n.e2.accept(this);
		System.out.println("mul eax,edx");
		last = null;
	}

	public void visit(ArrayLookup n) {
		// TODO Auto-generated method stub
		n.e1.accept(this);
		System.out.println("push eax");
		n.e2.accept(this);
		System.out.println("pop edx");
		System.out.println("shl eax, 2");
		System.out.println("add eax,edx");
		System.out.println("mov eax, [eax]");
	}

	public void visit(ArrayLength n) {
		// TODO Auto-generated method stub
		n.e.accept(this);
		System.out.println("mov eax,[eax-4]");
	}

	public void visit(Call n) {
		// TODO Auto-generated method stub
	    n.e.accept(this);
	    for ( int i = 0; i < n.el.size(); i++ ) {
	    	System.out.print("mov eax,");
	    	n.el.elementAt(n.el.size()-(i+1)).accept(this);
	    	System.out.println("push eax");
	    }
	    if(!(n.e instanceof This)){
	    	System.out.print("mov ecx,[ebp+");
	    }
	    last = n;
	    n.e.accept(this);
	    System.out.println("mov eax,[ecx]");
	    Metodo m = null;
	    for(Classe c : table.getClasses().values()){
	    	if(m == null){
	    		m = c.getMethods(n.i.s);
	    	}
	    }
	    System.out.println("call dword ptr [eax+"+m.getOffset()+"]");
	    last = null;
	}

	public void visit(IntegerLiteral n) {
		// TODO Auto-generated method stub
		if(last instanceof ArrayAssign){
			System.out.println("mov eax,"+n.i);
		}
		else{
			System.out.println(n.i);
		}
		
	}

	public void visit(True n) {
		// TODO Auto-generated method stub
		System.out.println("mov eax,1");
	}

	public void visit(False n) {
		// TODO Auto-generated method stub
		System.out.println("mov eax,0");
	}

	public void visit(IdentifierExp n) {
		// TODO Auto-generated method stub
			variable v = null;
			for(variable va : lastClass.getGlobais().values()){
				if(va.getKey().equals(n.s)){
					v = va;
					System.out.println("["+v.getKey()+"]");
				}
			}
			if(v==null){
				for(variable va:lastMethod.getLocais().values()){
					if(va.getKey().equals(n.s)){
						v = va;
					}
				}
				if(v!=null){
					System.out.println("ebp-"+v.getOffset());
				}
			}
			if(v==null){
				for(variable va:lastMethod.getParametros()){
					if(va.getKey().equals(n.s)){
						v = va;
					}
				}
				System.out.println("ebp+"+v.getOffset());
			}
		
	}

	public void visit(This n) {
		// TODO Auto-generated method stub
		System.out.println("mov ecx,"+lastClass.getKey()+"$");
	}

	public void visit(NewArray n) {
		// TODO Auto-generated method stub
		System.out.println("push eax");
		System.out.println("add eax,1");
		System.out.println("shl eax,2");
		System.out.println("push eax");
		System.out.println("call mallocEquiv");
		System.out.println("add esp,4");
		System.out.println("pop edx");
		System.out.println("mov [eax], edx");
		System.out.println("mov ecx,[esp]");
		System.out.println("add eax,4");
	}

	public void visit(NewObject n) {
		// TODO Auto-generated method stub
		if(last instanceof Call){
			Classe c = table.get_class(n.i.s);
			System.out.println(c.getOffset()+"]");
			return;
		}
		int tam = 4;
		Classe c = table.get_class(n.i.s);
		tam += c.getMetodos().size() * 4;
		while(c.getDadClass() != null){
			c = c.getDadClass();
			tam += c.getMetodos().size() *4;
		}
		System.out.println("push "+tam);
		System.out.println("call mallocEquiv");
		System.out.println("add esp, 4");
		System.out.println("lea edx,"+n.i.s+"$$");
		System.out.println("mov [eax],edx");
		System.out.println("mov ecx, eax");
		System.out.println("push ecx");
		System.out.println("call "+n.i.s+"$"+n.i.s);
		System.out.println("pop eax");
		System.out.println("mov [ebp+"+c.getOffset()+"],eax");
	}

	public void visit(Not n) {
		// TODO Auto-generated method stub
		last = n;
		n.e.accept(this);
		last = null;
	}

	public void visit(Identifier n) {
		// TODO Auto-generated method stub
	}
	public void vtable(Classe dadClass){
		String sDad;
		ArrayList<String> mpl = new ArrayList();
		ArrayList<String> ml = new ArrayList();
		ArrayList<String> fim = new ArrayList();

		System.out.println("\t.data");
		if(dadClass == null){
			System.out.println(lastClass.getKey()+"$$"+"\tdd\t"+"0");
			System.out.println("\tdd\t"+lastClass.getKey()+"$"+lastClass.getKey());
			for(String s: lastClass.getMetodos().keySet()){
				System.out.println("\tdd\t"+lastClass.getKey()+"$"+s);
			}
		}else{
			sDad = dadClass.getKey();
			System.out.println(lastClass.getKey()+"$$"+"\tdd\t"+sDad);
			System.out.println("\tdd\t"+lastClass.getKey()+"$"+lastClass.getKey());
			for(String mp : dadClass.getMetodos().keySet()){
				mpl.add(mp);
			}
			for(String m : lastClass.getMetodos().keySet()){
				ml.add(m);
			}
			for(int i=0;i<mpl.size();i++){
				if(ml.contains(mpl.get(i))){
					fim.add("\tdd\t"+lastClass.getKey()+"$"+mpl.get(i).toString());
				}else{
					fim.add("\tdd\t"+dadClass.getKey()+"$"+mpl.get(i).toString());
				}
			}
			for(int j =0;j<ml.size();j++){
				if(!fim.contains("\tdd\t"+lastClass.getKey()+"$"+ml.get(j))){
					fim.add("\tdde\t"+lastClass.getKey()+"$"+ml.get(j).toString());
				}
			}
			for(String s : fim){
				System.out.println(s);
			}

		}

	}
	public void prologo(){
		System.out.println("push ebp");
		System.out.println("mov ebp,esp");
	}
	public void epilogo(){
		System.out.println("pop ebp");
		System.out.println("ret");
	}
}