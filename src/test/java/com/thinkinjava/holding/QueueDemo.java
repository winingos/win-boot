package com.thinkinjava.holding;

import java.util.*;

/**
 * Created by ning.wang on 2016/9/13.
 * Upcasting to a Queue from a LinkedList.
 */
public class QueueDemo {
    public static final void printQ(Queue queue){
        while (queue.peek()!=null){
            System.out.print(queue.remove()+" ");
        }
        System.out.println();

    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        Random r = new Random(47);
        for (int i = 0; i < 10; i++) {
            queue.offer(r.nextInt(i+10));
        }
        printQ(queue);
        Queue<Character> qc = new LinkedList<>();
        for (Character c : "Brontosaurus".toCharArray()) {
            qc.offer(c);
        }
        printQ(qc);
    }
}
class PriorityQueueDemo{
    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        Random r = new Random(47);
        for (int i = 0; i < 10; i++) {
            priorityQueue.offer(r.nextInt(i+10));
        }
        QueueDemo.printQ(priorityQueue);
        List<Integer> ints = Arrays.asList(25, 22, 20, 18, 14, 9, 3, 1, 1, 2, 3, 9, 14, 18, 21, 23, 25);
        priorityQueue = new PriorityQueue<>(ints);
        QueueDemo.printQ(priorityQueue);
        priorityQueue = new PriorityQueue<>(ints.size(), Collections.reverseOrder());
        priorityQueue.addAll(ints);
        QueueDemo.printQ(priorityQueue);

        String fact = "EDUCATION SHOULD ESCHEW OBFUSCATION";
        List<String> strings = Arrays.asList(fact.split(""));
        PriorityQueue<String> strPq = new PriorityQueue<>(strings);
        QueueDemo.printQ(strPq);
        strPq=new PriorityQueue<>(strings.size(),Collections.reverseOrder());
        strPq.addAll(strings);
        QueueDemo.printQ(strPq);

        Set<Character> cset = new HashSet<>();
        for (Character c : fact.toCharArray()) {
            cset.add(c);
        }
        PriorityQueue<Character> pq = new PriorityQueue<>(cset);
        QueueDemo.printQ(pq);
        PriorityQueue<Objex> op = new PriorityQueue<>();
        op.offer(new Objex());
        op.offer(new Objex());


    }
}
class Objex extends Object implements Comparator{

    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }
}
