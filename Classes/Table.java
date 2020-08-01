import java.util.ArrayList;
import java.util.Random;

public class Table {
    Random rand = new Random();
    ArrayList<String> cards = new ArrayList<String>();

    String card1;
    String card2;
    String card3;
    String card4;
    String card5;

    Table(){
        int i1 = rand.nextInt(Cards.remCards.size());
        this.card1 = Cards.remCards.get(i1);
        Cards.remCards.remove(i1);

        i1 = rand.nextInt(Cards.remCards.size());
        this.card2 = Cards.remCards.get(i1);
        Cards.remCards.remove(i1);

        i1 = rand.nextInt(Cards.remCards.size());
        this.card3 = Cards.remCards.get(i1);
        Cards.remCards.remove(i1);

        i1 = rand.nextInt(Cards.remCards.size());
        this.card4 = Cards.remCards.get(i1);
        Cards.remCards.remove(i1);

        i1 = rand.nextInt(Cards.remCards.size());
        this.card5 = Cards.remCards.get(i1);
        Cards.remCards.remove(i1);

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
    }
}
