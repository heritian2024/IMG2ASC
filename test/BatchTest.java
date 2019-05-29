import util.BatchHelper;

/**
 * Created by ch on 2019/5/28.
 */
public class BatchTest {
    public static void main(String[] args) {

        String inGifPath = "C:/Users/ch/Desktop/素材/";
        String outGifPath = "C:/Users/ch/Desktop/gif/";

        BatchHelper.GifBatch(inGifPath, outGifPath, 2);

    }
}
