import com.jsen.test.Boot;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Boot.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SPBTest {

    @Test
    public void testSimple() {

    }

}
