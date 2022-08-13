import app.ImageConvert;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageConvertTest {
    @Test
    public void test() {
        String infile = ".\\resources\\img\\langlang.jpg";
        String path = ".\\resources\\img\\";
        ImageConvert imageConvert = new ImageConvert(3);
        imageConvert.Image2Text(infile, path);
        imageConvert.Image2Image(infile, path+"langlang_ascii.jpg");
    }

    @Test
    public void test_BufferedImage() throws Exception {
//        String infile = "C:\\Users\\cfets\\Desktop\\sample.jpg";
//        String outFile = "C:\\Users\\cfets\\Desktop\\sample_ascii.jpg";
        String infile = ".\\resources\\img\\langlang.jpg";
        String outFile = ".\\resources\\img\\langlang_ascii_BufferedImage.jpg";

        //入读图片
        File file = new File(infile);
        BufferedImage bufferedImage = ImageIO.read(file);

        //BufferedImage转换
        ImageConvert imageConvert = new ImageConvert(3);
        BufferedImage asciiBufferedImage = null;
        asciiBufferedImage = imageConvert.Image2AsciiImage(bufferedImage);

        //写入文件
        FileOutputStream fos = new FileOutputStream(new File(outFile));
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);
        encoder.encode(asciiBufferedImage);
        fos.close();
    }
}
