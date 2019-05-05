package pl.com.ttpsc.Order;


import pl.com.ttpsc.Data.MusicRecord;

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

    private Map<Integer, MusicRecord> musicRecordOrder = new LinkedHashMap<>();

    public Map<Integer, MusicRecord> getMusicRecordOrder() {
        return musicRecordOrder;
    }

    public void setMusicRecordOrder(Map<Integer, MusicRecord> musicRecordOrder) {
        this.musicRecordOrder = musicRecordOrder;
    }
}
