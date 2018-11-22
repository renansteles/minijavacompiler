package TypeCheck;

import java.util.HashMap;
import java.util.Map;


public class table{
	Map<String,Classe> classes ;

	public table(){
		classes = new HashMap<String,Classe>(); 
	}

	public boolean add_class(String s, Classe c){
		if(classes.containsKey(s)){
			return false;
		}
		classes.put(s, new Classe(s,c));
		return true;
	}

	public Classe get_class(String s){
		if(classes.containsKey(s)){
			return classes.get(s);
		}
		return null;
	}
	public void update_class(String s, Classe c){
		classes.replace(s, c);
	}


	public Metodo get_methods(String s,String c2){
		Classe c = get_class(c2);
		if(c == null) throw new IllegalArgumentException("The class: "+c2+"are not defined");
		while(c != null){
			if(c.getMethods(s) !=null){
				return c.getMethods(s);
			}else{
				if(c.getDadClass()== null){
					c = null;
				}else{
					c = c.getDadClass();
				}
			}
		}
		throw new IllegalArgumentException("Metodo" +s+ "não definido na classe"+c2);
	}

	public variable get_var(Metodo m,Classe c, String s){
		if(m != null){
			if(m.getLocais().containsKey(s)){
				return m.getLocais().get(s);
			}
			if(m.getParametros().contains(s)){
				return m.pegar_parametro(s);
			}
		}

		while(c != null){
			if(c.getGlobals(s)!= null){
				return c.getGlobals(s);
			}else{
				if(c.getDadClass()== null){
					c = null;
				}else{
					c = c.getDadClass();
				}
			}
		}
		throw new IllegalArgumentException("Variavel "+s+"não definida");

	}

	public Map<String, Classe> getClasses() {
		return classes;
	}



}