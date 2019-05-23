package app;

import algo.RGB2Gray;
import com.sun.imageio.plugins.gif.GIFImageReader;
import com.sun.imageio.plugins.gif.GIFImageReaderSpi;
import util.FileHelper;

import javax.imageio.stream.FileImageInputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by ch on 2019/5/19.
 */
public class GifConvert {

    static int SPEED = 10;

    /**
     * 动图转换，输出多文本
     *
     * @param inFilePath       输入GIF路径
     * @param outDirectoryPath 输出文件夹路径
     */
    public static void Gif2Texts(String inFilePath, String outDirectoryPath) {
        try {
            FileImageInputStream fileImageInputStream = new FileImageInputStream(new File(inFilePath));
            GIFImageReader gifImageReader = new GIFImageReader(new GIFImageReaderSpi());
            gifImageReader.setInput(fileImageInputStream);
            // 每一帧都保存为文本
            int number = gifImageReader.getNumImages(true);
            for (int i = 0; i < number; i++) {
                StringBuffer sb = new StringBuffer();
                int width = gifImageReader.read(i).getWidth();
                int height = gifImageReader.read(i).getHeight();
                int minx = gifImageReader.read(i).getMinX();
                int miny = gifImageReader.read(i).getMinY();
                for (int j = miny; j < height; j += SPEED) {
                    for (int k = minx; k < width; k += SPEED) {
                        int pixel = gifImageReader.read(i).getRGB(k, j);
                        char ascii = RGB2Gray.conver(pixel);
                        sb.append(ascii);
                    }
                    sb.append("\r\n");
                }
                FileHelper.WriteFile(sb.toString(), outDirectoryPath + "/" + i + ".txt");
            }
        } catch (IOException e) {
            e.printStackTrace();
            //TODO:替换为log4j
            System.out.println("文件读取失败");
        }
    }

    public void Gif2Gif(String inFilePath, String outDirectoryPath) {

    }


}
