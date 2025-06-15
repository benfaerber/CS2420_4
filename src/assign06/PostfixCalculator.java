package assign06;

/**
 * Evaluates postfix expressions using a stack.
 * 
 * @author CS 2420 course staff and Benjamin Faerber
 * @version 06/14/2025
 */
public class PostfixCalculator {
	
	/**
	 * Evaluates the postfix expression.
	 * This method must use an instance of your LinkedListStack class.
	 * 
	 * @param expression to evaluate
	 * @return value that the expression evaluates to
	 * @throws IllegalArgumentException if the expression in invalid
	 */
	public static double evaluate(String expression) {
		// TODO fill in this method using your LinkedListStack class
		
		return 0.0;
	}
	
	/**
	 * Private helper method.
	 * Performs one operation using two operands.
	 * The operator must be one of "+", "-", "*", or "/".
	 * 
	 * @param left operand
	 * @param operator to apply
	 * @param right operand
	 * @return the result of applying the operator
	 */
	private static double applyOperator(double left, String operator, double right) {
		switch(operator) {
		case "+":
			return left + right;
		case "-":
			return left - right;
		case "*":
			return left * right;
		case "/":
			return left / right;
		}
		throw new IllegalArgumentException("Unsupported operator: " + operator);
	}
}
