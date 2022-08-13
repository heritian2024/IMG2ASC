package app;

import algo.RGB2Gray;
import util.FileHelper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.apache.tools.ant.types.resources.MultiRootFileSet.SetType.file;

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
     * @param inImagePath      输入Image路径
     * @param outDirectoryPath 输出Text路径
     */
    public void Image2Text(String inImagePath, String outDirectoryPath) {
        try {
            File file = new File(inImagePath);
            BufferedImage bufferedImage = ImageIO.read(file);
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
            String SUFFIX = SPEED + "-";
            FileHelper.WriteFile(sb.toString(), outDirectoryPath + SUFFIX + file.getName() + ".txt");
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
            File file = new File(inImagePath);
            if(file.exists()){
                BufferedImage bufferedImage = ImageIO.read(file);
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
                String SUFFIX = SPEED + "-";
                txt2img(width, height, sb.toString(), outImagePath, SPEED, true);
            }
        } catch (IOException e) {
            e.printStackTrace();
            //TODO:替换为log4j
            System.out.println("文件读取失败");
        }
    }

    /**
     * BufferedImage直接转换
     * @param inBufferedImage
     * @return
     */
    public BufferedImage Image2AsciiImage(BufferedImage inBufferedImage) {
        BufferedImage asciiBufferedImage = null;
        StringBuffer asciiContent = new StringBuffer();
        int width = inBufferedImage.getWidth();
        int height = inBufferedImage.getHeight();
        int minx = inBufferedImage.getMinX();
        int miny = inBufferedImage.getMinY();
        for (int j = miny; j < height; j += SPEED) {
            for (int k = minx; k < width; k += SPEED) {
                int pixel = inBufferedImage.getRGB(k, j);
                char ascii = RGB2Gray.conver(pixel);
                asciiContent.append(ascii);
            }
            asciiContent.append("\r\n");
        }
        //绘图准备
        asciiBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = asciiBufferedImage.createGraphics();
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(Color.BLACK);//设置前景色
        graphics.setFont(new Font(FONT, Font.PLAIN, SPEED));
        //文本内容解析
        String[] strs = asciiContent.toString().split(SPLIT_TYPOS);
        for (int i = 0; i < strs.length; i++) {
            for (int j = 0; j < strs[i].length(); j++) {
                graphics.drawString(String.valueOf(strs[i].charAt(j)), j * SPEED, (i + 1) * SPEED);
            }
        }
        graphics.dispose();
        return asciiBufferedImage;
    }
}
