import java.util.ArrayList;
import java.util.Iterator;
public class listElement {
    private int min;
    private int max;
    private boolean win;

    public listElement(int c1, int c2, boolean w) {
        if (c1 > c2) {
            this.min = c2;
            this.max = c1;
        } else {
            this.min = c1;
            this.max = c2;
        }

        this.win = w;
    }

    public static ArrayList<listElement> sort(ArrayList<listElement> a) {
        listElement obj = null;

        for(int i = 0; i < a.size() - 1; ++i) {
            int temp = ((listElement)a.get(i)).getMax();
            int index = i;
            obj = (listElement)a.get(i);

            for(int j = i + 1; j < a.size(); ++j) {
                if (temp < ((listElement)a.get(j)).getMax()) {
                    temp = ((listElement)a.get(j)).getMax();
                    index = j;
                    obj = (listElement)a.get(j);
                }
            }

            a.set(index, a.get(i));
            a.set(i, obj);
        }

        return a;
    }

    public static ArrayList<listElement> split(int n, ArrayList<listElement> ar) {
        ArrayList<listElement> temp = new ArrayList();

        for(int i = 0; i < ar.size(); ++i) {
            if (((listElement)ar.get(i)).getMin() == n) {
                temp.add(ar.get(i));
            }
        }

        return temp;
    }

    public static ArrayList combine(ArrayList<listElement> a, ArrayList<listElement> b) {
        Iterator var2 = b.iterator();

        while(var2.hasNext()) {
            listElement l = (listElement)var2.next();
            a.add(l);
        }

        return a;
    }

    public int getMin() {
        return this.min;
    }

    public int getMax() {
        return this.max;
    }

    public boolean getWin() {
        return this.win;
    }

    public String toString() {
        return this.min + "-" + this.max;
    }

    public static void export(ArrayList<listElement> a) {
        ArrayList<listElement> sorted = new ArrayList();

        for(int i = 1; i <= 14; ++i) {
            sorted = combine(sorted, sort(split(i, a)));
        }

        ArrayList<statElement> statistics = new ArrayList();
        int counter = 0;

        while(counter < sorted.size()) {
            statistics.add(new statElement((listElement)sorted.get(counter)));

            while(counter < sorted.size() && ((listElement)sorted.get(counter)).toString().equals(((statElement)statistics.get(statistics.size() - 1)).getHeader())) {
                if (((listElement)sorted.get(counter)).getWin()) {
                    ((statElement)statistics.get(statistics.size() - 1)).addX();
                }

                ++counter;
                ((statElement)statistics.get(statistics.size() - 1)).addN();
            }
        }

        statElement.toTxt(statistics);
    }

}
