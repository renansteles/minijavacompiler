package TypeCheck;


import AbstractSyntaxTree.Type;
public class variable {
	private String key;
	private Type type;
	private int offset;
	
	public variable(String key, Type type,int offset){
		this.key = key;
		this.type = type;
		this.setOffset(offset);
	}

	public String getKey() {
		return key;
	}

	public Type getType() {
		return type;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}		
}