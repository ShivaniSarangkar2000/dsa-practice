import java.util.Arrays;

public class RemoveDuplicates {

    public int removeDuplicates(int[] nums) {
        int k = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[k] = nums[i];
                k++;
            }
        }

        return k;
    }

    public static void main(String[] args) {
        RemoveDuplicates sol = new RemoveDuplicates();

        int[] nums1 = {1, 1, 2};
        int k1 = sol.removeDuplicates(nums1);
        System.out.println(k1 + ", " + Arrays.toString(Arrays.copyOf(nums1, k1))); // 2, [1, 2]

        int[] nums2 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int k2 = sol.removeDuplicates(nums2);
        System.out.println(k2 + ", " + Arrays.toString(Arrays.copyOf(nums2, k2))); // 5, [0, 1, 2, 3, 4]
    }
}