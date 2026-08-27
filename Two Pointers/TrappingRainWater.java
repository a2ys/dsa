public class TrappingRainWater {
    public static int trap(int[] height) {
        int maxL = 0, maxR = 0;
        int n = height.length;

        int[] maxLeftArray = new int[n];
        int[] maxRightArray = new int[n];

        for (int i = 0; i < n; i++) {
            int j = n - 1 - i;

            maxLeftArray[i] = maxL;
            maxRightArray[j] = maxR;

            maxL = Math.max(maxL, height[i]);
            maxR = Math.max(maxR, height[j]);
        }

        int sum = 0;

        for (int i = 0; i < n; i++) {
            int candidate = Math.min(maxLeftArray[i], maxRightArray[i]);

            if (candidate - height[i] > 0)
                sum += candidate - height[i];
        }

        return sum;
    }

    public static void main(String[] args) {
        int height[] = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };

        System.out.println(trap(height));
    }
}
