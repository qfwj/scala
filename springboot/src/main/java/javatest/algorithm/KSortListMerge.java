package javatest.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import static qf.javatest.algorithm.ListIml.initSingleList;

public class KSortListMerge {

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(initSingleList());
        list.add(initSingleList());
        SingleNode end = kSortListMerge(list);
        System.out.println(end);
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
    public static SingleNode kSortListMerge( List<SingleNode> nodes ) {
       if(nodes == null) return  null;
        if(nodes.size() == 1 )return nodes.get(0);
        int size = nodes.size();
        SingleNode tail = new SingleNode(1);
        SingleNode head = tail;
        PriorityQueue<SingleNode> priorityQueue = new PriorityQueue(size);
        for(int i =0; i < nodes.size(); ++i){
            priorityQueue.add(nodes.get(i));
        }
        while (nodes.size()> 1) {
            SingleNode node    =  priorityQueue.poll();
            tail.next = node;
            tail  = tail.next;
            if(node.next != null){
                priorityQueue.add(node.next);
            }
        }
        SingleNode tm = priorityQueue.poll();
       if( tm != null){
           tail.next = tm;
        }
        return  head.next;
    }
}

class Tuple  implements Comparable {
    SingleNode node;
    int i;

    public Tuple(SingleNode node, int i) {
        this.node = node;
        this.i = i;
    }

    @Override
    public int compareTo(Object o) {
        return this.node.value - ((Tuple)o).node.value;
    }
}