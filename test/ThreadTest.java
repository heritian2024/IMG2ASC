import util.ThreadHelper;

/**
 * Created by ch on 2019/6/14.
 */
public class ThreadTest {

    public static void main(String[] args) {

        String inImagePath = "C:/Users/ch/Desktop/Thread/ImageIn/";
        String outImagePath = "C:/Users/ch/Desktop/Thread/ImageOut/";
        for (int i = 1; i <= 3; i++) {
            ThreadHelper.ImageThread(inImagePath, outImagePath, 2);
        }

        String inGifPath = "C:/Users/ch/Desktop/Thread/GifIn/";
        String outGifPath = "C:/Users/ch/Desktop/Thread/GifOut/";
        for (int i = 2; i <= 3; i++) {
            ThreadHelper.GifThread(inGifPath, outGifPath, i);
        }

    }
}
