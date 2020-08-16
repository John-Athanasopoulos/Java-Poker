import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Random rand = new Random();
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Integer> nums = new ArrayList<Integer>();
    static ArrayList<Character> chars = new ArrayList<Character>();
    static ArrayList<String> cardsToChange = new ArrayList<String>();

    static int[] playerCards = {0,0,0,0,0,0,0,0,0};
    static int[] computerCards = {0,0,0,0,0,0,0,0,0};

    static int moneyOnTheBet = 0;
    static int playerMoney = 1000;
    static int computerMoney = 1000;

    public static void main(String... args){
        System.out.println("    This is a game of Poker created by Atermon!");
        System.out.println("               Hope you enjoy :)");
        System.out.println("---------------------Welcome!---------------------");
        //Making the deck
        for(int i=1; i <= 13; i++){
            nums.add(i);
        }
        chars.add('H');
        chars.add('C');
        chars.add('S');
        chars.add('D');

        String a = "";

        for(char c : chars){
            for(int n : nums){
                a = Integer.toString(n);
                Cards.cards.add(c + a);
            }
        }
        Cards.remCards = Cards.cards; //cards is the deck, remCards are the currently remaining cards.
        //Finished the deck
        //Creation of player and the computer
        Player player = new Player();
        Player computer = new Player();
        Table table = new Table();

        while(playerMoney > 0 && computerMoney > 0) {
            System.out.println("------------------Start of Round!------------------");
            player.cards.addAll(table.cards);
            computer.cards.addAll(table.cards);
            standardBet();
            changeCards(player);
            betMore();
            check(player, computer);
            Cards.cards = new ArrayList<>();
            for(char c : chars){
                for(int n : nums){
                    a = Integer.toString(n);
                    Cards.cards.add(c + a);
                }
            }
            Cards.remCards = Cards.cards;
            moneyOnTheBet = 0;
            player = new Player();
            computer = new Player();
            table = new Table();
            System.out.println("-------------------End of Round!-------------------");
            System.out.println(playerMoney);
            System.out.println(computerMoney);
        }
        endGame();
    }

    static void endGame(){
        Scanner sc = new Scanner(System.in);
        if(playerMoney <= 0){
            System.out.println("You have lost all your money and cannot continue further...");
            System.out.println("Press Enter to exit.");
            String f = sc.next();
            System.exit(0); // Exit the program
        }
        else if(computerMoney <= 0){
            System.out.println("The computer has lost all its money. Congrats! You win!");
            System.out.println("Press Enter to exit.");
            String f = sc.next();
            System.exit(0); // Exit the program
        }
    }

    static void standardBet(){
        playerMoney -= 100;
        computerMoney -= 100;
        endGame();
        moneyOnTheBet +=200;
    }

    static void betMore(){
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);
        System.out.println("If you want to bet more you can enter a number (0-"+playerMoney+")");
        int d = sc.nextInt();
        while(d > playerMoney){
            System.out.println("You don't have that much money! Enter a number again:");
            d = sc.nextInt();
        }
        playerMoney -= d;
        computerMoney -= d;
        moneyOnTheBet += 2*d;
        if(computerMoney < 0){
            System.out.println("The computer has lost all its money. Congrats! You win!");
            System.out.println("Press Enter to exit.");
            String f = sc.next();
            System.exit(0); // Exit the program
        }
        int r = rand.nextInt(10);
        if(r%4 == 0 && computerMoney >= 50){ //30% possibility (for cases when r is equal to 0, 4 or 8
            System.out.println("The computer decided to bet 50 more.");
            playerMoney -= 50;
            computerMoney -= 50;
            moneyOnTheBet += 100;
            if(playerMoney < 0){
                System.out.println("You have lost all your money and cannot continue further...");
                System.out.println("Press Enter to exit.");
                String f = sc.next();
                System.exit(0); // Exit the program
            }
            betMore();
        }
    }

    static void check(Player player, Player computer){
        playerCards[0] = Hands.straightFlush(player.cards);
        playerCards[1] = Hands.fourOfAKind(player.cards);
        playerCards[2] = Hands.fullHouse(player.cards);
        playerCards[3] = Hands.flush(player.cards);
        playerCards[4] = Hands.straight(player.cards);
        playerCards[5] = Hands.threeOfAKind(player.cards);
        playerCards[6] = Hands.twoPair(player.cards);
        playerCards[7] = Hands.pair(player.cards);
        playerCards[8] = Hands.highCard(player.cards);
        computerCards[0] = Hands.straightFlush(computer.cards);
        computerCards[1] = Hands.fourOfAKind(computer.cards);
        computerCards[2] = Hands.fullHouse(computer.cards);
        computerCards[3] = Hands.flush(computer.cards);
        computerCards[4] = Hands.straight(computer.cards);
        computerCards[5] = Hands.threeOfAKind(computer.cards);
        computerCards[6] = Hands.twoPair(computer.cards);
        computerCards[7] = Hands.pair(computer.cards);
        computerCards[8] = Hands.highCard(computer.cards);
        System.out.println(Arrays.toString(playerCards));
        System.out.println(Arrays.toString(computerCards));

        for(int i = 0; i < 9; i++){
            if(playerCards[i] > computerCards[i]){
                System.out.println("You win!");
                playerMoney += moneyOnTheBet;
                break;
            }
            else if (playerCards[i] < computerCards[i]){
                System.out.println("You lose!");
                computerMoney += moneyOnTheBet;
                break;
            }
            else{
                if(i == 8) {
                    System.out.println("It's a tie!");
                    playerMoney += moneyOnTheBet / 2;
                    computerMoney += moneyOnTheBet / 2;
                }
            }
        }
    }

    static void changeCards(Player p){
        System.out.println("Your cards are: " + p.cards);
        System.out.println("How many cards do you want to change? (Permitted: 0-2)");
        int per = sc.nextInt();
        while(!(per >=0 && per <=2)){
            System.out.println("The number of cards you can change is 0 to 2.");
            System.out.println("How many cards do you want to change? (Permitted: 0-2)");
            per = sc.nextInt();
        }

        for(int i = 1; i <= per; i++) {
            boolean b = true;
            while(b) {
                int counter = 0;
                if(i == 1) {
                    System.out.println("To change a card, type its type and number (ex. H1 or D12).");
                }
                else{
                    System.out.println("Pick a card:");
                }
                String ctc = sc.next();
                for (String s : p.cards) {
                    if (s.equals(ctc)) {
                        int i1 = rand.nextInt(Cards.remCards.size());
                        cardsToChange.add(Cards.remCards.get(i1));
                        b = false;
                        break;
                    }
                    else if (counter == 6 && s.equals(p.cards.get(p.cards.size() - 1))) {
                        System.out.println("This card doesn't exist in your deck");
                    }
                    counter++;
                }
                if(!b){
                    p.cards.remove(ctc);
                    Cards.remCards.add(ctc);
                    p.cards.add(cardsToChange.get(0));
                    Cards.remCards.remove(cardsToChange.get(0));
                    cardsToChange.remove(cardsToChange.get(0));
                }
            }
        }
        System.out.println("Your cards after the changes are: " + p.cards + "\n");
    }
}
