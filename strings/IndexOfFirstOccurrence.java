public class IndexOfFirstOccurrence {
    public int strStr(String haystack, String needle) {
        int h = haystack.length();
        int n = needle.length();

        for (int i = 0; i <= h - n; i++) {
            if (haystack.substring(i, i + n).equals(needle)) {
                return i;
            }
        }

        return -1;
    }
    public static void main(String[] args) {
        IndexOfFirstOccurrence sol = new IndexOfFirstOccurrence();
        System.out.println(sol.strStr("sadbutsad", "sad"));   // 0
        System.out.println(sol.strStr("leetcode", "leeto")); //-1

    }
}
