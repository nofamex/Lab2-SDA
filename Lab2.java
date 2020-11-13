import java.io.*;
import java.util.*;

public class Lab2 {
    private static InputReader in = new InputReader(System.in);
    private static PrintWriter out = new PrintWriter(System.out);
    private static LinkedList wordList = new LinkedList();
    public static ListNode pointer1;
    public static ListNode pointer2;

    //change pointer data
    public static void swapPointerData() {
        String temp = pointer1.data;
        pointer1.data = pointer2.data;
        pointer2.data = temp;
    }

    //change pointer reference
    public static void swapPointer() {
        swapPointerData();
        ListNode temp = pointer1;
        pointer1 = pointer2;
        pointer2 = temp;
    }

    //execute query
    public static void runQuery(String command) {
        String arg1 = "";
        String arg2 = "";
        if(command.equals("GESER_KANAN") || command.equals("GESER_KIRI") || command.equals("HAPUS")) {
            arg1 = in.next();
            if(command.equals("GESER_KANAN")) {
                if (arg1.equals("1")) {
                    if (pointer1.next.data.equals("2")) {
                        swapPointer();
                    }
                    pointer1 = pointer1.shiftRight();
                } else {
                    if (pointer2.next.data.equals("1")) {
                        swapPointer();
                    }
                    pointer2 = pointer2.shiftRight();
                }
            } else if(command.equals("GESER_KIRI")) {
                if (arg1.equals("1")) {
                    if (pointer1.previous.data.equals("2")) {
                        swapPointer();
                    }
                    pointer1 = pointer1.shiftLeft();
                } else {
                    if (pointer2.previous.data.equals("1")) {
                        swapPointer();
                    }
                    pointer2 = pointer2.shiftLeft();
                }
            } else {
                if (arg1.equals("1")) {
                    pointer1.delete();
                } else {
                    pointer2.delete();
                }
            }
        } else if(command.equals("TULIS")) {
            arg1 = in.next();
            arg2 = in.next();
            if (arg1.equals("1")) {
                pointer1.write(arg2);
            } else {
                pointer2.write(arg2);
            }
        } else {
            pointer1.swap();
        }
    }

    public static void main(String[] args) {
        String word = in.next();
        wordList.add("1");
        wordList.add("2");
        pointer1 = wordList.header.next;
        pointer2 = wordList.last;
        for (String s : word.split("")){
            wordList.add(s);
        }

        int queryQuantity = in.nextInt();
        for (int i = 0; i < queryQuantity; i++) {
            String command = in.next();
            runQuery(command);
        }

        wordList.printAll();
        out.close();
    }

    //linked list class
    static class LinkedList {
        public ListNode header;
        public ListNode last;

        public LinkedList() {
            header = new ListNode("0", null, null);
            last = header;
        }

        //add new element to linked list
        public void add(String newData) {
            ListNode newNode = new ListNode(newData, null, null);
            newNode.previous = last;
            last.next = newNode;
            last = newNode;
        }

        //print all element
        public void printAll() {
            ListNode current = header.next;
            while (current != null) {
                if (!current.data.equals("1") && !current.data.equals("2")) {
                    out.print(current.data);
                }
                // out.print(current.data);
                current = current.next;
            }
        }
    }

    //node class
    static class ListNode {
        public ListNode previous;
        public String data;
        public ListNode next;

        public ListNode(String datax, ListNode nextx, ListNode prevx) {
            this.data = datax;
            this.next = nextx;
            this.previous = prevx;
        }

        //send element to the right
        public ListNode shiftRight() {
            String tempData = data;
            data = next.data;
            next.data = tempData;
            return next;
        }

        //send element to the left
        public ListNode shiftLeft() {
            String tempData = data;
            data = previous.data;
            previous.data = tempData;
            return previous;
        }

        //delete element left to the pointer
        public void delete(){
            ListNode current;
            if (previous.data.equals("1") || previous.data.equals("2")) {
                current = previous;
            } else {
                current = previous.next;
            }
            current.previous = current.previous.previous;
            current.previous.next = current;
        }

        //write element left to pointer
        public void write(String datax) {
            ListNode current;
            if (previous.data.equals("1") || previous.data.equals("2")) {
                current = previous;
            } else {
                current = previous.next;
            }
            ListNode newNode = new ListNode(datax, current, current.previous);
            current.previous.next = newNode;
            current.previous = newNode;
        }

        //swap element
        public void swap() {
            if (!(previous.data.equals(pointer2.data) ^ pointer2.previous.data.equals(data))) {
                String tempData = previous.data;
                previous.data = pointer2.previous.data;
                pointer2.previous.data = tempData;
            }
        }
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;
 
        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }
 
        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }
 
        public int nextInt() {
            return Integer.parseInt(next());
        }
 
    }
}