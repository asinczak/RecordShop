package pl.com.ttpsc.service;

import pl.com.ttpsc.model.MusicRecord;
import pl.com.ttpsc.model.Record;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class OrderService {

    private static OrderService orderService;
    private OrderService () {}

    public static OrderService getInstance(){
        if (orderService == null){
            orderService = new OrderService();
        }
        return orderService;
    }


    SettingsService settingsService = SettingsService.getInstance();
    FileService fileService = FileService.getInstance();
    ShopService shopService = ShopService.getInstance();
    EnteringDataService enteringDataService = EnteringDataService.getInstance();
    MusicRecordService musicRecordService = MusicRecordService.getInstance();



    public void takeAnOrder () throws IOException, ClassNotFoundException, JAXBException {

        if (FileService.NAME_OF_MUSIC_SHOP.equals(fileService.getClassToDo())) {
        saveDataToOrder();
        } else if (FileService.NAME_OF_RECORD_LIBRARY.equals(fileService.getClassToDo())) {
            saveDataToOrder();
            shopService.removeRecordFromList();
            fileService.writeMusicRecordToXMLfile();
        }
    }

    public Record fillOrderList (int counter, int id) throws IOException, ClassNotFoundException {

            Record record = shopService.getRecordFromGivenId(id);
            settingsService.checkingOrderToGet().put(counter, record);
            return record;
    }

    public void saveDataToOrder() throws IOException, ClassNotFoundException, JAXBException {
        int counter = 1;
        boolean checking = true;

        do {
            int id = enteringDataService.getDataToMakeAnOrder();
            if (id == 0) {
                System.out.println(IGeneralMessages.INFO_STATEMENT_1);
                checking = false;
            } else {
              MusicRecord musicRecord = (MusicRecord) fillOrderList(counter, id);
              musicRecordService.setAvailableAmt(musicRecord);
                counter++;
            }

        }while (checking) ;


    }



}