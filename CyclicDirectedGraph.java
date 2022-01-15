import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CyclicDirectedGraph {
	public static boolean isCyclicAux(List<List<Integer>> al, int v, boolean[] visited, boolean[] inStack) {
		if (inStack[v]) {
			return true;
		}
		if (visited[v]) {
			return false;
		}
		visited[v] = true;
		inStack[v] = true;
		for (int i : al.get(v)) {
			if (isCyclicAux(al, i, visited, inStack)) {
				return true;
			}
		}
		inStack[v] = false;
		return false;
	}

	public static boolean isCyclic(List<List<Integer>> al, int n) { // O(v+e) time, O(v) space
		boolean[] visited = new boolean[n];
		boolean[] inStack = new boolean[n];
		for (int i = 0; i < n; ++i) {
			if (isCyclicAux(al, i, visited, inStack)) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		// Custom driver code for this problem
		int n = 2;
		List<List<Integer>> al = new ArrayList<>(n);
		for (int i = 0; i < n; ++i) {
			al.add(new ArrayList<Integer>());
		}

		al.get(0).add(1);
		
		al.get(1).add(0);

		System.out.println(isCyclic(al, n));
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
