import java.util.HashMap;

public class IsomorphicStrings {

    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> sToT = new HashMap<>();
        HashMap<Character, Character> tToS = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);

            if (sToT.containsKey(sc) && sToT.get(sc) != tc) return false;
            if (tToS.containsKey(tc) && tToS.get(tc) != sc) return false;

            sToT.put(sc, tc);
            tToS.put(tc, sc);
        }

        return true;
    }

    public static void main(String[] args) {
        IsomorphicStrings sol = new IsomorphicStrings();

        System.out.println(sol.isIsomorphic("egg", "add"));    // true
        System.out.println(sol.isIsomorphic("f11", "b23"));    // false
        System.out.println(sol.isIsomorphic("paper", "title")); // true
        System.out.println(sol.isIsomorphic("badc", "baba"));  // false
    }
}