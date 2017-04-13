import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Brute {
	private static List<Point> readList(String filename) throws Exception {
		Scanner scan = new Scanner(new File(filename));
		int numpoints = scan.nextInt();
		List<Point> points = new ArrayList<Point>();
		for (int i = 0; i < numpoints; i++) {
			int x = scan.nextInt();
			int y = scan.nextInt();
			Point p = new Point(x, y);
			points.add(p);
		}
		scan.close();
		return points;
	}

	public static void main(String args[]) throws Exception {
		String filename = args[0];
		List<Point> points = readList(filename);
		Collections.sort(points);
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);

		for (int i = 0; i < points.size(); i++) {
			points.get(i).draw();
		}
		for (int i = 0; i < points.size(); i++) {
			for (int j = i + 1; j < points.size(); j++) {
				for (int k = j + 1; k < points.size(); k++) {
					for (int l = k + 1; l < points.size(); l++) {
						double slopeij = points.get(i).slopeTo(points.get(j));
						double slopejk = points.get(j).slopeTo(points.get(k));
						double slopekl = points.get(k).slopeTo(points.get(l));
						double slopeli = points.get(l).slopeTo(points.get(i));

						if ((slopeij == slopejk) && (slopejk == slopekl)
								&& (slopekl == slopeli) && (slopeij == slopeli)) {
							System.out.print(points.get(i) + "->");
							System.out.print(points.get(j) + "->");
							System.out.print(points.get(k) + "->");
							System.out.print(points.get(l) + "\n");
							points.get(i).drawTo(points.get(l));
						}
					}
				}
			}
		}
	}

}
