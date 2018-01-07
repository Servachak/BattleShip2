import java.util.Scanner;

/**
 * Created by adavi on 07.01.2018.
 */
public class BattleShip2 {

    public static final int RIADKY = 12,STOVPCHYKY = 12;
    public  static  final String ISEMPTY = "  ", SHIP = "@", SHOT = "X";
    public static String [][] field = new String [RIADKY][STOVPCHYKY];
    public static int ACTIV_PLAYER;

    public static int STATUC_GAME;

    public static int STATUC_GAME_CONTINUES = 0,STATUC_GAME_WIN_PLAER = 1, STATUC_GAME_WIN_COMPUTER = 2;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

    }

    public static void startGame(){}

    public static String [][] battleShipField(){

        for (int i = 0; i < field.length; i++){

            for (int j = 0; j < field.length; j++){

                if (i == 0 || i == field.length - 1) {

                    if (j != 0 && j != field.length - 1) {

                        if (field[i][j] == null) {
                            field[i][j] = String.valueOf(j);
                        }
                    }
                    else{
                        field[i][j] = ISEMPTY;
                    }
                }else{
                    if (j != 0 && j != field.length - 1) {

                        if (field[i][j] == null) {
                            field[i][j] = ISEMPTY;
                        }
                    }
                    else{
                        if (j == 0){
                            field[i][j] = i+ "|";
                        }else{
                            field[i][j] = "|" + i;
                        }
                    }
                } System.out.print(field[i][j]);
            }
            System.out.println();
        }
        return field;
    }
    public static String[][] shipPlacement(int coordinateX, int coordinateY){
        System.out.println();

                for (int i = 0; i < field.length; i++) {

                    for (int j = 0; j < field.length; j++) {

                        if (i == coordinateX && j == coordinateY) {
                            field[i][j] = SHIP;
                        }
                        if (field[i][j].equals(SHIP)) {
                            field[i][j] = SHIP;
                        }
                    }
                }
       return field;
    }

    public static String[][] computerShip(){

        int i = 1;
        while (i < 6) {

            int coordinateX = (int) (Math.random() * 10 + 1);
            int coordinateY = (int) (Math.random() * 10 + 1);

            if (coordinateX > 0 || coordinateY > 0) {
                shipPlacement(coordinateX, coordinateY);
            }
            i++;
        }

        return field;
    }

    public static String[][] playerShip(){

        System.out.println();
        int countShip = 1;
        boolean isFalse;

       do {
           System.out.println("Enter X coordinate for your " + countShip + " ship");
           int coordinateX = scanner.nextInt();

           System.out.println("Enter Y coordinate for your " + countShip + " ship");
           int coordinateY = scanner.nextInt();

           if (coordinateX > 0 || coordinateY > 0){
           shipPlacement(coordinateX, coordinateY);

           System.out.println("Can add more? Y/N");
           String answer = scanner.next();

           isFalse = equalsAnswer(answer);
       }else{
            System.out.println("You must place the ship in the range from 0 to 10!\nTry again? Y/N");
            String answer = scanner.next();

            isFalse = equalsAnswer(answer);
        }
        countShip++;


       }while(isFalse);

       return field;
    }

    public static boolean equalsAnswer(String answer) {
        if (!answer.equalsIgnoreCase("y")) {
            return false;
        }else
            return true;
    }

    public static void proanalizuvatyPole(){}
    public static String findWhoWon(){}


}
