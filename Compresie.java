import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Compresie {

	public static int n;
	public static int m;


	public static void main(String[] args) {
		String inputPath = "compresie.in";
		String outputPath = "compresie.out";

		List<Integer> A = new ArrayList<>();
		List<Integer> B = new ArrayList<>();

		try {
			MyScanner reader = new MyScanner(new FileReader(inputPath));
			PrintWriter fout = new PrintWriter((outputPath));

			// read the input
			n = reader.nextInt();
			for (int i = 0; i < n; i++) {
				A.add(reader.nextInt());
			}

			m = reader.nextInt();

			for (int i = 0; i < m; i++) {
				B.add(reader.nextInt());
			}

			List<Integer> rezultat = new ArrayList<>();
			int sumA = A.get(0);
			int sumB = B.get(0);

			int i = 1;
			int j = 1;
			while (i < A.size() && j < B.size()) {
				if (sumA < sumB) {
					sumA += A.get(i);
					i++;
				} else if (sumA > sumB) {
					sumB += B.get(j);
					j++;
				} else {
					rezultat.add(sumA);
					sumA = A.get(i);
					sumB = B.get(j);
					i++;
					j++;
				}
			}

			// add the remaining elements to sumA and sumB
			while (i < A.size()) {
				sumA += A.get(i);
				i++;
			}
			while (j < B.size()) {
				sumB += B.get(j);
				j++;
			}

			// check if the last sumA and sumB are equal
			// if not, we can't compress the arrays
			if (sumA == sumB) {
				rezultat.add(sumA);
				fout.println(String.format("%d", rezultat.size()));
			} else {
				fout.println("-1");
			}

			fout.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + e.getMessage());
		}
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