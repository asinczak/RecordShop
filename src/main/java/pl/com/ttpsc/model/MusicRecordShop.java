package pl.com.ttpsc.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement (name = "MusicRecordShop")
@XmlAccessorType (XmlAccessType.FIELD)
public class MusicRecordShop {

    private static MusicRecordShop musicRecordShop;

    private MusicRecordShop(){}

    public static MusicRecordShop getInstance(){
        if (musicRecordShop == null){
            musicRecordShop = new MusicRecordShop();
        }
        return musicRecordShop;
    }

    @XmlElement (name = "MusicRecord")
    private static List<MusicRecord> musicRecordList = new ArrayList<>();

    public List<MusicRecord> getRecordList() {
        return musicRecordList;
    }

    public void setRecordList(List<MusicRecord> musicRecordList) {
       this.musicRecordList = musicRecordList;
    }
}
