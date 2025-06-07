package assign01;

/**
 * This class represents a matrix of numbers. It has a set number of rows and
 * columns. A vector is a special case in which the number of columns or rows in
 * one. (A column vector has one column. A row vector has one row).
 *
 * @author CS 2420 course staff & Benjamin Faerber
 * @version 2025-5-13
 */
public class Matrix {

	// 2D array to hold the numbers of the matrix
	protected double[][] data;
	// The number of columns and rows
	protected int columns;
	protected int rows;

	/**
	 * Creates a new matrix by copying in a 2D array of numbers. Input data is a
	 * row-major 2D array. That means the top left corner is data[0][0], and the
	 * number to its right is data[0][1], while the number below it is data[1][0].
	 *
	 * @param data a 2D array holding the numbers of the matrix
	 * @throws IllegalArgumentException if the number of rows or columns is zero, or
	 *                                  if not all rows have the same length.
	 */
	public Matrix(double[][] data) {
		// Check number of rows
		if (data.length == 0)
			throw new IllegalArgumentException("Number of rows must be positive.");
		rows = data.length;

		// Check number of columns
		columns = data[0].length;
		for (int i = 0; i < rows; i++) {
			if (data[i].length == 0) {
				throw new IllegalArgumentException("Number of columns must be positive.");
			}
			if (data[i].length != columns) {
				throw new IllegalArgumentException("Number of columns must be equal for all rows.");
			}
		}

		this.data = new double[rows][columns];
		for (int currentRow = 0; currentRow < rows; currentRow++) {
			for (int currentCol = 0; currentCol < columns; currentCol++) {
				this.data[currentRow][currentCol] = data[currentRow][currentCol];
			}
		}
	}

	/**
	 * Determines whether this matrix is "equal to" another matrix, where equality
	 * is defined as both having the same number of rows, same number of columns,
	 * and containing the same numbers in the same positions.
	 *
	 * @param other another Matrix to compare
	 * @return true if this Matrix is equal to the other
	 */
	public boolean equals(Object other) {
		if (!(other instanceof Matrix))
			return false;
		Matrix otherMat = (Matrix) other;

		if (otherMat.columns != this.columns || otherMat.rows != this.rows) {
			return false;
		}

		for (int currentRow = 0; currentRow < this.rows; currentRow++) {
			for (int currentCol = 0; currentCol < this.columns; currentCol++) {
				double entryA = this.data[currentRow][currentCol];
				double entryB = otherMat.data[currentRow][currentCol];
				if (entryA != entryB) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Returns a new matrix that is the sum of this and another matrix.
	 *
	 * @param other another matrix to be added to this matrix
	 * @return a new matrix that is the sum of this and other
	 * @throws IllegalArgumentException if this and the other matrix do not have the
	 *                                  same number of rows or columns.
	 */
	public Matrix add(Matrix other) {
		if (this.rows != other.rows || this.columns != other.columns) {
			throw new IllegalArgumentException("Matrixes must be the same length and width!");
		}

		// I dont want to mutate the current matrix
		double[][] added = this.data.clone();
		for (int currentRow = 0; currentRow < this.rows; currentRow++) {
			for (int currentCol = 0; currentCol < this.columns; currentCol++) {
				added[currentRow][currentCol] += other.data[currentRow][currentCol];
			}
		}

		return new Matrix(added);
	}

	/**
	 * Returns the product of this and another matrix. The order of multiplication
	 * is (this * other).
	 *
	 * @param other another matrix to multiply this one
	 * @return a new matrix that is the product of this and other
	 * @throws IllegalArgumentException if the number of rows in the other matrix
	 *                                  does not equal the number of columns in
	 *                                  this.
	 */
	public Matrix multiply(Matrix other) {
		if (this.columns != other.rows) {
			throw new IllegalArgumentException("Must have same number of columns and rows!");
		}

		double[][] results = new double[rows][other.columns];
		for (int currentRow = 0; currentRow < rows; currentRow++) {
			for (int currentCol = 0; currentCol < other.columns; currentCol++) {
				for (int j = 0; j < columns; j++) {
					double a = data[currentRow][j];
					double b = other.data[j][currentCol];
					results[currentRow][currentCol] += a * b;
				}
			}
		}

		return new Matrix(results);
	}

	/**
	 * Generates and returns a text representation of this matrix. For example, this
	 * is a 3-row, 2-column matrix: "1.0 2.0 2.0 4.0 3.0 6.0"
	 *
	 * Each line ends with a newline "\n" and no space after the last number. Notice
	 * there is no newline or space at the end of the string.
	 *
	 * @return a string representation of this matrix
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int currentRow = 0; currentRow < this.rows; currentRow++) {
			for (int currentCol = 0; currentCol < this.columns; currentCol++) {
				double current = this.data[currentRow][currentCol];
				builder.append(current);
				if (currentCol != this.columns - 1) {
					builder.append(" ");
				}
			}

			if (currentRow != this.rows - 1) {
				builder.append("\n");
			}
		}

		return builder.toString();
	}
}
