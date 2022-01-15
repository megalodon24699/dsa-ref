import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class ConnectNodesBT {
	static class Node {
		int data;
		Node left, right, next;

		public Node(int aData) {
			data = aData;
			left = null;
			right = null;
			next = null;
		}

		@Override
		public String toString() {
			return Integer.toString(data);
		}
	}

	public static void connect(Node root) { // O(n) time, O(n) space
		Deque<Node> queue = new ArrayDeque<>();
		if (root == null) {
			return;
		}
		queue.add(root);
		while (!queue.isEmpty()) {
			int n = queue.size();
			for (int i = 0; i < n; ++i) {
				Node node = queue.remove();
				node.next = (i == n-1) ? null : queue.getFirst();
				if (node.left != null) {
					queue.add(node.left);
				}
				if (node.right != null) {
					queue.add(node.right);
				}
			}
		}
	}

	public static void inorder(Node root) {
		if (root == null) {
			return;
		}
		inorder(root.left);
		System.out.print(root + " ");
		inorder(root.right);
	}

	public static void getList(Node node) {
		while (node != null) {
			System.out.print(node + " ");
			node = node.next;
		}
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		// Custom driver code for this question
		Node root = new Node(10);
		root.left = new Node(20);
		root.left.left = new Node(40);
		root.left.right = new Node(60);
		root.right = new Node(30);

		connect(root);

		System.out.print("{ "); getList(root); getList(root.left); getList(root.left.left); System.out.println("}");
		System.out.print("{ "); inorder(root); System.out.println("}");
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
