public class Try{
    public static void main(String[] args){
        int[] a={1,2,3};
        int i=0;
        Stack st=new Stack();
        st.Init();
        for(i=0;i<3;i++){
            st.push(a[i]);
        }
        for(i=0;i<3;i++){
            System.out.println(a[i]);
        }
    }
}