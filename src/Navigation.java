/*
 * Markus Bowie mabo1371
 */

public class Navigation {

    private UserControls usercontrols = new UserControls();

    public void welcomeMessage() {
        System.out.println("\nWelcome to Our Dog Years Inc\n\nThe available commands are: \n\n\nDisplay commands\nRegister new dog\nIncrease age\nList dogs\nRemove dog\nRegister new user\nList users\nRemove user\nStart auction\nList auctions\nList bids\nMake bid\nClose auction\nExit\n\n");

    }

    public void displayCommands() {
        System.out.println("The available commands are: \n\n\nDisplay commands\nRegister new dog\nIncrease age\nList dogs\nRemove dog\nRegister new user\nList users\nRemove user\nStart auction\nList auctions\nList bids\nMake bid\nClose auction\nExit\n\n");

    }

    public void exitMessage() {
        System.out.println("Thank you for your visit.\nHave a nice day!");

    }

    public void auctionMenu() {

        boolean shouldExit = false;

        while (shouldExit == false) {

            String command = InputHandler.readString("Enter command: ");

            switch (command.toLowerCase()) {

                case "register new dog":
                    System.out.println("You have entered the command register new dog");
                    usercontrols.registerDog();

                    break;
                case "increase age":
                    System.out.println("You have entered the command increase age");
                    usercontrols.increaseAge();
                    break;
                case "list dogs":
                    System.out.println("You have entered the command list dogs");
                    usercontrols.listDogs();
                    break;
                case "remove dog":
                    System.out.println("You have entered the command remove dog");
                    usercontrols.removeDog();
                    break;
                case "register new user":
                    System.out.println("You have entered the command register new user");
                    usercontrols.registerNewUser();
                    break;
                case "list users":
                    System.out.println("You have entered the command list users");
                    usercontrols.listUsers();
                    break;
                case "remove user":
                    System.out.println("You have entered the command remove user");
                    usercontrols.removeUser();
                    break;
                case "start auction":
                    System.out.println("You have entered the command start auction");
                    usercontrols.startAuction();
                    break;
                case "list auctions":
                    System.out.println("You have entered the command list auctions");
                    usercontrols.listAuctions();
                    break;
                case "list bids":
                    System.out.println("You have entered the command list bids");
                    usercontrols.listBids();
                    break;
                case "make bid":
                    System.out.println("You have entered the command make bid");
                    usercontrols.makeBid();
                    break;
                case "close auction":
                    System.out.println("You have entered the command close auction");
                    usercontrols.closeAuction();
                    break;
                case "exit":
                    System.out.println("Exited");
                    shouldExit = true;

                    break;
                default:
                    System.out.println("Error: Unrecognized command");
                    break;
            }
        }
    }
}

