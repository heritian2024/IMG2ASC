import app.ImageConvert;

public class ImageTest {

    public static void main(String[] args) {
        String infile = "C:/Users/ch/Desktop/sample.png";
        String outfile = "C:/Users/ch/Desktop/sample.txt";

        ImageConvert.Image2Text(infile, outfile);

    }
}
