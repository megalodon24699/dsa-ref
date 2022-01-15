import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Sort012LL {
	static class Node {
		int data;
		Node next;

		public Node(int data) {
			this.data = data;
			this.next = null;
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

	public static void sort012(Node head) { // O(n) time, O(1) space
		Node ptr = head;
		Node zeroes = null, ones = null, twos = null;
		while (ptr != null) {
			Node at = ptr;
			ptr = ptr.next;
			switch (at.data) {
				case 0:
					at.next = zeroes;
					zeroes = at;
					break;
				case 1:
					at.next = ones;
					ones = at;
					break;
				case 2:
					at.next = twos;
					twos = at;
					break;
			}
		}
		// I don't have the mechanism to add from back, so displaying it here itself
		// Modify this to merge and return the head of the merged list
		// I have just ended up breaking the input list into 3 separate lists :P
		ptr = zeroes;
		System.out.print("{ ");
		while (ptr != null) {
			System.out.print(ptr.data + " ");
			ptr = ptr.next;
		}
		ptr = ones;
		while (ptr != null) {
			System.out.print(ptr.data + " ");
			ptr = ptr.next;
		}
		ptr = twos;
		while (ptr != null) {
			System.out.print(ptr.data + " ");
			ptr = ptr.next;
		}
		System.out.println("}");
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();
		LinkedList ll = new LinkedList();
		for (int i = 0; i < n; ++i) {
			ll.add(fs.nextInt());
		}

		sort012(ll.head);
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
