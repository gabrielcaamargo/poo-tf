package entities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private Stack<String> historico = new Stack<>();
    private String currentCardName = "home";

    public MainFrame() {
        setTitle("Gerenciador de Músicas e Álbuns");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        HomePanel homePanel = new HomePanel(this);
        VisualizarPanel visualizarPanel = new VisualizarPanel(this);
        CadastroPanel cadastroPanel = new CadastroPanel(this);
        BuscaPanel buscaPanel = new BuscaPanel(this);
        EstatisticasPanel estatisticasPanel = new EstatisticasPanel(this);
        FuncionalidadeExtraPanel extraPanel = new FuncionalidadeExtraPanel(this);

        cardPanel.add(homePanel, "home");
        cardPanel.add(visualizarPanel, "visualizar");
        cardPanel.add(cadastroPanel, "cadastro");
        cardPanel.add(buscaPanel, "busca");
        cardPanel.add(estatisticasPanel, "estatisticas");
        cardPanel.add(extraPanel, "extra");

        add(cardPanel);
        setVisible(true);
    }

    public void showCard(String name) {
        if (currentCardName != null && !currentCardName.equals(name)) {
            historico.push(currentCardName);
        }
        cardLayout.show(cardPanel, name);
        currentCardName = name;
    }


    public void voltar() {
        if (!historico.isEmpty()) {
            String anterior = historico.pop();
            cardLayout.show(cardPanel, anterior);
            currentCardName = anterior;
        }
    }

    private String getCurrentCard() {
        for (Component comp : cardPanel.getComponents()) {
            if (comp.isVisible()) {
                for (java.util.Map.Entry<Object, Component> entry : ((java.util.Map<Object, Component>)((java.awt.Container)cardPanel).getLayout()).entrySet()) {
                    if (entry.getValue() == comp) {
                        return (String) entry.getKey();
                    }
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}

class HomePanel extends JPanel {
    public HomePanel(MainFrame frame) {
        setLayout(new GridLayout(6, 1, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

        JButton btnVisualizar = new JButton("Visualizar Dados");
        JButton btnCadastrar = new JButton("Cadastrar Novo");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnEstatisticas = new JButton("Estatísticas");
        JButton btnExtra = new JButton("Top 5 Músicas Populares");

        btnVisualizar.addActionListener(e -> frame.showCard("visualizar"));
        btnCadastrar.addActionListener(e -> frame.showCard("cadastro"));
        btnBuscar.addActionListener(e -> frame.showCard("busca"));
        btnEstatisticas.addActionListener(e -> frame.showCard("estatisticas"));
        btnExtra.addActionListener(e -> frame.showCard("extra"));

        add(new JLabel("Pucfy", SwingConstants.CENTER));
        add(btnVisualizar);
        add(btnCadastrar);
        add(btnBuscar);
        add(btnEstatisticas);
        add(btnExtra);
    }
}

class VisualizarPanel extends JPanel {
    public VisualizarPanel(MainFrame frame) {
        setLayout(new BorderLayout());
        add(new JLabel("Visualizar Entidades", SwingConstants.CENTER), BorderLayout.NORTH);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> frame.voltar());
        add(btnVoltar, BorderLayout.SOUTH);
    }
}

class CadastroPanel extends JPanel {
    public CadastroPanel(MainFrame frame) {
        setLayout(new BorderLayout());
        add(new JLabel("Cadastrar Entidade", SwingConstants.CENTER), BorderLayout.NORTH);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> frame.voltar());
        add(btnVoltar, BorderLayout.SOUTH);
    }
}

class BuscaPanel extends JPanel {
    public BuscaPanel(MainFrame frame) {
        setLayout(new BorderLayout());
        add(new JLabel("Busca por Música", SwingConstants.CENTER), BorderLayout.NORTH);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> frame.voltar());
        add(btnVoltar, BorderLayout.SOUTH);
    }
}

class EstatisticasPanel extends JPanel {
    public EstatisticasPanel(MainFrame frame) {
        setLayout(new BorderLayout());
        add(new JLabel("Estatísticas", SwingConstants.CENTER), BorderLayout.NORTH);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> frame.voltar());
        add(btnVoltar, BorderLayout.SOUTH);
    }
}

class FuncionalidadeExtraPanel extends JPanel {
    public FuncionalidadeExtraPanel(MainFrame frame) {
        setLayout(new BorderLayout());
        add(new JLabel("Top 5 Músicas Populares", SwingConstants.CENTER), BorderLayout.NORTH);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> frame.voltar());
        add(btnVoltar, BorderLayout.SOUTH);
    }
}