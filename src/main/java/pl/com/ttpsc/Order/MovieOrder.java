package pl.com.ttpsc.Order;

import pl.com.ttpsc.Data.MovieRecord;
import pl.com.ttpsc.Data.Record;

import java.util.LinkedHashMap;
import java.util.Map;

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
