
import entities.Album;
import entities.Artista;
import entities.Banda;
import entities.Musica;
import enums.MusicaGenero;

public class App {
    public static void main(String[] args) throws Exception {
        Artista bruceDickinson = new Artista("Bruce Dickinson", "Britânico", "Vocalista");
        Artista steveHarris = new Artista("Steve Harris", "Britânico", "Baixista");
        Artista daveMurray = new Artista("Dave Murray", "Britânico", "Guitarrista");
        Artista janickGers = new Artista("Janick Gers", "Britânico", "Guitarrista");
        Artista nickoMcBrain = new Artista("Nicko McBrain", "Britânico", "Baterista");
        Artista adrianSmith = new Artista("Adrian Smith", "Britânico", "Guitarrista");

        Banda ironMaiden = new Banda("Iron Maiden", "Britânico", MusicaGenero.ROCK, 1975, null);
        ironMaiden.addIntegrante(bruceDickinson);
        ironMaiden.addIntegrante(steveHarris);
        ironMaiden.addIntegrante(daveMurray);
        ironMaiden.addIntegrante(janickGers);
        ironMaiden.addIntegrante(nickoMcBrain);
        ironMaiden.addIntegrante(adrianSmith);

        Musica invaders = new Musica("Invaders", MusicaGenero.ROCK, 1982, 450, 270000000);
        Musica childrenOfTheDamned = new Musica("Children of the Damned", MusicaGenero.ROCK, 1982, 300, 42180000);
        Musica thePrisoner = new Musica("The Prisoner", MusicaGenero.ROCK, 1982, 602, 28333000);
        Musica acaciaAvenue = new Musica("22 Acacia Avenue", MusicaGenero.ROCK, 1982, 636, 25000000);
        Musica theNumberOfTheBeast = new Musica("The Number of the Beast", MusicaGenero.ROCK, 1982, 420, 270000000);
        Musica runToTheHills = new Musica("Run to the Hills", MusicaGenero.ROCK, 1982, 240, 420000000);
        Musica gangland = new Musica("Gangland", MusicaGenero.ROCK, 1982, 300, 10000000);
        Musica hallowedBeThyName = new Musica("Hallowed Be Thy Name", MusicaGenero.ROCK, 1982, 480, 2000000);

        Album<Banda> theNumberOfTheBeastAlbum = new Album<Banda>("The Number of The Beast", ironMaiden, 1982,
                MusicaGenero.ROCK);
        theNumberOfTheBeastAlbum.addMusica(invaders);
        theNumberOfTheBeastAlbum.addMusica(childrenOfTheDamned);
        theNumberOfTheBeastAlbum.addMusica(thePrisoner);
        theNumberOfTheBeastAlbum.addMusica(acaciaAvenue);
        theNumberOfTheBeastAlbum.addMusica(theNumberOfTheBeast);
        theNumberOfTheBeastAlbum.addMusica(runToTheHills);
        theNumberOfTheBeastAlbum.addMusica(gangland);
        theNumberOfTheBeastAlbum.addMusica(hallowedBeThyName);

        ironMaiden.addAlbum(theNumberOfTheBeastAlbum);

        System.out.println("Detalhes do álbum:");
        System.out.println(theNumberOfTheBeastAlbum);
    }
}
