public class Card {
                    //Card value
    private int value;
    private  Color color;
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    @Override
    public String toString() {
        return "Card{" +
                "value=" + value +
                ", color=" + color +
                '}';
    }
    public String Color(){
        switch (color){
            case SPADE:
                return "SPADE";
            case HEART:
                return "HEART";
            case DIAMOND:
                return "DIAMOND";
            case CLUB:
                return "CLUB";
        }
        return "";
    }
    public String TransValue(){
        if (value <= 10 && value >1){
            return Integer.toString(value);
        }else {
            switch (value) {
                case 1:
                    return "Ace";
                case 11:
                    return "Jack";
                case 12:
                    return "Queen";
                case 13:
                    return "King";
            };

        }
        return "";
    }
}
