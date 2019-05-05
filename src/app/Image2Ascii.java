package app;

import util.ImageHelper;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.imageio.ImageIO;

/**
 * Created by ch on 2019/4/27.
 */
public class Image2Ascii {
    static String ascii = "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\\\"^`'.";
    static String base = "@#&$%*o!;.";
    //main方法调用
    public static void main(String[] args) throws Exception {
        load("G:/phone.jpg", "F:/gif/woman.txt");//静态图片转字符保存为txt文件
    }
    /**
     * 图片转字符
     * @param imagePath 图片路径
     * @param txtPath 文本存放路径
     * @throws IOException
     */
    public static void load(String imagePath, String txtPath)
            throws IOException {
        BufferedImage image = ImageHelper.resize(ImageIO.read(new File(imagePath)),150,150);
        load(image, txtPath);
    }
    /**
     * 图片转字符
     * @param bi BufferedImage图片
     * @param txtPath 文本存放路径
     * @throws IOException
     */
    public static void load(BufferedImage bi, String txtPath)
            throws IOException {
        try {
            int width = bi.getWidth();
            int height = bi.getHeight();
            boolean flag = false;
            String result = "";
            for (int i = 0; i < height; i += 2) {
                for (int j = 0; j < width; j++) {
                    int pixel = bi.getRGB(j, i); // 下面三行代码将一个数字转换为RGB数字
                    int red = (pixel & 0xff0000) >> 16;
                    int green = (pixel & 0xff00) >> 8;
                    int blue = (pixel & 0xff);
                    float gray = 0.299f * red + 0.578f * green + 0.114f * blue;
                    int index = Math.round(gray * (base.length() + 1) / 255);
                    result += index >= base.length() ? " " : String.valueOf(base.charAt(index));
                }
                result += "\r\n";
            }
            flag = writeTxtFile(result,txtPath);//保存字符到文本文件
            System.out.println(flag?"图片转字符保存成功":"图片转字符保存失败");
        } catch (Exception e) {
            System.out.println("图片转字符异常"+e.getMessage());
        }
    }
    /**
     * 字符保存到txt文件中
     * @param imageStr 字符
     * @param txtPath  txt文件
     * @return boolean
     * @throws Exception
     */
    private static boolean writeTxtFile(String imageStr, String txtPath) throws Exception{
        // 先读取原有文件内容，然后进行写入操作
        boolean flag = false;
        String filein = imageStr;
        String temp = "";
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        FileOutputStream fos = null;
        PrintWriter pw = null;
        try {
            // 文件路径
            File file = new File(txtPath);
            if (!file.exists()) {
                file.createNewFile();
            }
            // 将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buf = new StringBuffer();
            // 保存该文件原有的内容
            for (int j = 1; (temp = br.readLine()) != null; j++) {
                buf = buf.append(temp);
            }
            buf.append(filein);
            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buf.toString().toCharArray());
            pw.flush();
            flag = true;
        } catch (IOException e) {
            System.out.println("文件保存失败"+e.getMessage());
        } finally {
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return flag;
    }
}