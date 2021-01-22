package javatest.algorithm;

/**
 * @Description: KMP算法实现
 * @author: zhbo
 * @date 2020/7/17 14:37
 */
public class ListIml {

    public static void main(String[] args) {
        revert(initSingleList());
        System.out.println(12);
    }

    public static SingleNode revert(SingleNode head) {
        if(head == null && head.next == null ) return head;
        SingleNode head1 = head.next;
        SingleNode head2 = head;
        head2.next = null;
        while(head1 != null ){
            SingleNode tmp = head1.next;
            head1.next = head2;
            head2 = head1;
            head1 = tmp;
        }
        return head2;

    }
    public static SingleNode initSingleList() {
        SingleNode node0 =  new SingleNode(0);
        SingleNode node1 =  new SingleNode(1);
        SingleNode node2 =  new SingleNode(2);
        SingleNode node3 =  new SingleNode(3);
        node0.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = null;
        return  node0;
    }
}

class SingleNode {
    SingleNode next;
    int value;

    public SingleNode(int value) {
        this.value = value;
    }
}