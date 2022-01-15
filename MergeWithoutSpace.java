import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class MergeWithoutSpace {
	public static void merge(int[] us, int[] vs, int m, int n) { // O(m*n) time, O(1) space
		for (int i = n-1; i >= 0; --i) {
			int j, last = us[m-1];
			for (j = m-2; j >= 0 && us[j] > vs[i]; --j) {
				us[j+1] = us[j];
			}
			if (j != m-2 || last > vs[i]) {
				us[j+1] = vs[i];
				vs[i] = last;
			}
		}
	}

	public static void mergeOptimized(int[] us, int[] vs, int m, int n) { // O((m+n)*log(m+n)) time, O(1) space
		int i = 0, j = 0, k = m-1;
		while (i <= k && j < n) {
			if (us[i] <= vs[j]) {
				++i;
			}
			else {
				int t = us[k];
				us[k] = vs[j];
				vs[j] = t;
				++j; --k;
			}
		}
		sort(us);
		sort(vs);
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
		int[] us = new int[m];
		for (int i = 0; i < m; ++i) {
			us[i] = fs.nextInt();
		}
		int[] vs = new int[n];
		for (int i = 0; i < n; ++i) {
			vs[i] = fs.nextInt();
		}

		mergeOptimized(us, vs, m, n);

		System.out.println(Arrays.toString(us));
		System.out.println(Arrays.toString(vs));
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
