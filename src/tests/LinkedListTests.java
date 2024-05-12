package tests;

import lists.CustomDeque;
import lists.CustomLinkedList;
import lists.CustomList;


public class LinkedListTests{
    public void runAllTests(){
        printTest("deque operations test", dequeTest());
        printTest("list operation test", listTest());
    }

    private void printTest(String testName, boolean result){
        System.out.printf("Test %s result: %s", testName, result);
    }

    private boolean dequeTest(){
        CustomDeque<Integer> list = new CustomLinkedList<>();
        list.addFirst(1);
        list.addFirst(0);
        list.addLast(2);
        list.addLast(3);
        list.addFirst(-1);

        list.removeFirst();
        list.removeLast();

        System.out.println(list);

        if (list.size() != 3) return false;
        return list.getFirst() == 0 && list.getLast() == 2;
    }

    private boolean listTest(){
        CustomList<String> list = new CustomLinkedList<>();

        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        list.add("five");
        list.add("six");
        list.add("seven");
        list.add("one");

        list.remove(1);
        list.remove(1);
        list.remove("one");
        list.remove("five");
        list.remove("one");
        list.set(1, "six item");

        System.out.println(list);

        if (list.size() != 3) return false;
        if(!list.get(0).equals("four")) return false;
        if(!list.get(1).equals("six item")) return false;
        return list.get(2).equals("seven");
    }
}
