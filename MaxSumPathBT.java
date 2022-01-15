import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class MaxSumPathBT {
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

	// Maximum path sum from root to leaf
	public static int getMaxPathSumRootLeaf(Node node, int max, int curr) { // O(n) time, O(log(n)) space
		if (node == null) {
			return max;
		}
		curr += node.data;
		if (node.left == null && node.right == null) {
			max = Math.max(max, curr);
			return max;
		}
		max = Math.max(max, getMaxPathSumRootLeaf(node.left, max, curr));
		max = Math.max(max, getMaxPathSumRootLeaf(node.right, max, curr));
		return max;
	}

	// Maximum path sum from a leaf to another leaf
	public static Pair<Integer, Integer> getMaxPathSumLeaf(Node node) { // O(n) time, O(log(n)) space
		if (node == null) {
			return Pair.of(0, 0);
		}
		Pair<Integer, Integer> left = getMaxPathSumLeaf(node.left);
		Pair<Integer, Integer> right = getMaxPathSumLeaf(node.right);
		int lltl = left.first, rltl = right.first;
		int lrtl = left.second, rrtl = right.second;
		int ltl = Math.max(Math.max(lltl, rltl), lrtl + rrtl + node.data);
		int rtl = Math.max(lrtl, rrtl) + node.data;
		return Pair.of(ltl, rtl);
	}

	// Maximum path sum from any node to any other node
	public static Pair<Integer, Integer> getMaxPathSum(Node node) { // O(n) time, O(log(n)) space
		if (node == null) {
			return Pair.of(0, 0);
		}
		Pair<Integer, Integer> left = getMaxPathSum(node.left);
		Pair<Integer, Integer> right = getMaxPathSum(node.right);
		int max = Math.max(left.first, right.first);
		int asNode = Math.max(Math.max(left.second, right.second) + node.data, node.data);
		max = Math.max(max, Math.max(asNode, left.second + node.data + right.second));
		return Pair.of(max, asNode);
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		// Custom driver code for this question
		Node root = new Node(10);
		root.left = new Node(2);
		root.left.left = new Node(20);
		root.left.right = new Node(1);
		root.right = new Node(10);
		root.right.right = new Node(25);
		root.right.right.left = new Node(3);
		root.right.right.right = new Node(4);

		System.out.println(getMaxPathSum(root).first);
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
