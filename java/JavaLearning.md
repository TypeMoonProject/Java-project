### 信息隐藏原则  
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

其中Boolean、Character、Integer、Long、Short拥有常量池，其相同值的指针相等，而Double、Float没有常量池，相同值对应指针不同。包装类使基本类型成为对象。
```Java
public class Test {
    public static void main(String[] args){
        Character a='我';
        Character b='我';
        char c='我';
        System.out.println(a==b);//false
        System.out.println(b==c);//true（自动拆箱）
    }
}
```