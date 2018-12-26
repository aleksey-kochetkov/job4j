package e.model;

import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.io.File;

public class StubMultipartFile implements MultipartFile {

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getOriginalFilename() {
        return null;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public long getSize() {
        return 3;
    }

    @Override
    public byte[] getBytes() {
        return new byte[] {1, 2, 3};
    }

    @Override
    public InputStream getInputStream() {
        return null;
    }

    @Override
    public void transferTo(File var1) {
    }
}
