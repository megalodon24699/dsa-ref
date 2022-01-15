import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class StringCircle {
	public static final int MAX_CHARS = 26;

	public static List<List<Integer>> transpose(List<List<Integer>> al) {
		int n = al.size();
		List<List<Integer>> t = new ArrayList<>(n);
		for (int i = 0; i < n; ++i) {
			t.add(new ArrayList<>());
		}
		for (int i = 0; i < n; ++i) {
			for (int j : al.get(i)) {
				t.get(j).add(i);
			}
		}
		return t;
	}

	public static void dfs(List<List<Integer>> al, int v, boolean[] visited) {
		visited[v] = true;
		for (int i : al.get(v)) {
			if (!visited[i]) {
				dfs(al, i, visited);
			}
		}
	}

	public static boolean isStronglyConnected(List<List<Integer>> al) {
		int n = al.size();
		boolean[] visited = new boolean[n];
		int idx = 0;
		while (al.get(idx).size() == 0) {
			++idx;
		}
		dfs(al, idx, visited);
		for (int i = 0; i < n; ++i) {
			if (al.get(i).size() > 0 && !visited[i]) {
				return false;
			}
		}
		al = transpose(al);
		Arrays.fill(visited, false);
		dfs(al, idx, visited);
		for (int i = 0; i < n; ++i) {
			if (al.get(i).size() > 0 && !visited[i]) {
				return false;
			}
		}
		return true;
	}

	public static boolean isEulerianCyclePresent(List<List<Integer>> al, int[] indegree) {
		int n = al.size();
		if (!isStronglyConnected(al)) {
			return false;
		}
		for (int i = 0; i < n; ++i) {
			if (al.get(i).size() != indegree[i]) {
				return false;
			}
		}
		return true;
	}

	public static boolean canBeChained(String[] arr, int n) { // O(n) time, O(n) space
		List<List<Integer>> al = new ArrayList<>(MAX_CHARS);
		for (int i = 0; i < MAX_CHARS; ++i) {
			al.add(new ArrayList<Integer>());
		}
		int[] indegree = new int[MAX_CHARS];
		for (String s : arr) {
			int from = s.charAt(0) - 'a', to = s.charAt(s.length()-1) - 'a';
			al.get(from).add(to);
			++indegree[to];
		}
		return isEulerianCyclePresent(al, indegree);
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();
		String[] arr = new String[n];
		for (int i = 0; i < n; ++i) {
			arr[i] = fs.next();
		}

		System.out.println(canBeChained(arr, n));
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
