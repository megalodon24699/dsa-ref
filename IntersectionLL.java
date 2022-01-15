import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class IntersectionLL {
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

	public static Node getIntersection(LinkedList a, LinkedList b) { // O(m+n) time, O(1) space
		Node aPtr = a.head;
		Node bPtr = b.head;
		if (aPtr == null || bPtr == null) {
			return null;
		}
		while (aPtr != bPtr) {
			aPtr = aPtr.next;
			bPtr = bPtr.next;
			if (aPtr == null) {
				aPtr = b.head;
			}
			if (bPtr == null) {
				bPtr = a.head;
			}
		}
		return aPtr;
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		// Custom driver code for this question
		LinkedList a = new LinkedList();
		a.add(5);
		a.add(4);
		a.add(8);
		Node toJoin = a.head;
		a.add(1);
		a.add(4);

		LinkedList b = new LinkedList();
		b.add(1);
		b.add(6);
		b.add(5);
		b.head.next.next.next = toJoin;

		System.out.println(getIntersection(a, b).data);
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
