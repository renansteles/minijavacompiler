package types;

public class IntArray extends AbsTipo{
	public int dimen;
	public String tipo;
	
	@Override
	public boolean igual(AbsTipo outro) {
		if(!(outro instanceof AbsTipo)){
			return false;
		}
		IntArray i = (IntArray) outro;
		if(dimen != i.dimen){
			return false;
		}
		return tipo.equals(i.tipo);
	}
	@Override
	public boolean insta(AbsTipo outro) {
		return false;
	}
}