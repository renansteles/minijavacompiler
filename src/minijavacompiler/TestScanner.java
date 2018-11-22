package minijavacompiler;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;

import Scanner.*;
import Parser.sym;
import java_cup.runtime.Symbol;

public class TestScanner {
    public static void main(String [] args) {
    	Scanner si = new Scanner(System.in);
    	int escolha ;
    	String arq ="";
        try {
        	while(true){
            // create a scanner on the input file
        	System.out.println("Escolha qual o programa deseja!" +
        			"\n (1) - BinarySearch \t (2) - BinaryTree \n (3) - BubbleSort \t (4) - Factorial \n (5) - LinearSearch \t (6) - Linked List \n (7) - QuickSort \t (8) - TreeVisitor \n (0) - Sair \n");
        	escolha = si.nextInt();
            // create a scanner on the input file
        	switch(escolha){
        	case 1:
        		arq = "BinarySearch";
        		break;
        	case 2:
        		arq = "BinaryTree";
        		break;
        	case 3:
        		arq = "BubbleSort";
        		break;
        	case 4:
        		arq = "Factorial";
        		break;
        	case 5:
        		arq = "LinearSearch";
        		break;
        	case 6:
        		arq = "LinkedList";
        		break;
        	case 7:
        		arq = "QuickSort";
        		break;
        	case 8:
        		arq = "TreeVisitor";
        		break;
        	case 0:
        		return;
        	default:
        		System.out.println("Opção Incorreta!");
        		arq = "";
        		break;
        	}
        	if(!arq.equals("")){
	        
        	FileInputStream f = new FileInputStream("SamplePrograms/SampleMiniJavaPrograms/"+arq+".java");
        	InputStream is = f;
            scanner s = new scanner(is);
                Symbol t = s.next_token();
                while (t.sym != sym.EOF){ 
                    // print each token that we scan
                    System.out.print(s.symbolToString(t) + " ");
                    t = s.next_token(); 
                }
            System.out.print("\nLexical analysis successfull\n"); 
        	}
        }
        } catch (Exception e) {
            // yuck: some kind of error in the compiler implementation
            // that we're not expecting (a bug!)
            System.err.println("Unexpected internal compiler error: " + 
                        e.toString());
            // print out a stack dump
            e.printStackTrace();
        }
   }
}

