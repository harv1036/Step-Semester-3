class BmiCalculator {

    static String getBmiStatus(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal";
        } else if (bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    static void printWellnessReport(double[] heights, double[] weights) {
        System.out.println("Person | Height (m) | Weight (kg) | BMI | Status");
        for (int i = 0; i < heights.length; i++) {
            double bmi = weights[i] / (heights[i] * heights[i]);
            String status = getBmiStatus(bmi);
            System.out.println("Person " + (i + 1) + " | " + heights[i] + " | " + weights[i]
                    + " | " + String.format("%.2f", bmi) + " | " + status);
        }
    }

    public static void main(String[] args) {
        double[] heights = {1.75, 1.60, 1.68, 1.80, 1.55};
        double[] weights = {70, 90, 55, 95, 48};

        printWellnessReport(heights, weights);
    }
}
