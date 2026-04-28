/**
 * algorithm
 * Created by ls on 2026/04/28.
 **/

/**
 * 给你一个整数 x ，如果 x 是一个回文整数(如果一个整数向前和向后读都相同，则它是一个 回文数。 例如，121 是回文数，而 123 不是。)，返回 true ；否则，返回 false 。
 * <p>
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * <p>
 * 例如，121 是回文，而 123 不是。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：x = 121
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：x = -121
 * 输出：false
 * 解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3：
 * <p>
 * 输入：x = 10
 * 输出：false
 * 解释：从右向左读, 为 01 。因此它不是一个回文数。
 */

// 观察数据的特性，寻找特殊解法，而不是一上来的刻板方案，多思考，用常数计算效率更高
public class 回文数9 {

    // 我写的
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        String str = String.valueOf(x);
        for (int i = 0, j = str.length() - 1; i < str.length() / 2; i++, j--) {
            if (str.charAt(i) != str.charAt(j)) return false;
        }
        return true;
    }

    // 看了官方的，这里的/和%的使用值得借鉴
    // 2332
    public boolean isPalindromeV2(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;
        int reverseNumber = 0;
        while (x > reverseNumber) {
            reverseNumber = reverseNumber * 10 + x % 10;
            x /= 10;
        }
        return reverseNumber == x || reverseNumber / 10 == x;
    }
}
