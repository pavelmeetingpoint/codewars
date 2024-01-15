package com.company;

import com.company.array.DeepOfSubArray;
import com.company.array.SumOfSubArray;
import com.company.prime.MinimalPerimeter;
import com.company.tasks.CanConstruct;
import com.company.tasks.CanSum;
import com.company.tasks.Fibonacci;
import com.company.tasks.HowSum;

import java.util.*;
import java.util.function.Consumer;

import static com.company.prime.Euclidean.primeDivisors;
import static com.company.prime.Sieve.factorization;
import static com.company.prime.Sieve.sieve;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        var start = System.currentTimeMillis();

        int n = 60;
        System.out.println("Prime numbers up to " + primeDivisors(n) + ":");

        System.out.println("Time:" + (System.currentTimeMillis() - start));
    }

    public static int solution(int[] a, int[] b) {
        Stack<Integer> stackFlow = new Stack<>();
        for(int i = a.length - 1; i >= 0; i--){
            handle(stackFlow, a, b, i);
        }
        return stackFlow.size();
    }

    private static void handle(Stack<Integer> stackFlow, int[] a, int[] b, int i){
        if(stackFlow.isEmpty()){
            stackFlow.push(i);
        }
        else {
            int next = stackFlow.peek();
            if (b[next] == b[i]) {
                stackFlow.push(i);
            } else if (b[i] == 1 && a[i] > a[next]) {
                stackFlow.pop();
                handle(stackFlow, a, b, i);
            } else if(b[i] == 0){
                stackFlow.push(i);
            }

        }
    }


}

