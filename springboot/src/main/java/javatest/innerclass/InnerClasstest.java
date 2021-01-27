package javatest.innerclass;


/**
 * @Description:  非静态内部类不能有静态属性
 * @author: zhbo
 * @date 2020/10/28 10:46
 */
public  class InnerClasstest {

    private int j = InnerStatic.m1;
    private   int jj = InnerStatic.m1;
    private  static   int jj1 = InnerStatic.m1;
    //private   int jj1 = InnerStatic.m2;
    public  void out() {

    }
    private static void outstatic() {
        InnerStatic.innnerStatic12();
    }
    public  void  main() {
          InnerStatic.innnerStatic12();
    }

   static   class InnerStatic{
      public   static int m1 =jj1;
      public    int m2 =12;

       public void innnerStatic() {
           outstatic();
       }

      private static void innnerStatic12() {
          outstatic();
      }
   }

   public class Innerclass{
         private  int m ;
      //   private static int m1 ;  不能有静态变量  https://www.cnblogs.com/lulu638/p/4033581.html
       private    void innner(String[] args) {
           System.out.println(j);
           out();
       }
    }
}
