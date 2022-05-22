/*
 * Markus Bowie mabo1371
 */


public class Dog {

    private String name;

    private String breed;

    private int age;

    private int weight;

    private User owner = null;

    private boolean inAuction = false;

    public Dog(String name, String breed, int age, int weight) {

        this.name = name;
        this.breed = breed;
        this.age = age;
        this.weight = weight;
    }

    public boolean inAuction() {
        return inAuction;
    }

    public void setInAuction(boolean inAuction) {
        this.inAuction = inAuction;
    }

    public String getName() {
        return name;
    }


    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }


    public double getTailLength() {
        if (breed.equalsIgnoreCase("tax") || breed.equalsIgnoreCase("dachshund")) {
            return 3.70;
        } else {
            return (age * weight) / 10.0;
        }

    }

    public String toString() {
        return name;
    }

    public String getInfo() {
        String ownerString = "";
        if (owner != null) {
            ownerString += "Owned by: " + owner.getName();
        }
        return name + " " + breed + " " + age + " " + weight + " " + getTailLength() + " " + ownerString;

    }

    public void increaseAge() {
        age++;
    }


}

