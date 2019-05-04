package pl.com.ttpsc.Data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "MovieRecord")
public class MovieRecord extends Record {

    private String genre;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "MovieRecord{" +
                "Id= " + this.getId() +
                ", Title= '" + this.getTitle() + '\'' +
                ", Price= " + this.getPrice() +
                "genre='" + genre + '\'' +
                '}';
    }
}
