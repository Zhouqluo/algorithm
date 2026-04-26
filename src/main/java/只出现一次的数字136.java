/**
 * algorithm
 * Created by ls on 2026/04/26.
 **/

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * <p>
 * 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
 * <p>
 * <p>
 * <p>
 * 示例 1 ：
 * <p>
 * 输入：nums = [2,2,1]
 * <p>
 * 输出：1
 * <p>
 * 示例 2 ：
 * <p>
 * 输入：nums = [4,1,2,1,2]
 * <p>
 * 输出：4
 * <p>
 * 示例 3 ：
 * <p>
 * 输入：nums = [1]
 * <p>
 * 输出：1
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 3 * 104
 * -3 * 104 <= nums[i] <= 3 * 104
 * 除了某个元素只出现一次以外，其余每个元素均出现两次。
 */

// 异或运算 ^
// 特性：0 ^ x = x      x ^ x = 0
public class 只出现一次的数字136 {

    public static int singleNumberV2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Integer i : map.keySet()) {
            if (map.get(i) == 1) return i;
        }
        return -1;
    }

    public static int singleNumber(int[] nums) {
        int num = 0;
        for (int i : nums) num ^= i;
        return num;
    }

    public static void main(String[] args) {
        int[] t1 = new int[]{4, 7, 7, 8, 4, 1, 8};
        System.out.println(singleNumber(t1));
        System.out.println(singleNumberV2(t1));
    }
}
