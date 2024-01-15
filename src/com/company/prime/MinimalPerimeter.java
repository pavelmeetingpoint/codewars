package com.company.prime;

/**
 * returns the minimal perimeter of any rectangle whose area is exactly equal to n
 */
public class MinimalPerimeter {

    public static int countKprimes(int n) {
        if(n == 1){
            return 4;
        }
        int perimeter = 0;
        int prev = 1;
        for(int i = 2; i <= n; i++){
            if(n % i == 0){
                if(i*i == n){
                    perimeter = i + i;
                    break;
                }
                if(i*prev == n){
                    perimeter = i + prev;
                    break;
                }
                prev = i;
            }
        }
        return 2*perimeter;
    }

}

