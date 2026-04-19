public class PalindromeNumber {

    public boolean isPalindrome(int x) {
        // negative numbers and numbers ending in 0 (except 0 itself) are not palindromes
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;

        int reversed = 0;
        while (x > reversed) {
            reversed = reversed * 10 + x % 10;
            x /= 10;
        }

        // even length: x == reversed
        // odd length:  x == reversed / 10 (middle digit doesn't matter)
        return x == reversed || x == reversed / 10;
    }

    public static void main(String[] args) {
        PalindromeNumber sol = new PalindromeNumber();

        System.out.println(sol.isPalindrome(121));  // true
        System.out.println(sol.isPalindrome(-121)); // false
        System.out.println(sol.isPalindrome(10));   // false
        System.out.println(sol.isPalindrome(0));    // true
        System.out.println(sol.isPalindrome(1221)); // true
    }
}