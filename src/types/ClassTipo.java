package types;

import TypeCheck.Classe;


public class ClassTipo extends AbsTipo{
	Classe pai;
	String key;
	
	public ClassTipo(Classe p, String k){
		this.pai = p;
		this.key = k;
	}
	public String getKey(){
		return this.key;
	}
	public Classe getPai(){
		return this.pai;
	}
	


	@Override
	public boolean igual(AbsTipo outro) {
		// TODO Auto-generated method stub
		ClassTipo t = (ClassTipo) outro;
		if(this.key.equals(t.key)){
			return true;
		}
		return false;
	}

	@Override
	public boolean insta(AbsTipo outro) {
		// TODO Auto-generated method stub
		ClassTipo t = (ClassTipo) outro;
		Classe c2 = this.pai;
		while(c2 != null){
			if(c2.getKey().equals(t.key)){
				return true;
			}
			c2 = c2.getDadClass();
		}
		return false;
	}

}