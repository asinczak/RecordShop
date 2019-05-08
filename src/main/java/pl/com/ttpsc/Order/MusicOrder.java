package pl.com.ttpsc.Order;


import pl.com.ttpsc.Data.MusicRecord;
import pl.com.ttpsc.Data.Record;

import java.util.LinkedHashMap;
import java.util.Map;

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
