package com.company;


import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("NonAsciiCharacters")
public class Main {

    public static void main(String[] args) throws InterruptedException {

        //String[] array = "()".split("");
        //System.out.println(check(array));

    }

    public static boolean check(String[] array){
        Stack<String> stringStack = new Stack<>();
        for(String string : array){
            if(string.equals("(") || string.equals("{") || string.equals("[")){
                stringStack.push(string);
                continue;
            }
            if(stringStack.isEmpty())
                return false;
            if(string.equals(")")){
                String back = stringStack.pop();
                if(back.equals("{") || back.equals("[")){
                    return false;
                }
            }
            else if(string.equals("}")){
                String back = stringStack.pop();
                if(back.equals("(") || back.equals("[")){
                    return false;
                }
            }
            else if(string.equals("]")){
                String back = stringStack.pop();
                if(back.equals("(") || back.equals("{")){
                    return false;
                }
            }
        }
        return stringStack.isEmpty();

    }

}

























                /*
        Node<Integer> root = new Node(1);
        Node<Integer> left1 = new Node(2);
        Node<Integer> right1 = new Node(2);
        root.setLeft(left1);
        root.setRight(right1);
        Node<Integer> left2 = new Node(4);
        Node<Integer> right2 = new Node(5);
        left1.setLeft(left2);
        left1.setRight(right2);
        right1.setLeft(right2);
        right1.setRight(left2);
        */


        /*


    /*




    char[][] mountain = {
            "^^^^^^        ".toCharArray(),
            " ^^^^^^^^     ".toCharArray(),
            "  ^^^^^^^     ".toCharArray(),
            "  ^^^^^       ".toCharArray(),
            "  ^^^^^^^^^^^ ".toCharArray(),
            "  ^^^^^^      ".toCharArray(),
            "  ^^^^        ".toCharArray()
    };
    System.out.println(peakHeight(mountain));
    */
    /*
            Первая лекция

            final ExecutorService service = Executors.newFixedThreadPool(10);

            for (int i = 0; i < 1000; i++) {
                service.execute(() -> new СчетВБанке());
                service.execute(() -> бабаЯга.положитьДеньги(10));
            }

            new Thread(() -> кощейБессмертный.положитьДеньги(10)).start();
            new Thread(() -> бабаЯга.узнатьБаланс()).start();

            Thread.sleep(100L);
            счетвБанке.посмотретьБаланс("Центро Банк");
            service.shutdown();
        */

        /*

        Node<Integer> root = new Node(1);
        Node<Integer> left1 = new Node(2);
        Node<Integer> right1 = new Node(3);
        root.setLeft(left1);
        root.setRight(right1);
        Node<Integer> left2 = new Node(4);
        Node<Integer> right2 = new Node(5);
        left1.setLeft(left2);
        left1.setRight(right2);
        Node<Integer> left3 = new Node(6);
        Node<Integer> right3 = new Node(7);
        right1.setLeft(left3);
        right1.setRight(right3);

        List<Integer> list = printBfvTree(root);
        System.out.println(list);

        */