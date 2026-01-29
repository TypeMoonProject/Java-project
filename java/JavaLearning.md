### 信息隐藏原则（封装）  
通常隐藏类的属性`private`，而公开类的方法`public`，其他类只能通过方法间接获得该类的属性。  
```Java
public class Try{
    private int num=1;
    public int GetNum(){
        return this.num;
    }
}
```
### static关键字  
#### 静态变量

静态变量（static variable）是属于类的变量，而不是类的实例。它在类加载时初始化，并且在内存中只有一个副本，被所有实例共享。例如：
```Java
public class Student {
static String school = "郑州大学";
String name;
int age;

public Student(String name, int age) {
this.name = name;
this.age = age;
}
}
```
在上述代码中，`school`是静态变量，所有`Student`对象共享同一个 school 变量。

#### 静态方法

静态方法（static method）是属于类的方法，而不是类的实例。调用静态方法时，不需要创建类的实例。静态方法可以访问静态变量，但不能访问非静态变量。例如：
```Java
public class StaticMethodExample {
static String school = "郑州大学";

static void changeSchool() {
school = "河南大学";
}

public static void main(String[] args) {
StaticMethodExample.changeSchool();
System.out.println(StaticMethodExample.school); // 输出: 河南大学
}
}
```
在上述代码中，`changeSchool`是静态方法，可以直接通过类名调用。

#### 静态代码块

静态代码块（static block）用于在类加载时执行静态初始化操作。它通常用于初始化静态变量，并且在类加载时执行一次。例如：
```Java
public class StaticBlockExample {
static {
System.out.println("静态代码块");
}

public static void main(String[] args) {
System.out.println("main 方法");
}
}
```
在上述代码中，静态代码块在`main`方法之前执行。

#### 静态内部类

静态内部类（static inner class）是嵌套在另一个类中的静态类。它可以在不创建外部类实例的情况下被实例化，并且可以访问外部类的静态成员。例如：
```Java
public class OuterClass {
static int outerStaticVariable = 42;

static class StaticInnerClass {
void printOuterStaticVariable() {
System.out.println("Outer static variable: " + outerStaticVariable);
}
}

public static void main(String[] args) {
StaticInnerClass inner = new StaticInnerClass();
inner.printOuterStaticVariable(); // 输出: Outer static variable: 42
}
}
```
在上述代码中，`StaticInnerClass`是静态内部类，可以访问外部类的静态变量。
通过理解和使用`static`关键字，可以在 Java 编程中更高效地管理类的成员和方法，提高代码的可读性和性能。  
### 单例模式
```Java
public class Single{
    private static obj=new Single();
    private String content="";
    private Single(){
        this.content="a";
    }
    public static Single GetSingle(){
        return obj;
    }
}
```
构造函数为`private`，故在其他类中不能实例化，只能使用`GetSingle`获得`obj`的指针，使不同地方获取的对象为同一个对象。常用于数据共存、数据共享。  
### 包装类
| 数据类型|包装类 |
|:----|:----|
|double|Double|  
|float|Float|
|boolean|Boolean|
|char|Character|
|int|Integer|
|long|Long|
|short|Short|
|byte|Byte|  

