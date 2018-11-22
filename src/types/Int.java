package types;

public class Int extends AbsTipo{
	public String tipo;
	
	@Override
	public boolean igual(AbsTipo outro) {
		if(!(outro instanceof AbsTipo)){
			return false;
		}
		Int i = (Int) outro;
		
		return tipo.equals(i.tipo);
	}

	@Override
	public boolean insta(AbsTipo outro) {
		return false;
	}

}