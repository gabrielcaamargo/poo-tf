package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public MainFrame() {
        setTitle("Gerenciador de Músicas e Álbuns");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);


        HomePanel homePanel = new HomePanel(this);
        VisualizarPanel visualizarPanel = new VisualizarPanel();
        CadastroPanel cadastroPanel = new CadastroPanel();
        BuscaPanel buscaPanel = new BuscaPanel();
        EstatisticasPanel estatisticasPanel = new EstatisticasPanel();
        FuncionalidadeExtraPanel extraPanel = new FuncionalidadeExtraPanel();

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
        cardLayout.show(cardPanel, name);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}

class HomePanel extends JPanel {
    public HomePanel(MainFrame frame) {
        setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(40, 200, 40, 200));

        JButton btnVisualizar = new JButton("Visualizar Dados");
        JButton btnCadastrar = new JButton("Cadastrar Novo");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnEstatisticas = new JButton("Estatísticas");
        JButton btnExtra = new JButton("Top 5 Músicas Populares");

        Dimension btnSize = new Dimension(200, 40);
        JButton[] buttons = {btnVisualizar, btnCadastrar, btnBuscar, btnEstatisticas, btnExtra};
        for (JButton btn : buttons) {
            btn.setPreferredSize(btnSize);
            buttonPanel.add(btn);
        }

        btnVisualizar.addActionListener(e -> frame.showCard("visualizar"));
        btnCadastrar.addActionListener(e -> frame.showCard("cadastro"));
        btnBuscar.addActionListener(e -> frame.showCard("busca"));
        btnEstatisticas.addActionListener(e -> frame.showCard("estatisticas"));
        btnExtra.addActionListener(e -> frame.showCard("extra"));

        JLabel title = new JLabel("Bem-vindo ao Pucfy", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }
}

class VisualizarPanel extends JPanel {
    public VisualizarPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("Visualizar Entidades", SwingConstants.CENTER), BorderLayout.NORTH);
    }
}

class CadastroPanel extends JPanel {
    public CadastroPanel() {
        setLayout(new BorderLayout());
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Artista", new FormArtista());
        tabbedPane.addTab("Banda", new FormBanda());
        tabbedPane.addTab("Álbum", new FormAlbum());
        tabbedPane.addTab("Música", new FormMusica());

        add(tabbedPane, BorderLayout.CENTER);
    }
}

class FormArtista extends JPanel {
    public FormArtista() {
        setLayout(new GridLayout(4, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JTextField nome = new JTextField();
        JTextField nacionalidade = new JTextField();
        JTextField funcao = new JTextField();
        JButton salvar = new JButton("Salvar Artista");
        salvar.setPreferredSize(new Dimension(150, 30));

        add(new JLabel("Nome:"));
        add(nome);
        add(new JLabel("Nacionalidade:"));
        add(nacionalidade);
        add(new JLabel("Função:"));
        add(funcao);
        add(new JLabel());
        add(salvar);
    }
}

class FormBanda extends JPanel {
    public FormBanda() {
        setLayout(new GridLayout(5, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JTextField nome = new JTextField();
        JTextField nacionalidade = new JTextField();
        JComboBox<String> genero = new JComboBox<>(new String[]{"ROCK", "POP", "JAZZ", "RAP", "REGGAE", "ALTERNATIVE", "BLUES"});
        JTextField anoFormacao = new JTextField();
        JButton salvar = new JButton("Salvar Banda");
        salvar.setPreferredSize(new Dimension(150, 30));

        add(new JLabel("Nome:"));
        add(nome);
        add(new JLabel("Nacionalidade:"));
        add(nacionalidade);
        add(new JLabel("Gênero:"));
        add(genero);
        add(new JLabel("Ano de Formação:"));
        add(anoFormacao);
        add(new JLabel());
        add(salvar);
    }
}

class FormAlbum extends JPanel {
    public FormAlbum() {
        setLayout(new GridLayout(5, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JTextField nome = new JTextField();
        JTextField ano = new JTextField();
        JComboBox<String> genero = new JComboBox<>(new String[]{"ROCK", "POP", "JAZZ", "RAP", "REGGAE", "ALTERNATIVE", "BLUES"});
        JTextField artista = new JTextField();
        JButton salvar = new JButton("Salvar Álbum");
        salvar.setPreferredSize(new Dimension(150, 30));

        add(new JLabel("Nome do Álbum:"));
        add(nome);
        add(new JLabel("Ano de Lançamento:"));
        add(ano);
        add(new JLabel("Gênero:"));
        add(genero);
        add(new JLabel("Artista/Banda:"));
        add(artista);
        add(new JLabel());
        add(salvar);
    }
}

class FormMusica extends JPanel {
    public FormMusica() {
        setLayout(new GridLayout(6, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JTextField nome = new JTextField();
        JComboBox<String> genero = new JComboBox<>(new String[]{"ROCK", "POP", "JAZZ", "RAP", "REGGAE", "ALTERNATIVE", "BLUES"});
        JTextField ano = new JTextField();
        JTextField duracao = new JTextField();
        JTextField streams = new JTextField();
        JButton salvar = new JButton("Salvar Música");
        salvar.setPreferredSize(new Dimension(150, 30));

        add(new JLabel("Nome da Música:"));
        add(nome);
        add(new JLabel("Gênero:"));
        add(genero);
        add(new JLabel("Ano de Lançamento:"));
        add(ano);
        add(new JLabel("Duração (segundos):"));
        add(duracao);
        add(new JLabel("Streams:"));
        add(streams);
        add(new JLabel());
        add(salvar);
    }
}

class BuscaPanel extends JPanel {
    public BuscaPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("Busca por Música", SwingConstants.CENTER), BorderLayout.NORTH);
    }
}

class EstatisticasPanel extends JPanel {
    public EstatisticasPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("Estatísticas", SwingConstants.CENTER), BorderLayout.NORTH);
    }
}

class FuncionalidadeExtraPanel extends JPanel {
    public FuncionalidadeExtraPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("Top 5 Músicas Populares", SwingConstants.CENTER), BorderLayout.NORTH);
    }
}
