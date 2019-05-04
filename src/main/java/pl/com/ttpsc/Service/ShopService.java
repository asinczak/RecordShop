package pl.com.ttpsc.Service;

import pl.com.ttpsc.Data.MusicShop;

public class ShopService {

    private static ShopService shopService;
    private ShopService () {}

    public static ShopService getInstance() {
        if(shopService == null) {
            shopService = new ShopService();
        }
        return shopService;
    }

    MusicShop musicShop = MusicShop.getInstance();

    public boolean checkingIfsuchIdExists (int id) {
        boolean checking = musicShop.getRecordList().stream().anyMatch(record -> id == record.getId());

        return checking;
    }

}
