package pl.com.ttpsc.service;

import pl.com.ttpsc.model.MovieRecordLibrary;
import pl.com.ttpsc.model.MusicRecordShop;
import pl.com.ttpsc.model.Record;
import pl.com.ttpsc.order.MovieOrder;
import pl.com.ttpsc.order.MusicOrder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SettingsService {

    private static SettingsService settingsService;
    private SettingsService () {}

    public static SettingsService getInstance(){
        if (settingsService == null){
            settingsService = new SettingsService();
        }
        return settingsService;
    }

    FileService fileService = FileService.getInstance();
    MusicRecordShop musicRecordShop = MusicRecordShop.getInstance();
    MovieRecordLibrary movieRecordLibrary = MovieRecordLibrary.getInstance();
    MovieOrder movieOrder = MovieOrder.getInstance();
    MusicOrder musicOrder = MusicOrder.getInstance();

    public List<? extends Record> checkingListToGet() throws IOException, ClassNotFoundException {
        List <? extends Record> recordList = null;
        String className = fileService.getClassToDo();
        if(className.equals(FileService.NAME_OF_MUSIC_SHOP)){
            recordList = musicRecordShop.getRecordList();
        } else if (className.equals(FileService.NAME_OF_RECORD_LIBRARY)){
            recordList = movieRecordLibrary.getMovieRecordList();
        }
        return recordList;

    }

    public Map<Integer, Record> checkingOrderToGet () throws IOException, ClassNotFoundException {
        String settings = fileService.getClassToDo();
        if (FileService.NAME_OF_MUSIC_SHOP.equals(settings)){

            return musicOrder.getMusicRecordOrder();
        } else {
            return movieOrder.getMovieRecordOrder();
        }
    }
}
