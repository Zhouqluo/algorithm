import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * algorithm
 * Created by ls on 2026/04/24.
 * --
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 * 示例 1：
 * 输入：s = "()"
 * 输出：true
 * 示例 2：
 * 输入：s = "()[]{}"
 * 输出：true
 * 示例 3：
 * 输入：s = "(]"
 * 输出：false
 * 示例 4：
 * 输入：s = "([])"
 * 输出：true
 * 示例 5：
 * 输入：s = "([)]"
 * 输出：false
 * 提示：
 * 1 <= s.length <= 104
 * s 仅由括号 '()[]{}' 组成
 **/

// s.length() & 1 == 1 判断奇数，奇数的二进制最后是1，偶数是0，借助&1的特性，都为1才是1判断奇偶数
// 利用题目特性做提前剪枝，可以减少不必要计算
// 用映射把“匹配关系”抽象出来，代码更清晰
// 左括号入栈，右括号出栈并匹配，最终判断 stack.isEmpty() → 栈为空则合法
// 启发：栈是处理“嵌套结构”的常用工具
// 适用场景：括号、HTML/XML 标签、表达式嵌套
public class 有效的括号 {

    public static void main(String[] args) {
        String s1 = "()";
        String s2 = "([])";
        String s3 = "({[]})";
        String s4 = "()[]{}";
        String s5 = "[[]]{}{}([{{[]}}])";
        String s6 = "[";
        String s7 = ")";
        String s8 = "[[[[[[[]";
        String s9 = "(][)";

        String[] strings = new String[]{s1, s2, s3, s4, s5, s6, s7, s8, s9};
        for (int i = 1; i <= strings.length; i++) {
            System.out.println("s" + i + ": " + isValidArray(strings[i - 1]));
        }


        // 适用于只有一种括号
        System.out.println(isValidSimple(")("));
    }

    public static boolean isValid(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        s = s.trim();
        if (s.isEmpty() || (s.length() & 1) == 1) {
            return false;
        }

        Map<Character, Character> map = new HashMap<>();
        map.put('}', '{');
        map.put(']', '[');
        map.put(')', '(');

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                if (stack.isEmpty() || stack.peek() != map.get(c)) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    public static boolean isValidArray(String s) {
        if (s == null || s.isEmpty() || (s.length() & 1) == 1) return false;

        Stack<Character> stack = new Stack<>();
        char[] match = new char[128];
        match[')'] = '(';
        match[']'] = '[';
        match['}'] = '{';

        for (char c : s.toCharArray()) {
            if (match[c] != 0) { // 是右括号
                if (stack.isEmpty() || stack.pop() != match[c]) return false;
            } else { // 左括号入栈
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    public static boolean isValidSwitch(String s) {
        if (s == null || s.isEmpty() || (s.length() & 1) == 1) return false;

        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            switch (c) {
                case ')':
                    if (stack.isEmpty() || stack.pop() != '(') return false;
                    break;
                case ']':
                    if (stack.isEmpty() || stack.pop() != '[') return false;
                    break;
                case '}':
                    if (stack.isEmpty() || stack.pop() != '{') return false;
                    break;
                default: // 左括号直接入栈
                    stack.push(c);
                    break;
            }
        }
        return stack.isEmpty();
    }

    public static boolean isValidSimple(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') count++;
            else if (c == ')') count--;
            if (count < 0) return false; // 出现右括号多于左括号
        }
        return count == 0;
    }

    // 不断删除匹配的括号对，直到字符串为空或无法匹配
    // 最坏N * N
    public static boolean isValidReplace(String s) {
        if (s == null) return false;
        int len;
        do {
            len = s.length();
            s = s.replace("()", "")
                    .replace("{}", "")
                    .replace("[]", "");
        } while (s.length() != len);
        return s.isEmpty();
    }
}
