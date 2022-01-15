import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class SwapPairEqual {
	public static int getSum(int[] arr) {
		int sum = 0;
		for (int i : arr) {
			sum += i;
		}
		return sum;
	}

	public static boolean getPair(int[] u, int[] v, int m, int n) { // O(m*n) time, O(1) space
		int uSum = getSum(u), vSum = getSum(v);
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				int uCurrSum = uSum - u[i] + v[j];
				int vCurrSum = vSum + u[i] - v[j];
				if (uCurrSum == vCurrSum) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean getPairAlt(int[] u, int[] v, int m, int n) { // O(m*n) time, O(1) space
		int uSum = getSum(u), vSum = getSum(v);
		int diff = uSum - vSum;
		if (diff % 2 != 0) {
			return false;
		}
		diff /= 2;
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				if (u[i] - v[j] == diff) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean getPairAltOptimized(int[] u, int[] v, int m, int n) { // O(m*log(m) + n*log(n)) time, O(1) space
		sort(u); sort(v);
		int uSum = getSum(u), vSum = getSum(v);
		int diff = uSum - vSum;
		if (diff % 2 != 0) {
			return false;
		}
		diff /= 2;
		int i = 0, j = 0;
		while (i < m && j < n) {
			if (u[i] - v[j] < diff) {
				++i;
			} else if (u[i] - v[j] > diff) {
				++j;
			} else {
				return true;
			}
		}
		return false;
	}

	public static boolean getPairAltSuperOptimized(int[] u, int[] v, int m, int n) { // O(m+n) time, O(m) space
		int uSum = getSum(u), vSum = getSum(v);
		int diff = uSum - vSum;
		if (diff % 2 != 0) {
			return false;
		}
		diff /= 2;
		Set<Integer> set = new HashSet<>();
		for (int i : u) {
			set.add(i);
		}
		for (int i : v) {
			if (set.contains(i + diff)) {
				System.out.println(i+diff + " " + i);
				return true;
			}
		}
		return false;
	}

	public static int[] sort(int[] arr) {
		List<Integer> l = new ArrayList<>();
		for (int i : arr) {
			l.add(i);
		}
		Collections.sort(l);
		for (int i = 0; i < arr.length; ++i) {
			arr[i] = l.get(i);
		}
		return arr;
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int m = fs.nextInt();
		int n = fs.nextInt();
		int[] u = new int[m];
		int[] v = new int[n];
		for (int i = 0; i < m; ++i) {
			u[i] = fs.nextInt();
		}
		for (int i = 0; i < n; ++i) {
			v[i] = fs.nextInt();
		}

		System.out.println(getPairAltSuperOptimized(u, v, m, n));
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
