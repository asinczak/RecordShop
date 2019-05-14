package pl.com.ttpsc.service;

import pl.com.ttpsc.model.MovieRecordLibrary;
import pl.com.ttpsc.model.MusicRecordShop;
import pl.com.ttpsc.model.Record;
import pl.com.ttpsc.order.MovieOrder;
import pl.com.ttpsc.order.MusicOrder;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ShopService {

    private static ShopService shopService;
    private ShopService () {}

    public static ShopService getInstance() {
        if(shopService == null) {
            shopService = new ShopService();
        }
        return shopService;
    }

    MusicRecordShop musicRecordShop = MusicRecordShop.getInstance();
    MovieRecordLibrary movieRecordLibrary = MovieRecordLibrary.getInstance();
    SettingsService settingsService = SettingsService.getInstance();
    MusicOrder musicOrder = MusicOrder.getInstance();
    MovieOrder movieOrder = MovieOrder.getInstance();
    FileService fileService = FileService.getInstance();

    public boolean checkingIfsuchIdExistsInMusicShop(int id) {

        boolean checking = musicRecordShop.getRecordList().stream().anyMatch(record -> id == record.getId());
        return checking;
    }

    public int assignIdToRecord() throws IOException, ClassNotFoundException {
        int id = 1;

        Optional<List<? extends Record>> list = Optional.ofNullable(settingsService.checkingListToGet());

        if (list.isPresent()){
            List recordList = settingsService.checkingListToGet();
            Record record = (Record) recordList.stream().reduce((first, second) -> second).orElse(null);
            int lastIdFromList = record.getId();
            id = lastIdFromList + 1;
            return id;
        } else {

            return id;
        }
    }

    public Record getRecordFromGivenId(int id) throws IOException, ClassNotFoundException {
        Record record = settingsService.checkingListToGet().stream().parallel().
                filter(recordFrpmList -> (recordFrpmList).getId() == id).findFirst().get();

        return record;
    }

    public Map<Integer, Record> checkWhichOrderGet() throws IOException {
        if (FileService.NAME_OF_MUSIC_SHOP.equals(fileService.getClassToDo())){
            return musicOrder.getMusicRecordOrder();
        } else {
            return movieOrder.getMovieRecordOrder();
        }
    }

    public void removeRecordFromList () throws IOException, ClassNotFoundException {
        Map<Integer, Record> orderMap = checkWhichOrderGet();

        for (Map.Entry<Integer, Record> entry : orderMap.entrySet()) {
            for (int i = 0; i < settingsService.checkingListToGet().size(); i++) {
                Record record = entry.getValue();
                if (record.equals(settingsService.checkingListToGet().get(i))) {
                    settingsService.checkingListToGet().remove(record);
                }
            }
        }
    }

        public boolean checkingIfsuchIdExistsInMovieLibrary (int id) {

            boolean checking = movieRecordLibrary.getMovieRecordList().stream().anyMatch(record -> id == record.getId());
            return checking;
        }

}
