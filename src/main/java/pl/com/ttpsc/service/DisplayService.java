package pl.com.ttpsc.service;

import pl.com.ttpsc.model.Record;
import pl.com.ttpsc.order.MovieOrder;

import javax.xml.bind.JAXBException;
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

    SettingsService settingsService = SettingsService.getInstance();
    FileService fileService = FileService.getInstance();
    MovieOrder movieOrder = MovieOrder.getInstance();

    public void displayMenuForMusicShop (){
        System.out.println(IGeneralMessages.MENU_OPTION_1);
        System.out.println(IGeneralMessages.MENU_OPTION_2);
        System.out.println(IGeneralMessages.MENU_OPTION_3);
        System.out.println(IGeneralMessages.MENU_OPTION_4);
        System.out.println(IGeneralMessages.MENU_OPTION_5);
        System.out.println(IGeneralMessages.MENU_OPTION_6);
    }

    public void displayAllRecords () throws IOException, ClassNotFoundException {
        Optional <List <? extends Record>> list = Optional.ofNullable(settingsService.checkingListToGet());

        if(list.isPresent()) {
            List<? extends Record> recordList = settingsService.checkingListToGet();
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

    public void displayMenuForMovieLibrary() {
        System.out.println(IGeneralMessages.MENU_OPTION_1);
        System.out.println(IGeneralMessages.MENU_OPTION_2);
        System.out.println(IGeneralMessages.MENU_OPTION_10);
        System.out.println(IGeneralMessages.MENU_OPTION_11);
        System.out.println(IGeneralMessages.MENU_OPTION_12);
        System.out.println(IGeneralMessages.MENU_OPTION_13);
        System.out.println(IGeneralMessages.MENU_OPTION_14);

    }

    public void displayAllOrderedMovieRecords () throws JAXBException {
        movieOrder.setMovieRecordOrder(fileService.readMovieOrderFromXMLfile());

        movieOrder.getMovieRecordOrder().forEach((k, v) -> System.out.println("Id order: "+  k +"\n movie record: "+ v));
    }
}

