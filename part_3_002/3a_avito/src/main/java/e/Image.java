package e;

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
}
