public interface StackInterface{
    public static final int NUM=100;
    public void Init();
    public boolean IsEmpty();
    public boolean IsFull();
    public void push(int n);
    public int pop();
    public int peek();
}