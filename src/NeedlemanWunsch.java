public class NeedlemanWunsch {

    private static String seq_1 = "";
    private static String seq_2 = "";
    private float match;
    private float mismatch;
    private float gap;

    public NeedlemanWunsch(String seq_1, String seq_2, float match, float mismatch, float gap) {
        this.seq_1 = seq_1;
        this.seq_2 = seq_2;
        this.match = match;
        this.mismatch = mismatch;
        this.gap = gap;

        System.out.println("Sequence 1: " + seq_1);
        System.out.println("Sequence 2: " + seq_2);

        printScoreMatrix();
    }

    public float[][] initializeMatrix() {
        int rows = seq_1.length() + 1;
        int columns = seq_2.length() + 1;

        float[][] scoreMatrix = new float[rows][columns];

        for (int j=0; j<columns; j++) {
            scoreMatrix[0][j] = j * gap;
        }


        for (int i=0; i<rows; i++) {
            scoreMatrix[i][0] = i * gap;
        }

        return scoreMatrix;

    }

    public float[][] getFilledMatrix() {
         float upScore;
         float leftScore;
         float diagScore;
         float maxScore;

         float[][] scoreMatrix = initializeMatrix();
         int rows = seq_1.length() + 1;
         int columns = seq_2.length() + 1;

         for (int i=1; i<rows; i++) {
             for (int j=1; j<columns; j++) {
                 upScore = scoreMatrix[i-1][j] + gap;
                 leftScore = scoreMatrix[i][j-1] + gap;
                 if(seq_1.charAt(i-1) == seq_2.charAt(j-1)) {
                     diagScore = scoreMatrix[i-1][j-1] + match;
                 } else {
                     diagScore = scoreMatrix[i-1][j-1] + mismatch;
                 }

                 maxScore = Math.max(upScore, Math.max(leftScore,diagScore));
                 scoreMatrix[i][j] = maxScore;

             }
         }

         return scoreMatrix;
    }

    public void printScoreMatrix() {
        int rows = seq_1.length() + 1;
        int columns = seq_2.length() + 1;

        float[][] scoreMatrix = getFilledMatrix();
        System.out.println("The Score Matrix");

        for (int i = 0; i <= seq_2.length(); i++) {
            if (i == 0) {
                System.out.print("\t\t\t");
            } else {
                System.out.print(seq_2.charAt(i - 1) + "\t\t ");
            }
        }

        System.out.println();

        for (int i = 0; i < rows; i++) {
            if (i == 0) {
                System.out.print("\t");
            } else {
                System.out.print(seq_1.charAt(i - 1) + "\t");
            }
            for (int j = 0; j < columns; j++) {
                float cellValue = scoreMatrix[i][j];
                System.out.print(String.format("%.2f\t", cellValue));
            }
            System.out.println();
        }

        System.out.println("\nBest score: " +scoreMatrix[rows-1][columns-1]);
    }

    public static void main(String[] args) {

        String seq1 = "GAT";
        String seq2 = "GCAT";
        float match = 1;
        float mismatch = -1;
        float gap = -1;

        NeedlemanWunsch obj1 = new NeedlemanWunsch(seq1, seq2, match, mismatch, gap);
        System.out.println(OptimalAlignment.getOptimalAlignment(obj1.getFilledMatrix(), seq1, seq2, match, mismatch, gap));

    }


}
