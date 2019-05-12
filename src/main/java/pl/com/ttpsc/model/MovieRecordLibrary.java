package pl.com.ttpsc.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement (name = "MovieRecordLibrary")
@XmlAccessorType (XmlAccessType.FIELD)
public class MovieRecordLibrary {

    private static MovieRecordLibrary movieRecordLibrary;
    private MovieRecordLibrary () {}

    public static MovieRecordLibrary getInstance(){
        if (movieRecordLibrary == null){
            movieRecordLibrary = new MovieRecordLibrary();
        }
        return movieRecordLibrary;
    }

    @XmlElement (name = "MovieRecord")
    private List<MovieRecord> movieRecordList = new ArrayList<>();

    public List<MovieRecord> getMovieRecordList() {
        return movieRecordList;
    }

    public void setMovieRecordList(List<MovieRecord> movieRecordList) {
        this.movieRecordList = movieRecordList;
    }
}
