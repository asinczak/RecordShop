package pl.com.ttpsc.Service;

import pl.com.ttpsc.Data.MovieRecord;
import pl.com.ttpsc.Data.MovieRecordLibrary;
import pl.com.ttpsc.Data.MusicRecord;
import pl.com.ttpsc.Data.MusicShop;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.List;

public class FileService {

    private static FileService fileService;
    private FileService () {}

    public static FileService getInstance(){
        if (fileService == null){
            fileService = new FileService();
        }
        return fileService;
    }

    private static final String STOCK_FILE_NAME = "Stock.xml";
    private static final String LIBRARY_FILE_NAME = "Library.xml";
    private static final String SETTINGS_FILE_NAME = "Default.settings";
    static final String NAME_OF_MUSIC_SHOP = MusicShop.class.getName();
    static final String NAME_OF_RECORD_LIBRARY = MovieRecordLibrary.class.getName();


    public void writeMusicRecordToXMLfile() throws JAXBException{

        JAXBContext jaxbContext = JAXBContext.newInstance(MusicShop.class);
        Marshaller marshaller = jaxbContext.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        marshaller.marshal(MusicShop.getInstance(), new File(STOCK_FILE_NAME));

    }

    public List<MusicRecord> readMusicRecordFromXMLfile() throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(MusicShop.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        MusicShop musicShop = (MusicShop) unmarshaller.unmarshal(new File(STOCK_FILE_NAME));

        List <MusicRecord> musicRecordList = musicShop.getRecordList();

        return musicRecordList;
    }

    public void setValueInSettings (int option) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SETTINGS_FILE_NAME))) {

            if (option == 1) {
                writer.write(NAME_OF_MUSIC_SHOP);
            } else if (option == 2) {
                writer.write("MovieRecordLibrary");
            }
        }
    }

    public String getClassToDo () throws IOException, ClassNotFoundException {
        String className = null;
       try (BufferedReader reader = new BufferedReader(new FileReader(SETTINGS_FILE_NAME))) {
            className = reader.readLine();
       }
       return className;
    }

    public void writeMovieRecordToXMLfile () throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(MovieRecordLibrary.class);
        Marshaller marshaller = jaxbContext.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        marshaller.marshal(MovieRecordLibrary.getInstance(), new File(LIBRARY_FILE_NAME));
    }

    public List <MovieRecord> readMovieRecordFromXMLfile () throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(MovieRecordLibrary.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        MovieRecordLibrary movieRecordLibrary = (MovieRecordLibrary) unmarshaller.unmarshal(new File(LIBRARY_FILE_NAME));

        List <MovieRecord> movieRecordList = movieRecordLibrary.getMovieRecordList();

        return movieRecordList;
    }
}
