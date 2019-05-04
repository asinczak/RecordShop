package pl.com.ttpsc.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement (name = "MusicShop")
@XmlAccessorType (XmlAccessType.FIELD)
public class MusicShop {

    private static MusicShop musicShop;

    private MusicShop(){}

    public static MusicShop getInstance(){
        if (musicShop == null){
            musicShop = new MusicShop();
        }
        return musicShop;
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
