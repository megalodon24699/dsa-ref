import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class QueueLL {
	static class Node {
		int data;
		Node next;

		public Node(int aData) {
			data = aData;
			next = null;
		}
	}

	static class LinkedList {
		Node head, tail;

		public LinkedList() {
			head = null;
			tail = null;
		}

		@Override
		public String toString() {
			String repr = "{ ";
			Node ptr = head;
			while (ptr != null) {
				repr += ptr.data + " ";
				ptr = ptr.next;
			}
			repr += "}";
			return repr;
		}

		public void addFront(int data) {
			Node toAdd = new Node(data);
			if (head == null && tail == null) {
				head = toAdd;
				tail = toAdd;
				return;
			}
			toAdd.next = head;
			head = toAdd;
		}

		public void addBack(int data) {
			Node toAdd = new Node(data);
			if (head == null && tail == null) {
				head = toAdd;
				tail = toAdd;
				return;
			}
			tail.next = toAdd;
			tail = toAdd;
		}

		public int remove() {
			if (head == null && tail == null) {
				return -1;
			}
			int t = head.data;
			if (head == tail) {
				head = null;
				tail = null;
			} else {
				head = head.next;
			}
			return t;
		}
	}

	static class Queue {
		LinkedList ll;

		public Queue() {
			ll = new LinkedList();
		}

		@Override
		public String toString() {
			return ll.toString();
		}

		public void enqueue(int data) { // O(1) time, O(1) space
			ll.addBack(data);
		}

		public int dequeue() { // O(1) time, O(1) space
			return ll.remove();
		}
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		Queue q = new Queue();
		int n = fs.nextInt();
		for (int i = 0; i < n; ++i) {
			int cmd = fs.nextInt();
			if (cmd == 1) {
				q.enqueue(fs.nextInt());
			}
			else {
				System.out.println(q.dequeue());
			}
		}
	}

	static class FastScanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");

		String next() {
			while (!st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}

		long nextLong() {
			return Long.parseLong(next());
		}
	}

	static class Pair<F extends Comparable<F>, S extends Comparable<S>> implements Comparable<Pair<F, S>> {
		public final F first;
		public final S second;

		Pair(F aFirst, S aSecond) {
			first = aFirst;
			second = aSecond;
		}

		@Override
		public int compareTo(Pair<F, S> o) {
			int t = first.compareTo(o.first);
			if (t == 0) {
				t = second.compareTo(o.second);
			}
			return t;
		}

		@Override
		public int hashCode() {
			return (31 + first.hashCode()) * 31 + second.hashCode();
		}

		@Override
		public boolean equals(Object o) {
			if (!(o instanceof Pair)) {
				return false;
			}
			if (o == this) {
				return true;
			}
			Pair p = (Pair)o;
			return first.equals(p.first) && second.equals(p.second);
		}

		@Override
		public String toString() {
			return "{ " + first + ", " + second + " }";
		}

		public static <F extends Comparable<F>, S extends Comparable<S>> Pair<F, S> of(F aFirst, S aSecond) {
			return new Pair<>(aFirst, aSecond);
		}
	}
}
