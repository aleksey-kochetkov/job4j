package e.domain;

import java.util.Arrays;

public class Image {
    private int adId;
    private byte[] bytes;

    public Image() {
    }

    public Image(byte[] bytes) {
        this.bytes = bytes;
    }

    public int getAdId() {
        return this.adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }

    public byte[] getBytes() {
        return this.bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Image)) {
            return false;
        }
        Image i = (Image) o;
        return (i.adId == this.adId)
                                   && Arrays.equals(i.bytes, this.bytes);
    }

    @Override
    public int hashCode() {
        int result = this.adId;
        result = 31 * result + Arrays.hashCode(this.bytes);
        return result;
    }
}
