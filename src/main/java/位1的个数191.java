/**
 * algorithm
 * Created by ls on 2026/04/26.
 **/

/**
 * 给定一个正整数 n，编写一个函数，获取一个正整数的二进制形式并返回其二进制表达式中 设置位 的个数（也被称为汉明重量）。
 * 设置位指：set bit 指在某数的二进制表示中值为 1 的二进制位。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 11
 * 输出：3
 * 解释：输入的二进制串 1011 中，共有 3 个设置位。
 * 示例 2：
 * <p>
 * 输入：n = 128
 * 输出：1
 * 解释：输入的二进制串 10000000 中，共有 1 个设置位。
 * 示例 3：
 * <p>
 * 输入：n = 2147483645
 * 输出：30
 * 解释：输入的二进制串 1111111111111111111111111111101 中，共有 30 个设置位。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 231 - 1
 */

// 公式：n & (n-1) → 消掉 n 二进制表示中最右边的 1
// 1 << i 1的二进制00001，左移位运算，比如1<<2， 00100，生成掩码掩码进行与运算判断是不是1
public class 位1的个数191 {


    public static void main(String[] args) {
        int number = 11;
        System.out.println(v1(number));
        System.out.println(v2(number));
        System.out.println(v3(number));
    }

    // v1，我写的第一版，气笑了，哈哈
    public static int v1(int number) {
        StringBuilder sb = new StringBuilder();
        while (number > 0) {
            int re = number % 2;
            sb.insert(0, re);
            number /= 2;
        }
        int count = 0;
        for (char c : sb.toString().toCharArray()) {
            if (c == '1') {
                count++;
            }
        }
        return count;
    }

    public static int v2(int n) {
        int cnt = 0;
        while (n > 0) {
            cnt++;
            // n     = 00101100   (十进制 44)
            // n-1   = 00101011   (十进制 43)
            // n & (n-1) = 00101000  (十进制 40)
            // 公式：n & (n-1) → 消掉 n 二进制表示中最右边的 1
            n &= n - 1;
        }
        return cnt;
    }

    public static int v3(int n) {
        int ret = 0;
        for (int i = 0; i < 32; i++) {
            // 1 << i 1的二进制00001，左移位运算，比如1<<2， 00100，生成掩码掩码进行与运算判断是不是1
            if ((n & (1 << i)) != 0) {
                ret++;
            }
        }
        return ret;
    }
}
