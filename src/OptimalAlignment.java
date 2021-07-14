public class OptimalAlignment {

    public static String getOptimalAlignment(float[][] scoreMatrix, String seq_1, String seq_2, float match, float mismatch, float gap) {
        int rows = seq_1.length();
        int column = seq_2.length();

        StringBuilder alignment1_Builder = new StringBuilder();
        StringBuilder alignment2_Builder = new StringBuilder();

        seq_1 = "-" + seq_1;
        seq_2 = "-" + seq_2;

        while (rows != 0 && column != 0) {
            if (scoreMatrix[rows][column] == (scoreMatrix[rows][column - 1] + gap)) {
                alignment1_Builder.insert(0, "-");
                alignment2_Builder.insert(0, seq_2.charAt(column));
                column--;
            } else if (scoreMatrix[rows][column] == (scoreMatrix[rows - 1][column - 1] + (seq_1.charAt(rows) == seq_2.charAt(column) ? match : mismatch))) {
                alignment1_Builder.insert(0, seq_1.charAt(rows));
                alignment2_Builder.insert(0, seq_2.charAt(column));
                rows--;
                column--;
            } else if (scoreMatrix[rows][column] == (scoreMatrix[rows - 1][column] + gap)){
                alignment1_Builder.insert(0, seq_1.charAt(rows));
                alignment2_Builder.insert(0, "-");
                rows--;
            }
        }


        System.out.println("The Best Alignment: ");
        return alignment1_Builder + "\n" +alignment2_Builder;
    }
}
