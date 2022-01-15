import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class MinimumPlatforms {
	public static int getMinimumPlatforms(int[] arrs, int[] deps, int n) { // O(n^2) time, O(1) space
		int gMax = 0;
		for (int i = 0; i < n-1; ++i) {
			int currMax = 1;
			for (int j = i+1; j < n; ++j) {
				if ((arrs[i] <= arrs[j] && arrs[j] <= deps[i]) || (arrs[j] <= arrs[i] && arrs[i] <= deps[j])) {
					++currMax;
				}
			}
			gMax = Math.max(gMax, currMax);
		}
		return gMax;
	}

	public static int getMinimumPlatformsOptimized(int[] arrs, int[] deps, int n) { // O(n*log(n)) time, O(1) space
		sort(arrs);
		sort(deps);
		int currMax = 0, gMax = 0;
		int i = 0, j = 0;
		while (i < n && j < n) {
			if (arrs[i] <= deps[j]) {
				++currMax; ++i;
			}
			else {
				--currMax; ++j;
			}
			gMax = Math.max(gMax, currMax);
		}
		return gMax;
	}

	public static int getMinimumPlatformsSuperOptimized(int[] arrs, int[] deps, int n) { // O(n) time, O(1) space
		int[] platforms = new int[2361];
		for (int i = 0; i < n; ++i) {
			++platforms[arrs[i]];
			--platforms[deps[i]+1];
		}
		int gMax = 0;
		for (int i = 1; i < 2361; ++i) {
			platforms[i] += platforms[i-1];
			gMax = Math.max(gMax, platforms[i]);
		}
		return gMax;
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

		int n = fs.nextInt();
		int[] arrs = new int[n];
		for (int i = 0; i < n; ++i) {
			arrs[i] = fs.nextInt();
		}
		int[] deps = new int[n];
		for (int i = 0; i < n; ++i) {
			deps[i] = fs.nextInt();
		}

		System.out.println(getMinimumPlatformsSuperOptimized(arrs, deps, n));
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
