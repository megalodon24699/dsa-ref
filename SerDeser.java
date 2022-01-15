import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class SerDeser {
	static class Node implements Comparable<Node>  {
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

	public static void serialize(Node node, StringBuilder sb) { // O(n) time, O(log(n)) stack space
		if (node == null) {
			sb.append("# ");
			return;
		}
		sb.append(node.data + " ");
		serialize(node.left, sb);
		serialize(node.right, sb);
	}

	public static Pair<Node, Integer> deserialize(String[] s, int idx) { // O(n) time, O(log(n)) stack space ?
		if (s[idx].equals("#")) {
			return Pair.of(null, idx+1);
		}
		Node node = new Node(Integer.parseInt(s[idx]));
		Pair<Node, Integer> p = deserialize(s, idx+1);
		node.left = p.first;
		idx = p.second;
		p = deserialize(s, idx);
		node.right = p.first;
		idx = p.second;
		return Pair.of(node, idx);
	}

	public static boolean isEqual(Node a, Node b) { // O(min(m, n)) time, O(log(min(m, n))) space
		if (a == null && b == null) {
			return true;
		} else if (a != null && b != null) {
			return (a.data == b.data) && isEqual(a.left, b.left) && isEqual(a.right, b.right);
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		Node root = new Node(20);
		root.left = new Node(8);
		root.left.left = new Node(4);
		root.left.right = new Node(12);
		root.left.right.left = new Node(10);
		root.left.right.right = new Node(14);

		StringBuilder sb = new StringBuilder();
		serialize(root, sb);
		String serialized = sb.toString().trim();

		Node newRoot = deserialize(serialized.split(" "), 0).first;

		System.out.println(isEqual(root, newRoot)); // verify
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
