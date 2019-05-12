package pl.com.ttpsc.service;

import pl.com.ttpsc.model.MovieRecord;
import pl.com.ttpsc.model.MovieRecordLibrary;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class MovieRecordService {

    private static MovieRecordService movieRecordService;
    private MovieRecordService () {}

    public static MovieRecordService getInstance(){
        if (movieRecordService == null){
            movieRecordService = new MovieRecordService();
        }
        return movieRecordService;
    }

    EnteringDataService enteringDataService = EnteringDataService.getInstance();
    MovieRecordLibrary movieRecordLibrary = MovieRecordLibrary.getInstance();
    FileService fileService = FileService.getInstance();

    public void addRecordToLibrary () throws IOException, ClassNotFoundException, JAXBException {
        MovieRecord movieRecord = enteringDataService.getDataToEnterNewMovieRecord();
        if (movieRecord == null){
            System.out.println(IGeneralMessages.INFO_STATEMENT_4);
        } else {
            movieRecordLibrary.getMovieRecordList().add(movieRecord);
            fileService.writeMovieRecordToXMLfile();
        }
    }
}
