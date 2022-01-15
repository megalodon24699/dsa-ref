import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class CountPairs {
	public static long countPairs(int[] xs, int[] ys, int m, int n) { // O(m*n) time, O(1) space
		long count = 0;
		for (int x : xs) {
			for (int y : ys) {
				if (Math.pow(x, y) > Math.pow(y, x)) {
					++count;
				}
			}
		}
		return count;
	}

	public static long countPairsAux(int x, int[] ys, int n, int[] fs) {
		if (x == 0) {
			return 0;
		}
		if (x == 1) {
			return fs[0];
		}
		int idx = Arrays.binarySearch(ys, x);
		if (idx < 0) {
			idx = -idx - 1;
		} else {
			while (idx < n && ys[idx] == x) {
				++idx;
			}
		}
		long count = n - idx;
		count += fs[0] + fs[1];
		if (x == 2) {
			count -= fs[3] + fs[4];
		}
		if (x == 3) {
			count += fs[2];
		}
		return count;
	}

	public static long countPairsOptimized(int[] xs, int[] ys, int m, int n) { // O((m+n)*log(n)) time, O(1) space
		sort(ys);
		int[] fs = new int[5];
		for (int y : ys) {
			if (y < 5) {
				++fs[y];
			}
		}
		long count = 0;
		for (int x : xs) {
			count += countPairsAux(x, ys, n, fs);
		}
		return count;
	}

	public static void sort(int[] arr) {
		List<Integer> ls = new ArrayList<>();
		for (int i : arr) {
			ls.add(i);
		}
		Collections.sort(ls);
		for (int i = 0; i < arr.length; ++i) {
			arr[i] = ls.get(i);
		}
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int m = fs.nextInt();
		int n = fs.nextInt();
		int[] xs = new int[m];
		for (int i = 0; i < m; ++i) {
			xs[i] = fs.nextInt();
		}
		int[] ys = new int[n];
		for (int i = 0; i < n; ++i) {
			ys[i] = fs.nextInt();
		}

		System.out.println(countPairsOptimized(xs, ys, m, n));
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
}
