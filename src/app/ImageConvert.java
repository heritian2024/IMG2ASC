package app;

import algo.RGB2Gray;
import com.sun.imageio.plugins.gif.GIFImageReader;
import com.sun.imageio.plugins.gif.GIFImageReaderSpi;
import util.FileHelper;
import util.ImageHelper;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by ch on 2019/5/19.
 */
public class ImageConvert extends BaseConvert {

    private int SPEED = 5;

    public ImageConvert() {
    }

    public ImageConvert(int speed) {
        this.SPEED = speed;
    }

    /**
     * 图片转文本
     *
     * @param inImagePath 输入Image路径
     * @param outFilePath 输出Text路径
     */
    public void Image2Text(String inImagePath, String outFilePath) {
        try {
//            BufferedImage bufferedImage = ImageHelper.resize(ImageIO.read(new File(inImagePath)), 150, 150);
            BufferedImage bufferedImage = ImageIO.read(new File(inImagePath));
            StringBuffer sb = new StringBuffer();
            // convert
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            for (int i = 0; i < height; i += SPEED) {
                for (int j = 0; j < width; j += SPEED) {
                    int pixel = bufferedImage.getRGB(j, i);
                    char ascii = RGB2Gray.conver(pixel);
                    sb.append(ascii);
                }
                sb.append("\r\n");
            }
            FileHelper.WriteFile(sb.toString(), outFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            //TODO:替换为log4j
            System.out.println("文件读取失败");
        }
    }

    /**
     * 将转换后的ASCII文本保存为图片
     *
     * @param inImagePath
     * @param outImagePath
     */
    public void Image2Image(String inImagePath, String outImagePath) {
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(inImagePath));
            StringBuffer sb = new StringBuffer();
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            int minx = bufferedImage.getMinX();
            int miny = bufferedImage.getMinY();
            for (int j = miny; j < height; j += SPEED) {
                for (int k = minx; k < width; k += SPEED) {
                    int pixel = bufferedImage.getRGB(k, j);
                    char ascii = RGB2Gray.conver(pixel);
                    sb.append(ascii);
                }
                sb.append("\r\n");
            }
            txt2img(width, height, sb.toString(), outImagePath, SPEED, true);
        } catch (IOException e) {
            e.printStackTrace();
            //TODO:替换为log4j
            System.out.println("文件读取失败");
        }
    }

}
