public class Stack {
    private int[] array;
    private int top;
    private int capacity;

    public Stack(int capacity) {
        this.array = new int[capacity];
        this.capacity = capacity;
        this.top = -1;
    }

    public void push(int element) throws Exception {
        if (isFull()) {
            throw new Exception("The stack is full.");
        }
        
        array[++top] = element;
    }

    public int pop() throws Exception {
        if (isEmpty()) {
            throw new Exception("The stack is empty.");
        }

        return array[top--];
    }

    public int peek() throws Exception {
        if (isEmpty()) {
            throw new Exception("The stack is empty.");
        }
        
        return array[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == capacity - 1;
    }
}
