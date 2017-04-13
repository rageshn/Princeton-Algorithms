import java.util.Iterator;

/*
 * This class implements the double ended queue which supports adding and removing items
 * from either front or back of the data structure.
 */
public class Deque<Item> implements Iterable<Item> {
	private Node first;
	private Node last;
	private int itemCount;

	/*
	 * Class specifying each node
	 */
	private class Node {
		private Item item;
		private Node next;
		private Node prev;

		Node(Item item) {
			this.item = item;
			this.next = null;
			this.prev = null;
		}
	}

	/*
	 * Class for iterating over the collection
	 */
	private class DequeIterator implements Iterator<Item> {
		private Node current;

		public DequeIterator() {
			current = first;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}

		@Override
		public Item next() {
			if (!hasNext()) {
				throw new java.util.NoSuchElementException();
			}
			Node node = current;
			current = current.next;
			return node.item;
		}
	}

	public Deque() {
		first = null;
		last = null;
		itemCount = 0;
	}

	/*
	 * Checks whether the collection is empty or not
	 * 
	 * @returns boolean value indicating whether the collection is empty or not
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/*
	 * Returns the size of the collection
	 * 
	 * @returns Collection size as an Integer
	 */
	public int size() {
		return itemCount;
	}

	/*
	 * Adds the item in the front of the collection
	 * 
	 * @throws java.lang.NullPointerException when null value is passed in
	 * parameter
	 * 
	 * @Param item to be added
	 */
	public void addFirst(Item item) {
		if (item == null)
			throw new java.lang.NullPointerException("Item cannot be null");

		if (this.isEmpty()) {
			first = new Node(item);
			last = first;
		} else {
			Node node = new Node(item);
			node.next = first;
			first.prev = node;
			first = node;
		}
		itemCount++;
	}

	/*
	 * Adds item at the end of the collection
	 * 
	 * @throws java.lang.NullPointerException when null value is passed in the
	 * parameter.
	 * 
	 * @Param item to be added
	 */
	public void addLast(Item item) {
		if (item == null)
			throw new java.lang.NullPointerException("Item cannot be null");

		if (isEmpty()) {
			last = new Node(item);
			first = last;
		} else {
			Node node = new Node(item);
			last.next = node;
			node.prev = last;
			last = node;
		}
		itemCount++;
	}

	/*
	 * Removes the first element in the collection
	 * 
	 * @throws java.util.NoSuchElementException if the collection is empty
	 * 
	 * @returns item which is removed
	 */
	public Item removeFirst() {
		if (this.isEmpty())
			throw new java.util.NoSuchElementException();

		Node node = first;
		if (size() == 1) {
			first = null;
			last = null;
		} else {
			first.next.prev = null;
			first = first.next;
		}
		itemCount--;
		node.next = null;
		return node.item;
	}

	/*
	 * Removes the last element from the collection and returns the same
	 * 
	 * @throws java.util.NoSuchElementException if the collection is empty.
	 * 
	 * @returns item which is removed
	 */
	public Item removeLast() {
		if (this.isEmpty())
			throw new java.util.NoSuchElementException();

		Node node = last;
		if (size() == 1) {
			last = null;
			first = null;
		} else {
			last.prev.next = null;
			last = last.prev;
		}
		itemCount--;
		node.next = null;
		return node.item;
	}

	@Override
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}

	/*
	 * For unit testing
	 */
	public static void main(String[] args) {
		Deque<String> dq = new Deque<String>();
		dq.addFirst("Hi");
		dq.addLast("Hello");
		dq.removeLast();
		dq.addFirst("How");
		dq.addFirst("are");
		dq.removeFirst();
		dq.addLast("you");
		dq.removeLast();

		for (String s : dq) {
			System.out.print(s + " - ");
		}
	}
}