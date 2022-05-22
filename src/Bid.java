/**
 * Markus Bowie mabo1371
 */
public class Bid {

    private int bidAmount;

    private User user;


    public Bid(int bidAmount, User user) {
        this.bidAmount = bidAmount;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public int getBidAmount() {
        return bidAmount;
    }


}


