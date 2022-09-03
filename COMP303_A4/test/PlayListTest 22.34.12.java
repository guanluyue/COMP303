import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PlayListTest {
    private PlayList playlist;
    private final ArrayList<Song> expected = new ArrayList<>();
    private final Song s1 = new Song("song1","artist1");
    private final Song s2 = new Song("song2","artist2");
    private final Song s3 = new Song("song3","artist3");
    private final Song s4 = new Song("song4","artist4");
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void clearList(){
        playlist = new PlayList("name1");
        playlist.clear();
        expected.clear();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testAddPlayable_Null() throws AssertionError{
        Assertions.assertThrows(AssertionError.class, ()-> playlist.addPlayable(null));
    }

    @Test
    public void testAddPlayable(){
        playlist.addPlayable(s1);
        expected.add(s1);
        ArrayList<Playable> actual = playlist.getList();
        playlist.play();
        assertEquals("Now playing song1\n", outputStream.toString());
    }

    @Test
    public void testRemovePlayable(){
        playlist.addPlayable(s1);
        playlist.removePlayable(0);
        assertEquals(expected,playlist.getList());
    }

    @Test
    public void testSetName(){
        playlist.setName("name2");
        assertEquals(playlist.getName(),"name2");
    }

    @Test
    public void testShuffle(){
        playlist.addPlayable(s1);
        playlist.addPlayable(s2);
        playlist.addPlayable(s3);
        playlist.shuffle();
        //playlist.play();
        expected.add(s1);
        expected.add(s2);
        expected.add(s3);
        ArrayList<Playable> actual = playlist.getList();
        assertFalse(expected.equals(actual));
    }

    @Test
    public void testUndo_Empty(){
        playlist.addPlayable(s1);
        playlist.undo();
        //This is an extra undo that does nothing to the list
        playlist.undo();
        ArrayList<Playable> actual = playlist.getList();
        assertEquals(expected, actual);
    }

    @Test
    public void testUndo_Multiple(){
        playlist.addPlayable(s1);
        playlist.addPlayable(s2);
        playlist.addPlayable(s3);
        playlist.undo();
        playlist.undo();
        expected.add(s1);
        ArrayList<Playable> actual = playlist.getList();
        assertEquals(expected, actual);
    }

    @Test
    public void testUndo_Add(){
        playlist.addPlayable(s1);
        playlist.undo();
        ArrayList<Playable> actual = playlist.getList();
        assertEquals(expected, actual);
    }

    @Test
    public void testRedo_Empty(){
        playlist.redo();
        ArrayList<Playable> actual = playlist.getList();
        assertEquals(expected,actual);
    }

    @Test
    public void testRedo_AfterNonStateModifying(){
        playlist.addPlayable(s1);
        String dummy = playlist.getName();
        playlist.redo();
        //s1 will not be again added to playlist because a non-state modifying method was called
        expected.add(s1);
        ArrayList<Playable> actual = playlist.getList();
        assertEquals(expected,actual);

    }

    @Test
    public void testRedoLastCommand_Add(){
        playlist.addPlayable(s1);
        playlist.undo();
        playlist.redo();
        playlist.addPlayable(s2);
        playlist.redo();
        expected.add(s1);
        expected.add(s2);
        expected.add(s2);
        ArrayList<Playable> actual = playlist.getList();
        assertEquals(expected, actual);
    }

    @Test
    public void testRedoUndo_Add(){
        playlist.addPlayable(s1);
        playlist.undo();
        playlist.redo();
        expected.add(s1);
        ArrayList<Playable> actual = playlist.getList();
        assertEquals(expected, actual);
    }

    @Test
    public void testMultipleRedoUndo_Add(){
        playlist.addPlayable(s1);
        playlist.addPlayable(s2);
        playlist.addPlayable(s3);
        playlist.undo();
        playlist.undo();
        playlist.undo();
        playlist.redo();
        expected.add(s1);
        ArrayList<Playable> actual = playlist.getList();
        assertEquals(expected, actual);
    }

    @Test
    public void testUndo_Remove(){
        playlist.addPlayable(s1);
        playlist.removePlayable(0);
        playlist.undo();
        expected.add(s1);
        ArrayList<Playable> actual = playlist.getList();
        assertEquals(expected, actual);
    }

    @Test
    public void test_RemoveInvalidIndex(){
        playlist.addPlayable(s1);

        Assertions.assertThrows(AssertionError.class, ()-> playlist.removePlayable(-1));
    }

    @Test
    public void test_RemoveOutOfBound(){

        Assertions.assertThrows(AssertionError.class, ()-> playlist.removePlayable(0));
    }


    @Test
    public void testRedo_RemoveOutOfBound(){
        playlist.addPlayable(s1);
        playlist.removePlayable(0);
        Assertions.assertThrows(AssertionError.class, ()-> playlist.redo());
    }

    @Test
    public void testRedoUndo_Remove(){
        playlist.addPlayable(s1);
        playlist.removePlayable(0);
        playlist.undo();
        playlist.redo();
        assertEquals(expected, playlist.getList());
    }

    @Test
    public void testMultipleRedo_Remove(){
        playlist.addPlayable(s1);
        playlist.addPlayable(s2);
        playlist.addPlayable(s3);
        playlist.addPlayable(s4);
        playlist.removePlayable(0);
        playlist.redo();
        playlist.redo();
        expected.add(s4);
        assertEquals(expected,playlist.getList());
    }

    @Test
    public void testRedo_WhenNoUndone(){
        playlist.addPlayable(s1);
        playlist.addPlayable(s2);
        playlist.undo();
        playlist.redo();
        //These following redo()s will do nothing because there's no undone
        playlist.redo();
        playlist.redo();
        expected.add(s1);
        expected.add(s2);
        assertEquals(expected,playlist.getList());
    }

    @Test
    public void test_SetNameNull(){
        Assertions.assertThrows(AssertionError.class, ()-> playlist.setName(null));
    }

    @Test
    public void testUndo_SetName(){
        playlist.setName("name2");
        playlist.undo();
        assertEquals(playlist.getName(),"name1");
    }

    @Test
    public void testRedoUndo_SetName(){
        playlist.setName("name2");
        playlist.undo();
        playlist.redo();
        assertEquals(playlist.getName(),"name2");
    }

    @Test
    public void testUndo_Shuffle(){
        playlist.addPlayable(s1);
        playlist.addPlayable(s2);
        playlist.addPlayable(s3);
        playlist.shuffle();
        playlist.undo();
        expected.add(s1);
        expected.add(s2);
        expected.add(s3);
        assertEquals(expected, playlist.getList());
    }

    @Test
    public void testRedoUndo_Shuffle(){
        playlist.addPlayable(s1);
        playlist.addPlayable(s2);
        playlist.addPlayable(s3);
        playlist.shuffle();
        playlist.undo();
        playlist.redo();
        expected.add(s1);
        expected.add(s2);
        expected.add(s3);
        ArrayList<Playable> actual = playlist.getList();
        assertFalse(expected.equals(actual));
    }
}
