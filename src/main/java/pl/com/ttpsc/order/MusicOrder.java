package pl.com.ttpsc.order;


import pl.com.ttpsc.model.Record;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedHashMap;
import java.util.Map;

@XmlRootElement (name = "MusicOrder")
@XmlAccessorType (XmlAccessType.FIELD)
public class MusicOrder {

    private static MusicOrder musicOrder;
    private MusicOrder(){}

    public static MusicOrder getInstance(){
        if (musicOrder == null){
            musicOrder = new MusicOrder();
        }
        return musicOrder;
    }

    private Map<Integer, Record> musicRecordOrder = new LinkedHashMap<>();

    public Map<Integer, Record> getMusicRecordOrder() {
        return musicRecordOrder;
    }

    public void setMusicRecordOrder(Map<Integer, Record> musicRecordOrder) {
        this.musicRecordOrder = musicRecordOrder;
    }
}
