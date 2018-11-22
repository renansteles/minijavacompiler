package minijavacompiler;

import Scanner.*;
import TypeCheck.CreateTable;
import TypeCheck.TypeVisitor;
import TypeCheck.table;
import Visitor.PrettyPrintVisitor;
import Parser.*;
import AbstractSyntaxTree.*;
import CodeGen.Generate;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;
import java_cup.runtime.Symbol;

public class Main {
    public static void main(String [] args) {
    	Scanner si = new Scanner(System.in);
    	int escolha ;
    	String arq ="";
    	while(true){
	        try {
	        	System.out.println("\n------------------------------\n (1) - BinarySearch \n (2) - BinaryTree \n (3) - BubbleSort \n (4) - Factorial \n (5) - LinearSearch \n (6) - Linked List \n (7) - QuickSort \n (8) - TreeVisitor \n (9) - Final \t\n (0) - EXIT \n------------------------------\n");
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
	        	case 9:
	        		arq = "Final";
	        		break;
	        	case 0:
	        		return;
	        	default:
	        		System.out.println("Opção Incorreta!");
	        		arq = "";
	        		break;
	        	}
	        	if(!arq.equals("")){
	        		// Getting the option chosen by the user
		        	FileInputStream f = new FileInputStream(System.getProperty("user.dir")+"/src/SamplePrograms/SampleMiniJavaPrograms/"+arq+".java");
		        	InputStream is = f;
		        	
		        	// Scanner
		            scanner s = new scanner(is);
		            System.out.println("Scanner Sucefull");
		            
		            // Parser
		            parser p = new parser(s);
		            Symbol root;
		            root = p.parse();
		            System.out.println("Parsing successfull");
					Program program = (Program)root.value;
					
					// Printing  .java
					program.accept(new PrettyPrintVisitor());
					
					// Creates Table 1, passed from the analyzed
					table table = new table();
					CreateTable ct = new CreateTable(table);
					program.accept(ct);
					
					//Table 2ª, passed of the syntactic analyzer, type checking
					TypeVisitor typeVisitor = new TypeVisitor(table);
					program.accept(typeVisitor);
					
					//Printing the table
					System.out.println(typeVisitor);
					
					// Generating assembly code
					Generate g = new Generate(table);
					program.accept(g);
				
	        	}
	        }  catch (Exception e) {
	            System.err.println("Unexpected internal compiler error: " + 
	                               e.toString());
	            e.printStackTrace();
	        }
	    }
	}
}