package app;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by ch on 2019/5/26.
 */
public class BaseConvert {

    public String SPLIT_TYPOS = "\r\n";
    public String FONT = "微软雅黑";

    /**
     * 文本转图片
     * 例如，"qwer\nkj\n234\n"转为
     * qwer
     * nkj
     * 234
     *
     * @param width
     * @param height
     * @param content 输入文本，以换行符分隔
     * @param outFile 输出图片文件
     * @return
     */
    public BufferedImage txt2img(int width, int height, String content, String outFile, int speed, boolean logFlag) {
        //绘图准备
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bufferedImage.createGraphics();
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(Color.BLACK);//设置前景色
        graphics.setFont(new Font(FONT, Font.PLAIN, speed));
        //文本内容解析
        String[] strs = content.split(SPLIT_TYPOS);
        for (int i = 0; i < strs.length; i++) {
            for (int j = 0; j < strs[i].length(); j++) {
                graphics.drawString(String.valueOf(strs[i].charAt(j)), j * speed, (i + 1) * speed);
            }
        }
        graphics.dispose();
        if (logFlag) {
            // 保存图片
            try {
                FileOutputStream fos = new FileOutputStream(new File(outFile));
                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);
                encoder.encode(bufferedImage);
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                //TODO:替换为log4j
                System.out.println("输出路径不存在");
            } catch (IOException e) {
                e.printStackTrace();
                //TODO:替换为log4j
                System.out.println("IO错误");
            }
        }
        return bufferedImage;
    }
}
