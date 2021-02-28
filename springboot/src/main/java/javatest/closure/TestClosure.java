package javatest.closure;

public class TestClosure {

    Integer y = new Integer(3434);
    public static void main(String[] args) {

    }


    public int  main12() {
        Integer dd = new Integer(1333);
        class Inner1 {
            public  int  main() {
                return dd+y+12;
            }
        }

        return  new Inner1().main();
    }
    class Inner {
        Integer dd = new Integer(1333);
        public  int  main() {
            return y+12 + dd;
        }
    }
}
