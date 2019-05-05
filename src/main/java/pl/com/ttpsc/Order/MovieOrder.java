package pl.com.ttpsc.Order;

import pl.com.ttpsc.Data.MovieRecord;

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


    public Map<Integer, MovieRecord> getMovieRecordOrder() {
        return movieRecordOrder;
    }

    public void setMovieRecordOrder(Map<Integer, MovieRecord> movieRecordOrder) {
        this.movieRecordOrder = movieRecordOrder;
    }

    private Map<Integer, MovieRecord> movieRecordOrder = new LinkedHashMap<>();

}
