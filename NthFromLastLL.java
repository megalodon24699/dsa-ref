import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class NthFromLastLL {
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

	public static int getNthLast(LinkedList ll, int k) { // O(n) time, O(1) space
		Node l = ll.head, r = ll.head;
		int count = 0;
		while (count < k && r != null) {
			r = r.next;
			++count;
		}
		if (count < k) {
			return -1;
		}
		while (r != null) {
			r = r.next;
			l = l.next;
		}
		return l.data;
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();
		int k = fs.nextInt();
		LinkedList ll = new LinkedList();
		for (int i = 0; i < n; ++i) {
			ll.add(fs.nextInt());
		}

		System.out.println(ll);

		System.out.println(getNthLast(ll, k));
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
