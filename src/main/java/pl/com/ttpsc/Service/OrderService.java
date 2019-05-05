package pl.com.ttpsc.Service;

import pl.com.ttpsc.Data.Record;
import pl.com.ttpsc.Order.MovieOrder;
import pl.com.ttpsc.Order.MusicOrder;

import java.io.IOException;
import java.util.Map;

public class OrderService {

    MovieOrder movieOrder = MovieOrder.getInstance();
    MusicOrder musicOrder = MusicOrder.getInstance();
    FileService fileService = FileService.getInstance();



    public void takeAnOrder (){

    }
}
