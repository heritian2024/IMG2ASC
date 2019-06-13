import app.ImageConvert;

public class ImageTest {

    public static void main(String[] args) {
        String infile = "C:/Users/ch/Desktop/sample.jpg";
        String path = "C:/Users/ch/Desktop/";

        ImageConvert imageConvert = new ImageConvert(3);

        imageConvert.Image2Text(infile, path);

        imageConvert.Image2Image(infile, path);

    }
}

