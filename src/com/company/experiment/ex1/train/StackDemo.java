package train;

import java.util.Stack;

public class StackDemo {
    public static void main(String[] args) {
        Stack<String> all = new Stack<>(); // 实例化栈结构
        all.push("A");
        all.push("B");
        all.push("C");
        System.out.println(all.pop());
        System.out.println(all.pop());
        System.out.println(all.pop());
        System.out.println(all.pop()); // 栈内已无元素仍调用pop，抛出EmptyStackException异常
    }
}
