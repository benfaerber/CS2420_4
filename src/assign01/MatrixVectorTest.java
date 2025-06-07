package assign01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This tester class assesses the correctness of the Matrix and Vector classes.
 * 
 * IMPORTANT NOTE: The tests provided to get you started rely heavily on a 
 *                 correctly implemented equals method.  Be careful of false
 *                 positives (i.e., tests that pass because your equals method
 *                 incorrectly returns true). 
 * 
 * @author CS 2420 course staff & Benjamin Faerber
 * @version 2025-5-13
 */
class MatrixVectorTest {
	
	private Matrix squareMat, twoFiveMat, twoTwoMat, threeThreeMat, identityMat, canvasExampleA, canvasExampleB;
	private Vector threeVec, fiveVec, twoVec, vec123, vec456, vec213;

	@BeforeEach
	void setUp() throws Exception {
		// Creates a 3x3 matrix
		squareMat = new Matrix(new double[][] {{1, 2, 3},{7, 8, 9},{4, 5, 6}});
		// Creates a 2x5 matrix
		twoFiveMat = new Matrix(new double[][] {{-4, 7, 1, 0, 2},{6, 3, 9, 1, 5}});
		// Creates a 2x2 matrix
		twoTwoMat = new Matrix(new double[][] {{1.7, -22.4},{2.718, 55}});
		threeThreeMat = new Matrix(new double[][] {{1, 2, 3},{4, 5, 6}, {7, 8, 9}});
		// Creates a 5x5 identity matrix
		identityMat = new Matrix(new double[][] {{1,0,0,0,0},{0,1,0,0,0},{0,0,1,0,0},{0,0,0,1,0},{0,0,0,0,1}});
		
		// Creates vectors
		threeVec = new Vector(new double[] {2.1, 4.7, -0.8});
		fiveVec = new Vector(new double[] {10, 1.5, 200, 17.2, 3.14});
		twoVec = new Vector(new double[] {1000, 1});
		
		vec123 = new Vector(new double[] {1, 2, 3});
		vec456 = new Vector(new double[] {4, 5, 6});
		vec213 = new Vector(new double[] {2, 1, 3});
		
		canvasExampleA = new Matrix(new double[][] {
			{1, 2, 3},
			{4, 5, 6},
			{7, 8, 9},
		});
		
		canvasExampleB = new Matrix(new double[][] {
			{10, 11},
			{12, 13},
			{14, 15},
		});

	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void matrixEquality() {
		assertTrue(squareMat.equals(new Matrix(new double[][] {{1, 2, 3},{7, 8, 9},{4, 5, 6}})));
	}
	
	@Test
	void matrixInequalityDifferentValues() {
		assertFalse(squareMat.equals(new Matrix(new double[][] {{1, 2, 3},{7, 5, 9},{4, 5, 6}})));
	}
	
	@Test
	void vectorInequalityDifferentSizes() {
		assertFalse(threeVec.equals(fiveVec));
	}

	@Test
	public void createMatrixFromBadArray() {
		double arr[][] = { { 1, 2 }, { 3 } };
		assertThrows(IllegalArgumentException.class, () -> { new Matrix(arr); });
		// NOTE: The code above is an example of a lambda expression.
	}
	
	@Test
	void matrixToString() {
		String resultStr = "1.7 -22.4\n2.718 55.0";
		assertEquals(resultStr, twoTwoMat.toString());		
	}
	
	@Test
	void matrixAdd() {
		assertTrue((twoTwoMat.add(twoTwoMat)).equals(new Matrix(new double[][] {{1.7*2, -22.4*2},{2.718*2, 55*2}})));
	}
	
	@Test
	void matrixAddBadLengths() {
		assertThrows(IllegalArgumentException.class, () -> { twoTwoMat.add(threeThreeMat); });
	}
	
	@Test
	void matrixMultiply() {
		double product[][] = {{1.7*1.7 - 22.4*2.718, -22.4*1.7 - 22.4*55},{2.718*1.7 + 55*2.718, -22.4*2.718 + 55*55}};
		assertTrue((twoTwoMat.multiply(twoTwoMat)).equals(new Matrix(product)));
	}
	
	@Test
	void vectorDotProduct() {
		double dotProdResult = threeVec.dotProduct(threeVec);
		assertEquals(2.1*2.1 + 4.7*4.7 + (-0.8)*(-0.8), dotProdResult, (2.1*2.1 + 4.7*4.7 + (-0.8)*(-0.8)) * 1e-14);
	}
	
	@Test
	void vectorMagnitude() {
		double mag = twoVec.magnitude();
		assertEquals(Math.sqrt(1000001), mag, Math.sqrt(1000001) * 1e-14);
	}
	
	// Recall that the methods from Matrix are also inherited by Vector.
	// For testing exceptions, use the pattern from createMatrixFromBadArray() above:
	//   assertThrows(IllegalArgumentException.class, () -> { [statement]; });
	// replacing [statement] with the code you expect to throw an exception
	
	/**
	 * The first example of matrix multiplication listed in the assignment details
	 */
	@Test
	void matrixMultiplyCanvasExample() {
		Matrix canvasExampleA = new Matrix(new double[][] {
			{1, 2, 3},
			{4, 5, 6},
			{7, 8, 9},
		});
		
		Matrix canvasExampleB = new Matrix(new double[][] {
			{10, 11},
			{12, 13},
			{14, 15},
		});

		double product[][] = {
			{76, 82},
			{184, 199},
			{292, 316},
		};
		assertTrue((canvasExampleA.multiply(canvasExampleB)).equals(new Matrix(product)));
	}
	
	/**
	 * The second example of matrix multiplication listed in the assignment details
	 */
	@Test
	void matrixMultiplyCanvasExample2() {
		Matrix canvasExampleA = new Matrix(new double[][] {
			{1, 2, 3},
			{4, 5, 6},
			{7, 8, 9},
		});
		
		Matrix canvasExampleB = new Matrix(new double[][] {
			{10},
			{11},
			{12},
		});

		double product[][] = {
			{68},
			{167},
			{266},
		};
		assertTrue((canvasExampleA.multiply(canvasExampleB)).equals(new Matrix(product)));
	}
	
	/** The dot product example from canvas */
	@Test
	public void dotProductCanvasExample() {
		Vector vecA = new Vector(new double[] {1, 2, 3});
		Vector vecB = new Vector(new double[] {4, 5, 6});
		
		assertEquals(vecA.dotProduct(vecB), 32);
	}

	/** The vector magnitude example from canvas */
	@Test
	void vectorMagnitudeCanvasExample() {
		double mag = vec123.magnitude();
		double expected = Math.sqrt(1*1 + 2*2 + 3*3);
		assertEquals(expected, mag, expected * 1e-14);
	}
	
	/** Ensure matrix sizes are properly validated before multiplying */
	@Test
	public void attemptMultiplyInvalidMatrix() {		
		assertThrows(IllegalArgumentException.class, () -> twoTwoMat.multiply(threeThreeMat));
	}
	
	/** Ensure dotProduct validates the length of the vectors! */	
	@Test
	void attemptVectorInvalidDotProduct() {
		assertThrows(IllegalArgumentException.class, () -> { threeVec.dotProduct(twoVec); });
	}
	
	@Test
	void matrixToStringOneRow() {
		String resultStr = "1.7 -22.4";
		assertEquals(resultStr, new Matrix(new double[][] {{1.7, -22.4}}).toString());		
	}
	
	@Test
	void vectorToString() {
		String resultStr = "1.7\n-22.4";
		String stringified = new Vector(new double[] {1.7, -22.4}).toString();
		assertTrue(resultStr.equals(stringified));
	}
	
	@Test
	void matrixMultipliedByVector1() {
		Matrix mulled = squareMat.multiply(vec123);
		Vector expected = new Vector(new double[] {14, 50, 32});
		assertEquals(mulled, expected);		
	}
	
	@Test
	void matrixMultipliedByVector2() {
		Vector smallVec = new Vector(new double[] { 1, 2 });
		Matrix mat = new Matrix(new double[][] {
			{1, 2},
			{1, 2},
			{1, 2},
		});
		
		Matrix added = mat.multiply(smallVec);
		Vector expected = new Vector(new double[] {5, 5, 5});
		assertEquals(added, expected);		
	}
	
	@Test
	void matrixMultipliedByVector3() {
		Matrix mul = canvasExampleA.multiply(vec213);
		Vector expected = new Vector(new double[] {13, 31, 49});
		
		assertEquals(mul, expected);
	}
	
	
	@Test
	void matrixMultipliedByVector4() {
		Vector smallVec = new Vector(new double[] { 1, 2, 3 });
		Matrix mat = new Matrix(new double[][] {
			{1, 2, 3},
			{1, 2, 3},
			{1, 2, 3},
		});
		
		Matrix added = mat.multiply(smallVec);
		Vector expected = new Vector(new double[] {14, 14, 14});
		assertEquals(added, expected);		
	}
}