public class EightsHand extends Hand {
    public EightsHand(String name) {
        super(name);
    }

    public int scoreHand() {
        int sum = 0;
        for (int i = 0; i < this.size(); i++) {
            int h = this.getCard(i).getRank();
            if (h > 10) {
                h = 10;
            } else if (h == 8) {
                h = 20;
            }
            sum += h;
        }
        return sum;
    }
}
