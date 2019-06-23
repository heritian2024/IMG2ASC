package app;

import algo.RGB2Gray;
import com.sun.imageio.plugins.gif.GIFImageReader;
import com.sun.imageio.plugins.gif.GIFImageReaderSpi;
import util.FileHelper;
import util.GIF.AnimatedGifEncoder;

import javax.imageio.stream.FileImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by ch on 2019/5/19.
 */
public class GifConvert extends BaseConvert {

    private int SPEED = 5;

    public GifConvert() {
    }

    public GifConvert(int speed) {
        this.SPEED = speed;
    }

    /**
     * 动图转换，输出多文本
     *
     * @param inGifPath        输入GIF路径
     * @param outDirectoryPath 输出文件夹路径
     */
    public void Gif2Texts(String inGifPath, String outDirectoryPath) {
        try {
            FileImageInputStream fileImageInputStream = new FileImageInputStream(new File(inGifPath));
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
                String SUFFIX = SPEED + "-";
                FileHelper.WriteFile(sb.toString(), outDirectoryPath + "/" + SUFFIX + i + ".txt");
            }
        } catch (IOException e) {
            e.printStackTrace();
            //TODO:替换为log4j
            System.out.println("文件读取失败");
        }
    }

    /**
     * 动图转换，输出文本式动图
     *
     * @param inGifPath        输入GIF路径
     * @param outDirectoryPath 输出文件夹路径
     */
    public void Gif2Gif(String inGifPath, String outDirectoryPath) {
        try {
            File file = new File(inGifPath);
            FileImageInputStream fileImageInputStream = new FileImageInputStream(file);
            GIFImageReader gifImageReader = new GIFImageReader(new GIFImageReaderSpi());
            gifImageReader.setInput(fileImageInputStream);
            // 每一帧都保存为图片
            int number = gifImageReader.getNumImages(true);
            BufferedImage[] bufferedImages = new BufferedImage[number];
            for (int i = 0; i < number; i++) {
                System.out.println(i);
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
                String SUFFIX = SPEED + "-";
                bufferedImages[i] = txt2img(width, height, sb.toString(), outDirectoryPath + "/" + SUFFIX + i + ".img", SPEED, false);
            }
            JPGS2GIF(bufferedImages, outDirectoryPath + SPEED + file.getName(), 200);
        } catch (IOException e) {
            e.printStackTrace();
            //TODO:替换为log4j
            System.out.println("文件读取失败");
        }
    }

    /**
     * 多张图片转动图方法
     *
     * @param bufferedImages
     * @param fileName
     * @param delay
     */
    private static void JPGS2GIF(BufferedImage[] bufferedImages, String fileName, int delay) {
        try {
            AnimatedGifEncoder animatedGifEncoder = new AnimatedGifEncoder();
            animatedGifEncoder.setRepeat(0);
            animatedGifEncoder.start(fileName);
            for (int i = 0; i < bufferedImages.length; i++) {
                animatedGifEncoder.setDelay(delay);
                animatedGifEncoder.addFrame(bufferedImages[i]);
            }
            animatedGifEncoder.finish();
        } catch (Exception e) {
            System.out.println("JPGS to GIF Failed.");
        }
    }
}
