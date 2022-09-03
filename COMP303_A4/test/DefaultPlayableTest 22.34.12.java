import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultPlayableTest {
    private Song song1 = new Song("song1","artist1");
    private Song song2 = new Song("song2","artist2");
    private Podcast podcast = new Podcast("podcast","host");
    private PlayList playlist = new PlayList("playlist");
    private DefaultPlayableManager dpm = new DefaultPlayableManager();
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp(){
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void test_null(){
        assertThrows(AssertionError.class, () -> dpm.setDefaultPlayable(null));
    }

    @Test
    public void testDefaultPlayable_Episode(){
        podcast.createAndAddEpisode("episode");
        Podcast.Episode episode = podcast.getEpisode(0);
        dpm.setDefaultPlayable(episode);
        Playable e = dpm.getDefaultPlayable();
        assertTrue(e instanceof Podcast.Episode);
        assertEquals(((Podcast.Episode) e).getEpisodeNumber(),1);
    }

    @Test
    public void testDefaultPlayable_Song(){
        dpm.setDefaultPlayable(song1);
        Playable s = dpm.getDefaultPlayable();
        assertTrue(s instanceof Song);
        assertEquals(((Song) s).getTitle(),"song1");
    }

    @Test
    public void testDefaultPlayable_Playlist(){
        playlist.addPlayable(song1);
        playlist.addPlayable(song2);
        dpm.setDefaultPlayable(playlist);
        Playable p = dpm.getDefaultPlayable();
        assertTrue(p instanceof PlayList);
        ArrayList<Playable> expected = new ArrayList<>();
        expected.add(song1);
        expected.add(song2);
        assertEquals(expected,((PlayList) p).getList());
    }

}
