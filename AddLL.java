import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class AddLL {
	static class Node implements Comparable<Node> {
		int data;
		Node next;

		public Node(int data) {
			this.data = data;
			this.next = null;
		}

		@Override
		public int compareTo(Node node) {
			return data - node.data;
		}
	}

	static class LinkedList {
		public Node head;

		public void add(int data) {
			Node toAdd = new Node(data);
			toAdd.next = head;
			head = toAdd;
		}

		@Override
		public String toString() {
			String repr = "[ ";
			Node ptr = head;
			while (ptr != null) {
				repr += ptr.data + " ";
				ptr = ptr.next;
			}
			repr += "]";
			return repr;
		}
	}

	public static int getLength(Node a) {
		int count = 0;
		while (a != null) {
			++count;
			a = a.next;
		}
		return count;
	}

	public static void addZeroes(LinkedList a, int k) {
		// This method is my implementation specific
		// This will change if I added elements from tail
		for (int i = 0; i < k; ++i) {
			a.add(0);
		}
	}

	public static Pair<Node, Integer> add(Node a, Node b) { // O(m+n) time, O(1) space (if recursion stack is not considered)
		if (a == null) {
			return Pair.of(null, 0);
		}
		Pair<Node, Integer> p = add(a.next, b.next);
		Node res = new Node(a.data + b.data + p.second);
		int carry = 0;
		if (res.data > 9) {
			carry = 1;
			res.data -= 10;
		}
		res.next = p.first;
		return Pair.of(res, carry);
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int m = fs.nextInt();
		int n = fs.nextInt();
		LinkedList a = new LinkedList();
		for (int i = 0; i < m; ++i) {
			a.add(fs.nextInt());
		}
		LinkedList b = new LinkedList();
		for (int i = 0; i < n; ++i) {
			b.add(fs.nextInt());
		}

		int diff = getLength(a.head) - getLength(b.head);
		if (diff != 0) {
			if (diff > 0) {
				addZeroes(b, diff);
			} else {
				addZeroes(a, -diff);
			}
		}

		LinkedList res = new LinkedList();
		res.head = add(a.head, b.head).first;

		System.out.println(res);
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
