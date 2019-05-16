package pl.com.ttpsc.service;

import pl.com.ttpsc.model.MusicRecord;
import pl.com.ttpsc.model.Record;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

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
            fileService.readMovieOrderFromXMLfile();
            saveDataToOrder();
            shopService.removeRecordFromList();
            fileService.writeMovieRecordToXMLfile();
            fileService.writeMovieOrderToXMLFile();
        }
    }

    public Record fillOrderList (int id) throws IOException, ClassNotFoundException {

            Record record = shopService.getRecordFromGivenId(id);
            settingsService.checkingOrderToGet().put(getKeyIdFromOrder(), record);
            return record;
    }

    public void saveDataToOrder() throws IOException, ClassNotFoundException, JAXBException {
        boolean checking = true;

        do {
            int id = enteringDataService.getDataToMakeAnOrder();
            if (id == 0) {
                System.out.println(IGeneralMessages.INFO_STATEMENT_1);
                checking = false;
            } else {
              checkingIfGivenIdIsCorrect(id);
            }

        }while (checking) ;
    }

    public void checkingIfGivenIdIsCorrect (int id) throws IOException, JAXBException, ClassNotFoundException {

        if (FileService.NAME_OF_MUSIC_SHOP.equals(fileService.getClassToDo())){
           checkingIfIdIsCorrectInMusicShop(id);
        } else if (FileService.NAME_OF_RECORD_LIBRARY.equals(fileService.getClassToDo())){
            checkingIfIdIsCorrectInMovieLibrary(id);
        }
    }

    public void checkingIfIdIsCorrectInMusicShop (int id) throws IOException, ClassNotFoundException, JAXBException {

        if (!shopService.checkingIfsuchIdExistsInMusicShop(id)){
            System.out.println(IGeneralMessages.INFO_STATEMENT_2);
        } else {
            MusicRecord musicRecord = (MusicRecord) fillOrderList(id);
            musicRecordService.setAvailableAmt(musicRecord);
        }
    }

    public void checkingIfIdIsCorrectInMovieLibrary (int id) throws IOException, ClassNotFoundException {

        if (!shopService.checkingIfsuchIdExistsInMovieLibrary(id)){
            System.out.println(IGeneralMessages.INFO_STATEMENT_2);
        } else {
            fillOrderList(id);
        }
    }

    public int getKeyIdFromOrder () throws IOException, ClassNotFoundException {
        int theLastKey = 0;
        for (Map.Entry <Integer, Record> entry : settingsService.checkingOrderToGet().entrySet()){
            Optional <Integer> keyFromOrder = Optional.of(entry.getKey());
            if(keyFromOrder.isPresent()) {
                int key = entry.getKey();
                if (key > theLastKey) {
                    theLastKey = key;
                }
            } else {
                theLastKey = 1;
            }
        }
        return theLastKey;
    }



}
