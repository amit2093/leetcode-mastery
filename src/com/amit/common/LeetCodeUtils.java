package com.amit.common;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class LeetCodeUtils {

    // --- ELITE COLOR PALETTE ---
    private static final String RESET = "\033[0m";
    private static final String BOLD = "\033[1m";
    private static final String DIM = "\033[2m";
    private static final String ITALIC = "\033[3m";

    // Borders & Accents (256-color mode)
    private static final String LINE = "\033[38;5;240m";    // Dark Grey line
    private static final String DOT = "\033[38;5;244m";     // Muted dot
    private static final String ACCENT = "\033[38;5;45m";   // Electric Blue

    // Syntax Highlighting
    private static final String KEY = "\033[38;5;81m";      // Sky Blue
    private static final String VAL_STR = "\033[38;5;215m"; // Soft Orange
    private static final String VAL_NUM = "\033[38;5;141m"; // Purple

    // Status
    private static final String PASS_ICON = "\033[38;5;82m" + "✔" + RESET;
    private static final String FAIL_ICON = "\033[38;5;196m" + "✘" + RESET;
    private static final String TIME_ICON = "\033[38;5;226m" + "⚡" + RESET;

    public static <T> void runTest(Object expected, Supplier<T> solver) {
        StackTraceElement caller = Thread.currentThread().getStackTrace()[2];
        String formattedName = formatClassName(caller.getClassName());

        List<Object> extractedValues = extractLambdaVariables(solver, caller.getClassName());
        List<String> sourceNames = getVariableNamesFromSource(caller);

        // --- HEADER (Now cleaner and case-correct) ---
        System.out.println("\n" + ACCENT + BOLD + " ⦿ " + RESET + BOLD + formattedName + RESET);
        System.out.println(LINE + " │" + RESET);

        // --- INPUT SECTION ---
        System.out.println(LINE + " ├─" + RESET + BOLD + " ARGUMENTS " + RESET);
        if (extractedValues.isEmpty()) {
            System.out.println(LINE + " │  " + DIM + "no arguments captured" + RESET);
        } else {
            for (int i = 0; i < extractedValues.size(); i++) {
                String varName = (sourceNames.size() > i) ? sourceNames.get(i) : "arg" + (i + 1);
                String formattedVal = formatObject(extractedValues.get(i), 0);
                printWrappedValue(varName, formattedVal);
            }
        }

        System.out.println(LINE + " │" + RESET);

        // --- EXECUTION ---
        String expectedStr = formatObject(expected, 0);
        System.out.println(LINE + " ├─" + RESET + BOLD + " EXPECTED" + RESET + "  " + expectedStr);

        try {
            long start = System.nanoTime();
            T result = solver.get();
            long end = System.nanoTime();
            double timeMs = (end - start) / 1_000_000.0;

            String actualStr = formatObject(result, 0);
            boolean isPass = checkEquality(expected, result);

            System.out.println(LINE + " ├─" + RESET + BOLD + " ACTUAL  " + RESET + "  " + actualStr);
            System.out.println(LINE + " │" + RESET);

            // --- FOOTER / STATUS ---
            String status = isPass ? PASS_ICON + " " + BOLD + "PASSED" : FAIL_ICON + " " + BOLD + "FAILED";
            System.out.print(LINE + " ╰── " + RESET + status + RESET);
            System.out.println(DIM + "  (" + TIME_ICON + " " + String.format("%.3f ms", timeMs) + ")" + RESET + "\n");

        } catch (Exception e) {
            System.out.println(LINE + " ╰── " + RESET + FAIL_ICON + BOLD + " EXCEPTION: " + RESET + "\033[31m" + e.getMessage() + RESET + "\n");
        }
    }

    private static void printWrappedValue(String key, String value) {
        int keyWidth = 7; // Adjusted width to keep arrows aligned
        String[] lines = value.split("\n");

        for (int i = 0; i < lines.length; i++) {
            System.out.print(LINE + " │  " + RESET);
            if (i == 0) {
                // Calculate padding for the key to keep arrows vertical
                String plainKey = key.replaceAll("\033\\[[0-9;]*m", "");
                int padding = Math.max(0, keyWidth - plainKey.length());

                System.out.print(KEY + key + RESET + " ".repeat(padding) + DIM + " ➜ " + RESET + lines[i]);
            } else {
                // Indent sub-lines (like JSON) to start exactly after the arrow
                System.out.print(" ".repeat(keyWidth + 3) + lines[i]);
            }
            System.out.println();
        }
    }

    private static String formatClassName(String fullClassName) {
        // 1. Get just the class name: LC209_MinimumSizeSubarraySum_SlidingWindow
        String rawName = fullClassName.substring(fullClassName.lastIndexOf('.') + 1);

        // 2. Remove the "SlidingWindow" or "TwoPointers" suffixes if they exist
        rawName = rawName.replaceAll("_(SlidingWindow|TwoPointers|BruteForce|Optimized)$", "");

        // 3. Handle the LC prefix and CamelCase spacing
        // This regex looks for LC followed by digits, or a lowercase followed by an uppercase
        String formatted = rawName
                .replaceAll("LC(\\d+)", "LC $1: ")
                .replaceAll("([a-z])([A-Z])", "$1 $2")
                .replace("_", "");

        return formatted.trim();
    }

    private static String formatObject(Object obj, int indent) {
        if (obj == null) return DIM + "null" + RESET;
        if (obj instanceof String) return VAL_STR + "\"" + obj + "\"" + RESET;
        if (obj instanceof Number || obj instanceof Boolean) return VAL_NUM + obj + RESET;

        if (obj instanceof int[]) return VAL_NUM + Arrays.toString((int[]) obj) + RESET;
        if (obj instanceof Object[]) return VAL_NUM + Arrays.deepToString((Object[]) obj) + RESET;

        if (!obj.getClass().getName().startsWith("java.")) {
            return objectToJson(obj, indent);
        }
        return obj.toString();
    }

    private static String objectToJson(Object obj, int indentLevel) {
        Class<?> clazz = obj.getClass();
        StringBuilder sb = new StringBuilder();
        String currentIndent = "  ".repeat(indentLevel);
        String innerIndent = "  ".repeat(indentLevel + 1);

        sb.append(LINE + "{" + RESET + "\n");
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getName().startsWith("this$")) continue;
            fields[i].setAccessible(true);
            try {
                sb.append(innerIndent).append(KEY).append(fields[i].getName()).append(RESET).append(DIM + ": " + RESET);
                sb.append(formatObject(fields[i].get(obj), indentLevel + 1));
                if (i < fields.length - 1) sb.append(LINE + "," + RESET);
                sb.append("\n");
            } catch (Exception ignored) {}
        }
        sb.append(currentIndent).append(LINE + "}" + RESET);
        return sb.toString();
    }

    private static List<Object> extractLambdaVariables(Object solver, String callerClass) {
        List<Object> values = new ArrayList<>();
        for (Field f : solver.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            try {
                Object val = f.get(solver);
                if (val != null && !f.getName().contains("this$") && !val.getClass().getName().equals(callerClass)) {
                    values.add(val);
                }
            } catch (Exception ignored) {}
        }
        return values;
    }

    private static boolean checkEquality(Object e, Object a) {
        if (e instanceof int[] && a instanceof int[]) return Arrays.equals((int[]) e, (int[]) a);
        if (e instanceof Object[] && a instanceof Object[]) return Arrays.deepEquals((Object[]) e, (Object[]) a);
        return (e == null) ? (a == null) : e.equals(a);
    }

    private static List<String> getVariableNamesFromSource(StackTraceElement caller) {
        List<String> names = new ArrayList<>();
        try {
            // 1. Locate the file (handling different project structures)
            String relativePath = caller.getClassName().replace('.', '/') + ".java";
            File f = new File(System.getProperty("user.dir") + "/src/" + relativePath);
            if (!f.exists()) f = new File(System.getProperty("user.dir") + "/" + relativePath);
            if (!f.exists()) return names;

            List<String> lines = Files.readAllLines(f.toPath());
            String code = lines.get(caller.getLineNumber() - 1).trim();

            // 2. Find the start of the method call inside the lambda
            int lambdaIdx = code.indexOf("->");
            if (lambdaIdx == -1) return names;

            int startParen = code.indexOf("(", lambdaIdx);
            if (startParen == -1) return names;

            // 3. Find the EXACT matching closing parenthesis for this call
            int depth = 0;
            int endParen = -1;
            for (int i = startParen; i < code.length(); i++) {
                char c = code.charAt(i);
                if (c == '(') depth++;
                else if (c == ')') {
                    depth--;
                    if (depth == 0) {
                        endParen = i;
                        break;
                    }
                }
            }

            if (endParen != -1) {
                String argsStr = code.substring(startParen + 1, endParen);

                // 4. SMART SPLIT: Split by comma, but only if we are at depth 0
                StringBuilder current = new StringBuilder();
                int bracketDepth = 0;
                for (char c : argsStr.toCharArray()) {
                    if (c == '(' || c == '[' || c == '{') bracketDepth++;
                    else if (c == ')' || c == ']' || c == '}') bracketDepth--;

                    if (c == ',' && bracketDepth == 0) {
                        names.add(current.toString().trim());
                        current.setLength(0);
                    } else {
                        current.append(c);
                    }
                }
                if (current.length() > 0) {
                    names.add(current.toString().trim());
                }
            }
        } catch (Exception ignored) {}
        return names;
    }
}