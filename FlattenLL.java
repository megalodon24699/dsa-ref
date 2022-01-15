import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class FlattenLL {
	static class Node {
		int data;
		Node right, bottom;

		public Node(int data) {
			this.data = data;
			this.right = null;
			this.bottom = null;
		}

		public static Node addVertical(Node node, int data) {
			Node toAdd = new Node(data);
			toAdd.bottom = node;
			node = toAdd;
			return node;
		}
	}

	static class LList {
		public Node head;

		@Override
		public String toString() {
			String repr = "[ ";
			Node col = head;
			while (col != null) {
				repr += "{ ";
				Node ptr = col;
				while (ptr != null) {
					repr += ptr.data + " ";
					ptr = ptr.bottom;
				}
				repr += "} ";
				col = col.right;
			}
			repr += "]";
			return repr;
		}
	}

	public static Node merge(Node a, Node b) {
		if (a == null) {
			return b;
		}
		if (b == null) {
			return a;
		}
		Node res;
		if (a.data <= b.data) {
			res = a;
			res.bottom = merge(res.bottom, b);
		} else {
			res = b;
			res.bottom = merge(a, res.bottom);
		}
		res.right = null;
		return res;
	}

	public static Node flatten(Node node) { // O(m*n) time, O(1) space
		if (node == null || node.right == null) {
			return node;
		}
		node.right = flatten(node.right);
		return merge(node, node.right);
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		// Custom data structure and driver code for this question
		LList ll = new LList();

		Node first = new Node(30);
		first = Node.addVertical(first, 8);
		first = Node.addVertical(first, 7);
		first = Node.addVertical(first, 5);

		Node second = new Node(20);
		second = Node.addVertical(second, 10);

		Node third = new Node(50);
		third = Node.addVertical(third, 22);
		third = Node.addVertical(third, 19);

		Node fourth = new Node(45);
		fourth = Node.addVertical(fourth, 40);
		fourth = Node.addVertical(fourth, 35);
		fourth = Node.addVertical(fourth, 28);

		third.right = fourth;
		second.right = third;
		first.right = second;

		ll.head = first;

		System.out.println(ll);

		ll.head = flatten(ll.head);

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