其中Boolean、Character（字母、符号常量池）、Integer、Long、Short拥有常量池，其相同值的指针相等，而Character（汉字）、Double、Float没有常量池，相同值对应指针不同。包装类使基本类型成为对象。  
基本类型传入函数时复制值，包装类传入时传入指针（传入类、数组、字符串即传入指针）。
```Java
public class Test {
    public static void main(String[] args){
        Character a='我';
        Character b='我';
        char c='我';
        System.out.println(a==b);//false
        System.out.println(b==c);//true（自动拆包）
    }
}
```
其中，对于String，存在常量池缓存与优化机制，仅允许String进行拼接，且存入缓存常量池，但仅有常量相加时优化。
```java
public class Test {
    public static void main(String[] args){
        String a="我是";
        String b="我"+"是";
        String c="我";
        String d=c+"是";//变量c+常量"是"
        System.out.println(a==b);//true
        System.out.println(d==a);//false
    }
}
```
基本类型的包装类和字符串有两种不同的创建方式，两种方式放置位置不同。
```java
Integer a=10;//放在栈中，常量化（存入常量池）
Integer a=new Integer(10);//放在堆中，不会常量化，但此方式在java9以后被弃用
```
包装类与基本类型比较、运算，或包装类进行运算会自动拆包（在强制需要内部值的地方自动拆包）。  
```Java
Integer a=10;
Integer b=10;
int c=10;
a+b;//20
a+c;//20
a==b;//true
a==c;//true
```
### 不可变对象
包装类为不可变对象，更改为指针指向新的对象，而旧对象依旧存在。不可变对象线程安全、并发读写性能高、可重复使用但是会使用较多内存。  
```java
public class Try{
    public static void main(String[] args){
        String a="ab";
        String b=a;
        b="de";
        System.out.println(a==b);//false
    }
}
```
故而`String a=b+"def"`效率低，占用内存使用`StringBuffer`（同步、线程安全）、`StringBuilder`（不同步、线程不安全、更快速）的`append`方法修改，这样可以原地修改，而非指向另一块内存。
### 抽象类与接口
#### 抽象类
抽象类需要写出`abstract`且抽象函数也需要写出`abstract`，可以没有抽象函数，可以全是抽象函数。不能实例化，只能继承。在继承的子类全部实现父类的抽象方法前，子类仍为抽象类，不能实例化。
```Java
public abstract class Example{//可以没有抽象函数，可以全是抽象函数
    public abstract int f();
    public int g(){
        ;
    }
}
```
先继承再实现接口。
```java
public B extends Example implements Example{
    ;
}
```
#### 接口
接口不需要写`abstract`且其中函数全为抽象函数（不需写`abstract`），其属性全为常量`public static final`。在继承的子类全部实现接口的抽象方法前，子类为抽象类，不能实例化。 
```Java
public interface class Example{
    int a=1;//等效于public static final int a=1;
    public int f();
}
```
一个类可以实现多个接口
```java
public class A implements B,C{
    ;
}
```
### Array
数组在初始化时不能确定内存，即为null指针
```java
int[2] arr;//错误
int arr[2];//错误
int[] arr = new int[2];//元素默认为0
int[] arr = new int[]{1,2}//初始化并赋值
```
1. 规则数组  
   规则数组即每个子数组（行数列）的元素数量相等
```java
int[] arr = new int[3][4];
```
2. 不规则数组
   每个子数列的元素数量不相等
```java
int[] arr = new int[3][];
int arr[0] = new int[2];
int arr[1] = new int[3];
int arr[2] = new int[4];
arr.length;//获取大小
```
### Collection(JCF)
java collection框架，需指定泛型，collection是List、set的父类   
基本方法
```java
add(e)//将给定的对象添加到当前集合中。
clear()//清空集合中所有的元素。
remove(e)//从当前集合中删除给定的对象。
contains(e)//判断当前集合中是否包含给定的对象。
isEmpty()//判断当前集合是否为空。
size()//返回集合中元素的个数。
toArray()//将集合中的元素存储到数组中。
get(index)//仅List有，取得index元素
```
#### List
##### ArrayList
以动态数组形式实现的列表，在充满后会自动扩充至之前的1.5倍，不支持同步，可以使用索引，不适合指定位置的插入、删除。建议使用for-index、for-each循环遍历，迭代器（顺序访问）遍历较慢。
```java
ArrayList<Integer> arr = new ArrayList<>();//可填初始大小，可不填
arr.add(1);//依次加入1
arr.add(1,3);//在index=1处加入3，其余依次后移
arr.remove(1);//去除index=1的元素，其余依次前移
arr.get(0);//取得index=0的元素（不删除），不能直接使用arr[0]
arr.size();//获取大小
```
##### LinkedList
以双向链表形式实现的列表，不支持同步，可以使用索引，可以被当作队列、双端队列、堆栈使用，头尾访问、插入、删除高效，随机访问较差，适用于经常变化的数据。建议使用for-each、迭代器（顺序访问）循环遍历，for-index较慢。
```java
LinkedList<Integer> arr = new LinkedList<>();
```
##### 比较
1. `arr.add(index,num)`ArrayList之后元素都会后移，发生大面积的数据移动，LinkedList仅添加所需元素。
2. `arr.get(index)`ArrayList表现优于LinkedList。
3. `arr.remove(index)`ArrayList之后元素都会前移，发生大面积的数据移动，LinkedList仅删除元素。
4. ArrayList适用于较多查询的数据，LinkedList适合频繁修改的数据。
##### Vector
一个动态数组，支持同步
```java
Vector();//创建一个默认大小为10的向量
Vector(int size);//创建一个指定大小的向量
Vector(int size, int incr);//创建一个指定大小和增量的向量，充满时每次增加incr
Vector(Collection c);//创建一个包含给定集合元素的向量
```
在前三种遍历方法之后，还可以使用Enumeration遍历，四种方法效率相差不大，但建议for的两种。
#### Set
##### HashSet
##### LinkedHashSet
##### TreeSet
#### Queue
### Map
### java类库
#### 数字类