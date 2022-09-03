import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // TASK: Write runner code to show your work
        Library library = Library.getInstance();
        library.setName("Library 1");
        library.setLanguage("A singleton Library containing non-duplicated Songs and Podcasts.");

        //First we test the uniqueness of objects in the Song class
        Song s1=Song.get("Hello","Adele");
        Song s2=Song.get("hello","adele");
        //This should evaluate to TRUE because s1 and s2 are referring to the same object
        System.out.println(s1==s2);
        Song s3=Song.get("Easy on me","Adele");
        library.addSong(s1);
        library.addSong(s2);
        library.addSong(s3);
        //Since duplicates are not added, there should only be 2 songs in the library
        System.out.println(library);

        //Now we test the uniqueness of objects in the Podcast class
        Podcast p1=Podcast.get("Podcast","Gwen");
        Podcast p2=Podcast.get("Podcast","gwen");
        Podcast p3=Podcast.get("Another Podcast","Gwen");
        //This should evaluate to TRUE because p1 and p2 are referring to the same object
        System.out.println(p1==p2);
        Episode e1=new Episode(p1,"I like comp303",1);
        Episode e2=new Episode(p1,"I love comp303",2);
        Episode e3=new Episode(p3,"I need comp303",1);
        library.addPodcast(p1);
        library.addPodcast(p2);
        library.addPodcast(p3);
        //There should be only two Podcast in the library
        System.out.println(library);

        PlayList playlist1=new PlayList("PlayList 1");
        PlayList playlist2=new PlayList("PlayList 2");
        PlayList playlist3=new PlayList("PlayList 3");
        //Add the same playable objects in the same order to playlist1 and playlist2
        playlist1.addPlayable(s1);
        playlist1.addPlayable(e1);
        playlist2.addPlayable(s1);
        playlist2.addPlayable(e1);
        //Add the same playable objects in different order to playlist3
        playlist3.addPlayable(e1);
        playlist3.addPlayable(s1);

        library.addPlayList(playlist1);
        library.addPlayList(playlist2);
        library.addPlayList(playlist3);
        //There should be only 2 playlists because playlist1 and 2 are the same
        System.out.println(library);

        ArrayList<Playable> playables = library.getaContent();
        for (Playable playable: playables){
            playable.play();
        }

    }
}
