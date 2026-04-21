public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            if (Character.toLowerCase(s.charAt(left)) !=
                    Character.toLowerCase(s.charAt(right))) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {
        ValidPalindrome palindrome = new ValidPalindrome();

        // Test cases
        System.out.println(palindrome.isPalindrome("A man, a plan, a canal: Panama")); // true
        System.out.println(palindrome.isPalindrome("race a car"));                      // false
        System.out.println(palindrome.isPalindrome(" "));                               // true
        System.out.println(palindrome.isPalindrome("0P"));                              // false
    }
}
