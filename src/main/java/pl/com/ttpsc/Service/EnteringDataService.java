package pl.com.ttpsc.Service;

import pl.com.ttpsc.Data.*;
import pl.com.ttpsc.Order.MovieOrder;
import pl.com.ttpsc.Order.MusicOrder;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class EnteringDataService {

    private static EnteringDataService enteringDataService;
    private EnteringDataService () {}
    public static EnteringDataService getInstance(){
        if (enteringDataService == null){
            enteringDataService = new EnteringDataService();
        }
        return enteringDataService;
    }

    MusicShop musicShop = MusicShop.getInstance();
    DisplayService displayService = DisplayService.getInstance();
    ShopService shopService = ShopService.getInstance();
    FileService fileService = FileService.getInstance();
    MovieOrder movieOrder = MovieOrder.getInstance();
    MusicOrder musicOrder = MusicOrder.getInstance();

    public MusicRecord getDataToEnterNewMusicRecord() throws IOException, ClassNotFoundException {
        MusicRecord musicRecord = null;

        Scanner scanner = new Scanner(System.in);
        System.out.println(IGeneralMessages.ENTER_DATA_1);
        String author = scanner.nextLine();

        if (checkingIfUserwantsReturnToMenu(author)){
            return musicRecord;
        } else {

            System.out.println(IGeneralMessages.ENTER_DATA_2);
            String title = scanner.nextLine();

            System.out.println(IGeneralMessages.ENTER_DATA_3);
            double price = scanner.nextDouble();

            System.out.println(IGeneralMessages.ENTER_DATA_4);
            int availableAmt = scanner.nextInt();

            musicRecord = new MusicRecord();
            musicRecord.setId(assignId());
            musicRecord.setAuthor(author);
            musicRecord.setTitle(title);
            musicRecord.setPrice(price);
            musicRecord.setAvailableAmt(availableAmt);

            return musicRecord;
        }
    }

    public int assignId () throws IOException, ClassNotFoundException {
        int id = 1;

        Optional<List<? extends Record>> list = Optional.ofNullable(displayService.checkingListToGet());

        if (list.isPresent()){
            List recordList = displayService.checkingListToGet();
            Record record = (Record) recordList.stream().reduce((first, second) -> second).orElse(null);
            int lastIdFromList = record.getId();
            id = lastIdFromList + 1;
            return id;
        } else {

            return id;
        }
    }

    public int getMenuOptionForMusicShop(){
        displayService.displayMenuForMusicShop();

        Scanner scanner = new Scanner(System.in);
        int menuOption = scanner.nextInt();

        return menuOption;
    }

    public int getIdRecordToUpdate () throws IOException, ClassNotFoundException {
        int id = 0;
        displayService.displayAllRecords();

        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println(IGeneralMessages.ENTER_DATA_5);
            id = scanner.nextInt();

            if (shopService.checkingIfsuchIdExists(id)){
                break;
            } else {
                System.out.println(IGeneralMessages.INFO_STATEMENT_3);
            }

        } while (true);


        return id;
    }

    public int getDataToChange () {
        int dataToChange = 0;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println(IGeneralMessages.ENTER_DATA_6);
            dataToChange = scanner.nextInt();
            if(dataToChange < 5 & dataToChange > 0){
                break;
            }

        } while (true);

        return dataToChange;
    }

    public String getDataToChangeAuthor () {
        String data = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println(IGeneralMessages.ENTER_DATA_1);
        data = scanner.nextLine();

        return data;
    }

    public String getDataToChangeTitle () {
        String data = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println(IGeneralMessages.ENTER_DATA_2);
        data = scanner.nextLine();

        return data;
    }

    public double getDataToChangePrice () {
        double data = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println(IGeneralMessages.ENTER_DATA_3);
        data = scanner.nextDouble();

        return data;
    }

    public int getDataToChangeAvailableNumber () {
        int data = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println(IGeneralMessages.ENTER_DATA_4);
        data = scanner.nextInt();

        return data;
    }

    public boolean checkingIfUserwantsReturnToMenu (String eneterData) {
        if (eneterData.equalsIgnoreCase("x")){
            return true;
        } else {
            return false;
        }
    }

    public int getMenuOptionForMainMenu (){
        displayService.displayMainMenu();

        Scanner scanner = new Scanner(System.in);
        int menuOption = scanner.nextInt();

        return menuOption;

    }

    public int getMenuOptionForMovieLibrary() {
        displayService.displayMenuForMovieLibrary();

        Scanner scanner = new Scanner(System.in);
        int menuOption = scanner.nextInt();

        return menuOption;
    }

    public MovieRecord getDataToEnterNewMovieRecord() throws IOException, ClassNotFoundException {
        MovieRecord movieRecord = null;

        Scanner scanner = new Scanner(System.in);
        System.out.println(IGeneralMessages.ENTER_DATA_7);
        String title = scanner.nextLine();

        if (checkingIfUserwantsReturnToMenu(title)){
            return movieRecord;
        } else {
            System.out.println(IGeneralMessages.ENTER_DATA_8);
            String genre = scanner.nextLine();

            System.out.println(IGeneralMessages.ENTER_DATA_3);
            double price = scanner.nextDouble();

            movieRecord = new MovieRecord();
            movieRecord.setId(assignId());
            movieRecord.setTitle(title);
            movieRecord.setPrice(price);
            movieRecord.setGenre(genre);

            return movieRecord;
        }
    }

    public Map<Integer, Record> checkingOrderToGet () throws IOException, ClassNotFoundException {
        String settings = fileService.getClassToDo();
        if (FileService.NAME_OF_MUSIC_SHOP.equals(settings)){

            return musicOrder.getMusicRecordOrder();
        } else {
            return movieOrder.getMovieRecordOrder();
        }
    }

    public void getDataToMakeAnOrder () throws IOException, ClassNotFoundException {
        displayService.displayAllRecords();
        int counter = 1;

       try( Scanner scanner = new Scanner(System.in)){
        System.out.println(IGeneralMessages.ENTER_DATA_9);
        int id = scanner.nextInt();


        if (FileService.NAME_OF_MUSIC_SHOP.equals(fileService.getClassToDo())) {
            MusicRecord musicRecord = (MusicRecord) getRecordToPutToOrder(id);
            checkingOrderToGet().put(counter, musicRecord);

        } else if (FileService.NAME_OF_RECORD_LIBRARY.equals(fileService.getClassToDo())) {
            MovieRecord movieRecord = (MovieRecord) getRecordToPutToOrder(id);
            checkingOrderToGet().put(counter, movieRecord);
            }
        }
    }

    public Record getRecordToPutToOrder (int id) throws IOException, ClassNotFoundException {
        Record record = displayService.checkingListToGet().stream().parallel().
                filter(recordFrpmList -> (recordFrpmList).getId() == id).findFirst().get();

        return record;

    }


}
