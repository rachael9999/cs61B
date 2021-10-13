public class drawTriangle{
    public static void main(String[] args) {
        int col = 0;
        int row = 0;
        int SIZE = 5;
        while (row <SIZE) {
            while (col <= row) {
                System.out.print('*');
                col = col + 1;
            }
            while (col > row) {
                System.out.println();
                col = 0;
                row = row + 1;
            }
        }
        System.out.println();
    }

}