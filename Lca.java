import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Lca {
	static class Node {
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
	}

	public static Node getLcaBst(Node root, int a, int b) { // O(log(n)) time, O(log(n)) space (can be made O(1) through iteration)
		if (root == null) {
			return null;
		}
		if (root.data < a && root.data < b) {
			return getLcaBst(root.right, a, b);
		}
		else if (root.data > a && root.data > b) {
			return getLcaBst(root.left, a, b);
		} else {
			return root;
		}
	}

	public static boolean getPath(Node root, int n, List<Node> path) {
		if (root == null) {
			return false;
		}
		path.add(root);
		if (root.data == n) {
			return true;
		}
		if (root.left != null && getPath(root.left, n, path)) {
			return true;
		}
		if (root.right != null && getPath(root.right, n, path)) {
			return true;
		}
		path.remove(root);
		return false;
	}

	public static Node getLcaBt(Node root, int a, int b) { // O(n) time, O(n) space
		List<Node> pathA = new ArrayList<>();
		List<Node> pathB = new ArrayList<>();
		if (!getPath(root, a, pathA) || !getPath(root, b, pathB)) {
			return null;
		}
		int i = 0, size = Math.min(pathA.size(), pathB.size());
		while (i < size && pathA.get(i).data == pathB.get(i).data) {
			++i;
		}
		return pathA.get(i-1);
	}

	public static Node getLcaBtSingleTraversal(Node root, int a, int b) { // O(n) time, O(n) space
		// Assumes that the keys are present in the tree
		// Can be modified to handle if the keys are not present
		if (root == null) {
			return null;
		}
		if (root.data == a || root.data == b) {
			return root;
		}
		Node left = getLcaBtSingleTraversal(root.left, a, b);
		Node right = getLcaBtSingleTraversal(root.right, a, b);
		if (left != null && right != null) {
			return root;
		}
		return (left != null) ? left : right;
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		// Custom driver code for this question
		Node root = new Node(1);
		root.left = new Node(2);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.right = new Node(3);
		root.right.left = new Node(6);
		root.right.right = new Node(7);

		System.out.println(getLcaBtSingleTraversal(root, 4, 5));
		System.out.println(getLcaBtSingleTraversal(root, 4, 6));
		System.out.println(getLcaBtSingleTraversal(root, 3, 4));
		System.out.println(getLcaBtSingleTraversal(root, 2, 4));
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
