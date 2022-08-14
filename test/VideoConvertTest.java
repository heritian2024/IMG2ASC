import org.junit.Test;

import static app.VideoConvert.Flv2AsciiFlv;

public class VideoConvertTest {
    @Test
    public void test() throws Exception {
        for (int i = 1; i < 5; i++) {
            int finalI = i;
            System.out.println("创建线程："+finalI);
            new Thread(()->{
                try {
                    Flv2AsciiFlv("C:\\Users\\cfets\\Desktop\\765060141_nb3-1-64.flv",
                            "C:\\Users\\cfets\\Desktop\\Thread",
                            "jay-"+ finalI,
                            finalI);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(e);
                }
            }).start();
        }
        System.in.read();//加入该代码，让主线程不挂掉
    }
}
