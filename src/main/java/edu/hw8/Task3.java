package edu.hw8;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.jetbrains.annotations.NotNull;

public final class Task3 {

    private Task3() {
    }

    private static final List<Character> CHARS = generateSymbols();
    private static final int MAX_LEN = 4;
    private static final int POOL_SIZE = 20;

    public static Map<String, String> decipherUserPasswords(List<String> userPasswordList) {
        if (userPasswordList == null) {
            return Map.of();
        }
        Map<String, String> hashAndLogin = parseLines(userPasswordList);
        Map<String, String> result = new HashMap<>();
        String pass = String.valueOf(CHARS.getFirst());

        while (result.size() != hashAndLogin.size() && pass.length() <= MAX_LEN) {
            String hash = convertPasswordToMD5Hash(pass);

            if (hashAndLogin.containsKey(hash)) {
                result.put(hashAndLogin.get(hash), pass);
            }

            pass = nextPassword(pass);
        }

        return result;
    }

    public static Map<String, String> decipherUserPasswordsInParallel(List<String> userPasswordList) {
        if (userPasswordList == null) {
            return Map.of();
        }
        Map<String, String> hashAndLogin = parseLines(userPasswordList);
        Map<String, String> result = new HashMap<>();
        try (ExecutorService executorService = Executors.newFixedThreadPool(POOL_SIZE)) {
            List<Callable<Void>> tasks = getTasks(result, hashAndLogin);

            List<Future<Void>> futures = executorService.invokeAll(tasks);

            for (var i : futures) {
                i.get();
            }

            executorService.shutdown();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @NotNull
    private static List<Callable<Void>> getTasks(Map<String, String> result, Map<String, String> hashAndLogin) {
        List<Callable<Void>> tasks = new ArrayList<>();

        for (char i : CHARS) {
            tasks.add(() -> {
                String pass = String.valueOf(i);

                while (result.size() != hashAndLogin.size() && pass.length() <= MAX_LEN) {
                    String hash = convertPasswordToMD5Hash(pass);

                    if (hashAndLogin.containsKey(hash)) {
                        result.put(hashAndLogin.get(hash), pass);
                    }

                    pass = nextPassword(pass);

                    if (pass.charAt(0) != i) {
                        pass = i + String.valueOf(CHARS.getFirst())
                            .repeat(i == CHARS.getLast() ? pass.length() - 1 : pass.length());
                    }
                }

                return null;
            });
        }

        return tasks;
    }

    private static Map<String, String> parseLines(List<String> userPasswordList) {
        Map<String, String> result = new HashMap<>();

        for (String line : userPasswordList) {
            String[] parsedLine = line.split(" ");

            if (parsedLine.length == 2) {
                result.put(parsedLine[1], parsedLine[0]);
            }
        }

        return result;
    }

    private static String convertPasswordToMD5Hash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            StringBuilder result = new StringBuilder();
            for (byte aByte : digest) {
                result.append(String.format("%02x", aByte));
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    private static String nextPassword(String password) {
        char[] string = password.toCharArray();

        for (int i = password.length() - 1; i >= 0; --i) {
            if (CHARS.contains(string[i]) && string[i] != CHARS.getLast()) {
                string[i] = CHARS.get(CHARS.indexOf(string[i]) + 1);
                for (int j = i + 1; j < password.length(); ++j) {
                    string[j] = CHARS.getFirst();
                }
                return String.valueOf(string);
            }
        }

        return String.valueOf(CHARS.getFirst()).repeat(password.length() + 1);
    }

    private static List<Character> generateSymbols() {
        List<Character> symbols = new ArrayList<>();

        for (char c = '0'; c <= '9'; ++c) {
            symbols.add(c);
        }
        for (char c = 'A'; c <= 'Z'; ++c) {
            symbols.add(c);
        }
        for (char c = 'a'; c <= 'z'; ++c) {
            symbols.add(c);
        }

        return symbols;
    }
}
