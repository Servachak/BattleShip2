import java.util.Scanner;

/**
 * Created by adavi on 07.01.2018.
 */
public class BattleShip2 {

    public static final int RIADKY = 12,STOVPCHYKY = 12;
    public  static  final String IS_EMPTY = " ", ZERO = "  ", SHIP = "@", HIT = "X", MISSED = "O";
    public static String [][] playerField = new String [RIADKY][STOVPCHYKY];
    public static String [][] computerField = new String [RIADKY][STOVPCHYKY];

    public static int ACTIV_PLAYER;

    public static boolean STATUS_GAME = true1
            5;

    public static int STATUS_GAME_CONTINUES = 0,STATUS_GAME_WIN_PLAER = 1, STATUS_GAME_WIN_COMPUTER = 2;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("*****Welcome to Battle Ship Game*****");
        startGame();
    }

    public static void startGame(){

        computerField = battleShipField();
        playerField = battleShipField();


       String [][] computerShip = computerShip();
       proanalizuvatyPole(computerShip);

        System.out.println("Computer posted ships\n NOW Your turn!!!!!!\n");

       String [][] playerShip = playerShip();
       proanalizuvatyPole(playerShip);

       while (STATUS_GAME) {
           computerShot();
           proanalizuvatyPole(playerShip);

           playerShot();
           proanalizuvatyPole(computerShip);
       }

    }

    public static String [][] battleShipField(){

        String [][] field = new String [RIADKY][STOVPCHYKY];

        for (int i = 0; i < field.length; i++){

            for (int j = 0; j < field.length; j++){

                if (i == 0 || i == field.length - 1) {

                    if (j != 0 && j != field.length - 1) {

                        if (field[i][j] == null) {
                            field[i][j] = String.valueOf(j);
                        }
                    }
                    else{
                        field[i][j] = ZERO;
                    }
                }else{
                    if (j != 0 && j != field.length - 1) {

                        if (field[i][j] == null) {
                            field[i][j] = IS_EMPTY;
                        }
                    }
                    else{
                        if (j == 0){
                            field[i][j] = i+ "|";
                        }else{
                            field[i][j] = "|" + i;
                        }
                    }
                }
            }
//            System.out.println();
        }
        return field;
    }

    public static String[][] shipPlacement(int coordinateX, int coordinateY, String[][] field){

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
                shipPlacement(coordinateX, coordinateY, computerField);
            }
            i++;
        }
        return computerField;
    }

    public static String[][] playerShip(){

        int countShip = 1;
        boolean isFalse;

       do {
           System.out.println("Enter X coordinate for your " + countShip + " ship");
           int coordinateX = scanner.nextInt();

           System.out.println("Enter Y coordinate for your " + countShip + " ship");
           int coordinateY = scanner.nextInt();

           if (coordinateX > 0 || coordinateY > 0){
           shipPlacement(coordinateX, coordinateY,playerField);

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

       return playerField;
    }

    public static boolean equalsAnswer(String answer) {
        if (!answer.equalsIgnoreCase("y")) {
            return false;
        }else
            return true;
    }

    public static void proanalizuvatyPole(String [][] array){
        for (int i = 0; i < array.length; i++){

            for (int j = 0; j < array.length; j++){

                System.out.print(array[i][j]);
            }
            System.out.println();
        }
    }

    public static boolean shot(int x, int y, String [][]array){

        boolean shot = true;

        for (int i = 0; i < array.length; i++){

            for (int j = 0; j < array.length; j++){
                if (x == i && y == j){
                    if (array[i][j].equals(SHIP)){
                        array[i][j] = HIT;
                        System.out.println("You hit the ship");
                        shot = true;
                    }if (array[i][j].equals(MISSED)){
                        shot  = true;
                        System.out.println("You've been firing here");
                    }else if (array[i][j].equals(IS_EMPTY)){
                        array[i][j] = MISSED;
                        System.out.println("MISSED");
                        shot = false;
                    }
                }
            }
        }return shot;
    }
    public static void computerShot(){

        boolean shot = true;
        int countShot = 1;
        do{
            System.out.println("Computer " + countShot + " shot");
        int coordinateX = (int) (Math.random() * 10 + 1);
        int coordinateY = (int) (Math.random() * 10 + 1);

        if (coordinateX > 0 || coordinateY > 0) {
            shot = shot(coordinateX,coordinateY,playerField);
        }
        }while(shot);
    }


    public static void playerShot(){

        boolean shotAgain;
        int countShot = 1;
        do {
            System.out.println("Enter X coordinate for " + countShot + " shot");
            int coordinateX = scanner.nextInt();

            System.out.println("Enter Y coordinate for your " + countShot + " shot");
            int coordinateY = scanner.nextInt();

            if (coordinateX > 0 || coordinateY > 0) {
                shotAgain = shot(coordinateX, coordinateY, computerField);

            } else {
                System.out.println("You must place the ship in the range from 0 to 10!\nTry again? Y/N");
                String answer = scanner.next();

                shotAgain = equalsAnswer(answer);
            }
            countShot++;
        }while(shotAgain);
    }

    public static String [][] battle(int x, int y,String [][] array){
        for (int i = 0; i < array.length; i++){

            for (int j = 0; j < array.length; j++){


            }
        }
        return array;
    }
    public static String findWhoWon(){
        return  null;
    }


}
