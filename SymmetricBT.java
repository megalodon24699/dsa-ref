import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class SymmetricBT {
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

	public static boolean isMirror(Node a, Node b) {
		if (a == null && b == null) {
			return true;
		} else if (a != null && b != null) {
			return (a.data == b.data && isMirror(a.left, b.right) && isMirror(a.right, b.left));
		} else {
			return false;
		}
	}

	public static boolean isSymmetric(Node root) { // O(n) time, O(log(n)) space
		return isMirror(root, root);
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		Node root = new Node(5);
		root.left = new Node(10);
		root.left.left = new Node(20);
		root.left.right = new Node(20);
		root.right = new Node(10);
		root.right.right = new Node(30);

		System.out.println(isSymmetric(root));
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
