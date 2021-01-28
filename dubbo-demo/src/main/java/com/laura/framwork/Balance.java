package com.laura.framwork;

import java.util.List;
import java.util.Random;

public class Balance {

    public static Url random(List<Url> list) {
        Random random =new Random();
        int n = random.nextInt(list.size());
        return list.get(n);
    }

}
