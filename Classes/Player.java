import java.util.ArrayList;
import java.util.Random;

public class Player {
    Random rand = new Random();
    ArrayList<String> cards = new ArrayList<String>();

    String card1;
    String card2;

    Player(){
        int i1 = rand.nextInt(Cards.remCards.size());
        this.card1 = Cards.remCards.get(i1);
        Cards.remCards.remove(i1);

        i1 = rand.nextInt(Cards.remCards.size());
        this.card2 = Cards.remCards.get(i1);
        Cards.remCards.remove(i1);

        cards.add(card1);
        cards.add(card2);
    }
}
