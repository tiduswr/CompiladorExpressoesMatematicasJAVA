package views;

import javax.swing.*;
import java.awt.*;

public class UserAlertsBuilder {

    public static void error(String message, String title, Component parentComponent){
        genericAlert(message, title, parentComponent, "error.png", "error.wav", "Erro!");
    }

    public static void ok(String message, String title, Component parentComponent){
        genericAlert(message, title, parentComponent, "ok.png", "ok.wav", "Sucesso!");
    }

    private static void genericAlert(String message, String title, Component parentComponent,
                                     String imageName, String soundName, String tituloFrame){
        //Configurando icone
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource(imageName));
        Image image = icon.getImage();
        Image novaImagem = image.getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        icon = new ImageIcon(novaImagem);

        //Criando a mensagem
        JPanel panel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel(title, icon, JLabel.CENTER);
        JLabel messageLabel = new JLabel(message);

        titleLabel.setVerticalTextPosition(JLabel.BOTTOM);
        titleLabel.setHorizontalTextPosition(JLabel.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        messageLabel.setVerticalTextPosition(JLabel.BOTTOM);
        messageLabel.setHorizontalTextPosition(JLabel.CENTER);
        messageLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(messageLabel, BorderLayout.SOUTH);

        // reproduzindo um som de alerta
        SoundPlayer soundPlayer = new SoundPlayer(soundName);
        soundPlayer.play();

        JOptionPane.showOptionDialog(parentComponent, panel, tituloFrame,
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
        soundPlayer.stop();
    }
}
