
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import dominio.Album;
import dominio.Artista;
import dominio.Musica;
import dominio.Playlist;

public class App {
    public static void main(String[] args) {

        Musica musica1 = new Musica("Californication", "Rock", "./assets/Red-Hot-Chili-Peppers-Californication.wav",120);
        Musica musica2 = new Musica("Otherside", "Rock", "./assets/Red-Hot-Chili-Peppers-Otherside.wav", 120);

        List<Musica> musicas1 = new ArrayList<>();
        musicas1.add(musica1);
        musicas1.add(musica2);

        List<Album> albuns = new ArrayList<>();
        Album album1 = new Album("Primeiro album", 2000, musicas1);
        albuns.add(album1);

        Artista artista = new Artista("Red Hot Chili Peppers", albuns);
        artista.addAlbum(album1);

        Playlist playlist = new Playlist("Adicionadas recentemente", musicas1);

        AudioPlayer player = new AudioPlayer();
        player.carregarListaReproducao(artista.getAlbuns().get(0).getMusicas());

        // Cria o botão Play/Stop e configura sua ação
        JButton playStopButton = new JButton("Play"); // criação do botão
        playStopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!player.isPlaying) {
                    player.playAudio();
                    playStopButton.setText("Stop");
                } else {
                    player.stopAudio();
                    playStopButton.setText("Play");
                }
            }
        });

        JButton proximaButton = new JButton("Próxima"); // botão próxima música
        proximaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.proximaButton();
                playStopButton.setText("Stop");
            }
        });

        JButton anteriorButton = new JButton("Anterior"); // botão música anterior
        anteriorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.anteriorButton();
                playStopButton.setText("Stop");
            }
        });

        ImageIcon icon = new ImageIcon("./assets/music.png");
        JOptionPane.showOptionDialog(
                null,
                "Música: ",
                "PlayMusic",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                icon,
                new Object[] { anteriorButton, playStopButton, proximaButton }, playStopButton);

        // Fecha o clip de áudio ao encerrar o programa
        if (player.audioClip != null) {
            player.audioClip.close();
        }
    }
}