
/*
 * This class reads the input & number of values to print from command line and 
 * and uses randomized queue to print the elements in random
 */
public class Subset {
	public static void main(String[] args) {
		int k = Integer.parseInt(args[0]);
		RandomizedQueue<String> rqueue = new RandomizedQueue<String>();
		String input = StdIn.readLine();
		String[] elements = input.split("\\s+");
		for (String s : elements) {
			rqueue.enqueue(s);
		}
		for (int i = 0; i < k; i++) {
			StdOut.println(rqueue.dequeue());
		}
	}

}