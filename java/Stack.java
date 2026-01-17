public class Stack implements StackInterface{
    private int[] num=new int[NUM];
    private int top;
    public void Init(){
        this.top=-1;
    }
    public boolean IsEmpty(){
        return this.top==-1;
    }
    public boolean IsFull(){
        return this.top==NUM;
    }
    public void push(int n){
        if(!IsFull()){
            num[++top]=n;
        }
    }
    public int pop(){
        int re=-1;
        if(!IsEmpty()){
            re=num[top--];
        }
        return re;
    }
    public int peek(){
        int re=-1;
        if(!IsEmpty()){
            re=num[top];
        }
        return re;
    }
}