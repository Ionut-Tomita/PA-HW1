import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Colorare {
	public static Long nrModels;// number of combinations possible
	public static Integer K;// number of pairs
	public static List<Integer> count = new ArrayList<>();
	public static List<Character> type = new ArrayList<>();
	public static final long MOD = (long) 1e9 + 7;

	public static void main(String[] args) {
		String inputPath = "colorare.in";
		String outputPath = "colorare.out";
		try {
			MyScanner fin = new MyScanner(new FileReader(inputPath));
			final PrintWriter fout = new PrintWriter((outputPath));

			// read the input
			K = fin.nextInt();

			for (int i = 0; i < K; i++) {
				count.add(fin.nextInt());
				char caracter = fin.next().charAt(0);
				type.add(caracter);
			}

			// calculate the number of possible models for the first pair
			if (type.get(0) == 'H') {
				nrModels = 1L * 6;
				nrModels *= power(3, count.get(0) - 1);
				nrModels %= MOD;
			} else {
				nrModels = 1L * 3;
				nrModels *= power(2, count.get(0) - 1);
				nrModels %= MOD;
			}

			// calculate the number of possible models for the rest of the pairs
			for (int i = 1; i < K; i++) {

				// previous and current Zones are 'H'
				if (type.get(i - 1) == 'H' && type.get(i) == 'H') {
					nrModels *= power(3, count.get(i));
					nrModels %= MOD;

					// previous and current Zones are 'V'
				} else if (type.get(i - 1) == 'V' && type.get(i) == 'V') {
					nrModels *= power(2, count.get(i));
					nrModels %= MOD;

					// previous Zone is 'V' and current Zone is 'H'
				} else if (type.get(i - 1) == 'V' && type.get(i) == 'H') {
					nrModels *= 2;
					nrModels *= power(3, count.get(i) - 1);
					nrModels %= MOD;

					// previous Zone is 'H' and current Zone is 'V'
				} else {
					nrModels *= power(2, count.get(i) - 1);
					nrModels %= MOD;
				}
			}

			fout.println(nrModels);

			fout.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + e.getMessage());
		}
	}

	// raise a number to a power efficiently
	private static long power(long base, long exponent) {
		long result = 1;

		while (exponent > 0) {
			if (exponent % 2 == 1) {
				result = (result % MOD * base % MOD) % MOD;
			}
			base = (base % MOD * base % MOD) % MOD;

			// divide the exponent by 2
			exponent >>= 1;
		}

		return result;
	}
}

class MyScanner {
	private BufferedReader br;
	private StringTokenizer st;

	public MyScanner(Reader reader) {
		br = new BufferedReader(reader);
	}

	public String next() {
		while (st == null || !st.hasMoreElements()) {
			try {
				st = new StringTokenizer(br.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return st.nextToken();
	}

	public int nextInt() {
		return Integer.parseInt(next());
	}

	public long nextLong() {
		return Long.parseLong(next());
	}

	public double nextDouble() {
		return Double.parseDouble(next());
	}

	public String nextLine() {
		String str = "";
		try {
			str = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
}