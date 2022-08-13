import org.junit.Test;

import static app.VideoConvert.Flv2AsciiFlv;

public class VideoConvertTest {
    @Test
    public void test() throws Exception {
        Flv2AsciiFlv("C:\\Users\\cfets\\Desktop\\765060141_nb3-1-64.flv",
                "C:\\Users\\cfets\\Desktop\\Thread",
                "jay",
                4);
    }
}
