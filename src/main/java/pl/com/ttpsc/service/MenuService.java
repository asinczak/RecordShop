package pl.com.ttpsc.service;

import pl.com.ttpsc.model.MovieRecordLibrary;
import pl.com.ttpsc.model.MusicRecordShop;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class MenuService {

    private static MenuService menuService;
    private MenuService () {}

    public static MenuService getInstance(){
        if (menuService == null){
            menuService = new MenuService();
        }
        return menuService;
    }

    EnteringDataService enteringDataService = EnteringDataService.getInstance();
    MusicRecordService musicRecordService = MusicRecordService.getInstance();
    MusicRecordShop musicRecordShop = MusicRecordShop.getInstance();
    FileService fileService = FileService.getInstance();
    DisplayService displayService = DisplayService.getInstance();
    MovieRecordService movieRecordService = MovieRecordService.getInstance();
    MovieRecordLibrary movieRecordLibrary = MovieRecordLibrary.getInstance();
    OrderService orderService = OrderService.getInstance();
    ReturnMovieRecordService returnMovieRecordService = ReturnMovieRecordService.getInstance();

    public void menuForMusicShop() throws IOException, ClassNotFoundException, JAXBException {

            musicRecordShop.setRecordList(fileService.readMusicRecordFromXMLfile());
            boolean switchGoes = true;
            do {

                int menuOption = enteringDataService.getMenuOptionForMusicShop();

                switch (menuOption) {

                    case 1:
                        musicRecordService.addRecordInStock();
                        break;
                    case 2:
                        displayService.displayAllRecords();
                        break;
                    case 3:
                        musicRecordService.updateRecord();
                        break;
                    case 4:
                        switchGoes = false;
                        System.out.println(IGeneralMessages.INFO_STATEMENT_1);
                        break;
                    default:
                        System.out.println(IGeneralMessages.INFO_STATEMENT_2);

                }
            } while (switchGoes);


    }

    public void mainMenu () {
        boolean switchGoes = true;

        do {
        try {
            int menuNumber = enteringDataService.getMenuOptionForMainMenu();
            switch (menuNumber){
                case 1:
                    fileService.setValueInSettings(1);
                    menuForMusicShop();
                    break;
                case 2:
                    fileService.setValueInSettings(2);
                    menuForMovieLibrary();
                    break;
                case 3:
                    switchGoes = false;
                    System.out.println(IGeneralMessages.INFO_STATEMENT_1);
                    break;
                default:
                    System.out.println(IGeneralMessages.INFO_STATEMENT_2);
            }
        } catch (IOException | ClassNotFoundException | JAXBException e) {
            e.printStackTrace();
        }

        }while (switchGoes);
    }

    private void menuForMovieLibrary() throws IOException, ClassNotFoundException, JAXBException {
        boolean switchGoes = true;
        movieRecordLibrary.setMovieRecordList(fileService.readMovieRecordFromXMLfile());
        do {
            int menuNumber = enteringDataService.getMenuOptionForMovieLibrary();
            switch (menuNumber){
                case 1:
                    movieRecordService.addRecordToLibrary();
                    break;
                case 2:
                    displayService.displayAllRecords();
                    break;
                case 3:
                    orderService.takeAnOrder();
                    break;
                case 4:
                    returnMovieRecordService.returnMusicRecord();
                    break;
                case 5:
                    switchGoes = false;
                    System.out.println(IGeneralMessages.INFO_STATEMENT_4);
                    break;
                default:
                    System.out.println(IGeneralMessages.INFO_STATEMENT_2);

            }

        }while (switchGoes);

    }


}
