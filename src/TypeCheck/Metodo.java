package TypeCheck;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import AbstractSyntaxTree.*;

public class Metodo {
	private String key;
	private Type tipo;
	private Map<String,variable> locais;
	private List<variable> parametros;
	private int offset;
	static int os;
	
	public Metodo(String s, Type t){
		this.key = s;
		tipo = t;
		locais = new HashMap<String,variable>();
		parametros = new ArrayList<variable>();
		this.offset = Classe.os+4;
		Classe.os += 4;
	}
	
	public boolean adicionar_parametros(variable v){
		if(parametros.contains(v)){
			return false;
		}
		parametros.add(v);
		return true;
	}
	
	public boolean adicionar_locais(String s, variable v){
		if(locais.containsKey(s)){
			return false;
		}
		locais.put(s, v);	
		return true;
	}
	
	public variable pegar_parametro(String s){
		for(variable v: parametros){
			if(v.getKey().equals(s)){
				return v;
			}
		}
		return null;
	}

	public String getKey() {
		return key;
	}

	public Type getTipo() {
		return tipo;
	}

	public Map<String, variable> getLocais() {
		return locais;
	}

	public List<variable> getParametros() {
		return parametros;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setTipo(Type tipo) {
		this.tipo = tipo;
	}

	public void setLocais(Map<String, variable> locais) {
		this.locais = locais;
	}

	public void setParametros(List<variable> parametros) {
		this.parametros = parametros;
	}
	
	public String toString(){
		String result = "\t\t"+this.key+"\t -> \t"+this.tipo+"\n" +
				"\t\t\tParametros: \n";
		for(variable v : parametros){
			result +="\t\t\t\t";
			result += v.getKey()+"\t -> \t"+v.getType()+"\t -> \t"+v.getOffset()+"\n";
		}
		result += "\t\t\tLocais: \n";
		for(String s : locais.keySet()){
			variable v = locais.get(s);
			result +="\t\t\t\t";
			result += v.getKey()+"\t -> \t"+v.getType()+"\t -> \t"+v.getOffset()+"\n";
		}
		
		return result;
	}

	public int getOffset() {
		return offset;
	}
}