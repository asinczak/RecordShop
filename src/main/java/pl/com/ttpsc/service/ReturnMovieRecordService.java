package pl.com.ttpsc.service;

import pl.com.ttpsc.model.MovieRecord;
import pl.com.ttpsc.model.MovieRecordLibrary;
import pl.com.ttpsc.model.Record;
import pl.com.ttpsc.order.MovieOrder;

import javax.xml.bind.JAXBException;
import java.util.stream.Stream;

public class ReturnMovieRecordService {

    private static ReturnMovieRecordService returnMovieRecordService;
    private ReturnMovieRecordService () {}

    public static ReturnMovieRecordService getInstance(){
        if (returnMovieRecordService == null){
            returnMovieRecordService = new ReturnMovieRecordService();
        }
        return returnMovieRecordService;
    }

    EnteringDataService enteringDataService = EnteringDataService.getInstance();
    OrderService orderService = OrderService.getInstance();
    MovieRecordLibrary movieRecordLibrary = MovieRecordLibrary.getInstance();


    public void returnMusicRecord () throws JAXBException {
        int idOrder = enteringDataService.getDataToReturnRecord();

        if (idOrder == 0){
            System.out.println(IGeneralMessages.INFO_STATEMENT_4);
        } else {
            saveRecordToLibrary(idOrder);
        }
    }

    private void saveRecordToLibrary(int idOrder) {
        if (!orderService.checkingIfSuchIdOrderExists(idOrder)){
            System.out.println(IGeneralMessages.INFO_STATEMENT_2);
        } else {
            Stream <Record> stream = orderService.getRecordFromOrdered(idOrder);
            MovieRecord record = (MovieRecord) stream.findFirst().get();
            movieRecordLibrary.getMovieRecordList().add(record);
        }
    }
}
