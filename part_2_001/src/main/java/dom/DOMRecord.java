package dom;

import java.math.BigDecimal;

public class DOMRecord {
    private int bidVolume;
    private int askVolume;

    DOMRecord(int bidVolume, int askVolume) {
        this.bidVolume = bidVolume;
        this.askVolume = askVolume;
    }

    void increaseBidVolumeBy(int d) {
        this.bidVolume += d;
    }

    void increaseAskVolumeBy(int d) {
        this.askVolume += d;
    }

    int getBidVolume() {
        return this.bidVolume;
    }

    int getAskVolume() {
        return this.askVolume;
    }
}
