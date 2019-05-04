package pl.com.ttpsc.Service;

import pl.com.ttpsc.Data.MusicRecord;
import pl.com.ttpsc.Data.MusicShop;

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

    MusicShop musicShop = MusicShop.getInstance();
    EnteringDataService enteringDataService = EnteringDataService.getInstance();
    FileService fileService = FileService.getInstance();

    public void addRecordInStock () throws JAXBException, IOException, ClassNotFoundException {
        MusicRecord musicRecord = enteringDataService.getDataToEnterNewMusicRecord();
        if (musicRecord == null){
            System.out.println(IGeneralMessages.INFO_STATEMENT_4);
        } else {
            musicShop.getRecordList().add(musicRecord);
            fileService.writeMusicRecordToXMLfile();
        }
    }

    public void updateRecord () throws JAXBException, IOException, ClassNotFoundException {
        int id = enteringDataService.getIdRecordToUpdate();

        MusicRecord musicRecord = musicShop.getRecordList().stream().filter(musicRecord1 -> musicRecord1.getId() == id).findFirst().get();

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
}
