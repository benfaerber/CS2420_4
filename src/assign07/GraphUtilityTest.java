package assign07;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphUtilityTest {
    List<Integer> smallSrcList;
    List<Integer> smallDestList;
    Integer smallSrcData;
    Integer smallDestData;

    @BeforeEach
    void setUp() throws Exception {
        smallSrcList = new ArrayList<>();
        smallSrcList.add(1);
        smallSrcList.add(2);
        smallSrcList.add(3);
        smallDestList = new ArrayList<>();
        smallDestList.add(4);
        smallDestList.add(5);
        smallDestList.add(6);
        smallDestData = 1;
        smallSrcData = 6;
    }

    @AfterEach
    void tearDown() throws Exception {}

    @Test
    void testAreConnected() {
        assertTrue(GraphUtility.areConnected(smallSrcList, smallDestList, smallSrcData, smallDestData));
    }
}
