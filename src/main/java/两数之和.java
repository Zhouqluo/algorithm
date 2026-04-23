import java.util.*;

/**
 * tempcode
 * Created by ls on 2026/04/23.
 * --
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。
 * 你可以按任意顺序返回答案。
 * 示例 1：
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * 示例 2：
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * 示例 3：
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 **/

/**
 * 哈希表能提高查询效率，有时候可以牺牲一些空间，换来O（1）的查询。
 * 不要着急构建全量哈希表。
 * 基础写好了，小小的改动或许只需要调整下数据结构就可以跟上新的需求。
 */
public class 两数之和 {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 6, 11, 4, 5, 17, 7, 5, 4, 10, 3, 3};
        int[] nums2 = new int[]{2, 6, 4, 7, 2, 10, 200};
        List<int[]> lists = hashPro(nums1, 9);
        for (int[] list : lists) {
            System.out.println(Arrays.toString(list));
        }
    }

    /**
     * O（N * N）
     * 为什么第一个for减少1？
     * 为什么第二个for初始条件是i+1
     */
    public static int[] baoli(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return null;
        }
        int len = nums.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                System.out.println(nums[i] + ":" + nums[j]);
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    /**
     * O（N）
     */
    public static int[] hash(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return new int[0];
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int need = target - nums[i];
            if (map.containsKey(need)) {
                return new int[]{map.get(need), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }

    /**
     * O（N）
     * 没有重复的数字情况下，找出所有的呢？
     */
    public static List<int[]> hashPlus(int[] nums, int target) {
        List<int[]> result = new ArrayList<>();
        if (nums == null || nums.length < 2) {
            return result;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int need = target - nums[i];
            Integer idx = map.get(need);
            if (idx != null) {
                result.add(new int[]{idx, i});
            }
            map.put(nums[i], i);
        }
        return result;
    }

    /**
     * O（N）
     * 允许数字重复的情况下，找出所有的呢？
     */
    public static List<int[]> hashPro(int[] nums, int target) {
        List<int[]> result = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int need = target - nums[i];
            List<Integer> list = map.get(need);
            if (list != null) {
                for (int idx : list) {
                    result.add(new int[]{idx, i});
                }
            }
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        return result;
    }
}
