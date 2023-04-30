package views;

import data_structures.SyntaxError;
import models.MathExpressionTranslator;
import translators.TranslatorBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form extends JFrame {

    private String[] tipos = new String[]{"Infixa", "Posfixa", "Prefixa"};

    public Form(){
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void initComponents() {
        // Configurações da janela
        setTitle("Tradutor de Expressões");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Configurações do painel principal
        JPanel painel = new JPanel(new BorderLayout(10, 10)); // layout de borda
        add(painel);

        // Painel superior: seleção de sintaxe partida e destino
        JPanel painelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10)); // layout de fluxo
        JLabel rotuloSintaxePartida = new JLabel("Sintaxe Partida:");
        JComboBox<String> comboBoxSintaxePartida = new JComboBox<>(tipos);
        JLabel rotuloSintaxeDestino = new JLabel("Sintaxe Destino:");
        JComboBox<String> comboBoxSintaxeDestino = new JComboBox<>(tipos);
        painelSuperior.add(rotuloSintaxePartida);
        painelSuperior.add(comboBoxSintaxePartida);
        painelSuperior.add(rotuloSintaxeDestino);
        painelSuperior.add(comboBoxSintaxeDestino);
        painel.add(painelSuperior, BorderLayout.NORTH);

        // Painel central: campo de entrada de texto
        JPanel painelCentral = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10)); // layout de fluxo
        JLabel rotuloExpressao = new JLabel("Expressão:");
        JTextField campoInput = new JTextField(20);
        painelCentral.add(rotuloExpressao);
        painelCentral.add(campoInput);
        painel.add(painelCentral, BorderLayout.CENTER);

        // Painel inferior: botão de validação
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // layout de fluxo
        JButton botaoValidar = new JButton("Validar");
        botaoValidar.addActionListener(e -> {
            validar(tipos[comboBoxSintaxePartida.getSelectedIndex()],
                    tipos[comboBoxSintaxeDestino.getSelectedIndex()],
                    campoInput.getText());
        });
        painelInferior.add(botaoValidar);
        painel.add(painelInferior, BorderLayout.SOUTH);

        // Configura o tamanho do formulário para o tamanho preferido de seus componentes
        pack();
        setResizable(false); // Impede que o usuário redimensione a janela
    }

    private void validar(String from, String to, String expression){
        MathExpressionTranslator translator = TranslatorBuilder.buildTranslator(from, to);
        if(translator != null){
            try{
                String traducao = translator
                        .verificarExpressao(expression)
                        .pegarUltimaTraducao();
                System.out.println();
                UserAlertsBuilder.ok(traducao, "Expressão traduzida para " + to, this);
            }catch (SyntaxError e){
                UserAlertsBuilder.error(e.getMessage(), "ERRO AO TRADUZIR EXPRESSÃO", this);
                System.out.println();
            }
        }
    }

}
