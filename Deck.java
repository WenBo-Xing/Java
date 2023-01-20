import java.util.Random;
import static java.util.Collections.swap;
public class Deck {
    private Card[] cards;
    private Integer index;
    private Random random;
    //Integer Card List
    public Deck(){
        random = new Random();
        index = 0;
        cards = new Card[52];
        for (int i = 0; i< 52;i++){
            cards[i] = new Card();
            cards[i].setValue(i % 13 +1);
            switch (i /13){
                case 0:
                    cards[i].setColor(Color.HEART);
                    break;
                case 1:
                    cards[i].setColor(Color.CLUB);
                    break;
                case 2:
                    cards[i].setColor(Color.SPADE);
                case 3:
                    cards[i].setColor(Color.DIAMOND);
            }
        }
    }//shuffle
    public void shuffle(){
        for (int i = 1; i<=52;i++){
            int randID = random.nextInt(i);
            swap(randID,i-1);
        }
        index = 0;
    }
    public Card getCard (){
        if (index == 52){
            return  null;
        }
        return cards[index++];
    }
    /*
    * public Card deal(){
        if(index == 1){
            System.out.println("No Cards\n");
        }
        index -= 1;
        return cards[index];

    }
    * */

    private void swap(int randID, int i) {
        Card temp = cards[i];
        cards[i] = cards[randID];
        cards[randID] = temp;

    }
    public Card[] getCards(){
        return cards;
    }
}
