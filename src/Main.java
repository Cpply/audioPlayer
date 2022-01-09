package com.xpply.audioplayer;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Audio Player");
        frame.setSize(500,145);
        frame.setVisible(true);
        frame.getContentPane().setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JFileChooser fc = new JFileChooser();

        JTextField path = new JTextField();
        path.setFont(new Font("Tahoma", Font.PLAIN, 10));
        path.setBounds(0, 0, 486, 20);
        frame.add(path);
        path.setColumns(10);

        JButton browseFile = new JButton("Browse File");
        browseFile.setBackground(Color.WHITE);
        browseFile.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        browseFile.setBounds(0, 22, 224, 86);
        browseFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fc.showOpenDialog(null);
                path.setText(String.valueOf(fc.getSelectedFile()));
            }
        });
        frame.add(browseFile);

        JButton playAudio = new JButton("Play");
        playAudio.setBackground(Color.WHITE);
        playAudio.setFont(new Font("Tahoma", Font.PLAIN, 20));
        playAudio.setBounds(267, 22, 219, 86);
        playAudio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File MyaudioFile = new File(String.valueOf(fc.getSelectedFile()));

                try {
                    AudioInputStream MyaudioStream = AudioSystem.getAudioInputStream(MyaudioFile);
                    AudioFormat Myformat = MyaudioStream.getFormat();
                    DataLine.Info Myinfo = new DataLine.Info(Clip.class, Myformat);
                    Clip MyaudioClip = (Clip) AudioSystem.getLine(Myinfo);
                    MyaudioClip.open(MyaudioStream);
                    MyaudioClip.start();
                } catch (UnsupportedAudioFileException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (LineUnavailableException ex) {
                    ex.printStackTrace();
                }
            }
        });
        frame.add(playAudio);
    }
}
