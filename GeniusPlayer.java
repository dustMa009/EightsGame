public class GeniusPlayer extends Player {

    public GeniusPlayer(String name) {
        super(name);
    }

    public Card play(Eights eights, Card prev) {
        Card card = searchForMatch(prev);
        if (card == null) {
            card = drawForMatch(eights, prev);
        }
        return card;
    }

    public Card searchForMatch(Card prev) {
        int k = -1;
        for (int i = 0; i < this.getHand().size(); i++) {
            Card card = this.getHand().getCard(i);
            if (card.getRank() == 8) {
                k = i;
                break;
            }
            if (cardMatches(card, prev)) {
                if (k == -1) {
                    k = i;
                } else {
                    int a = this.getHand().getCard(k).getRank();
                    if (a == 8 || card.getRank() > a) {
                        k = i;
                    }
                }
            }
        }
        if (k == -1) {
            return  null;
        } else {
            return this.getHand().popCard(k);
        }
    }
}
