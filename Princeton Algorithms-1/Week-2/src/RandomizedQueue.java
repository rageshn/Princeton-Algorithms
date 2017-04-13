import java.util.Iterator;

/*
 * This class implements the randomized queue where item is removed at random.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] queue;
	private int size;

	/*
	 * Initializes an empty data structure
	 */
	public RandomizedQueue() {
		size = 0;
		queue = (Item[]) new Object[size];
	}

	/*
	 * Checks whether the collection is empty or not
	 * 
	 * @returns boolean value indicating whether the collection is empty or not
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/*
	 * Returns the size of the collection
	 * 
	 * @returns Collection size as an Integer
	 */
	public int size() {
		return size;
	}

	/*
	 * Adds an element in the queue
	 * 
	 * @param item to be added
	 */
	public void enqueue(Item item) {
		if (item == null)
			throw new java.lang.NullPointerException();

		if (size == queue.length) {
			Item[] newqueue = null;
			if (queue.length == 0) {
				newqueue = (Item[]) new Object[1];
			} else {
				newqueue = (Item[]) new Object[2 * queue.length];
			}
			for (int i = 0; i < size; i++) {
				newqueue[i] = queue[i];
			}
			queue = newqueue;
		}
		queue[size] = item;
		size++;
	}

	/*
	 * Removes a random element from the queue
	 * 
	 * @returns item removed
	 */
	public Item dequeue() {
		if (size == 0)
			throw new java.util.NoSuchElementException();

		if (size <= queue.length / 4 && queue.length > size) {
			Item[] newqueue = (Item[]) new Object[queue.length / 2];
			for (int i = 0; i < size; i++) {
				newqueue[i] = queue[i];
			}
			queue = newqueue;
		}
		int index = StdRandom.uniform(size);
		Item item = queue[index];
		queue[index] = queue[--size];
		queue[size] = null;

		return item;
	}

	/*
	 * Sample method to check the removal functionality. It returns an element
	 * without deleting it
	 * 
	 * @returns item
	 */
	public Item sample() {
		if (size == 0)
			throw new java.util.NoSuchElementException();

		int index = StdRandom.uniform(size);
		return queue[index];
	}

	@Override
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator();
	}

	/*
	 * Class for iterating over the collection
	 */
	private class RandomizedQueueIterator implements Iterator<Item> {
		private Item[] copyqueue;
		private int iteratorindex;

		public RandomizedQueueIterator() {
			copyqueue = (Item[]) new Object[size];

			for (int i = 0; i < size; i++) {
				copyqueue[i] = queue[i];
			}
			for (int j = 1; j < size; j++) {
				int index = StdRandom.uniform(size);
				Item item = copyqueue[index];
				copyqueue[j] = copyqueue[index];
				copyqueue[index] = item;
			}
		}

		@Override
		public boolean hasNext() {
			return iteratorindex < size;
		}

		@Override
		public Item next() {
			if (iteratorindex == size)
				throw new java.util.NoSuchElementException();

			return copyqueue[iteratorindex++];
		}

		@Override
		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}
	}

	public static void main(String[] args) {
		RandomizedQueue<Integer> rqueue = new RandomizedQueue<Integer>();
		rqueue.enqueue(1);
		rqueue.enqueue(2);
		rqueue.dequeue();
		rqueue.enqueue(3);
		rqueue.dequeue();
		rqueue.enqueue(4);
		rqueue.enqueue(5);
		rqueue.dequeue();
		rqueue.enqueue(6);
		rqueue.dequeue();
		rqueue.enqueue(7);
		rqueue.enqueue(8);
		rqueue.dequeue();
		rqueue.enqueue(9);
		rqueue.enqueue(10);

		for (int i : rqueue) {
			System.out.print(i + " - ");
		}
	}
}