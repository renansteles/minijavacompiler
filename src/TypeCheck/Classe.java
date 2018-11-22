package TypeCheck;

import java.util.HashMap;
import java.util.Map;

import AbstractSyntaxTree.Identifier;
import AbstractSyntaxTree.IdentifierType;
import AbstractSyntaxTree.Type;

public class Classe {
	private String key;
	private Classe dadClass;
	private Map<String,Metodo> Methods;
	private Map<String,variable> globals;
	private int offset;
	static int os;
	public Classe(String key, Classe pai) {
		this.key = key;
		this.dadClass = pai;
		this.Methods = new HashMap<String, Metodo>();
		this.globals = new HashMap<String, variable>();
		this.offset = Classe.os+4;
		Classe.os += 4;
	}
	
	public boolean addMethods(String s, Metodo m){
		if(Methods.containsKey(s)){
			return false;
		}
		Methods.put(s, m);
		return true;
	}
	public boolean addGlobals(String s, variable v){
		if(globals.containsKey(s)){
			return false;
		}
		globals.put(s, v);
		return true;
	}
	
	public Metodo getMethods(String s){
		if(Methods.containsKey(s)){
			return Methods.get(s);
		}
		return null;
	}
	
	public variable getGlobals(String s){
		if(globals.containsKey(s)){
			return globals.get(s);
		}
		return null;
	}
	
	public void updateMethods(String s, Metodo m){
		Methods.replace(s, m);
	}
	public void updateGlobals(String s, variable v){
		globals.replace(s, v);
	}

	public String getKey() {
		return key;
	}

	public Classe getDadClass() {
		return dadClass;
	}

	public Map<String, Metodo> getMetodos() {
		return Methods;
	}

	public Map<String, variable> getGlobais() {
		return globals;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setPai(Classe pai) {
		this.dadClass = pai;
	}

	public void setMetodos(Map<String, Metodo> Metodos) {
		this.Methods = Metodos;
	}

	public void setGlobais(Map<String, variable> globais) {
		this.globals = globais;
	}
	
	public String toString(){
		String result = "\tVariaveis Globais: \n";
		for(String s: globals.keySet()){
			variable v = globals.get(s);
			result +="\t\t";
			result += v.getKey()+"\t -> \t"+v.getType()+"\t -> \t"+v.getOffset()+"\n";
		}
		result += "\tMetodos: \n";
		for(String s:Methods.keySet()){
			Metodo m = Methods.get(s);
			result += m.toString();
		}
		
		return result;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
}