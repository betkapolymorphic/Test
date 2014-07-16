package teamViewer.download;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by student on 7/16/2014.
 */
public class SerializedBufferedImage implements Externalizable {
    private BufferedImage image;

    public SerializedBufferedImage(BufferedImage image) {
        this.image = image;
    }

    public SerializedBufferedImage() {
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        ByteOutputStream byteOutputStream = new ByteOutputStream();
        ImageIO.write(image,"png", byteOutputStream);
        int sizeBytes = byteOutputStream.size();
        out.writeInt(sizeBytes);
        out.write(byteOutputStream.getBytes());
        System.out.println("bytes = "+sizeBytes);

        int  p  =0;
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        ByteInputStream bis = new ByteInputStream();
        int avaible = in.readInt();
        System.out.printf("bytes = "+avaible);

        byte[] b= new byte[avaible];

        int tmp = 0;
        for(int i=0;i<avaible;i++) {
            byte y = in.readByte();

            b[i] = y;
        }
        bis.setBuf(b);


        image = ImageIO.read(bis);
         int o  = 0;

    }
    public BufferedImage getImage() {
        return image;
    }
}
