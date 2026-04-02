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
    private static final String BORDER_COLOR = "\033[38;5;239m";


    // Set this to 1 or 2 to keep the UI tight
    private static final int MAX_DEPTH = 1;
    private static final int KEY_WIDTH = 10; // Adjusted width to keep arrows aligned

    // Add this as a ThreadLocal to keep it thread-safe for parallel tests
    private static final ThreadLocal<java.util.List<Integer>> stack =
            ThreadLocal.withInitial(java.util.ArrayList::new);

    public static <T> void runTest(Object expected, Supplier<T> solver) {
        stack.get().clear(); // Reset before each test

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
        String[] lines = value.split("\n");

        for (int i = 0; i < lines.length; i++) {
            System.out.print(LINE + " │  " + RESET);
            if (i == 0) {
                String plainKey = key.replaceAll("\033\\[[0-9;]*m", "");
                int padding = Math.max(0, KEY_WIDTH - plainKey.length());
                System.out.print(KEY + key + RESET + " ".repeat(padding) + DIM + " ➜ " + RESET + lines[i]);
            } else {
                // This alignment keeps the nested JSON perfectly under the arrow
                System.out.print(" ".repeat(KEY_WIDTH + 3) + lines[i]);
            }
            System.out.println();
        }
    }

    private static String formatClassName(String fullClassName) {
        // 1. Get just the class name: LC209_MinimumSizeSubarraySum_SlidingWindow
        String rawName = fullClassName.substring(fullClassName.lastIndexOf('.') + 1);

        // 2. Remove the "SlidingWindow" or "TwoPointers" suffixes if they exist
        rawName = rawName.replaceAll("_(SlidingWindow|TwoPointers|BruteForce|Optimized|PrefixSum)$", "");

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

        // 1. Enum & Primitive Wrapper Handling
        if (obj instanceof Enum) return VAL_NUM + ((Enum<?>) obj).name() + RESET;

        // 2. Recursion/Circular Guard (Stack-based)
        boolean isComplex = !(obj instanceof Number || obj instanceof Boolean || obj instanceof String);
        int id = System.identityHashCode(obj);
        if (isComplex) {
            if (stack.get().contains(id)) {
                return DIM + "[Recursive Ref:" + Integer.toHexString(id) + "]" + RESET;
            }
            stack.get().add(id);
        }

        try {
            // 3. Smart Collapse Logic (Collapse at level 2 if full=false)
            boolean expandAll = Boolean.getBoolean("full");
            int collapseAt = expandAll ? 99 : 2;

            if (indent >= collapseAt) {
                if (obj instanceof java.util.Map || (!obj.getClass().getName().startsWith("java.") &&
                        !obj.getClass().isArray() && isComplex)) {
                    return BORDER_COLOR + "{...}" + RESET;
                }
                if (obj instanceof java.util.Collection || (obj.getClass().isArray() &&
                        !(obj instanceof int[] || obj instanceof long[] || obj instanceof double[]))) {
                    return BORDER_COLOR + "[...]" + RESET;
                }
            }

            // 4. Basic Types
            if (obj instanceof String) return VAL_STR + "\"" + obj + "\"" + RESET;
            if (obj instanceof Number || obj instanceof Boolean) return VAL_NUM + obj + RESET;

            // 5. Multi-line Array Handling (The Matrix Fix)
            if (obj.getClass().isArray()) {
                // Keep 1D primitive arrays on one line for readability
                if (obj instanceof int[]) return VAL_NUM + Arrays.toString((int[]) obj) + RESET;
                if (obj instanceof long[]) return VAL_NUM + Arrays.toString((long[]) obj) + RESET;
                if (obj instanceof double[]) return VAL_NUM + Arrays.toString((double[]) obj) + RESET;

                Object[] arr = (Object[]) obj;
                if (arr.length == 0) return BORDER_COLOR + "[]" + RESET;

                StringBuilder sb = new StringBuilder();
                String currentIndent = "  ".repeat(indent);
                String innerIndent = "  ".repeat(indent + 1);

                sb.append(VAL_NUM + "[" + RESET + "\n");
                for (int i = 0; i < arr.length; i++) {
                    sb.append(innerIndent).append(formatObject(arr[i], indent + 1));
                    if (i < arr.length - 1) sb.append(LINE + "," + RESET);
                    sb.append("\n");
                }
                sb.append(currentIndent).append(VAL_NUM + "]" + RESET);
                return sb.toString();
            }

            // 6. Map Handling
            if (obj instanceof java.util.Map) {
                java.util.Map<?, ?> map = (java.util.Map<?, ?>) obj;
                if (map.isEmpty()) return BORDER_COLOR + "{}" + RESET;
                StringBuilder sb = new StringBuilder();
                String currentIndent = "  ".repeat(indent);
                String innerIndent = "  ".repeat(indent + 1);
                sb.append(LINE + "{" + RESET + "\n");
                int count = 0;
                for (java.util.Map.Entry<?, ?> entry : map.entrySet()) {
                    sb.append(innerIndent).append(KEY).append(entry.getKey()).append(RESET)
                            .append(DIM + ": " + RESET).append(formatObject(entry.getValue(), indent + 1));
                    if (++count < map.size()) sb.append(LINE + "," + RESET);
                    sb.append("\n");
                }
                sb.append(currentIndent).append(LINE + "}" + RESET);
                return sb.toString();
            }

            // 7. Collection Handling (List/Set)
            if (obj instanceof java.util.Collection) {
                java.util.Collection<?> col = (java.util.Collection<?>) obj;
                if (col.isEmpty()) return BORDER_COLOR + "[]" + RESET;
                StringBuilder sb = new StringBuilder();
                sb.append(VAL_NUM + "[" + RESET);
                int count = 0;
                for (Object item : col) {
                    if (count > 0) sb.append(DIM + ", " + RESET);
                    sb.append(formatObject(item, indent + 1));
                    if (++count >= 10 && col.size() > 10) {
                        sb.append(DIM + ", ..." + RESET);
                        break;
                    }
                }
                sb.append(VAL_NUM + "]" + RESET);
                return sb.toString();
            }

            // 8. Custom Object Handling (Reflection)
            if (!obj.getClass().getName().startsWith("java.")) {
                return objectToJson(obj, indent);
            }

            return obj.toString();

        } finally {
            // Pop from stack to allow the same object to be printed in different branches
            if (isComplex) {
                stack.get().remove(stack.get().size() - 1);
            }
        }
    }

    private static String objectToJson(Object obj, int indentLevel) {
        // Depth check
        boolean expandAll = Boolean.getBoolean("full");
        int effectiveDepth = expandAll ? 99 : MAX_DEPTH;
        if (indentLevel >= effectiveDepth) return BORDER_COLOR + "{...}" + RESET;

        Class<?> clazz = obj.getClass();
        StringBuilder sb = new StringBuilder();
        String currentIndent = "  ".repeat(indentLevel);
        String innerIndent = "  ".repeat(indentLevel + 1);

        sb.append(LINE + "{" + RESET + "\n");
        Field[] fields = clazz.getDeclaredFields();

        List<Field> validFields = new ArrayList<>();
        for (Field f : fields) {
            if (!f.getName().startsWith("this$")) validFields.add(f);
        }

        for (int i = 0; i < validFields.size(); i++) {
            Field f = validFields.get(i);
            f.setAccessible(true);
            try {
                sb.append(innerIndent).append(KEY).append(f.getName()).append(RESET).append(DIM + ": " + RESET);
                sb.append(formatObject(f.get(obj), indentLevel + 1));
                if (i < validFields.size() - 1) sb.append(LINE + "," + RESET);
                sb.append("\n");
            } catch (Exception ignored) {}
        }
        sb.append(currentIndent).append(LINE + "}" + RESET);
        return sb.toString();
    }

    private static List<Object> extractLambdaVariables(Object solver, String callerClass) {
        List<Object> values = new ArrayList<>();
        Field[] fields = solver.getClass().getDeclaredFields();

        for (Field f : fields) {
            f.setAccessible(true);
            try {
                Object val = f.get(solver);
                String fieldName = f.getName();

                // 1. Skip the 'this' reference to the Driver
                // 2. Skip any field that is an instance of a LeetCode Solution class
                if (fieldName.startsWith("this$") ||
                        (val != null && val.getClass().getSimpleName().startsWith("LC"))) {
                    continue;
                }

                values.add(val);
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
            String relativePath = caller.getClassName().replace('.', '/') + ".java";
            File f = new File(System.getProperty("user.dir") + "/src/" + relativePath);
            if (!f.exists()) f = new File(System.getProperty("user.dir") + "/" + relativePath);
            if (!f.exists()) return names;

            List<String> lines = Files.readAllLines(f.toPath());
            // Search a small window around the caller line to find the full lambda
            StringBuilder codeBuilder = new StringBuilder();
            int lineNum = caller.getLineNumber() - 1;
            for (int i = Math.max(0, lineNum - 3); i <= Math.min(lines.size() - 1, lineNum + 3); i++) {
                codeBuilder.append(lines.get(i).trim());
            }
            String code = codeBuilder.toString();

            // Find the arguments inside the method call following the lambda arrow
            int lambdaIdx = code.indexOf("->");
            if (lambdaIdx != -1) {
                int startParen = code.indexOf("(", lambdaIdx);
                int depth = 0;
                int endParen = -1;

                if (startParen != -1) {
                    for (int i = startParen; i < code.length(); i++) {
                        if (code.charAt(i) == '(') depth++;
                        else if (code.charAt(i) == ')') {
                            depth--;
                            if (depth == 0) { endParen = i; break; }
                        }
                    }
                }

                if (endParen != -1) {
                    String argsStr = code.substring(startParen + 1, endParen);
                    // Split while ignoring commas inside nested brackets
                    int bracketDepth = 0;
                    StringBuilder current = new StringBuilder();
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
                    if (current.length() > 0) names.add(current.toString().trim());
                }
            }
        } catch (Exception ignored) {}
        return names;
    }
}