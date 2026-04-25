import java.util.HashSet;

/**
 * algorithm
 * Created by ls on 2026/04/24.
 * --
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。注意 "bca" 和 "cab" 也是正确答案。
 * 示例 2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 **/


/**
 * type       v2                       v3
 * 左指针移动	  遇到重复才移动	           每次循环迭代固定移动一格
 * 右指针移动	  右指针每次循环都尝试扩张	   while 循环扩张到最大
 * 思考方式	  “窗口保持合法”	           “固定左边界，尽量探寻右边界”
 * 窗口状态	  窗口总是合法	           窗口可能每次左边界移动后先缩小，再扩张右边界
 */
public class 无重复字符的最长子串3 {

    public static void main(String[] args) {
        String str = "abcabecddbb";
        String str1 = "";
        String str2 = null;

        // int i = lengthOfLongestSubstringV1(str);
        // int i1 = lengthOfLongestSubstringV1(str1);
        // int i2 = lengthOfLongestSubstringV1(str2);

        int i = lengthOfLongestSubstringV3(str);
        int i1 = lengthOfLongestSubstringV3(str1);
        int i2 = lengthOfLongestSubstringV3(str2);

        System.out.println();

        // 5
        System.out.println(i);
        // 0
        System.out.println(i1);
        // 0
        System.out.println(i2);
    }

    /**
     * 暴力穷举
     * n*n
     */
    public static int lengthOfLongestSubstringV1(String s) {
        if (s == null || s.isEmpty()) return 0;
        int max = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            HashSet<Character> set = new HashSet<>();
            for (int j = i; j < len; j++) {
                char c = s.charAt(j);
                if (set.contains(c)) {
                    break;
                }
                set.add(c);
                max = Math.max(max, j - i + 1);
            }
        }
        return max;
    }

    /**
     * 滑动窗口（窗口始终保持合法，右指针探索新字符）
     * n
     */
    public static int lengthOfLongestSubstringV2(String s) {
        if (s == null || s.isEmpty()) return 0;

        int left = 0, maxLen = 0;
        HashSet<Character> set = new HashSet<>();

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            // 遇到重复出现的字符，不断缩减左指针，直到不再有已经出现的字符为止
            while (set.contains(c)) {
                set.remove(s.charAt(left));
                left++;
            }
            // 添加已经出现的字符，缓存
            set.add(s.charAt(right));
            // 进行一次最大长度的更新
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    /**
     * 滑动窗口（固定左边界，持续探索右边界最大可能）
     * n
     */
    public static int lengthOfLongestSubstringV3(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int right = -1, maxLen = 0;

        HashSet<Character> set = new HashSet<>();

        for (int left = 0; left < s.length(); left++) {
            // 每次左边界移动，移除上一个字符，保证窗口无重复。
            if (left > 0) {
                set.remove(s.charAt(left - 1));
            }

            // 不断扩张右指针直到遇到重复字符
            while (right + 1 < s.length() && !set.contains(s.charAt(right + 1))) {
                // 填充已出现的字符
                set.add(s.charAt(right + 1));
                // 扩展右指针
                ++right;
            }
            // 更新最大长度
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
