package pl.com.ttpsc.Service;

import pl.com.ttpsc.Data.MovieRecord;
import pl.com.ttpsc.Data.MusicRecord;
import pl.com.ttpsc.Data.Record;
import pl.com.ttpsc.Order.MovieOrder;
import pl.com.ttpsc.Order.MusicOrder;

import java.io.IOException;
import java.util.Map;

public class OrderService {

    MovieOrder movieOrder = MovieOrder.getInstance();
    MusicOrder musicOrder = MusicOrder.getInstance();
    FileService fileService = FileService.getInstance();
    DisplayService displayService = DisplayService.getInstance();
    EnteringDataService enteringDataService = EnteringDataService.getInstance();



    public void takeAnOrder () throws IOException, ClassNotFoundException {
        int counter = 1;
        boolean checking = true;

        do {
            int id = enteringDataService.getDataToMakeAnOrder();
            if (id == 0) {
                System.out.println(IGeneralMessages.INFO_STATEMENT_1);
                checking = false;
            } else {
                fillOrderList(counter, id);
                counter++;
            }

        }while (checking) ;

    }

    public Record getRecordToPutToOrder (int id) throws IOException, ClassNotFoundException {
        Record record = displayService.checkingListToGet().stream().parallel().
                filter(recordFrpmList -> (recordFrpmList).getId() == id).findFirst().get();

        return record;

    }

    public void fillOrderList (int counter, int id) throws IOException, ClassNotFoundException {

        if (FileService.NAME_OF_MUSIC_SHOP.equals(fileService.getClassToDo())) {
            MusicRecord musicRecord = (MusicRecord) getRecordToPutToOrder(id);
            enteringDataService.checkingOrderToGet().put(counter, musicRecord);

        } else if (FileService.NAME_OF_RECORD_LIBRARY.equals(fileService.getClassToDo())) {
            MovieRecord movieRecord = (MovieRecord) getRecordToPutToOrder(id);
            enteringDataService.checkingOrderToGet().put(counter, movieRecord);
        }

    }

}
