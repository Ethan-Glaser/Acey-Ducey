import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class simulation {
    static Integer[] cards = new Integer[52];
    static ArrayList<listElement> chart = new ArrayList();
    static int index = 0;
    static int simulations = 0;
    public static void main(String[] args) {
        if(args.length < 1) {
            System.out.println("Must Enter number of simulations");
        }
        else {
            System.out.println(LocalTime.now());
            System.out.println("running");
            fillArray();
            shuffleCards();
            int numSims = parseInt(args[0]);
            while (simulations < numSims) {
                int card1 = deal();
                int card2 = deal();
                if (card1 == 1) {
                    card1 = ace();
                }

                if (card2 == 1) {
                    card2 = 14;
                }

                if (shouldBet(card1, card2)) {
                    int card3 = deal();
                    boolean win = check(card1, card2, card3);
                    chart.add(new listElement(card1, card2, win));
                }
            }

            listElement.export(chart);
            System.out.println(LocalTime.now());
            System.out.println("done");
        }
    }

    static void fillArray() {
        for(int i = 1; i <= 13; ++i) {
            for(int j = 0; j < 4; ++j) {
                cards[index] = i;
                ++index;
            }
        }

        index = -1;
    }

    static void shuffleCards() {
        for(int i = 0; i < 5000; ++i) {
            int i1 = 0;

            int i2;
            for(i2 = 0; i1 == i2; i2 = (int)(Math.random() * 52.0D)) {
                i1 = (int)(Math.random() * 52.0D);
            }

            int temp = cards[i1];
            cards[i1] = cards[i2];
            cards[i2] = temp;
        }

    }

    static int deal() {
        if (index == 51) {
            ++simulations;
            shuffleCards();
            index = -1;
        }

        ++index;
        return cards[index];
    }

    static boolean shouldBet(int a, int b) {
        if (Math.abs(a - b) == 2) {
            return shoot();
        } else {
            return (double)(Math.abs(a - b) - 1) / 13.0D >= (double)((int)(Math.random() * 21.0D + 40.0D)) / 100.0D;
        }
    }

    static boolean shoot() {
        int shootPercent = 100;
        return (int)(Math.random() * 100.0D) < shootPercent;
    }

    static boolean check(int a, int b, int c) {
        if (a > b) {
            int temp = a;
            a = b;
            b = temp;
        }

        return c < b && c > a;
    }

    static int ace() {
        return (int)(Math.random() * 2.0D) == 0 ? 1 : 14;
    }

}
