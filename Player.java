import java.util.ArrayList;
import java.util.List;

public class Player {

    private List<Card> HandCards;

    private Integer money;
    private Integer bet;

    public Player(){
        money = 100;
        bet = 0;
        HandCards = new ArrayList<>();
    }

    public Integer getMoney() {
        return money;
    }

    public Integer getBet() {
        return bet;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public void setBet(Integer bet) {
        this.bet = bet;
    }

    public List<Card> getHandCards() {
        return HandCards;
    }

    public void setHandCards(List<Card> handCards) {
        HandCards = handCards;
    }

    public int showCard(){
        System.out.println("Your Cards are: Point:"+getSumPoint()+"\n");
        int i = 1;
        for (Card card: HandCards){
            System.out.println(i++ +"."+card.Color()+" "+card.TransValue());
        }
        return i;
    }
    public int getSumPoint(){
        int sum = 0;
        for (Card card:HandCards){
            sum += (card.getValue() > 10) ? 10 : card.getValue();
        }
        return sum;

    }
    public void clear(){
        HandCards = new ArrayList<>();
    }
}
