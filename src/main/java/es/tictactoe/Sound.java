package es.tictactoe;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
     
	public enum Sound {
	   GAME("Users/plt00/Documents/entornos/musica.mp3");
	   
	   
	   // Clase anidada para especificar volumen
	   public static enum Volume {
	      MUTE, LOW, MEDIUM, HIGH
	   }
	   
	   public static Volume volume = Volume.HIGH;
	   
	   // Cada efecto de sonido tiene su propio clip, cargado con su propio archivo de sonido
	   private Clip clip;
	   
	   // Constructor para construir cada elemento de la enumeración con su propio archivo de sonido
	   Sound(String soundFileName) {
	      try {
	         // Usar URL (en lugar de Archivo) para leer desde el disco y JAR
	         URL url = this.getClass().getResource(soundFileName);
	         // Configurar un flujo de entrada de audio canalizado desde el archivo de sonido
	         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
	         // Obtener un recurso del clip
	         clip = AudioSystem.getClip();
	         // Abrir un clip de audio y cargue muestras del flujo de entrada de audio
	         clip.open(audioInputStream);
	      } catch (UnsupportedAudioFileException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (LineUnavailableException e) {
	         e.printStackTrace();
	      }
	   }
	   
	   // Reproduzca o vuelva a reproducir el sonido desde el principio, rebobinando
	   public void play(Boolean loop) {
	      if (volume != Volume.MUTE) {
	         if (clip.isRunning())
	            clip.stop();   // Detener el reproductor si todavía está funcionando
	         clip.setFramePosition(0); // rebobinar hasta el principio
	         clip.start();     // empezar a jugar
	         if(loop)//vuelve a reproducir si el parámetro de bucle es verdadero
		    	  clip.loop(Clip.LOOP_CONTINUOUSLY);
	      }
	     
	   }
	   	   
	   public void stop() //detener la reproducción y rebobinar para volver a reproducir desde el principio
	   {
		   clip.stop();
		   clip.setFramePosition(0);
	   }
	   
	   public void mute() //no reproduzca sonidos (Silenciar sonido se selecciona en el menú Opciones)
	   {
		 volume = Volume.MUTE;
	   }
	}