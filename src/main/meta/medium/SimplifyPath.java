package main.meta.medium;

import java.util.Stack;

/**
 * 71. Simplify Path Medium Topics Companies You are given an absolute path for a Unix-style file system, which always
 * begins with a slash '/'. Your task is to transform this absolute path into its simplified canonical path.
 *
 * The rules of a Unix-style file system are as follows:
 *
 * A single period '.' represents the current directory. A double period '..' represents the previous/parent directory.
 * Multiple consecutive slashes such as '//' and '///' are treated as a single slash '/'. Any sequence of periods that
 * does not match the rules above should be treated as a valid directory or file name. For example, '...' and '....' are
 * valid directory or file names. The simplified canonical path should follow these rules:
 *
 * The path must start with a single slash '/'. Directories within the path must be separated by exactly one slash '/'.
 * The path must not end with a slash '/', unless it is the root directory. The path must not have any single or double
 * periods ('.' and '..') used to denote current or parent directories. Return the simplified canonical path.
 *
 * Example 1: Input: path = "/home/" Output: "/home" Explanation: The trailing slash should be removed.
 *
 * Example 2: Input: path = "/home//foo/" Output: "/home/foo" Explanation: Multiple consecutive slashes are replaced by
 * a single one.
 *
 * Example 3: Input: path = "/home/user/Documents/../Pictures" Output: "/home/user/Pictures" Explanation: A double
 * period ".." refers to the directory up a level (the parent directory).
 *
 * Example 4: Input: path = "/../" Output: "/" Explanation: Going one level up from the root directory is not possible.
 *
 * Example 5: Input: path = "/.../a/../b/c/../d/./" Output: "/.../b/d" Explanation: "..." is a valid name for a
 * directory in this problem.
 *
 * Constraints:
 *
 * 1 <= path.length <= 3000 path consists of English letters, digits, period '.', slash '/' or '_'. path is a valid
 * absolute Unix path. Accepted
 * 981.3K Submissions
 * 2.1M Acceptance Rate
 * 46.3% Topics String Stack
 */
public class SimplifyPath {
	static class Solution {
		/**
		 * Simplifies a given Unix-style file path by resolving any '.' (current directory) and '..' (parent directory)
		 * references and removing redundant slashes.
		 *
		 * For example, the path "/home/../user/./docs//" will be simplified to "/user/docs".
		 *
		 * @param path
		 * 		The input Unix-style file path to be simplified. The path may contain absolute or relative references, and
		 * 		may include redundant slashes.
		 * @return A simplified version of the file path. If the resulting path is empty, returns "/".
		 * @throws IllegalArgumentException
		 * 		If the input path is invalid.
		 *
		 * Time Complexity: The time complexity of this method is O(n), where n is the length of the input string
		 * 'path'. This is because we iterate through each character in the string once, and each operation within the
		 * loop (such as appending to the StringBuilder or manipulating the stack) takes constant time
		 * Space Complexity: The space complexity of this method is O(n), where n is the length of the input string 'path'.
		 * his is because in the worst case, all the directories in the path could be stored in the stack, which takes
		 * linear space relative to the length of the input string.
		 */
		public String simplifyPath(String path) {
			Stack<String> stack = new Stack<>();
			StringBuilder result = new StringBuilder();

			for (int i = 0; i < path.length(); ++i) {
				if (path.charAt(i) == '/') {
					continue;
				}
				StringBuilder temp = new StringBuilder();

				while (i < path.length() && path.charAt(i) != '/') {
					temp.append(path.charAt(i));
					++i;
				}

				String dir = temp.toString();

				if (dir.equals(".")) {
					continue;  // Ignore current directory reference
				} else if (dir.equals("..")) {
					if (!stack.isEmpty()) {
						stack.pop();  // Move one level up
					}
				} else {
					stack.push(dir);  // Add directory to the stack
				}
			}

			while (!stack.isEmpty()) {
				result.insert(0, "/" + stack.pop());
			}

			return result.isEmpty() ? "/" : result.toString();
		}


	}
}
