import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.List;

public class Game {
    private static Player player;
    private static Dealer dealer;
    private static Deck deck;
    private static Scanner scanner;
    private static Game game;

    public Game(){
        player = new Player();
        player.setMoney(100);
        dealer = new Dealer();
        deck = new Deck();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Game game = new Game();
        System.out.println("Welcome to BlackjJack\nYou have $100");
        Gamechoose();
    }
    public static void Gamechoose(){
        System.out.println("+---------------------------+");
        System.out.println("|                           |");
        System.out.println("|         1.START           |");
        System.out.println("|         2.Balance         |");
        System.out.println("|         3.END GAME        |");
        System.out.println("|                           |");
        System.out.println("+---------------------------+");

        int choice;
        do {
            choice = scanner.nextInt();
            switch (choice){
                case 1:
                    System.out.println("Game beginning");
                    play();
                    break;
                case 2:
                    System.out.println("Balance is : "+ player.getMoney());
                    play();
                    break;
                case 3:
                    System.out.println("Game ending");
                    break;
                default:
                    System.out.println("Please re-enter 1, 2, 3");
            }
        }while (choice>3||choice<1);
    }
    private static void play(){
        deck.shuffle();
        //deck.deal();

        player.clear();
        dealer.clear();
        chooseBet();

        dealer.getHandCard().add(deck.getCard());
        dealer.getHandCard().add(deck.getCard());
        dealer.showcard();

        player.getHandCards().add(deck.getCard());
        player.getHandCards().add(deck.getCard());
        player.showCard();
        boolean end = false;

        while (true){

            if (player.getMoney()<player.getBet()*2){
                System.out.println("Choose what you want");
                System.out.println("1.Card-out;     2.Stop");
                int choice = scanner.nextInt();

                if (choice == 1){
                    player.getHandCards().add(deck.getCard());
                    player.showCard();

                    if (BlackJacKRule.isPlayerOver(player)){
                        playerlose();
                        end = true;
                        break;
                    }
                    else
                        break;
                }
            }else{
                System.out.println("Choose what you want");
                System.out.println("1.Card-In;      2.Double;       3.Stop");
                int choice = scanner.nextInt();

                if (choice == 1){
                    player.getHandCards().add(deck.getCard());
                    player.showCard();

                    if (BlackJacKRule.isPlayerOver(player)){
                        playerlose();
                        end = true;
                        break;
                    }
                }else if (choice == 2) {
                    player.setBet(player.getBet() * 2);
                    player.getHandCards().add(deck.getCard());
                    player.showCard();

                    if (BlackJacKRule.isPlayerOver(player)) {
                        playerlose();
                        end = true;
                        break;
                    } else
                        break;
                }
            }
            if (!end){
                dealer.showCards();
                while (dealer.sumPoints()<17){
                    dealer.getHandCard().add(deck.getCard());
                    dealer.showCards();
                }
                if (dealer.sumPoints() > 21){
                    playerWin();
                }else {
                    realWinner(player,dealer);
                }
            }
           Gamechoose();
        }

    }
    private static void realWinner(Player player,Dealer dealer){
        List<Card> playerCards = player.getHandCards();
        int playerSum = 0;
        for (Card card:playerCards){

            if (card.getValue() == 1){
                playerSum += 11;
            }else if (card.getValue() > 10){
                playerSum += 10;
            }else {
                playerSum += card.getValue();
            }
        }
        while (playerSum > 21){
            playerSum -= 10;
        }
        boolean isPlayerBlackJcak = false;
        if (playerSum == 21 && playerCards.size() == 2){
            isPlayerBlackJcak = true;
        }

        List<Card> dealerCards = dealer.getHandCard();
        int dealerSum = 0;
        for (Card card:playerCards){

            if (card.getValue() == 1){
                dealerSum += 11;
            }else if (card.getValue() > 10){
                dealerSum += 10;
            }else {
                dealerSum += card.getValue();
            }
        }
        while (dealerSum> 21){
            dealerSum -= 10;
        }
        boolean isDealerBlackJack = false;
        if (dealerCards.size() == 2){
            isDealerBlackJack = true;
        }
        if (isPlayerBlackJcak){
            if (isDealerBlackJack){
                NoWinner();
            }else{
                playerWinMore();
            }
        }else {
            if (playerSum > dealerSum){
                playerWin();
            }else if (playerSum == dealerSum){
                NoWinner();
            }else {
                playerlose();
            }
        }

    }
    private static void NoWinner(){
        System.out.println("NoWinner Keep bet");
        System.out.println("Balance is : $" + player.getMoney());
    }
    private static void playerWin(){
        System.out.println("You Win this round");
        int bet = player.getBet();
        int money = player.getMoney() + bet;
        player.setMoney(money);
        System.out.println("You Win: $"+bet+" Balance : $" + money);
    }
    private static void playerWinMore(){
        System.out.println("You win at BlackJack this round ");
        int bet = player.getBet();
        int money = player.getMoney() + bet + bet /2;
        player.setMoney(money);
        System.out.println("You Win: $"+(bet + bet /2)+" Balance : $" + money);

    }
    private static void playerlose(){
        System.out.println("You lose this round");
        int bet = player.getBet();
        int money = player.getMoney() - bet;
        player.setMoney(money);
        System.out.println("You lose: $"+bet+" Balance : $" + money);

        if (player.getMoney() <= 0){
            System.out.println("You lose all money you have,just quit game");

            System.out.println("if you want keep playing choose 1,either back the game menu");
            int choose = scanner.nextInt();
            if (choose == 1){
                System.out.println("System will add $100");
                player.setMoney(100);
                Gamechoose();
            }else {
                Gamechoose();
            }
        }
    }
    private static void chooseBet(){
        System.out.println("Please choose bet: ");
        int setBet;
        do {
            setBet = scanner.nextInt();
            if (setBet > player.getMoney()){
                System.out.println("You don't have to much money,Please re-choose");
            }
            player.setBet(setBet);
        }
        while (setBet > player.getMoney());
    }
}
