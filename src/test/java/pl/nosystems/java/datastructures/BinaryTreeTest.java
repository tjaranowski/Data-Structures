package pl.nosystems.java.datastructures;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public final class BinaryTreeTest {

    private BinaryTree<Integer> binaryTree;

    @Mock
    private BinaryTree.DataPlaceChooser<Integer> dataPlaceChooser;

    @Before
    public void setUp() {
        binaryTree = new BinaryTree<>();
    }

    @Test
    public void putOneWhenRootIsNullTest() {
        binaryTree.put(0, null);
    }

    @Test
    public void putTwoWhenRootIsNullGoLeftTest() {
        binaryTree.put(0, null);

        when(dataPlaceChooser.getDirection(0)).thenReturn(BinaryTree.DIR.LEFT);

        binaryTree.put(1, dataPlaceChooser);

        verify(dataPlaceChooser, times(1)).getDirection(0);
    }

    @Test
    public void putTwoWhenRootIsNullGoRightTest() {
        binaryTree.put(0, null);

        when(dataPlaceChooser.getDirection(0)).thenReturn(BinaryTree.DIR.RIGHT);

        binaryTree.put(1, dataPlaceChooser);

        verify(dataPlaceChooser, times(1)).getDirection(0);
    }

    @Test
    public void putThreeTest() {
        final BinaryTree.DataPlaceChooser<Integer> dataPlaceChooser = new BinaryTree.DataPlaceChooser<Integer>() {

            @Override
            public BinaryTree.DIR getDirection(Integer currentNodeData) {
                return BinaryTree.DIR.LEFT;
            }
        };

        binaryTree.put(0, dataPlaceChooser);
        binaryTree.put(1, dataPlaceChooser);
        binaryTree.put(2, dataPlaceChooser);
    }

}
