package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

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

class Dados {
    static ArrayList<entities.Artista> artistas = new ArrayList<>();
    static ArrayList<entities.Banda> bandas = new ArrayList<>();
    static ArrayList<entities.Album> albuns = new ArrayList<>();
    static ArrayList<entities.Musica> musicas = new ArrayList<>();
}

class FormArtista extends JPanel {
    public FormArtista() {
        setLayout(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(4, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 50, 10, 50));

        JTextField nome = new JTextField();
        JTextField nacionalidade = new JTextField();
        JTextField funcao = new JTextField();
        JButton salvar = new JButton("Salvar Artista");
        salvar.setPreferredSize(new Dimension(150, 30));

        salvar.addActionListener(e -> {
            if (nome.getText().isEmpty() || nacionalidade.getText().isEmpty() || funcao.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Dados.artistas.add(new entities.Artista(nome.getText(), nacionalidade.getText(), funcao.getText()));
            JOptionPane.showMessageDialog(this, "Artista salvo com sucesso!");
        });

        form.add(new JLabel("Nome:"));
        form.add(nome);
        form.add(new JLabel("Nacionalidade:"));
        form.add(nacionalidade);
        form.add(new JLabel("Função:"));
        form.add(funcao);
        form.add(new JLabel());
        form.add(salvar);

        JButton voltar = new JButton("Voltar ao Menu");
        voltar.addActionListener(e -> {
            Container parent = this.getParent();
            while (!(parent instanceof MainFrame) && parent != null) {
                parent = parent.getParent();
            }
            if (parent instanceof MainFrame) {
                ((MainFrame) parent).showCard("home");
            }
        });

        JPanel bottom = new JPanel();
        bottom.add(voltar);

        add(form, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }
}

class FormBanda extends JPanel {
    public FormBanda() {
        setLayout(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(5, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 50, 10, 50));

        JTextField nome = new JTextField();
        JTextField nacionalidade = new JTextField();
        JComboBox<String> genero = new JComboBox<>(Arrays.stream(enums.MusicaGenero.values()).map(Enum::name).toArray(String[]::new));
        JTextField anoFormacao = new JTextField();
        JButton salvar = new JButton("Salvar Banda");
        salvar.setPreferredSize(new Dimension(150, 30));

        salvar.addActionListener(e -> {
            if (nome.getText().isEmpty() || nacionalidade.getText().isEmpty() || anoFormacao.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                int ano = Integer.parseInt(anoFormacao.getText());
                enums.MusicaGenero g = enums.MusicaGenero.valueOf((String) genero.getSelectedItem());
                Dados.bandas.add(new entities.Banda(nome.getText(), nacionalidade.getText(), g, ano, null));
                JOptionPane.showMessageDialog(this, "Banda salva com sucesso!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ano deve ser numérico.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        form.add(new JLabel("Nome:"));
        form.add(nome);
        form.add(new JLabel("Nacionalidade:"));
        form.add(nacionalidade);
        form.add(new JLabel("Gênero:"));
        form.add(genero);
        form.add(new JLabel("Ano de Formação:"));
        form.add(anoFormacao);
        form.add(new JLabel());
        form.add(salvar);

        JButton voltar = new JButton("Voltar ao Menu");
        voltar.addActionListener(e -> {
            Container parent = this.getParent();
            while (!(parent instanceof MainFrame) && parent != null) {
                parent = parent.getParent();
            }
            if (parent instanceof MainFrame) {
                ((MainFrame) parent).showCard("home");
            }
        });

        JPanel bottom = new JPanel();
        bottom.add(voltar);

        add(form, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }
}

class FormAlbum extends JPanel {
    public FormAlbum() {
        setLayout(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(5, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 50, 10, 50));

        JTextField nome = new JTextField();
        JTextField ano = new JTextField();
        JComboBox<String> genero = new JComboBox<>(Arrays.stream(enums.MusicaGenero.values()).map(Enum::name).toArray(String[]::new));
        JComboBox<String> artistasCombo = new JComboBox<>();

        for (entities.Artista a : Dados.artistas) {
            artistasCombo.addItem(a.getNomeArtista());
        }
        for (entities.Banda b : Dados.bandas) {
            artistasCombo.addItem(b.getNomeArtista());
        }

        JButton salvar = new JButton("Salvar Álbum");
        salvar.setPreferredSize(new Dimension(150, 30));

        salvar.addActionListener(e -> {
            if (nome.getText().isEmpty() || ano.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                int anoLanc = Integer.parseInt(ano.getText());
                enums.MusicaGenero g = enums.MusicaGenero.valueOf((String) genero.getSelectedItem());
                String artistaNome = (String) artistasCombo.getSelectedItem();

                for (entities.Artista a : Dados.artistas) {
                    if (a.getNomeArtista().equals(artistaNome)) {
                        entities.Album<entities.Artista> album = new entities.Album<>(nome.getText(), a, anoLanc, g);
                        a.addAlbum(album);
                        Dados.albuns.add(album);
                        JOptionPane.showMessageDialog(this, "Álbum salvo com sucesso!");
                        return;
                    }
                }
                for (entities.Banda b : Dados.bandas) {
                    if (b.getNomeArtista().equals(artistaNome)) {
                        entities.Album<entities.Banda> album = new entities.Album<>(nome.getText(), b, anoLanc, g);
                        b.addAlbum(album);
                        Dados.albuns.add(album);
                        JOptionPane.showMessageDialog(this, "Álbum salvo com sucesso!");
                        return;
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ano deve ser numérico.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        form.add(new JLabel("Nome do Álbum:"));
        form.add(nome);
        form.add(new JLabel("Ano de Lançamento:"));
        form.add(ano);
        form.add(new JLabel("Gênero:"));
        form.add(genero);
        form.add(new JLabel("Artista/Banda:"));
        form.add(artistasCombo);
        form.add(new JLabel());
        form.add(salvar);

        JButton voltar = new JButton("Voltar ao Menu");
        voltar.addActionListener(e -> {
            Container parent = this.getParent();
            while (!(parent instanceof MainFrame) && parent != null) {
                parent = parent.getParent();
            }
            if (parent instanceof MainFrame) {
                ((MainFrame) parent).showCard("home");
            }
        });

        JPanel bottom = new JPanel();
        bottom.add(voltar);

        add(form, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }
}

class FormMusica extends JPanel {
    public FormMusica() {
        setLayout(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(6, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 50, 10, 50));

        JTextField nome = new JTextField();
        JComboBox<String> genero = new JComboBox<>(Arrays.stream(enums.MusicaGenero.values()).map(Enum::name).toArray(String[]::new));
        JTextField ano = new JTextField();
        JTextField duracao = new JTextField();
        JTextField streams = new JTextField();
        JComboBox<String> albumCombo = new JComboBox<>();

        for (entities.Album<?> a : Dados.albuns) {
            albumCombo.addItem(a.getNome());
        }

        JButton salvar = new JButton("Salvar Música");
        salvar.setPreferredSize(new Dimension(150, 30));

        salvar.addActionListener(e -> {
            if (nome.getText().isEmpty() || ano.getText().isEmpty() || duracao.getText().isEmpty() || streams.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                int anoLanc = Integer.parseInt(ano.getText());
                int dur = Integer.parseInt(duracao.getText());
                int str = Integer.parseInt(streams.getText());
                enums.MusicaGenero g = enums.MusicaGenero.valueOf((String) genero.getSelectedItem());
                entities.Musica m = new entities.Musica(nome.getText(), g, anoLanc, dur, str);

                for (entities.Album<?> a : Dados.albuns) {
                    if (a.getNome().equals(albumCombo.getSelectedItem())) {
                        a.addMusica(m);
                        Dados.musicas.add(m);
                        JOptionPane.showMessageDialog(this, "Música salva com sucesso!");
                        return;
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Campos numéricos inválidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        form.add(new JLabel("Nome da Música:"));
        form.add(nome);
        form.add(new JLabel("Gênero:"));
        form.add(genero);
        form.add(new JLabel("Ano de Lançamento:"));
        form.add(ano);
        form.add(new JLabel("Duração (segundos):"));
        form.add(duracao);
        form.add(new JLabel("Streams:"));
        form.add(streams);
        form.add(new JLabel("Álbum:"));
        form.add(albumCombo);

        JButton voltar = new JButton("Voltar ao Menu");
        voltar.addActionListener(e -> {
            Container parent = this.getParent();
            while (!(parent instanceof MainFrame) && parent != null) {
                parent = parent.getParent();
            }
            if (parent instanceof MainFrame) {
                ((MainFrame) parent).showCard("home");
            }
        });

        JPanel bottom = new JPanel();
        bottom.add(salvar);
        bottom.add(voltar);

        add(form, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
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
