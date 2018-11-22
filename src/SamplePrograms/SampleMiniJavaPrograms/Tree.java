
class Tree{
	Tree left ;
	Tree right;
	int key ;
	boolean has_left ;
	boolean has_right ;
	Tree my_null ;


	//Tree new_node ;
	//Tree current_node ;
	//Tree parent_node ;

	// boolean ntb ;
	//boolean cont ;
	//boolean found ;
	//int ifound ;
	//  boolean is_root ;
	//  int     nti ;
	//  int key_aux ;
	// int auxkey1 ;
	// int auxkey2 ;

	public boolean Init(int v_key){
		key = v_key ;
		has_left = false ;
		has_right = false ;
		return true ;
	}

	public boolean SetRight(Tree rn){
		right = rn ;
		return true ;
	}

	public boolean SetLeft(Tree ln){
		left = ln ;
		return true ;
	}

	public Tree GetRight(){
		return right ;
	}

	public Tree GetLeft(){
		return left;
	}

	public int GetKey(){
		return key ;
	}

	public boolean SetKey(int v_key){
		key = v_key ;
		return true ;
	}

	public boolean GetHas_Right(){
		return has_right ;
	}

	public boolean GetHas_Left(){
		return has_left ;
	}

	public boolean SetHas_Left(boolean val){
		has_left = val ;
		return true ;
	}

	public boolean SetHas_Right(boolean val){
		has_right = val ;
		return true ;
	}

	public boolean Compare(int num1 , int num2){
		boolean ntb ;
		int nti ;

		ntb = false ;
		nti = num2 + 1 ;
		if (num1 < num2) ntb = false ;
		else if (!(num1 < nti)) ntb = false ;
		else ntb = true ;
		return ntb ;
	}

	public boolean Insert(int v_key){
		Tree new_node ;
		boolean ntb ;
		Tree current_node ;
		boolean cont ;
		int key_aux ;

		new_node = new Tree();
		ntb = new_node.Init(v_key) ;
		current_node = this ;
		cont = true ;
		while (cont){
			key_aux = current_node.GetKey();
			if (v_key < key_aux){
				if (current_node.GetHas_Left())
					current_node = current_node.GetLeft() ;
				else {
					cont = false ;
					ntb = current_node.SetHas_Left(true);
					ntb = current_node.SetLeft(new_node);
				}
			}
			else{
				if (current_node.GetHas_Right())
					current_node = current_node.GetRight() ;
				else {
					cont = false ;
					ntb = current_node.SetHas_Right(true);
					ntb = current_node.SetRight(new_node);
				}
			}
		}
		return true ;
	}

	public boolean Delete(int v_key){
		Tree current_node ;
		Tree parent_node ;
		boolean cont ;
		boolean found ;
		boolean ntb ;
		boolean is_root ;
		int key_aux ;

		current_node = this ;
		parent_node = this ;
		cont = true ;
		found = false ;
		is_root = true ;
		while (cont){
			key_aux = current_node.GetKey();
			if (v_key < key_aux)
				if (current_node.GetHas_Left()){
					parent_node = current_node ;
					current_node = current_node.GetLeft() ;
				}
				else cont = false ;
			else 
				if (key_aux < v_key)
					if (current_node.GetHas_Right()){
						parent_node = current_node ;
						current_node = current_node.GetRight() ;
					}
					else cont = false ;
				else { 
					if (is_root) 
						if (!current_node.GetHas_Right() && 
								!current_node.GetHas_Left() )
							ntb = true ;
						else 
							ntb = this.Remove(parent_node,current_node); 
					else ntb = this.Remove(parent_node,current_node);
					found = true ;
					cont = false ;
				}
			is_root = false ;
		}
		return found ;
	}

	public boolean Remove(Tree p_node, Tree c_node){
		boolean ntb ;
		int auxkey1 ;
		int auxkey2 ;

		if (c_node.GetHas_Left()) 
			ntb = this.RemoveLeft(p_node,c_node) ;
		else 
			if (c_node.GetHas_Right())
				ntb = this.RemoveRight(p_node,c_node) ;
			else {
				auxkey1 = c_node.GetKey();
				auxkey2 = (p_node.GetLeft()).GetKey() ;
				if (this.Compare(auxkey1,auxkey2)) {
					ntb = p_node.SetLeft(my_null);
					ntb = p_node.SetHas_Left(false);
				}
				else {
					ntb = p_node.SetRight(my_null);
					ntb = p_node.SetHas_Right(false);
				}
			}
		return true ;
	}

	public boolean RemoveRight(Tree p_node, Tree c_node){
		boolean ntb ;
		while (c_node.GetHas_Right()){
			ntb = c_node.SetKey((c_node.GetRight()).GetKey());
			p_node = c_node ;
			c_node = c_node.GetRight() ;
		}
		ntb = p_node.SetRight(my_null);
		ntb = p_node.SetHas_Right(false);
		return true ;
	}

	public boolean RemoveLeft(Tree p_node, Tree c_node){
		boolean ntb ;
		while (c_node.GetHas_Left()){
			ntb = c_node.SetKey((c_node.GetLeft()).GetKey());
			p_node = c_node ;
			c_node = c_node.GetLeft() ;
		}
		ntb = p_node.SetLeft(my_null);
		ntb = p_node.SetHas_Left(false);
		return true ;
	}


	public int Search(int v_key){
		Tree current_node ;
		int ifound ;
		boolean cont ;
		int key_aux ;

		current_node = this ;
		cont = true ;
		ifound = 0 ;
		while (cont){
			key_aux = current_node.GetKey();
			if (v_key < key_aux)
				if (current_node.GetHas_Left())
					current_node = current_node.GetLeft() ;
				else cont = false ;
			else 
				if (key_aux < v_key)
					if (current_node.GetHas_Right())
						current_node = current_node.GetRight() ;
					else cont = false ;
				else { 
					ifound = 1 ;
					cont = false ;
				}
		}
		return ifound ;
	}

	public boolean Print(){
		boolean ntb ;
		Tree current_node ;

		current_node = this ;
		ntb = this.RecPrint(current_node);
		return true ;
	}

	public boolean RecPrint(Tree node){
		boolean ntb ;

		if (node.GetHas_Left()){
			ntb = this.RecPrint(node.GetLeft());
		} else ntb = true ;
		System.out.println(node.GetKey());
		if (node.GetHas_Right()){
			ntb = this.RecPrint(node.GetRight());
		} else ntb = true ;
		return true ;
	}

	public int accept(Visitor v){
		int nti ;

		System.out.println(333);
		nti = v.visit(this) ;
		return 0 ;
	}

}
