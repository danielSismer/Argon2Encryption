package com.example.crypto;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class Argon2Crypto {
    private final Argon2 argon2;
    private final int iterations;
    private final int memoryKb;
    private final int parallelism;

    public Argon2Crypto() {
        this(3, 65536, 1);
    }

    public Argon2Crypto(int iterations, int memoryKb, int parallelism) {
        this.argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        this.iterations = iterations;
        this.memoryKb = memoryKb;
        this.parallelism = parallelism;
    }

    public String hash(char[] password) {
        return argon2.hash(iterations, memoryKb, parallelism, password);
    }

    public boolean verify(String hash, char[] password) {
        return argon2.verify(hash, password);
    }
}


