package pl.com.ttpsc.service;

import pl.com.ttpsc.model.MusicRecord;
import pl.com.ttpsc.model.MusicRecordShop;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class MusicRecordService {

    private static MusicRecordService musicRecordService;
    private MusicRecordService() {}

    public static MusicRecordService getInstance(){
        if (musicRecordService == null){
            musicRecordService = new MusicRecordService();
        }
        return musicRecordService;
    }

    MusicRecordShop musicRecordShop = MusicRecordShop.getInstance();
    EnteringDataService enteringDataService = EnteringDataService.getInstance();
    FileService fileService = FileService.getInstance();
    SettingsService settingsService = SettingsService.getInstance();

    public void addRecordInStock () throws JAXBException, IOException, ClassNotFoundException {
        MusicRecord musicRecord = enteringDataService.getDataToEnterNewMusicRecord();
        if (musicRecord == null){
            System.out.println(IGeneralMessages.INFO_STATEMENT_4);
        } else {
            musicRecordShop.getRecordList().add(musicRecord);
            fileService.writeMusicRecordToXMLfile();
        }
    }

    public void updateRecord () throws JAXBException, IOException, ClassNotFoundException {
        int id = enteringDataService.getIdRecordToUpdate();

        MusicRecord musicRecord = musicRecordShop.getRecordList().stream().filter(musicRecord1 -> musicRecord1.getId() == id).findFirst().get();

        int dataToUpdate = enteringDataService.getDataToChange();

        switch (dataToUpdate){
            case 1:
                musicRecord.setAuthor(enteringDataService.getDataToChangeAuthor());
                break;
            case 2:
                musicRecord.setTitle(enteringDataService.getDataToChangeTitle());
                break;
            case 3:
                musicRecord.setPrice(enteringDataService.getDataToChangePrice());
                break;
            case 4:
                musicRecord.setAvailableAmt(enteringDataService.getDataToChangeAvailableNumber());
                break;
        }
        fileService.writeMusicRecordToXMLfile();
    }

    public void decreaseNumberOfAvailableRecordsOnStock (MusicRecord musicRecord){
        int actualAvailableAmt = musicRecord.getAvailableAmt();

        musicRecord.setAvailableAmt(actualAvailableAmt - 1);
    }

    public void setAvailableAmt (MusicRecord orderedMusicRecord) throws IOException, ClassNotFoundException, JAXBException {
        if (FileService.NAME_OF_MUSIC_SHOP.equals(fileService.getClassToDo())){
            MusicRecord musicRecord = musicRecordShop.getRecordList().stream().findAny().
                    filter(recordFromList -> recordFromList.equals(orderedMusicRecord)).get();
                    checkingAvailableAmt(musicRecord);
        }
        fileService.writeMusicRecordToXMLfile();
    }

    public void checkingAvailableAmt (MusicRecord musicRecord) throws IOException, ClassNotFoundException {
        int actualAvailableAmt = musicRecord.getAvailableAmt();
        if (actualAvailableAmt == 1){
            settingsService.checkingListToGet().remove(musicRecord);
        } else {
            decreaseNumberOfAvailableRecordsOnStock(musicRecord);
        }
    }

}
