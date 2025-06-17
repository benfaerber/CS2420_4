package assign06;

import java.util.Scanner;

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
		String allowedChars = "0123456789. +-*/";
		for (String letter : expression.split("")) {
			if (! allowedChars.contains(letter)) {
				throw new IllegalArgumentException("Can only contain numbers and operators!");
			}
		}

		LinkedListStack<Double> stack = new LinkedListStack<>();
		Scanner scanner = new Scanner(expression);

		try {
			while (scanner.hasNext()) {
				if (scanner.hasNextDouble()) {
					double currentDouble = scanner.nextDouble();
					stack.push(currentDouble);
					continue;
				}

				if (scanner.hasNext()) {
					String operator = scanner.next();

					double itemRight = stack.pop();
					double itemLeft = stack.pop();

					double newResult = applyOperator(itemLeft, operator, itemRight);
					stack.push(newResult);
				}
			}

			return stack.pop();
		} catch (Exception e) {
			// If we get an error from our popping, consider it an illegal expression
			throw new IllegalArgumentException("Invalid expression " + expression);
		}

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
