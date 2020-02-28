import java.util.Scanner;
import java.util.ArrayList;

/**
 * Simulates a game of Crazy Eights.  See
 * https://en.wikipedia.org/wiki/Crazy_Eights
 * for basic play and scoring rules.
 */
public class Eights {

    private ArrayList<Player> players;
    private Hand drawPile;
    private Hand discardPile;
    private Scanner in;

    /**
     * Initializes the state of the game.
     */
    public Eights(String[] names) {
        Deck deck = new Deck("Deck");
        deck.shuffle();
        players = new ArrayList<Player>();

        // deal cards to each player
        int handSize = 5;
        for (int i = 0; i < names.length; i++) {
            Player a = new Player(names[i]);
            deck.deal(a.getHand(), handSize);
            this.players.add(a);
        }

        // turn one card face up
        discardPile = new Hand("Discards");
        deck.deal(discardPile, 1);

        // put the rest of the deck face down
        drawPile = new Hand("Draw pile");
        deck.dealAll(drawPile);

        // create the scanner we'll use to wait for the user
        in = new Scanner(System.in);
    }

    /**
     * Returns true if either hand is empty.
     */
    public boolean isDone() {
        for (Player i : players) {
            if (i.getHand().empty()) {
                return true;
            }
        }
        return false;

    }

    /**
     * Moves cards from the discard pile to the draw pile and shuffles.
     */
    public void reshuffle() {
        // save the top card
        Card prev = discardPile.popCard();

        // move the rest of the cards
        discardPile.dealAll(drawPile);

        // put the top card back
        discardPile.addCard(prev);

        // shuffle the draw pile
        drawPile.shuffle();
    }

    /**
     * Returns a card from the draw pile.
     */
    public Card draw() {
        if (drawPile.empty()) {
            reshuffle();
        }
        return drawPile.popCard();
    }

    /**
     * Switches players.
     */
    public Player nextPlayer(Player current) {
        int index = this.players.indexOf(current);
        index = (index + 1) % this.players.size();
        return this.players.get(index);
    }

    /**
     * Displays the state of the game.
     */
    public void displayState() {
        for (Player i : players) {
            i.display();
        }
        discardPile.display();
        System.out.print("Draw pile: ");
        System.out.println(drawPile.size() + " cards");
    }

    /**
     * Waits for the user to press enter.
     */
    public void waitForUser() {
        in.nextLine();
    }

    /**
     * One player takes a turn.
     */
    public void takeTurn(Player player) {
        Card prev = discardPile.last();
        Card next = player.play(this, prev);
        discardPile.addCard(next);

        System.out.println(player.getName() + " plays " + next);
        System.out.println();
    }

    /**
     * Plays the game.
     */
    public void playGame() {

        Player player = this.players.get(0);

        // keep playing until there's a winner
        while (!isDone()) {
            displayState();
            waitForUser();
            takeTurn(player);
            player = nextPlayer(player);
        }

        // display the final score
        //one.displayScore();
        //two.displayScore();
        for (Player i : players) {
            i.displayScore();
        }
    }

    /**
     * Creates the game and runs it.
     */
    public static void main(String[] args) {
        String[] names = {"allen", "mark", "fuck"};
        Eights game = new Eights(names);
        game.playGame();
    }

}