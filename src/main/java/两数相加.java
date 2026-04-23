/**
 * algorithm
 * Created by ls on 2026/04/23.
 * --
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 **/


/**
 * 有时候我们正着想太抽象，不如从结果反推，怎么通过一个函数的嵌套调用组织出我想要的效果，然后去尝试实现这个函数，或许会好一点。
 * 写递归尽量不要用全局变量，遵循函数式思想，无副作用。
 */
public class 两数相加 {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(3, null);
        ListNode l2 = new ListNode(4, l1);
        ListNode l3 = new ListNode(2, l2);

        ListNode l4 = new ListNode(4, null);
        ListNode l5 = new ListNode(6, l4);
        ListNode l6 = new ListNode(5, l5);

        ListNode l7 = new ListNode(9, null);
        ListNode l8 = new ListNode(9, l7);

        ListNode l9 = new ListNode(9, null);

        ListNode l10 = new ListNode(9, null);
        ListNode l11 = new ListNode(8, l10);

        ListNode l12 = new ListNode(5, null);
        ListNode l13 = new ListNode(4, l12);

        // 7435 5347
        // 564   465

        // 12 % 10 2
        // 12 / 10 1

        // 11 % 10 1
        // 11 / 10 1

        // 8 % 10 8
        // 8 / 10 0

        // 5 % 10 5
        // 5 / 10 0

        // 5812

        // ListNode listNode = addTwoNumbers(l3, l6);
        // ListNode listNode = addTwoNumbers(l8, l9); // 801
        // ListNode listNode = digui(l11, l13); // 251
        ListNode listNode = diGuiPlus(l11, l13); // 251
        while (listNode != null) {
            System.out.print(listNode.val);
            listNode = listNode.next;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        // 进位
        int carry = 0;
        while (l1 != null || l2 != null) {
            // 拿到两个数，如果没有默认是0
            int n1 = l1 == null ? 0 : l1.val;
            int n2 = l2 == null ? 0 : l2.val;
            // 相加计算（进位也算）
            int sum = n1 + n2 + carry;
            int num = sum % 10; // 这一位的计算结果
            if (head == null) {
                // 缓存头节点
                head = tail = new ListNode(num);
            } else {
                // 给下一个节点添加数据
                tail.next = new ListNode(num);
                // 往后移动一次，移动到新添加的节点
                tail = tail.next;
            }

            if (l1 != null) {
                l1 = l1.next;
            }

            if (l2 != null) {
                l2 = l2.next;
            }

            // 计算进位
            carry = sum / 10;
        }

        // 如果最后还有进位，补上，比如
        // 99
        // 9

        // 18 % 10 8
        // 18 / 10 1

        // 10 % 10 0
        // 10 / 10 1

        // 108

        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }

    /**
     * 89
     * 45
     * digui(8, 4)
     * sum:carry:0
     * sum:0+8
     * sum:8+4
     * v:12 % 10 = 2
     * carry:12 / 10 = 1
     * return (2, didui(9, 5))
     * ---
     * didui(9, 5)
     * sum:carry:1
     * sum:1+9
     * sum:10+5
     * v:15 % 10 = 5
     * carry:15 / 10 = 1
     * return (5, digui(null, null))
     * ---
     * digui(null, null)
     * sum:carry:1
     * sum:1+0
     * sum:1+0
     * v:1 % 10 = 1
     * carry:1 / 10 = 0
     * return (1, digui(null, null))
     * ---
     * digui(null,null)
     * return null;
     * 重点 return new ListNode(2, new ListNode(5, new ListNode(1, new ListNode(null))))
     * 2 -> 5 -> 1
     * 98
     * 54
     * 152
     */

    static int carry = 0;

    public static ListNode digui(ListNode l1, ListNode l2) {

        int sum = carry;

        // 定义递归退出边界
        if (l1 == null && l2 == null && carry == 0) {
            return null;
        }

        if (l1 != null) {
            sum += l1.val;
            l1 = l1.next;
        }
        if (l2 != null) {
            sum += l2.val;
            l2 = l2.next;
        }

        carry = sum / 10;

        return new ListNode(sum % 10, digui(l1, l2));
    }

    public static ListNode diGuiPlus(ListNode l1, ListNode l2) {
        return dfs(l1, l2, 0);
    }

    public static ListNode dfs(ListNode l1, ListNode l2, int carry) {
        if (l1 == null && l2 == null && carry == 0) {
            return null;
        }
        int sum = carry;
        if (l1 != null) {
            sum += l1.val;
            l1 = l1.next;
        }
        if (l2 != null) {
            sum += l2.val;
            l2 = l2.next;
        }
        return new ListNode(sum % 10, dfs(l1, l2, sum / 10));
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
