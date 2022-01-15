import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class RemoveLoopLL {
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

	public static void removeLoop(LinkedList ll) { // O(n) time, O(1) space
		boolean flag = false;
		Node slow = ll.head, fast = ll.head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				flag = true;
				break;
			}
		}
		if (flag) {
			slow = ll.head;
			if (slow == fast) {
				while (fast.next != slow) {
					fast = fast.next;
				}
				fast.next = null;
			} else {
				while (slow.next != fast.next) {
					slow = slow.next;
					fast = fast.next;
				}
				fast.next = null;
			}
		}
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();
		int x = fs.nextInt();
		LinkedList ll = new LinkedList();
		for (int i = 0; i < n; ++i) {
			ll.add(fs.nextInt());
		}
		Node ptr = ll.head;
		for (int i = 0; i < x; ++i) {
			ptr = ptr.next;
		}
		Node to = ptr;
		while (ptr.next != null) {
			ptr = ptr.next;
		}
		ptr.next = to;

		removeLoop(ll);

		System.out.println(ll);
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
