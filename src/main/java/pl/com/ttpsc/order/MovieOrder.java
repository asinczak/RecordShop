package pl.com.ttpsc.order;

import pl.com.ttpsc.model.Record;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedHashMap;
import java.util.Map;

@XmlRootElement (name = "MovieOrder")
@XmlAccessorType (XmlAccessType.FIELD)
public class MovieOrder {

    private static MovieOrder movieOrder;
    private MovieOrder () {}

    public static MovieOrder getInstance() {
        if (movieOrder == null){
            movieOrder = new MovieOrder();
        }
        return movieOrder;
    }


    public Map<Integer, Record> getMovieRecordOrder() {
        return movieRecordOrder;
    }

    public void setMovieRecordOrder(Map<Integer, Record> movieRecordOrder) {
        this.movieRecordOrder = movieRecordOrder;
    }

    private Map<Integer, Record> movieRecordOrder = new LinkedHashMap<>();

}
