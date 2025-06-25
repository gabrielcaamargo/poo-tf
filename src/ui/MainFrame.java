package ui;

import javax.swing.*;

import entities.Album;
import entities.Artista;
import entities.Banda;
import entities.Musica;

import java.awt.*;
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
        JButton[] buttons = { btnVisualizar, btnCadastrar, btnBuscar, btnEstatisticas, btnExtra };
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

        JLabel title = new JLabel("Visualizar Dados", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Aba de Artistas
        JPanel artistasPanel = createArtistasPanel();
        tabbedPane.addTab("Artistas", artistasPanel);

        // Aba de Bandas
        JPanel bandasPanel = createBandasPanel();
        tabbedPane.addTab("Bandas", bandasPanel);

        // Aba de Álbuns
        JPanel albunsPanel = createAlbunsPanel();
        tabbedPane.addTab("Álbuns", albunsPanel);

        // Aba de Músicas
        JPanel musicasPanel = createMusicasPanel();
        tabbedPane.addTab("Músicas", musicasPanel);

        add(tabbedPane, BorderLayout.CENTER);

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
        add(bottom, BorderLayout.SOUTH);
    }

    private JPanel createArtistasPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // Atualizar lista de artistas
        updateArtistasList(listModel);

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(700, 400));
        panel.add(scrollPane, BorderLayout.CENTER);

        // Botão para atualizar a lista
        JButton atualizar = new JButton("Atualizar Lista");
        atualizar.addActionListener(e -> updateArtistasList(listModel));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(atualizar);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createBandasPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // Atualizar lista de bandas
        updateBandasList(listModel);

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(700, 400));
        panel.add(scrollPane, BorderLayout.CENTER);

        // Botão para atualizar a lista
        JButton atualizar = new JButton("Atualizar Lista");
        atualizar.addActionListener(e -> updateBandasList(listModel));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(atualizar);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createAlbunsPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // Atualizar lista de álbuns
        updateAlbunsList(listModel);

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(700, 400));
        panel.add(scrollPane, BorderLayout.CENTER);

        // Botão para atualizar a lista
        JButton atualizar = new JButton("Atualizar Lista");
        atualizar.addActionListener(e -> updateAlbunsList(listModel));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(atualizar);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createMusicasPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // Atualizar lista de músicas
        updateMusicasList(listModel);

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(700, 400));
        panel.add(scrollPane, BorderLayout.CENTER);

        // Botão para atualizar a lista
        JButton atualizar = new JButton("Atualizar Lista");
        atualizar.addActionListener(e -> updateMusicasList(listModel));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(atualizar);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void updateArtistasList(DefaultListModel<String> listModel) {
        listModel.clear();
        if (Dados.artistas.isEmpty()) {
            listModel.addElement("Nenhum artista cadastrado.");
        } else {
            for (int i = 0; i < Dados.artistas.size(); i++) {
                Artista artista = Dados.artistas.get(i);
                listModel.addElement(String.format("%d. %s", i + 1, artista.toString()));
            }
        }
    }

    private void updateBandasList(DefaultListModel<String> listModel) {
        listModel.clear();
        if (Dados.bandas.isEmpty()) {
            listModel.addElement("Nenhuma banda cadastrada.");
        } else {
            for (int i = 0; i < Dados.bandas.size(); i++) {
                Banda banda = Dados.bandas.get(i);
                listModel.addElement(String.format("%d. %s", i + 1, banda.toString()));
            }
        }
    }

    private void updateAlbunsList(DefaultListModel<String> listModel) {
        listModel.clear();
        if (Dados.albuns.isEmpty()) {
            listModel.addElement("Nenhum álbum cadastrado.");
        } else {
            for (int i = 0; i < Dados.albuns.size(); i++) {
                Album<?> album = Dados.albuns.get(i);
                listModel.addElement(String.format("%d. %s", i + 1, album.toString()));
            }
        }
    }

    private void updateMusicasList(DefaultListModel<String> listModel) {
        listModel.clear();
        if (Dados.musicas.isEmpty()) {
            listModel.addElement("Nenhuma música cadastrada.");
        } else {
            for (int i = 0; i < Dados.musicas.size(); i++) {
                Musica musica = Dados.musicas.get(i);
                listModel.addElement(String.format("%d. %s", i + 1, musica.toString()));
            }
        }
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
        tabbedPane.addTab("Integrante", new FormIntegrante());

        add(tabbedPane, BorderLayout.CENTER);
    }
}

class Dados {
    static ArrayList<Artista> artistas = new ArrayList<>();
    static ArrayList<Banda> bandas = new ArrayList<>();
    static ArrayList<Album<?>> albuns = new ArrayList<>();
    static ArrayList<Musica> musicas = new ArrayList<>();
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
        JComboBox<String> genero = new JComboBox<>(
                Arrays.stream(enums.MusicaGenero.values()).map(Enum::name).toArray(String[]::new));
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
                Dados.bandas.add(new Banda(nome.getText(), nacionalidade.getText(), g, ano, null));
                nome.setText("");
                nacionalidade.setText("");
                anoFormacao.setText("");
                genero.setSelectedIndex(0);
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
        JComboBox<String> genero = new JComboBox<>(
                Arrays.stream(enums.MusicaGenero.values()).map(Enum::name).toArray(String[]::new));
        JComboBox<String> artistasCombo = new JComboBox<>();
        updateArtistasCombo(artistasCombo);

