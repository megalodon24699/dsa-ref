import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class VerticalBT {
	static class Node implements Comparable<Node> {
		int data;
		Node left, right;

		public Node(int aData) {
			data = aData;
			left = null;
			right = null;
		}

		@Override
		public int compareTo(Node node) {
			return data - node.data;
		}

		@Override
		public String toString() {
			return Integer.toString(data);
		}
	}

	public static Pair<Integer, Integer> getWidth(Node node, int hd, int aMin, int aMax) {
		if (node == null) {
			return Pair.of(aMin, aMax);
		}
		int min = Math.min(hd, aMin); int max = Math.max(hd, aMax);
		Pair<Integer, Integer> left = getWidth(node.left, hd-1, min, max);
		Pair<Integer, Integer> right = getWidth(node.right, hd+1, min, max);
		min = Math.min(left.first, right.first); max = Math.max(left.second, right.second);
		return Pair.of(min, max);
	}

	public static void getVerticalLine(Node node, int hd, int line) {
		if (node == null) {
			return;
		}
		if (hd == line) {
			System.out.print(node.data + " ");
		}
		getVerticalLine(node.left, hd-1, line);
		getVerticalLine(node.right, hd+1, line);
	}

	public static void verticalOrder(Node root) { // O(n^2) time, O(n) space
		Pair<Integer, Integer> p = getWidth(root, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
		int min = p.first; int max = p.second;
		System.out.print("{ ");
		for (int line = min; line <= max; ++line) {
			getVerticalLine(root, 0, line);
		}
		System.out.println("}");
	}

	public static void verticalOrderOptimized(Node root) { // O(n*log(n)) time, O(n) space
		Map<Integer, List<Integer>> map = new TreeMap<>();
		Deque<Pair<Node, Integer>> queue = new ArrayDeque<>();
		if (root == null) {
			return;
		}
		queue.add(Pair.of(root, 0));
		while (!queue.isEmpty()) {
			Pair<Node, Integer> p = queue.remove();
			Node node = p.first;
			int hd = p.second;
			if (map.containsKey(hd)) {
				map.get(hd).add(node.data);
			} else {
				List<Integer> l = new ArrayList<>();
				l.add(node.data);
				map.put(hd, l);
			}
			if (node.left != null) {
				queue.add(Pair.of(node.left, hd-1));
			}
			if (node.right != null) {
				queue.add(Pair.of(node.right, hd+1));
			}
		}
		System.out.print("{ ");
		for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
			for (int i : entry.getValue()) {
				System.out.print(i + " ");
			}
		}
		System.out.println("}");
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
		root.right.left.right = new Node(8);
		root.right.right = new Node(7);
		root.right.right.right = new Node(9);

		verticalOrderOptimized(root);
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
