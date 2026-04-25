/**
 * algorithm
 * Created by ls on 2026/04/25.
 **/

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串 s ，找到 它的第一个不重复的字符，并返回它的索引 。如果不存在，则返回 -1 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: s = "leetcode"
 * 输出: 0
 * 示例 2:
 * <p>
 * 输入: s = "loveleetcode"
 * 输出: 2
 * 示例 3:
 * <p>
 * 输入: s = "aabb"
 * 输出: -1
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= s.length <= 105
 * s 只包含小写字母
 */
public class 字符串中的第一个唯一字符387 {

    public static int firstUniqChar(String s) {
        if (s == null || s.isEmpty()) return -1;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++)
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        for (int i = 0; i < s.length(); i++)
            if (map.get(s.charAt(i)) == 1) return i;
        return -1;
    }

    public static int firstUniqCharV2(String s) {
        if (s == null || s.isEmpty()) return -1;
        Map<Character, Boolean> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++)
            // 1 false
            // >1 0 true
            map.put(s.charAt(i), map.containsKey(s.charAt(i)));
        for (int i = 0; i < s.length(); i++)
            if (!map.get(s.charAt(i))) return i;
        return -1;
    }


    public static void main(String[] args) {
        String[] testStrs = new String[]{"leetcode", "loveleetcode", "aabb"};

        System.out.println(firstUniqChar(testStrs[0]) == 0 ? '✅' : '❌');
        System.out.println(firstUniqChar(testStrs[1]) == 2 ? '✅' : '❌');
        System.out.println(firstUniqChar(testStrs[2]) == -1 ? '✅' : '❌');

        System.out.println(firstUniqCharV2(testStrs[0]) == 0 ? '✅' : '❌');
        System.out.println(firstUniqCharV2(testStrs[1]) == 2 ? '✅' : '❌');
        System.out.println(firstUniqCharV2(testStrs[2]) == -1 ? '✅' : '❌');
    }
}
