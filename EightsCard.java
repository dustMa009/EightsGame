public class EightsCard extends Card {
    public EightsCard (int rank, int suit) {
        super(rank, suit);
    }

    public static boolean match(Card x, Card y) {
        if (x.getRank() == y.getRank() || x.getSuit() == y.getSuit()) {
            return true;
        }
        if (y.getRank() == 8) {
            return true;
        }
        return false;
    }
}
