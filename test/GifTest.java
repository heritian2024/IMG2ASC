import app.GifConvert;
import util.FileHelper;

/**
 * Created by ch on 2019/5/24.
 */
public class GifTest {

    public static void main(String[] args) {

        String gif = "C:/Users/ch/Desktop/素材/香蕉君回头.gif";
        String path = "C:/Users/ch/Desktop/gif/";
        FileHelper.CheckPath(path);

        GifConvert gifConvert = new GifConvert(3);

//        gifConvert.Gif2Texts(gif, path);
        gifConvert.Gif2Gif(gif, path);

    }
}
