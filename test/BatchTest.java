import util.BatchHelper;

/**
 * Created by ch on 2019/5/28.
 */
public class BatchTest {
    public static void main(String[] args) {

//        String inGifPath = "C:/Users/ch/Desktop/GifIn/";
//        String outGifPath = "C:/Users/ch/Desktop/GifOut/";
//        for (int i = 1; i <= 3; i++) {
//            BatchHelper.GifBatch(inGifPath, outGifPath, i);
//        }

        String inImagePath = "C:/Users/ch/Desktop/ImageIn/";
        String outImagePath = "C:/Users/ch/Desktop/ImageOut/";
        for (int i = 1; i <= 6; i++) {
            BatchHelper.ImageBatch(inImagePath, outImagePath, i);
        }
    }
}
