import java.util.Comparator;

/*
 * Implements a immutable data type for a points on a plane
 */
public class Point implements Comparable<Point> {
	/*
	 * Compare points by slope
	 */
	public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();;

	private final int x;
	private final int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/*
	 * Draw a particular point
	 */
	public void draw() {
		StdDraw.point(x, y);
	}

	/*
	 * Draws a line from the invoking point to argument point.
	 */
	public void drawTo(Point that) {
		StdDraw.line(this.x, this.y, that.x, that.y);
	}

	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	@Override
	public int compareTo(Point that) {
		if (that == null) {
			throw new java.lang.NullPointerException();
		}
		if ((this.y < that.y) || (this.y == that.y && this.x < that.x)) {
			return -1;
		} else if (this.x == that.x && this.y == that.y) {
			return 0;
		}
		return 1;
	}

	/*
	 * Calculates the slope from invoking point to argument point
	 */
	public double slopeTo(Point that) {
		if (that == null) {
			throw new java.lang.NullPointerException();
		}
		if ((this.x == that.x) && (this.y == that.y)) {
			return Double.NEGATIVE_INFINITY;
		} else if (this.x == that.x) {
			return Double.POSITIVE_INFINITY;
		} else if (this.y == that.y) {
			return 0.0;
		}
		return (double) (that.y - this.y) / (that.x - this.x);
	}

	/*
	 * Compares the points by slope
	 */
	private class SlopeOrder implements Comparator<Point> {
		public int compare(Point a, Point b) {
			if (a == null || b == null) {
				throw new java.lang.NullPointerException();
			}
			double slopeA = slopeTo(a);
			double slopeB = slopeTo(b);
			if (slopeA == slopeB) {
				return 0;
			} else if (slopeA < slopeB) {
				return -1;
			}
			return 1;
		}
	}
}
