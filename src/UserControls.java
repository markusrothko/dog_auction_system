/*
 * Markus Bowie mabo1371
 */

import java.util.ArrayList;

public class UserControls {

    private ArrayList<User> users = new ArrayList<User>();

    private ArrayList<Dog> dogs = new ArrayList<Dog>();

    private ArrayList<Auction> auctions = new ArrayList<Auction>();

    private int auctionNumber = 0;

    private String readName(String prompt, String type) {
        String name = "";
        name = InputHandler.readString(prompt);
        while (name.equals("")) {
            System.out.println("Error: " + type + " can't be empty.");
            name = InputHandler.readString(prompt);
        }
        return name;
    }


    private void sortDogs() {
        for (int i = 0; i < dogs.size() - 1; i++) {
            Dog leftDog = dogs.get(i);
            Dog rightDog = dogs.get(i + 1);
            String leftName = leftDog.getName();
            String rightName = rightDog.getName();
            double leftTail = leftDog.getTailLength();
            double rightTail = rightDog.getTailLength();
            if (Double.compare(leftTail, rightTail) > 0) {

                dogs.set(i, rightDog);
                dogs.set(i + 1, leftDog);

                i = -1;

            }

            if (Double.compare(leftTail, rightTail) == 0) {

                if (leftName.compareTo(rightName) > 0) {

                    dogs.set(i, rightDog);
                    dogs.set(i + 1, leftDog);

                    i = -1;

                }


            }
        }
    }

    public UserControls() {

    }

    public void registerNewUser() {
        String name = readName("Enter the name of the user: ", "name");

        User u = new User(name);
        users.add(u);

        System.out.println(name + " added to the register");

    }

    public void listUsers() {

        if (users.size() > 0) {
            for (User u : users)
                System.out.println(u);

        } else {
            System.out.println("Error: No users in register ");
        }
    }


    public void removeUser() {

        String name = readName("Enter the name of the user: ", "name");

        for (int i = 0; i < users.size(); i++) {

            if (users.get(i).getName().equalsIgnoreCase(name)) {
                cancelBid(users.get(i));
                User u = users.remove(i);
                dogs.removeAll(u.getOwnedDogs());

                System.out.println(name + " has been removed from the register");
                return;
            }
        }
        System.out.println("Error: No such user");
    }

    public void registerDog() {

        String name = readName("Enter the name of the dog: ", "name");

        String breed = readName("Enter the breed of the dog: ", "breed");

        int age = InputHandler.readInt("Enter the age of the dog: ");

        int weight = InputHandler.readInt("Enter the weight of the dog: ");

        Dog d = new Dog(name, breed, age, weight);
        dogs.add(d);

    }

    public void increaseAge() {

        String name = readName("Enter the name of the dog: ", "name");

        for (Dog d : dogs) {

            if (d.getName().equalsIgnoreCase(name)) {

                d.increaseAge();
                System.out.println("The age of " + name + " has been increased");
                return;
            }
        }
        System.out.println("Error: No such dog");
    }

    public void listDogs() {

        if (dogs.size() > 0) {
            int tl = InputHandler.readInt("Enter minimum tail length: ");
            sortDogs();

            for (Dog d : dogs)

                if (tl < d.getTailLength())
                    System.out.println(d.getInfo());

        } else {
            System.out.println("Error: no dogs in register");
        }
    }


    public void removeDog() {

        String name = readName("Enter the name of the dog: ", "name");

        for (int i = 0; i < dogs.size(); i++) {

            if (dogs.get(i).getName().equalsIgnoreCase(name)) {
                Dog d = dogs.remove(i);
                if (d.getOwner() != null) {
                    d.getOwner().removeDog(d);
                }
                for (Auction a : auctions) {
                    if (a.getAuctionDog().equals(d)) {
                        auctions.remove(a);
                        break;
                    }

                }

                System.out.println(name + " has been removed from the list");
                return;
            }
        }

        System.out.println("Error: No such dog");
    }


