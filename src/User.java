/*
 * Markus Bowie mabo1371
 */

import java.util.ArrayList;

public class User {

    private String name;

    private Dog[] ownedDogs = new Dog[0];

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Dog> getOwnedDogs() {
        ArrayList <Dog> dogArrayList = new ArrayList<>();
        for (Dog ownedDog : ownedDogs) {
            dogArrayList.add(ownedDog);
        }
        return dogArrayList;

    }

    public String toString() {
        String s = name + " [";
        for (int i = 0;i < ownedDogs.length; i++) {
            s = s + ownedDogs[i];
            if (i != ownedDogs.length - 1) {
                s = s + ",";
            }
        }
        return s;
    }

    public void addDog(Dog dog) {
        Dog[] newOwnedDogs = new Dog[ownedDogs.length+1];
        for (int i = 0; i < ownedDogs.length; i++) {
            newOwnedDogs[i] = ownedDogs[i];
        }
        newOwnedDogs[newOwnedDogs.length-1] = dog;
        ownedDogs = newOwnedDogs;
    }

    public void removeDog(Dog dog) {

        int dogToRemove = -1;
        for (int i = 0; i < ownedDogs.length; i++) {
            if (ownedDogs[i] == dog) {
                dogToRemove = i;
                break;
            }

        }

        for (int i = dogToRemove+1; i < ownedDogs.length; i++) {
            ownedDogs[i-1] = ownedDogs[i];

        }

        Dog[] newOwnedDogs = new Dog[ownedDogs.length-1];
        for (int i = 0; i < newOwnedDogs.length; i++) {
            newOwnedDogs[i] = ownedDogs[i];
        }

        ownedDogs = newOwnedDogs;

    }

}




