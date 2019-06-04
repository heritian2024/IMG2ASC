import app.ImageConvert;

public class ImageTest {

    public static void main(String[] args) {
        String infile = "C:/Users/ch/Desktop/sample.jpg";
        String outfile = "C:/Users/ch/Desktop/sample.txt";
        String outimage = "C:/Users/ch/Desktop/sample-new.png";

        ImageConvert imageConvert = new ImageConvert(3);

        imageConvert.Image2Text(infile, outfile);

        imageConvert.Image2Image(infile, outimage);

    }
}

