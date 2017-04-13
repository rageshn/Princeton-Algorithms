public class Percolation {

	private int[][] grid;
	private WeightedQuickUnionUF qUnion = null;
	private int gridLength = 0;

	/*
	 * Initializes an empty N-by-N grids with closed sites
	 * 
	 * @throws java.lang.IllegalArgumentException if N <= 0
	 * 
	 * @param N - grid length
	 */
	public Percolation(int N) {
		if (N <= 0)
			throw new java.lang.IllegalArgumentException(
					"Please provide a value greater than 0");

		grid = new int[N][N];
		qUnion = new WeightedQuickUnionUF(N * N);
		gridLength = N;
		// Initial grid values
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				grid[i][j] = 0;
			}
		}
	}

	/*
	 * Opens a site at (i,j)
	 * 
	 * @throws java.lang.IndexOutOfBoundsException if i or j is not between 1
	 * and grid length
	 * 
	 * @param i grid index
	 * 
	 * @param j grid index
	 */
	public void open(int i, int j) {
		if (i < 1 || i > gridLength || j < 1 || j > gridLength)
			throw new java.lang.IndexOutOfBoundsException();

		grid[i - 1][j - 1] = 1;

		// Top Cell
		if (i > 1 && isOpen(i - 1, j)) {
			qUnion.union(gridLength * (i - 1) + (j - 1), gridLength * (i - 2)
					+ (j - 1));
		}

		// Left Cell
		if (j > 1 && isOpen(i, j - 1)) {
			qUnion.union(gridLength * (i - 1) + (j - 1), gridLength * (i - 1)
					+ (j - 2));
		}

		// Bottom Cell
		if (i < gridLength && isOpen(i + 1, j)) {
			qUnion.union(gridLength * (i - 1) + (j - 1), gridLength * (i)
					+ (j - 1));
		}

		// Right Cell
		if (j < gridLength && isOpen(i, j + 1)) {
			qUnion.union(gridLength * (i - 1) + (j - 1), gridLength * (i - 1)
					+ (j));
		}
	}

	/*
	 * Checks whether a particular site is opened or not
	 * 
	 * @throws java.lang.IndexOutOfBoundsException when i or j is not between 1
	 * and grid length
	 * 
	 * @return boolean as whether the site is open or closed
	 */
	public boolean isOpen(int i, int j) {
		if (i < 1 || i > gridLength || j < 1 || j > gridLength)
			throw new java.lang.IndexOutOfBoundsException();

		return (grid[i - 1][j - 1] == 1) ? true : false;
	}

	/*
	 * Whether the site is fully opened or not
	 * 
	 * @throws java.lang.IndexOutOfBoundsException when the parameter value is
	 * not between 1 and grid length
	 * 
	 * @param i grid index
	 * 
	 * @param j grid index
	 * 
	 * @return boolean as whether the site is fully opened or not
	 */
	public boolean isFull(int i, int j) {
		if (i < 1 || i > gridLength || j < 1 || j > gridLength)
			throw new java.lang.IndexOutOfBoundsException();

		for (int index = 0; index < gridLength; index++) {
			if (isOpen(i, j)
					&& qUnion.connected(i, gridLength * (i - 1) + (j - 1))) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Checks whether the system percolates or not
	 * 
	 * @return boolean as whether the system percolates or not
	 */
	public boolean percolates() {
		for (int x = 0; x < gridLength; x++) {
			for (int y = 0; y < gridLength; y++) {
				if (isOpen(1, x + 1)
						&& isOpen(gridLength, y + 1)
						&& qUnion.connected(x, (gridLength - 1) * gridLength
								+ y)) {
					return true;
				}
			}
		}
		return false;
	}
}