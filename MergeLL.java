import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class MergeLL {
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

	public static Node merge(Node a, Node b) { // O(m+n) time, O(1) space (if converted to iterative/tail recursive))
		if (a == null) {
			return b;
		}
		if (b == null) {
			return a;
		}
		Node res;
		if (a.data <= b.data) {
			res = a;
			res.next = merge(a.next, b);
		} else {
			res = b;
			res.next = merge(a, b.next);
		}
		return res;
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

		a.head = merge(a.head, b.head);

		System.out.println(a);
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
}