    public void startAuction() {

        String name = readName("Enter the name of the dog: ", "name");
        boolean hasDog = false;

        for (int i = 0; i < dogs.size(); i++) {
            if (dogs.get(i).getName().equalsIgnoreCase(name)) {
                hasDog = true;

                if (dogs.get(i).getOwner() != null) {
                    System.out.println("Error: Dog has an owner");
                    break;
                }
                if (dogs.get(i).inAuction()) {
                    System.out.println("Error: Dog is already up for auction.");
                    break;
                } else {
                    Auction auction = new Auction(dogs.get(i), auctionNumber);
                    auctions.add(auction);
                    dogs.get(i).setInAuction(true);
                    auctionNumber++;
                    System.out.println(dogs.get(i).getName() + " has been put up for auction in auction #" + auctionNumber);
                    break;
                }
            }
        }
        if (!hasDog) {
            System.out.println("Error: No such dog");
        }
    }


    public void makeBid() {
        boolean hasUser = false;
        boolean hasDog = false;
        String biddingUser = readName("Enter the name of the user: ", "name");

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getName().equalsIgnoreCase(biddingUser)) {
                hasUser = true;
                String biddingDog = readName("Enter the name of the dog: ", "name");
                for (int j = 0; j < auctions.size(); j++) {
                    if (auctions.get(j).getAuctionDog().getName().equalsIgnoreCase(biddingDog)) {
                        hasDog = true;

                        int default_bid = 1;
                        if (auctions.get(j).hasBid()) {
                            default_bid = (auctions.get(j).getPresentTopBid().getBidAmount() + 1);
                        }


                        int intendedBid = InputHandler.readInt("Bid amount (minimum " + default_bid + ")");
                        while (intendedBid < default_bid) {
                            System.out.println("Error: Bid too low.");
                            intendedBid = InputHandler.readInt("Bid amount (minimum " + default_bid + ")");
                        }
                        auctions.get(j).layBid(users.get(i), intendedBid);
                        System.out.println("Bid made.");


                    }

                }
                if (!hasDog) {
                    System.out.println("Error: no such dog currently being auctioned.");
                }
            }
        }
        if (!hasUser) {
            System.out.println("Error: no such user in system.");
        }
    }


    public void closeAuction() {
        boolean hasDog = false;
        Dog temp_dog;
        String dogName = readName("Enter the name of the dog: ", "name");
        for (int i = 0; i < auctions.size(); i++) {
            if (auctions.get(i).getAuctionDog().getName().equalsIgnoreCase(dogName)) {
                if (!auctions.get(i).hasBid()) {

                    return;
                }
                hasDog = true;
                temp_dog = auctions.get(i).getAuctionDog();
                Auction a = auctions.get(i);
                Bid winningBid = a.getPresentTopBid();
                User winningUser = winningBid.getUser();
                winningUser.addDog(temp_dog);
                temp_dog.setOwner(winningUser);
                auctions.remove(a);

                System.out.println(temp_dog.getName() + " has been taken down from auction.");
                System.out.println("The winning bid of " + winningBid.getBidAmount() + " kr was made by " + temp_dog.getOwner().getName() + ".");


                break;
            }
        }

        if (!hasDog) {
            System.out.println("Error: No such dog up for auction.");
        }
    }

    private void cancelBid(User user) {
        for (int i = 0; i < auctions.size(); i++) {
            auctions.get(i).cancelBid(user);
        }
    }


    public void listAuctions() {
        if (auctions.isEmpty()) {
            System.out.println("Error: No auctions in progress.");
            return;

        }

        for (int i = 0; i < auctions.size(); i++) {

            System.out.printf("Auction #" + auctions.get(i).getAuctionNumber() + ": " + auctions.get(i).getAuctionDog().getName() + ". Top bids: ");
            auctions.get(i).printTopThree();
        }


    }

    public void listBids() {
        boolean hasDog = false;
        String dogName = readName("Enter the name of the dog: ", "name");
        for (int i = 0; i < auctions.size(); i++) {
            if (auctions.get(i).getAuctionDog().getName().equalsIgnoreCase(dogName)) {
                auctions.get(i).listAllBids();
                hasDog = true;
            }
        }
        if (!hasDog) {
            System.out.println("Error: no such dog up for auction.");
        }
    }
}
