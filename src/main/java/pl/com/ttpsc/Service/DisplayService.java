package pl.com.ttpsc.Service;

import pl.com.ttpsc.Data.MovieRecordLibrary;
import pl.com.ttpsc.Data.MusicShop;
import pl.com.ttpsc.Data.Record;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class DisplayService {

    private static DisplayService displayService;
    private DisplayService (){}

    public static  DisplayService getInstance(){
        if (displayService == null){
            displayService = new DisplayService();
        }
        return displayService;
    }

    MusicShop musicShop = MusicShop.getInstance();
    FileService fileService = FileService.getInstance();
    MovieRecordLibrary movieRecordLibrary = MovieRecordLibrary.getInstance();

    public void displayMenuForMusicShop (){
        System.out.println(IGeneralMessages.MENU_OPTION_1);
        System.out.println(IGeneralMessages.MENU_OPTION_2);
        System.out.println(IGeneralMessages.MENU_OPTION_3);
        System.out.println(IGeneralMessages.MENU_OPTION_4);
        System.out.println(IGeneralMessages.MENU_OPTION_5);
        System.out.println(IGeneralMessages.MENU_OPTION_6);
    }

    public void displayAllRecords () throws IOException, ClassNotFoundException {
        Optional <List <? extends Record>> list = Optional.ofNullable(checkingListToGet());

        if(list.isPresent()) {
            List<? extends Record> recordList = checkingListToGet();
            recordList.stream().forEach(record -> System.out.println(record));
        } else {
            System.out.println(IGeneralMessages.INFO_STATEMENT_5);
        }
    }

    public void displayMainMenu() {
        System.out.println(IGeneralMessages.MENU_OPTION_1);
        System.out.println(IGeneralMessages.MENU_OPTION_2);
        System.out.println(IGeneralMessages.MENU_OPTION_7);
        System.out.println(IGeneralMessages.MENU_OPTION_8);
        System.out.println(IGeneralMessages.MENU_OPTION_9);
    }

    public List <? extends Record> checkingListToGet() throws IOException, ClassNotFoundException {
        List <? extends Record> recordList = null;
        String className = fileService.getClassToDo();
        if(className.equals(FileService.NAME_OF_MUSIC_SHOP)){
            recordList = musicShop.getRecordList();
        } else if (className.equals(FileService.NAME_OF_RECORD_LIBRARY)){
            recordList = movieRecordLibrary.getMovieRecordList();
        }
    return recordList;

    }

    public void displayMenuForMovieLibrary() {
        System.out.println(IGeneralMessages.MENU_OPTION_1);
        System.out.println(IGeneralMessages.MENU_OPTION_2);
        System.out.println(IGeneralMessages.MENU_OPTION_10);
        System.out.println(IGeneralMessages.MENU_OPTION_11);
        System.out.println(IGeneralMessages.MENU_OPTION_12);
        System.out.println(IGeneralMessages.MENU_OPTION_13);

    }
}

