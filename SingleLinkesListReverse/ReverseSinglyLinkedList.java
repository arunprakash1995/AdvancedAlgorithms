package cs6301.g23;
//SP2 - Problem 4
/**
 * This programs implements the functionality of reversing the Singly  *  Linked list.
 *
 * @author Arun Prakash Themothy Prabhu Vincent
 * @author Akshaya Udayakumar
 * @author Radhika Kalaiselvan
 *
 * Version 1.0 - 9/4/17
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import cs6301.g00.*;

public class ReverseSinglyLinkedList {
    
    /**
     * Method to implement reversing of Singly Linked List
     * using non-recursive approach
     * @param <T>
     * @param SLL
     *           : SinglyLinkedList - List to be reversed
     */
    public static <T> void reverse(SinglyLinkedList SLL){
        if(SLL.head.next == null){ // checking if the list is empty
            return ;
        }
        SinglyLinkedList.Entry<T> prev = null;
        SinglyLinkedList.Entry<T> current = SLL.head.next;
        SinglyLinkedList.Entry<T> next = null;
        SLL.tail = current;
        while(current!= null){ // checking if the last node of list is reached
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        SLL.head.next = prev;
    }
    
    /**
     * Method to reverse the link between current and previous nodes
     * @param <T>
     * @param current
     *               : Entry<T> - current node
     * @param previous
     *               : Entry<T> - previous node
     */
    public static <T> void recursiveRev(SinglyLinkedList.Entry<T> current, SinglyLinkedList.Entry<T> prev){
        if(current == null){ // checking if the last node of list is reached
            return;
        }
        SinglyLinkedList.Entry<T> next = current.next;
        current.next = prev;
        recursiveRev(next,current); // recursive call to reverse the links between current and previous
    }
    
    /**
     * Method to implement reversing of Singly Linked List
     * using recursive approach
     * @param <T>
     * @param SLL
     *           : SinglyLinkedList - List to be reversed
     */
    public static <T> void recursiveReverse(SinglyLinkedList SLL){
        if(SLL.head.next == null){// checking if the list is empty
            return;
        }
        SinglyLinkedList.Entry<T> temp= SLL.head.next;
        recursiveRev(SLL.head.next,null);
        SLL.head.next = SLL.tail;
        SLL.tail = temp;
        
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in;
        if(args.length > 0){
            File inputFile = new File(args[0]);
            in = new Scanner(inputFile);
        }
        else{
            in = new Scanner(System.in);
        }
        int n = in.nextInt();
        SinglyLinkedList<Integer> lst = new SinglyLinkedList<>();
        for(int i=1; i<=n; i++) {
            int value = in.nextInt();
            lst.add(new Integer(value));
        }
        System.out.print("Natural Order : ");
        lst.printList();
        System.out.print("Non-Recursive Reverse Order : ");
        ReverseSinglyLinkedList.reverse(lst);
        lst.printList();
        ReverseSinglyLinkedList.reverse(lst);
        System.out.print("Natural Order : ");
        lst.printList();
        System.out.print("Recursive Reverse Order : ");
        ReverseSinglyLinkedList.recursiveReverse(lst);
        lst.printList();
        
    }
}


/**
 * Sample input : 
10   -  no of elements in the Singly Linked List
 1
 5
 63
 4
 3
 7
 3
 4
 2
 1
    Sample output:
 Natural Order : 10: 1 5 63 4 3 7 3 4 2 1
 Non-Recursive Reverse Order : 10: 1 2 4 3 7 3 4 63 5 1
 Natural Order : 10: 1 5 63 4 3 7 3 4 2 1
 Recursive Reverse Order : 10: 1 2 4 3 7 3 4 63 5 1
*/
