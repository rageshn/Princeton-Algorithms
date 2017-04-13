public class PercolationStats {

	private Percolation perc;
	private int expCount;
	private double[] percolationThreshold;

	/*
	 * Validates the Percolation for N*N grids for T independent experiments
	 * 
	 * @throws java.lang.IllegalArgumentException when N <= 0 or T <= 0
	 * 
	 * @param N Number of grids
	 * 
	 * @param T Number of experiments
	 */
	public PercolationStats(int N, int T) {
		if (N <= 0 || T <= 0)
			throw new java.lang.IllegalArgumentException(
					"Values of N and T must be greate than 0");

		expCount = T;
		int random_i, random_j, count;
		percolationThreshold = new double[T];

		for (int i = 0; i < T; i++) {
			perc = new Percolation(N);
			count = 0;

			while (!perc.percolates()) {
				random_i = StdRandom.uniform(N) + 1;
				random_j = StdRandom.uniform(N) + 1;
				if (!perc.isOpen(random_i, random_j)) {
					count++;
					perc.open(random_i, random_j);
				}
			}
			percolationThreshold[i] = count / (double) (N * N);
		}
	}

	/*
	 * Calculates the mean Percolation Threshold for T experiments
	 * 
	 * @return mean percolation threshold for T experiments
	 */
	public double mean() {
		double sum = 0;
		double mean = 0;
		for (int i = 0; i < percolationThreshold.length; i++) {
			sum += percolationThreshold[i];
		}
		mean = sum / expCount;

		return mean;
	}

	/*
	 * Calculates Standard Deviation of Percolation Thresholds for T experiments
	 * 
	 * @return Standard Deviation of Percolation Threshold for T experiments
	 */
	public double stddev() {
		double sum = 0;
		double sd = 0;
		for (int i = 0; i < percolationThreshold.length; i++) {
			sum += Math.pow(percolationThreshold[i] - mean(), 2);
		}
		sd = sum / (expCount - 1);
		sd = Math.sqrt(sd);

		return sd;
	}

	/*
	 * Calculates the Low end point of 95% confidence interval
	 * 
	 * @return Low end point of 95% confidence interval
	 */
	public double confidenceLo() {
		double low = 0;
		low = mean() - (1.96 * stddev() / Math.sqrt(expCount));
		return low;
	}

	/*
	 * Calculates the High end point of 95% confidence level
	 * 
	 * @return High end point of 95% confidence level
	 */
	public double confidenceHi() {
		double high;
		high = mean() + (1.96 * stddev() / Math.sqrt(expCount));
		return high;
	}

	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);

		PercolationStats pStats = new PercolationStats(N, T);
		StdOut.println("java PercolationStats " + N + " " + T);
		StdOut.println("mean                    = " + pStats.mean());
		StdOut.println("stddev                  = " + pStats.stddev());
		StdOut.println("95% confidence interval = " + pStats.confidenceLo()
				+ " , " + pStats.confidenceHi());
	}
}