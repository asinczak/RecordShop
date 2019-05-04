package pl.com.ttpsc.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "MusicRecord")
public class MusicRecord  extends Record implements Serializable{

    private static final long serialVersionUID = 369258147L;


    private String author;
    private int availableAmt;

    public int getAvailableAmt() {
        return availableAmt;
    }

    public void setAvailableAmt(int availableAmt) {
        this.availableAmt = availableAmt;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "MusicRecord{" +
                "Id= " + this.getId() +
                ", Author= '" + author + '\'' +
                ", Title= '" + this.getTitle() + '\'' +
                ", Price= " + this.getPrice() +
                ", On stock= " + availableAmt +
                '}';
    }
}
