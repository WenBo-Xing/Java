import java.util.List;

public class BlackJacKRule {

    public static boolean isPlayerOver(Player player){

        List<Card> cardList = player.getHandCards();
        int sum = 0;
        for (Card card:cardList){
            int value = card.getValue();
            sum += value >= 10 ? 10: value;
            if (sum > 21){
                return true;
            }
        }

        return false;
    }
}
