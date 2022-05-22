/*
 * Markus Bowie mabo1371
 */

import java.util.ArrayList;
import java.util.List;

public class Auction {

    private List<Bid> bids = new ArrayList<>();

    private Dog dog;

    private int auctionNumber;

    public Auction(Dog dog, int auctionNumber) {

        this.dog = dog;
        this.auctionNumber = auctionNumber;
    }

    public Dog getAuctionDog() {
        return dog;
    }

    public int getAuctionNumber() {
        return auctionNumber + 1;
    }

    public void printTopThree() {
        System.out.printf("[");
        for (int i = bids.size() - 1; i >= bids.size() - 3 && i >= 0; i--) {
            Bid bid = bids.get(i);
            System.out.printf(bid.getUser().getName() + " " + bid.getBidAmount() + " kr, ");
        }
        System.out.println("]");
    }


    public void layBid(User user, int intendedBid) {
        if (hasBid() && intendedBid < getPresentTopBid().getBidAmount()) {
            System.out.println("Error: Bid too low.");
            return;
        }

        cancelBid(user);

        Bid new_bid = new Bid(intendedBid, user);
        bids.add(new_bid);
    }

    public Bid getPresentTopBid() {
        int highestIndex = bids.size() - 1;
        return bids.get(highestIndex);
    }

    public boolean hasBid() {
        if (bids.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public void cancelBid(User user) {
        for (int i = 0; i < bids.size(); i++) {
            if (bids.get(i).getUser().getName().equalsIgnoreCase(user.getName())) {
                bids.remove(bids.get(i));
                break;
            }
        }
    }

    public void listAllBids() {
        for (int i = bids.size() - 1; i >= 0; i--) {
            Bid bid = bids.get(i);
            System.out.println("User: " + bid.getUser().getName() + " " + bid.getBidAmount() + " kr");
        }
    }

}









