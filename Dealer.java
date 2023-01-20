import java.util.ArrayList;
import java.util.List;

public class Dealer {
    private List<Card> HandCard;

    public Dealer(){
        HandCard = new ArrayList<>();
    }
    public List<Card> getHandCard(){
        return HandCard;
    }

    public void setHandCard(List<Card> handCard) {
        HandCard = handCard;
    }
    public void showcard(){
        System.out.println("The Dealer hand-card is :"+sumPoints()+"\n");
        int i = 1;
        System.out.println("1."+"Unknown");
        System.out.println("2. "+HandCard.get(1).Color()+" 1"+HandCard.get(1).TransValue());
    }
   public void showCards(){
       System.out.println("The Dealer Hand-Cards are :Point \n : "+getHandCard());
       int i = 1;
       for (Card card:HandCard){
           System.out.println(i++ + "."+ card.Color()+" "+card.TransValue());
       }
   }
   public void clear(){
        HandCard = new ArrayList<>();
   }
   public int sumPoints(){
        int sum = 0;
        for (Card card: HandCard){
            sum += card.getValue() > 10 ? 10: card.getValue();
        }
        return sum;
   }
}
