import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class ShuntingYardAlgo {
	
	
	/**
	 * Enum for assigning the precedence of each operator 1 being the lowest and 4 being the highest 
	 * opPrecedence variable is used to get the precedence of the particular operator.
	 */
	private enum OperatorPrecedence{
		ADD(1), SUBTRACT(1), MULTIPLY(2), DIVIDE(2), EXPO(3), UNARY(4);
		final int opPrecedence;
		OperatorPrecedence(int opPrecedence){
			this.opPrecedence = opPrecedence;
		}
	}
	
	//Adding all the operators and their precedence values to the HashMap. Operators are stored as Characters	
	private static Map<Character, OperatorPrecedence> operators = new HashMap<Character, OperatorPrecedence>() {{
		put('+',OperatorPrecedence.ADD);
		put('-',OperatorPrecedence.SUBTRACT);
		put('*',OperatorPrecedence.MULTIPLY);
		put('/',OperatorPrecedence.DIVIDE);
		put('^',OperatorPrecedence.EXPO);
		put('!',OperatorPrecedence.UNARY);			
	}};
		
	/**
	 * hasHigherPrecedence method is used to get if operator2 is of higher precedence than the operator1 passed as parameters
	 * @param operator1
	 * @param operator2
	 * @return
	 */
	private static boolean hasHigherPrecedence(char operator1, char operator2){
		if(operators.containsKey(operator2) && operators.containsKey(operator1)){
			return (operators.get(operator2).opPrecedence >= operators.get(operator1).opPrecedence);
		}
		else
			return false;
	}
	
	/**
	 * returnPostFix method returns the postfix notation of the corresponding infix passed as parameter.
	 * If the character read is a letter or a number it is directly added to the output postfix notation.
	 * If the character is an operator, push to the stack if the operator at the top of the stack has a lower
	 * precedence. If the operator at the top of the stack has higher precedence, pop until the top of the stack 
	 * has an operator of lower precedence. If the character is a '(', push to the stack. If it a ')' pop all operators 
	 * till a '(' is encountered. If the infix notation is read, and the stack is non-empty, pop everything from the stack. 
	 * @param infix
	 * @return
	 */
	public static Queue<Character> returnPostFix(String infix){
		Queue<Character> postfix = new LinkedList<Character>();
		Stack<Character> stack = new Stack<Character>();
		for(char s:infix.toCharArray()){                           //iterating over the infix string
			if(Character.isDigit(s) || Character.isLetter(s)){     //Checking if the character is a number or a letter
				postfix.add(s);
			}
			else if(operators.containsKey(s)){                    //checking if the character is one of the operators
				while(!stack.isEmpty() && hasHigherPrecedence(s, stack.peek())){
					postfix.add(stack.pop());               
				}
				stack.push(s);                                    
			}
			else if(s == '('){                                    //if the character was '(' push to the stack
				stack.push(s);
			}
			else if(s == ')'){                                    //if the character read was ')' 
				while(stack.peek() != '(' ){
					postfix.add(stack.pop());
				}
				stack.pop();
			}
		}
		while(!stack.isEmpty()){                                   //if the input infix notation is empty & stack contains operators
			postfix.add(stack.pop());
		}
		return postfix;
	}
	
	/**
	 * The method calls the returnPostFix with the user input infix notation
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException{
		Scanner in;
		 if (args.length > 0) {
		 File inputFile = new File(args[0]);
		 in = new Scanner(inputFile);
		 } 
		 else {
			 in = new Scanner(System.in); 
		 }
		 Queue<Character> postFix = ShuntingYardAlgo.returnPostFix(in.next());
		 System.out.println("Postfix expression: "+postFix.toString());
		 in.close();
	}
}

/** 
 * Sample input : a+b-c
 * Output : Postfix expression: [a, b, +, c, -]
 * /
