import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;


public class Criptat {

	public static void main(String[] args) {
		String inputPath = "criptat.in";
		String outputPath = "criptat.out";
		try {
			MyScanner scanner = new MyScanner(new FileReader(inputPath));
			final PrintWriter fout = new PrintWriter((outputPath));

			int N = Integer.parseInt(scanner.nextLine());
			String[] words = new String[N];
			int maxLength = 0;

			// read the input words
			for (int i = 0; i < N; i++) {
				words[i] = scanner.nextLine();
			}

			List<Character> candidates = new ArrayList<>();

			// Find the dominant letter for each word
			for (String word : words) {
				Map<Character, Integer> occurrences = new HashMap<>();
				int length = word.length();

				// Save the number of occurrences for each letter
				for (int j = 0; j < length; j++) {
					char letter = word.charAt(j);
					occurrences.put(letter, occurrences.getOrDefault(letter, 0) + 1);
				}

				// Find the letter with the most occurrences
				int maxOccurrences = 0;
				char maxLetter = 0;
				for (Map.Entry<Character, Integer> entry : occurrences.entrySet()) {
					if (entry.getValue() > maxOccurrences) {
						maxOccurrences = entry.getValue();
						maxLetter = entry.getKey();
					}
				}

				// If the letter appears in more than half of the word's length, it is a candidate
				if (maxOccurrences > length / 2) {
					candidates.add(maxLetter);
				}
			}

			// Find the longest password
			for (char candidate : candidates) {

				// Sort the words by the ratio of the number of occurrences of the candidate letter
				// in descending order
				Arrays.sort(words, (a, b) -> {
					double ratioA = (double) countOccurrences(a, candidate) / a.length();
					double ratioB = (double) countOccurrences(b, candidate) / b.length();
					return Double.compare(ratioB, ratioA);
				});

				int currentLength = 0;
				int dominantCount = 0;

				for (String word : words) {
					int letterCount = countOccurrences(word, candidate);

					// Add the word to the password if it respects the condition for dominant letter
					if (dominantCount + letterCount > (currentLength + word.length()) / 2) {
						currentLength += word.length();
						dominantCount += letterCount;
					} else {
						maxLength = Math.max(maxLength, currentLength);
						// skip to the next candidate
						continue;
					}
				}
			}

			fout.println(maxLength);
			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Count the number of occurrences of a letter in a string
	private static int countOccurrences(String str, char letter) {
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == letter) {
				count++;
			}
		}
		return count;
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
