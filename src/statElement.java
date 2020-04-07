import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;

public class statElement {
    private String header;
    private int x;
    private int n;

    public statElement(listElement l) {
        this.header = l.toString();
    }

    public String getHeader() {
        return this.header;
    }

    public int getX() {
        return this.x;
    }

    public int getN() {
        return this.n;
    }

    public void addN() {
        ++this.n;
    }

    public void addX() {
        ++this.x;
    }

    public String CL() {
        double phat = (double)this.getX() / (double)this.getN();
        double lower = (double)((int)((phat - 1.96D * Math.sqrt(phat * (1.0D - phat) / (double)this.getN())) * 1000.0D)) / 10.0D;
        double higher = (double)((int)((phat + 1.96D * Math.sqrt(phat * (1.0D - phat) / (double)this.getN())) * 1000.0D)) / 10.0D;
        return lower + "-" + higher;
    }

    public String toString() {
        return this.getHeader() + "," + this.getX() + "/" + this.getN() + "," + this.CL();
    }

    public static void toTxt(ArrayList<statElement> a) {
        try {
            PrintStream out = new PrintStream(new FileOutputStream("data.txt"));
            Iterator var2 = a.iterator();
            out.println("Card Range,Odds of Winning,95% Confidence Level");
            while(var2.hasNext()) {
                statElement s = (statElement)var2.next();
                out.println(s.toString());
            }

            out.close();
        } catch (FileNotFoundException var4) {
            var4.printStackTrace();
        }

    }
}
