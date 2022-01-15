import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class TrappingRainwater {
	public static long getWater(int[] arr, int n) { // O(n^2) time, O(1) space
		long water = 0;
		for (int i = 0; i < n; ++i) {
			int lmax = Integer.MIN_VALUE;
			for (int j = 0; j <= i; ++j) {
				lmax = Math.max(lmax, arr[j]);
			}
			int rmax = Integer.MIN_VALUE;
			for (int j = i; j < n; ++j) {
				rmax = Math.max(rmax, arr[j]);
			}
			water += Math.min(lmax, rmax) - arr[i];
		}
		return water;
	}

	public static long getWaterOptimized(int[] arr, int n) { // O(n) time, O(n) space
		int[] lmaxs = new int[n];
		lmaxs[0] = arr[0];
		for (int i = 1; i < n; ++i) {
			lmaxs[i] = Math.max(lmaxs[i-1], arr[i]);
		}
		int[] rmaxs = new int[n];
		rmaxs[n-1] = arr[n-1];
		for (int i = n-2; i >= 0; --i) {
			rmaxs[i] = Math.max(rmaxs[i+1], arr[i]);
		}
		long water = 0;
		for (int i = 0; i < n; ++i) {
			water += Math.min(lmaxs[i], rmaxs[i]) - arr[i];
		}
		return water;
	}

	public static long getWaterSuperOptimized(int[] arr, int n) { // O(n) time, O(1) space
		int l = 0, r = n-1;
		int lmax = Integer.MIN_VALUE, rmax = Integer.MIN_VALUE;
		long water = 0;
		while (l <= r) {
			if (lmax <= rmax) {
				lmax = Math.max(lmax, arr[l]);
				water += lmax - arr[l];
				++l;
			} else {
				rmax = Math.max(rmax, arr[r]);
				water += rmax - arr[r];
				--r;
			}
		}
		return water;
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; ++i) {
			arr[i] = fs.nextInt();
		}

		System.out.println(getWaterSuperOptimized(arr, n));
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