        // Botão para atualizar a lista de artistas/bandas
        JButton atualizarArtistas = new JButton("Atualizar Lista");
        atualizarArtistas.addActionListener(e -> updateArtistasCombo(artistasCombo));

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
        form.add(atualizarArtistas);
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

    private void updateArtistasCombo(JComboBox<String> combo) {
        combo.removeAllItems();

        if (Dados.artistas.isEmpty() && Dados.bandas.isEmpty()) {
            combo.addItem("Nenhum artista/banda cadastrado");
            return;
        }

        for (entities.Artista a : Dados.artistas) {
            combo.addItem(a.getNomeArtista());
        }
        for (entities.Banda b : Dados.bandas) {
            combo.addItem(b.getNomeArtista());
        }
    }
}

class FormMusica extends JPanel {
    public FormMusica() {
        setLayout(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(7, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 50, 10, 50));

        JTextField nome = new JTextField();
        JComboBox<String> genero = new JComboBox<>(
                Arrays.stream(enums.MusicaGenero.values()).map(Enum::name).toArray(String[]::new));
        JTextField ano = new JTextField();
        JTextField duracao = new JTextField();
        JTextField streams = new JTextField();
        JComboBox<String> albumCombo = new JComboBox<>();
        updateAlbunsCombo(albumCombo);

        // Botão para atualizar a lista de álbuns
        JButton atualizarAlbuns = new JButton("Atualizar Lista");
        atualizarAlbuns.addActionListener(e -> updateAlbunsCombo(albumCombo));

        JButton salvar = new JButton("Salvar Música");
        salvar.setPreferredSize(new Dimension(150, 30));

        salvar.addActionListener(e -> {
            if (nome.getText().isEmpty() || ano.getText().isEmpty() || duracao.getText().isEmpty()
                    || streams.getText().isEmpty()) {
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
        form.add(atualizarAlbuns);
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

    private void updateAlbunsCombo(JComboBox<String> combo) {
        combo.removeAllItems();

        if (Dados.albuns.isEmpty()) {
            combo.addItem("Nenhum álbum cadastrado");
            return;
        }

        for (entities.Album<?> a : Dados.albuns) {
            combo.addItem(a.getNome());
        }
    }
}

class FormIntegrante extends JPanel {
    public FormIntegrante() {
        setLayout(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(4, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 50, 10, 50));

        JComboBox<String> bandasCombo = new JComboBox<>();
        updateBandasCombo(bandasCombo);

        JComboBox<String> artistasCombo = new JComboBox<>();
        updateArtistasCombo(artistasCombo);

        // Botões para atualizar as listas
        JButton atualizarBandas = new JButton("Atualizar Bandas");
        atualizarBandas.addActionListener(e -> updateBandasCombo(bandasCombo));

        JButton atualizarArtistas = new JButton("Atualizar Artistas");
        atualizarArtistas.addActionListener(e -> updateArtistasCombo(artistasCombo));

        JButton adicionar = new JButton("Adicionar Integrante");
        adicionar.setPreferredSize(new Dimension(150, 30));

        adicionar.addActionListener(e -> {
            String bandaNome = (String) bandasCombo.getSelectedItem();
            String artistaNome = (String) artistasCombo.getSelectedItem();

            if (bandaNome == null || artistaNome == null || 
                bandaNome.equals("Nenhuma banda cadastrada") || 
                artistaNome.equals("Nenhum artista cadastrado")) {
                JOptionPane.showMessageDialog(this, "Selecione uma banda e um artista válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Encontrar a banda selecionada
            entities.Banda bandaSelecionada = null;
            for (entities.Banda b : Dados.bandas) {
                if (b.getNomeArtista().equals(bandaNome)) {
                    bandaSelecionada = b;
                    break;
                }
            }

            // Encontrar o artista selecionado
            entities.Artista artistaSelecionado = null;
            for (entities.Artista a : Dados.artistas) {
                if (a.getNomeArtista().equals(artistaNome)) {
                    artistaSelecionado = a;
                    break;
                }
            }

            if (bandaSelecionada != null && artistaSelecionado != null) {
                bandaSelecionada.addIntegrante(artistaSelecionado);
                JOptionPane.showMessageDialog(this, 
                    String.format("Artista '%s' adicionado à banda '%s' com sucesso!", 
                    artistaNome, bandaNome));
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao encontrar banda ou artista selecionado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        form.add(new JLabel("Banda:"));
        form.add(bandasCombo);
        form.add(atualizarBandas);
        form.add(new JLabel("Artista:"));
        form.add(artistasCombo);
        form.add(atualizarArtistas);
        form.add(new JLabel());
        form.add(adicionar);

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

    private void updateBandasCombo(JComboBox<String> combo) {
        combo.removeAllItems();
        
        if (Dados.bandas.isEmpty()) {
            combo.addItem("Nenhuma banda cadastrada");
            return;
        }
        
        for (entities.Banda b : Dados.bandas) {
            combo.addItem(b.getNomeArtista());
        }
    }

    private void updateArtistasCombo(JComboBox<String> combo) {
        combo.removeAllItems();
        
        if (Dados.artistas.isEmpty()) {
            combo.addItem("Nenhum artista cadastrado");
            return;
        }
        
        for (entities.Artista a : Dados.artistas) {
            combo.addItem(a.getNomeArtista());
        }
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
