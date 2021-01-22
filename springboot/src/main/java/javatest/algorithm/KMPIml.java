package javatest.algorithm;

/**
 * @Description: KMP算法实现
 * @author: zhbo
 * @date 2020/7/17 14:37
 */
public class KMPIml {
    public static void main(String[] args) {
        nextArr("abac");
    }


    public static int[] nextArr(String par) {
        int[] next = new int[par.length()];
        char[] chars = par.toCharArray();
        next[0] = -1;
        for (int i = 1; i < par.length(); i++) {
            if (chars[i] == chars[next[i - 1] + 1]) {
                next[i] = next[i - 1] + 1;
            } else {
                int k = next[i - 1];
                while (k > 0) {
                    if (chars[i] == chars[next[k] + 1]) {
                        k = next[k] + 1;
                        break;
                    } else {
                        k = next[k];
                    }
                }
                next[i] = k;
            }
        }
        return next;
    }

    public static int indexOf(String text, String p) {
        int[] next = nextArr(p);
        char[] textArr = text.toCharArray();
        char[] pArr = p.toCharArray();
        int i = 0, j = 0;
        while (i < textArr.length && j < p.length()) {
            if (textArr[i] == pArr[j]) {
                i ++;
                j ++;
            } else if (next[j] >= 0) {
                j = next[j];
            } else {
                j = next[j] + 1;
            }
        }
        return j == p.length() ? i - j : -1;

    }
}
