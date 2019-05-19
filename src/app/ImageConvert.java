package app;

import algo.RGB2Gray;
import util.FileHelper;
import util.ImageHelper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by ch on 2019/5/19.
 */
public class ImageConvert {

    /**
     * 图片转文本
     *
     * @param inFilePath  输入Image路径
     * @param outFilePath 输出Text路径
     */
    public static void Image2Text(String inFilePath, String outFilePath) {
        try {
            BufferedImage bufferedImage = ImageHelper.resize(ImageIO.read(new File(inFilePath)), 150, 150);
            StringBuffer sb = new StringBuffer();
            // convert
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
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
     * @param inFilePath
     * @param outFilePath
     */
    public void Image2Image(String inFilePath, String outFilePath) {

    }

}
