public class Stack<T> {
    //private T[] items = (T[]) new Object[10]; Nu e cel mai ortodox
    private T[] items = (T[]) new Object[10];
    private int size = 0;

//    public Stack() {
//        items = new T[10];  tot nu merge
//    }

    public T peek() {
        if(size > 0)
            return items[size-1];
        return null;
    }

    public void push(T val) {
        if(size < 10)
            items[size++] = val;
    }

    public T pop() {
        if(size > 0)
            return items[--size];
        return null;
    }

    //Class.class
    //reflect

    public static <T> void copy(T t[], Stack<T> stack) {
        for(T elem: t)
            stack.push(elem);
    }
}
