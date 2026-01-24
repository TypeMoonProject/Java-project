package deque;

import java.util.NoSuchElementException;

public class Test {
    public static void main(String[] args) {
// HybridDeque<Integer> dq = new HybridDeque<>(4);

// // 1) 初始：空
// System.out.println(dq.size());      // 0
// System.out.println(dq.peekFirst()); // null
// System.out.println(dq.peekLast());  // null

// // 2) 插入：混合 addFirst/addLast
// dq.addFirst(2);  // [2]
// dq.addFirst(1);  // [1,2]
// dq.addLast(3);   // [1,2,3]
// dq.addLast(4);   // [1,2,3,4]

// // 3) 检查 peek
// System.out.println(dq.peekFirst()); // 1
// System.out.println(dq.peekLast());  // 4
// System.out.println(dq.size());      // 4

// // 4) 删除：两端各删一次
// System.out.println(dq.removeFirst()); // 1  -> [2,3,4]
// System.out.println(dq.removeLast());  // 4  -> [2,3]

// // 5) 再插入
// dq.addFirst(9);   // [9,2,3]
// dq.addLast(10);   // [9,2,3,10]

// // 6) 逐个弹出
// System.out.println(dq.removeFirst()); // 9
// System.out.println(dq.removeFirst()); // 2
// System.out.println(dq.removeFirst()); // 3
// System.out.println(dq.removeFirst()); // 10
// System.out.println(dq.isEmpty());     // true

HybridDequeWithCircularArray<Integer> dq = new HybridDequeWithCircularArray<>(4);
try {
    dq.removeFirst();
    System.out.println("ERROR: should have thrown");
} catch (NoSuchElementException e) {
    System.out.println("OK removeFirst empty");
}
    }
}