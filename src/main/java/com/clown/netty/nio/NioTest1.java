package com.clown.netty.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

public class NioTest1 {
    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(10);

        for (int i =0 ; i< 10 ;i++){
            SecureRandom random = new SecureRandom();
            int ranNum = random.nextInt(10);
            intBuffer.put(ranNum);
        }

        intBuffer.flip();

        for (int j = 0 ; j < 10 ;j++){
            System.out.println(intBuffer.get());
        }
    }
}
