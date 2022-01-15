import java.util.Scanner;

public class SieveOfEratosthenes {
	public static void getPrimes(boolean[] primes, int n) { // O(n*log(log(n))) time
		for (int i = 2; i <= n; ++i) {
			primes[i] = true;
		}
		for (int i = 2; i*i <= n; ++i) {
			if (primes[i]) {
				for (int j = i*i; j <= n; j += i) {
					primes[j] = false;
				}
			}
		}
	}

	public static void printPrimes(int n) {
		boolean[] primes = new boolean[n+1];
		getPrimes(primes, n);
		for (int i = 2; i <= n; ++i) {
			if (primes[i]) {
				System.out.print(i + " ");
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		System.out.print("Enter upper limit: ");
		int n = in.nextInt();

		in.close();

		printPrimes(n);
	}
}
