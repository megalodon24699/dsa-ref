import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Huffman {
	public static final int MAX_CHARS = 26;

	static class Node implements Comparable<Node> {
		char data;
		Node left, right;

		public Node(char c) {
			data = c;
			left = null;
			right = null;
		}

		@Override
		public int compareTo(Node node) {
			return data - node.data;
		}

		@Override
		public String toString() {
			return Character.toString(data);
		}
	}

	public static Node getHuffManTree(List<Pair<Node, Integer>> hist) {
		PriorityQueue<Pair<Node, Integer>> pq = new PriorityQueue<>(new Comparator<Pair<Node, Integer>>() {
			public int compare(Pair<Node, Integer> p, Pair<Node, Integer> q) {
				return p.second - q.second;
			}
		});
		pq.addAll(hist);
		
		while (pq.size() > 1) {
			Pair<Node, Integer> p = pq.poll(), q = pq.poll();
			Node node = new Node('#');
			node.left = p.first;
			node.right = q.first;
			pq.add(Pair.of(node, p.second + q.second));
		}
		return pq.poll().first;
	}

	public static void getCodes(Node node, String s, Map<Character, String> codes) {
		if (node.left == null && node.right == null) {
			codes.put(node.data, s);
			return;
		}
		getCodes(node.left, s + "0", codes);
		getCodes(node.right, s + "1", codes);
	}

	public static Pair<Node, String> encode(String s) { // O(n*log(n)) time, O(n) space
		int[] counter = new int[MAX_CHARS];
		for (char c : s.toCharArray()) {
			++counter[c - 'a'];
		}
		List<Pair<Node, Integer>> hist = new ArrayList<>();
		for (int i = 0; i < MAX_CHARS; ++i) {
			if (counter[i] != 0) {
				hist.add(Pair.of(new Node((char)(i + 'a')), counter[i]));
			}
		}
		Node root = getHuffManTree(hist);
		Map<Character, String> codes = new HashMap<>();
		getCodes(root, "", codes);

		StringBuilder sb = new StringBuilder();
		for (char c : s.toCharArray()) {
			sb.append(codes.get(c));
		}
		return Pair.of(root, sb.toString());
	}

	public static String decode(Node root, String encoded) { // O(n*log(n)) time, O(n) space
		StringBuilder sb = new StringBuilder();
		Node node = root;
		for (char c : encoded.toCharArray()) {
			if (c == '0') {
				node = node.left;
			} else {
				node = node.right;
			}
			if (node.left == null && node.right == null) {
				sb.append(node.data);
				node = root;
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		String s = fs.next();
		Pair<Node, String> p = encode(s);
		Node root = p.first;
		String encoded = p.second;

		System.out.println(encoded);
		System.out.println(decode(root, encoded));
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
