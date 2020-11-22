package algo;

/**
 * Created by ch on 2019/5/19.
 * RGB色转为灰度色算法
 * https://blog.csdn.net/xdrt81y/article/details/8289963
 * Gray = R*0.299 + G*0.587 + B*0.114
 */
public class RGB2Gray {
//    private static String base = "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\\\"^`'.";
//    private static String base = "@#&$%*o!;.";
    private static String base = "@#&$%*o!;.";

    public static char conver(int pixel) {
        int red = (pixel & 0xff0000) >> 16;
        int green = (pixel & 0xff00) >> 8;
        int blue = (pixel & 0xff);
        float gray = 0.299f * red + 0.578f * green + 0.114f * blue;
        int index = Math.round(gray * (base.length() + 1) / 255);
        return index >= base.length() ? ' ' : base.charAt(index);
//        int index = Math.round(gray * (ascii.length() + 1) / 255);
//        return index > ascii.length() ? ' ' : ascii.charAt(index);
    }
}
