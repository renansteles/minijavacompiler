package types;

public class Boolean extends AbsTipo {
	String tipo;

	@Override
	public boolean igual(AbsTipo outro) {
		// TODO Auto-generated method stub		
		if(!(outro instanceof AbsTipo)){
			return false;
		}
		Boolean i = (Boolean) outro;	
		return tipo.equals(i.tipo);
	}

	@Override
	public boolean insta(AbsTipo outro) {
		// TODO Auto-generated method stub
		return false;
	}
	
}