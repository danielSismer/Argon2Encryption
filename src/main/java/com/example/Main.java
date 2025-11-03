package com.example;

import com.example.crypto.Argon2Crypto;
import com.example.math.LinearSystemSolver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Projeto: Sistemas de Equações e Criptografia Argon2");
        System.out.println("1) Resolver sistema linear Ax=b");
        System.out.println("2) Gerar hash Argon2");
        System.out.println("3) Verificar hash Argon2");
        System.out.print("Escolha uma opção: ");
        String choice = reader.readLine();

        switch (choice) {
            case "1":
                executarSistemaLinear(reader);
                break;
            case "2":
                gerarHash(reader);
                break;
            case "3":
                verificarHash(reader);
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void executarSistemaLinear(BufferedReader reader) throws Exception {
        System.out.print("Informe n (ordem da matriz A): ");
        int n = Integer.parseInt(reader.readLine());
        double[][] A = new double[n][n];
        double[] b = new double[n];

        System.out.println("Digite a matriz A linha por linha (n valores por linha, separados por espaço):");
        for (int i = 0; i < n; i++) {
            String[] parts = reader.readLine().trim().split("\\s+");
            for (int j = 0; j < n; j++) {
                A[i][j] = Double.parseDouble(parts[j]);
            }
        }

        System.out.println("Digite o vetor b (n valores separados por espaço):");
        String[] bparts = reader.readLine().trim().split("\\s+");
        for (int i = 0; i < n; i++) {
            b[i] = Double.parseDouble(bparts[i]);
        }

        LinearSystemSolver solver = new LinearSystemSolver();
        double[] x = solver.solve(A, b);
        System.out.println("Solução x = " + Arrays.toString(x));
    }

    private static void gerarHash(BufferedReader reader) throws Exception {
        System.out.print("Digite a senha para gerar o hash: ");
        char[] pwd = reader.readLine().toCharArray();
        Argon2Crypto crypto = new Argon2Crypto();
        String hash = crypto.hash(pwd);
        Arrays.fill(pwd, '\\0');
        System.out.println("Hash Argon2: " + hash);
    }

    private static void verificarHash(BufferedReader reader) throws Exception {
        System.out.print("Digite o hash Argon2: ");
        String hash = reader.readLine();
        System.out.print("Digite a senha: ");
        char[] pwd = reader.readLine().toCharArray();
        Argon2Crypto crypto = new Argon2Crypto();
        boolean ok = crypto.verify(hash, pwd);
        Arrays.fill(pwd, '\\0');
        System.out.println(ok ? "Senha confere." : "Senha NÃO confere.");
    }
}


