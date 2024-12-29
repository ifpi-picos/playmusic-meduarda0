import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import dominio.Musica;

public class AudioPlayer {
  public Clip audioClip;
  public boolean isPlaying = false;
  private List<Musica> listaDeReproducao = new ArrayList<>();
  private int index = 0;

  public void loadAudio(String filePath) {
    System.out.println("loadAudio: " + filePath);
    try {
      // Carrega o arquivo de áudio
      File audioFile = new File(filePath);
      AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

      // Configura o Clip
      AudioFormat format = audioStream.getFormat();
      DataLine.Info info = new DataLine.Info(Clip.class, format);
      audioClip = (Clip) AudioSystem.getLine(info);

      audioClip.open(audioStream);
    } catch (UnsupportedAudioFileException e) { //se der esse tipo de erro
      System.out.println("O formato do arquivo de áudio não é suportado.");
      e.printStackTrace();
    } catch (LineUnavailableException e) {
      System.out.println("A linha de áudio não está disponível.");
      e.printStackTrace();
    } catch (IOException e) {
      System.out.println("Erro ao ler o arquivo de áudio.");
      e.printStackTrace();
    }
  }

  public void carregarListaReproducao (List<Musica> musicas){
    this.listaDeReproducao = musicas;
    this.index=0;
    loadAudio (musicas.get(index).getArquivoAudio());
  }

  public void proximaButton (){
    if (listaDeReproducao != null){
      stopAudio();
      if ( index <(listaDeReproducao.size() -1)){
        index ++;
        loadAudio(listaDeReproducao.get(index).getArquivoAudio());
        playAudio();
      } else if (index >= (listaDeReproducao.size()-1)){
        index = 0; 
        loadAudio(listaDeReproducao.get(index).getArquivoAudio());
        playAudio();
      }
  
    } else {System.out.println("lista de reprodução está vazia.");
    }
  }

  public void anteriorButton (){
    if (listaDeReproducao != null){
      stopAudio();
      if (index >0 && index <= (listaDeReproducao.size()-1)){
        index--;
        loadAudio(listaDeReproducao.get(index).getArquivoAudio());
        playAudio();
      } else if (index == 0){
        index = listaDeReproducao.size()-1; 
        loadAudio(listaDeReproducao.get(index).getArquivoAudio());
        playAudio();
      }
    } else {System.out.println("lista de reprodução está vazia.");
    }
  }

  public void playAudio() {
    System.out.println("playAudio");
    if (audioClip != null && !isPlaying) {
      audioClip.setFramePosition(0); // Reinicia o áudio do começo
      System.out.println("playAudio start");
      audioClip.start();
      isPlaying = true;
    }
  }

  public void stopAudio() {
    System.out.println("stopAudio");
    if (audioClip != null && isPlaying) {
      System.out.println("stopAudio stop");
      audioClip.stop();
      isPlaying = false;
    }
  }
}