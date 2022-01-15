import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class BtDll {
	static class Node implements Comparable<Node> {
		int data;
		Node left, right;

		public Node(int aData) {
			data = aData;
			left = null;
			right = null;
		}

		@Override
		public String toString() {
			return Integer.toString(data);
		}

		@Override
		public int compareTo(Node node) {
			return data - node.data;
		}
	}

	public static Pair<Node, Node> toDll(Node root) { // O(n) time, O(log(n)) space
		if (root == null) {
			return Pair.of(null, null);
		}
		Pair<Node, Node> pLeft = toDll(root.left);
		Pair<Node, Node> pRight = toDll(root.right);
		Node head = root, tail = root;
		if (root.left != null) {
			head = pLeft.first;
			root.left = pLeft.second;
			pLeft.second.right = root;
		}
		if (root.right != null) {
			tail = pRight.second;
			root.right = pRight.first;
			pRight.first.left = root;
		}
		return Pair.of(head, tail);
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		// Custom driver code for this function
		Node root = new Node(10);
		root.left = new Node(12);
		root.left.left = new Node(25);
		root.left.right = new Node(30);
		root.right = new Node(15);
		root.right.left = new Node(36);

		Pair<Node, Node> p = toDll(root);

		Node ptr = p.first;
		System.out.print("{ ");
		while (ptr != null) {
			System.out.print(ptr.data + " ");
			ptr = ptr.right;
		}
		System.out.println("}");

		ptr = p.second;
		System.out.print("{ ");
		while (ptr != null) {
			System.out.print(ptr.data + " ");
			ptr = ptr.left;
		}
		System.out.println("}");
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
