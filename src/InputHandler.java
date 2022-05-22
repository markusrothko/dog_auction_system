/*
 * Markus Bowie mabo1371
 */

import java.util.Scanner;

public class InputHandler {

    private static Scanner input = new Scanner(System.in);

    public static int readInt(String prompt) {
        System.out.println(prompt);
        int no = input.nextInt();
        input.nextLine();
        return no;
    }

    public static String readString(String prompt) {
        System.out.println(prompt);
        return input.nextLine().trim().toLowerCase();
    }



}