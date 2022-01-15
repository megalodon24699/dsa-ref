import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class PalindromeLL {
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

	public static Node reverse(Node head) {
		if (head == null || head.next == null) {
			return head;
		}
		Node p = null, c = head, n = null;
		while (c != null) {
			n = c.next;
			c.next = p;
			p = c;
			c = n;
		}
		return p;
	}

	public static boolean compare(Node a, Node b) {
		Node aPtr = a, bPtr = b;
		while (aPtr != null && bPtr != null) {
			if (aPtr.data != bPtr.data) {
				return false;
			}
			aPtr = aPtr.next;
			bPtr = bPtr.next;
		}
		if (aPtr == null && bPtr == null) {
			return true;
		}
		return false;
	}

	public static boolean isPalindrome(Node head) { // O(n) time, O(1) space
		if (head == null || head.next == null) {
			return true;
		}
		Node prev = head, slow = head, fast = head;
		while (fast != null && fast.next != null) {
			prev = slow;
			slow = slow.next;
			fast = fast.next.next;
		}
		Node middle = null;
		if (fast != null) { // odd number of elements
			middle = slow;
			slow = slow.next;
		}
		Node second = slow; // second half
		prev.next = null;
		second = reverse(second);
		boolean result = compare(head, second);
		second = reverse(second);
		if (middle != null) {
			prev.next = middle;
			middle.next = second;
		} else {
			prev.next = second;
		}
		return result;
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();
		LinkedList ll = new LinkedList();
		for (int i = 0; i < n; ++i) {
			ll.add(fs.nextInt());
		}

		System.out.println(isPalindrome(ll.head));
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
