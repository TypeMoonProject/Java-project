### 1. Java 集合体系结构
请回答以下问题：  
#### Collection 和 Map 的主要区别是什么？  
Collection为单列集合，储存单个对象 
Map为双列集合，每个元素分为key和value两部分，构成key->value的映射关系。其中，key唯一，value可重复
#### List、Set的典型特点分别是什么？  
List元素有序，有索引，可重复  
Set元素无序，无索引，元素内容不可重复
#### 下列常见集合的主要区别是什么？  
ArrayList  
以动态数列形式实现的列表，元素有序，有索引，可重复。随机访问快，但插入、删除慢（需移动元素，大面积移动元素）  
LinkedList  
以双向链表形式实现的列表，元素有序，有索引，可重复。头尾插入、删除快（仅需删除单个节点），但随机访问、中间增删慢（需要遍历）。  
HashSet  
基于哈希表实现，元素无序，唯一。插入、删除、访问都快。  
HashMap  
通过数组+链表实现，储存键值对，key唯一，value可重复。插入、删除、访问都快。  
#### 为什么 Map 不继承 Collection 接口？  
Collection关注单个元素，Map关注key与value的映射关系，两者的关注点不同。  
二者数据结构不同，Collection是单列表，Map是双列表。  
如果Map继承Collection接口，则Map需要实现add()、size()等对键值对无效的方法。
### 2. 订单去重与存储  
系统中存在**重复订单（orderId 相同）**的问题。  
如果使用 Set 对订单进行去重：  
#### 需要重写哪些方法？  
重写equals()、hascode()方法，equals()未重写，hashcode()相同无法判断是否重复，hascode()未重写，相同orderId的对象可能落入不同哈希桶，Set不会调用equals()比较，导致重复。
#### 如果只按 orderId 去重，equals 和 hashCode 应该如何设计？  
先通过hashCode()定位哈希桶，再通过equals()遍历桶内元素去重。
```java
public int hashCode() { 
    return Objects.hash(orderId); 
}
public boolean equals(Object obj) {
    return obj instanceof Order && Objects.equals(this.orderId, ((Order) obj).orderId);
}
```
如果改用 Map<String, Order> 来去重：  
#### key 应该是什么？  
将orderId转换为字符串作为Key。可以直接利用orderid而无需修改equals()、hashcode()方法
```java
String key = String.valueOf(order.getOrderId());
```
#### 相比 Set，Map 的优缺点是什么？  
优点：不需要重写equals()、hashcode()方法，可以直接通过key访问对象，访问速度快。
缺点：Map需要储存key，内存占用较大
### 3. 订单排序  
现在需要按以下规则对订单排序：  
按 amount 从大到小  
金额相同的情况下，按 createTime 从早到晚  
请回答：  
#### 应该使用 Comparable 还是 Comparator？为什么？  
使用comparator。comparable适用于基于对象自身属性进行排序，compareto适合对单一规则排序，且会修改Order原本的代码，强行使用会导致在其他场景Order被错误覆盖。而comparator是外部比较器，无需修改原来代码，且可以同时定义amount和time两种排序方式。
### 4. 集合使用场景  
#### 请说明以下场景适合使用哪种集合，并说明原因：  
##### 高频随机访问订单列表  
ArrayList，由数组实现的列表，可以通过index来随机访问，时间复杂度为O(1)，随机访问快
##### 需要频繁插入、删除中间元素  
LinkedList，基于双向链表实现的列表，插入、删除仅需操作单个节点，效率高
##### 需要保证订单插入顺序并可快速查找 
LinkedHashMap（需键值对）或 LinkedHashSet（仅需元素），通过双向链表保证了元素插入顺序，利用哈希表，使增删插的时间复杂度为O(1)，效率高
##### 需要根据 userId 快速查询该用户的所有订单  
HashMap，以userId为key，对应订单列表为value，查询的时间复杂度为O(1)，通过key来查找对应的value效率高
##### 需要对订单进行去重且不关心顺序  
HashSet，基于哈希表实现，通过equals()和hashCode()方法判断是否重复，时间复杂度为O(1)，去重效率高
### 5. 遍历与修改问题  
#### 以下代码是否会抛异常？为什么？
会抛异常。因为在遍历集合时修改了集合元素，java集合的修改计数器modCount在修改时递增，而遍历时集合会检查modCount与expectedModCount是否相等，在遍历时修改集合导致modCount!=expectedModCount,抛出异常。
```java  
List<String> list = new ArrayList<>();
list.add("A");
list.add("B");
list.add("C");
list.add("D");

for (String s : list) {
    if ("B".equals(s)) {
        list.remove(s);
    }
}
```
#### 会抛出什么异常？  
```
Exception in thread "main" java.util.ConcurrentModificationException
        at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1096)
        at java.base/java.util.ArrayList$Itr.next(ArrayList.java:1050)
        at Test.main(Test.java:11)
```
#### 正确的修改方式有哪些？  
1. 使用迭代器修改
```java
Iterator<String> it = list.iterator();
while (it.hasNext()) {
    String s = it.next();
    if ("B".equals(s)) {
        it.remove(); 
    }
}
```
2. 使用并发安全集合,修改会复制旧数组，遍历操作基于旧数组，避免冲突
```java
List<String> list = new CopyOnWriteArrayList<>(Arrays.asList("A", "B", "C"));
for (String s : list) {
    if ("B".equals(s)) {
        list.remove(s);
    }
}
```
3. 使用临时集合记录待操作元素，先遍历记录需修改的元素，遍历完成后统一修改
```java
List<String> toRemove = new ArrayList<>();
for (String s : list) {
    if ("B".equals(s)) {
        toRemove.add(s);
    }
}
list.removeAll(toRemove);
```
### 6. 并发场景下的集合（选做）  
在高并发订单创建场景中：  
#### 为什么 ArrayList 和 HashMap 是线程不安全的？  
1. ArrayList原因：  
add()操作涉及多个步骤，若两个线程同时执行 add()，既需要判断容量足够，又需要同时开始写入，导致数据覆盖或丢失元素。  
若遍历中直接调用list.remove()而非迭代器的remove()会导致 modCount != expectedModCount，抛出ConcurrentModificationException异常。  
线程A修改 ArrayList后，线程B可能因CPU缓存未刷新而读取到旧数据  
2. HashMap原因：
HashMap在扩容时通过头插法迁移链表节点，若两个线程同时扩容，可能形成环形链表，导致get()操作陷入死循环  
与ArrayList类似，遍历时直接修改结构会抛出ConcurrentModificationException  
当两个线程同时put且key的哈希值冲突时，线程A判断桶位为空，准备写入时被挂起，线程B写入数据成功，线程A恢复后直接覆盖线程B的数据，导致数据丢失 
#### 常见的线程安全集合有哪些？  
Vector，使用 synchronized 关键字来实现线程安全  
Hashtable，使用 synchronized 关键字来实现线程安全
ConcurrentHashMap，它会将整个表分为多个Segment，每个Segment都是一个独立的哈希表，每个线程只能访问特定的Segment，从而保证了线程安全。
CopyOnWriteArrayList，它通过复制原有的List来实现并发访问的线程安全，每次对CopyOnWriteArrayList进行修改时都会创建一个新的数组