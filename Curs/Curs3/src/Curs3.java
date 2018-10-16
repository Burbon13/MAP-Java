public class Curs3 {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("hahaha");
        stack.push("haha");
        stack.push("aha");

        String[] v = {"fa","Fd"};
        Stack.copy(v,stack);
    }
}
