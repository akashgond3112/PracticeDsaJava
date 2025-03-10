package main.meta;

/**
 * Rotational Cipher One simple way to encrypt a string is to "rotate" every alphanumeric character by a certain amount.
 * Rotating a character means replacing it with another character that is a certain number of steps away in normal
 * alphabetic or numerical order. For example, if the string "Zebra-493?" is rotated 3 places, the resulting string is
 * "Cheud-726?". Every alphabetic character is replaced with the character 3 letters higher (wrapping around from Z to
 * A), and every numeric character replaced with the character 3 digits higher (wrapping around from 9 to 0). Note that
 * the non-alphanumeric characters remain unchanged. Given a string and a rotation factor, return an encrypted string.
 * Signature string rotationalCipher(string input, int rotationFactor) Input 1 <= |input| <= 1,000,000 0 <=
 * rotationFactor <= 1,000,000 Output Return the result of rotating input a number of times equal to rotationFactor.
 * Example 1 input = Zebra-493? rotationFactor = 3 output = Cheud-726? Example 2 input =
 * abcdefghijklmNOPQRSTUVWXYZ0123456789 rotationFactor = 39 output = nopqrstuvwxyzABCDEFGHIJKLM9012345678
 */
public class RotationalCipher {

	static class Main {

		// Add any helper functions you may need here

		/**
		 * ### **Time Complexity**
		 * - The function **iterates through each character** of the input string exactly once.
		 * - For each character, it performs a **constant amount of operations** (checking type and applying rotation).
		 * - Let **N** be the length of the input string.
		 * - **Time Complexity: \( O(N) \)**
		 *
		 * ### **Space Complexity**
		 * - The function uses a **StringBuilder**, which stores a copy of the transformed string.
		 * - The extra space used is proportional to the length of the input string.
		 * - **Space Complexity: \( O(N) \)** (for storing the result).*/
		String rotationalCipher(String input, int rotationFactor) {
			StringBuilder result = new StringBuilder();

			for (int i = 0; i < input.length(); i++) {
				char c = input.charAt(i);
				if (Character.isDigit(c)) {
					int shiftedDigit = (c - '0' + rotationFactor) % 10;
					result.append((char) (shiftedDigit + '0'));
				} else if (Character.isUpperCase(c)) {
					int shifted = (c - 'A' + rotationFactor) % 26;
					result.append((char) (shifted + 'A'));
				} else if (Character.isLowerCase(c)) {
					int shifted = (c - 'a' + rotationFactor) % 26;
					result.append((char) (shifted + 'a'));
				} else {
					result.append(c);
				}
			}

			return result.toString();
		}

		// These are the tests we use to determine if the solution is correct.
		// You can add your own at the bottom.
		int test_case_number = 1;

		void check(String expected, String output) {
			boolean result = (expected.equals(output));
			char rightTick = '\u2713';
			char wrongTick = '\u2717';
			if (result) {
				System.out.println(rightTick + " Test #" + test_case_number);
			} else {
				System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
				printString(expected);
				System.out.print(" Your output: ");
				printString(output);
				System.out.println();
			}
			test_case_number++;
		}

		void printString(String str) {
			System.out.print("[\"" + str + "\"]");
		}

		public void run() {
			String input_1 = "All-convoYs-9-be:Alert1.";
			int rotationFactor_1 = 4;
			String expected_1 = "Epp-gsrzsCw-3-fi:Epivx5.";
			String output_1 = rotationalCipher(input_1, rotationFactor_1);
			check(expected_1, output_1);

			String input_2 = "abcdZXYzxy-999.@";
			int rotationFactor_2 = 200;
			String expected_2 = "stuvRPQrpq-999.@";
			String output_2 = rotationalCipher(input_2, rotationFactor_2);
			check(expected_2, output_2);

			// Add your own test cases here

		}

		public static void main(String[] args) {
			new Main().run();
		}
	}
}
