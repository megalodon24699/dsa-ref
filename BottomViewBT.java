import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BottomViewBT {
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

	public static void bottomView(Node root) { // O(n*log(n)) time, O(w) space (log(n) due to TreeMap)
		Deque<Pair<Node, Integer>> queue = new ArrayDeque<>();
		Map<Integer, Integer> map = new TreeMap<>();
		if (root == null) {
			return;
		}
		queue.add(Pair.of(root, 0));
		while (!queue.isEmpty()) {
			Pair<Node, Integer> p = queue.remove();
			Node node = p.first;
			int hd = p.second;
			map.put(hd, node.data);
			if (node.left != null) {
				queue.add(Pair.of(node.left, hd-1));
			}
			if (node.right != null) {
				queue.add(Pair.of(node.right, hd+1));
			}
		}
		System.out.print("{ ");
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			System.out.print(entry.getValue() + " ");
		}
		System.out.println("}");
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		// Custom driver code for this question
		Node root = new Node(10);
		root.left = new Node(20);
		root.left.left = new Node(40);
		root.left.right = new Node(60);
		root.right = new Node(30);

		bottomView(root);
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
