import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class CombinationSum {
	public static void getCombinationsAux(List<Integer> nums, int idx, int sum, List<Integer> currCombination, List<List<Integer>> combinations) {
		if (sum == 0) {
			combinations.add(new ArrayList<>(currCombination));
			return;
		}
		for (int i = idx; i < nums.size(); ++i) {
			if (sum - nums.get(i) < 0) {
				break;
			}
			currCombination.add(nums.get(i));
			getCombinationsAux(nums, i, sum-nums.get(i), currCombination, combinations); // i -> i+1 for non-repeated combinations
			currCombination.remove(nums.get(i));
		}
	}

	public static void getCombinations(int[] arr, int n, int sum) { // ? complexity
		Set<Integer> set = new HashSet<>();
		for (int i : arr) {
			set.add(i);
		}
		List<Integer> nums = new ArrayList<>(set);
		Collections.sort(nums);
		List<List<Integer>> combinations = new ArrayList<>();
		List<Integer> currCombination = new ArrayList<>();

		getCombinationsAux(nums, 0, sum, currCombination, combinations);

		for (List<Integer> combination : combinations) {
			System.out.println(combination);
		}
	}

	public static void getUniqueCombinationsAux(List<Integer> nums, int idx, int sum, List<Integer> currCombination, List<List<Integer>> combinations) {
		if (sum == 0) {
			combinations.add(new ArrayList<>(currCombination));
			return;
		}
		for (int i = idx; i < nums.size(); ++i) {
			if (sum - nums.get(i) < 0) {
				break;
			}
			if (i > idx && nums.get(i) == nums.get(i-1)) {
				continue;
			}
			currCombination.add(nums.get(i));
			getUniqueCombinationsAux(nums, i+1, sum-nums.get(i), currCombination, combinations); // i -> i+1 for non-repeated combinations
			currCombination.remove(nums.get(i));
		}
	}

	public static void getUniqueCombinations(int[] arr, int n, int sum) { // ? complexity
		List<Integer> nums = new ArrayList<>();
		for (int i : arr) {
			nums.add(i);
		}
		Collections.sort(nums);
		List<List<Integer>> combinations = new ArrayList<>();
		List<Integer> currCombination = new ArrayList<>();

		getUniqueCombinationsAux(nums, 0, sum, currCombination, combinations);

		for (List<Integer> combination : combinations) {
			System.out.println(combination);
		}
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();
		int sum = fs.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; ++i) {
			arr[i] = fs.nextInt();
		}

		getUniqueCombinations(arr, n, sum);
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
