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

        cardPanel.add(homePanel, "home");
        cardPanel.add(visualizarPanel, "visualizar");
        cardPanel.add(cadastroPanel, "cadastro");
        cardPanel.add(buscaPanel, "busca");
        cardPanel.add(estatisticasPanel, "estatisticas");
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

        Dimension btnSize = new Dimension(200, 40);
        JButton[] buttons = { btnVisualizar, btnCadastrar, btnBuscar, btnEstatisticas};
        for (JButton btn : buttons) {
            btn.setPreferredSize(btnSize);
            buttonPanel.add(btn);
        }

        btnVisualizar.addActionListener(e -> frame.showCard("visualizar"));
        btnCadastrar.addActionListener(e -> frame.showCard("cadastro"));
        btnBuscar.addActionListener(e -> frame.showCard("busca"));
        btnEstatisticas.addActionListener(e -> frame.showCard("estatisticas"));

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

        JPanel artistasPanel = createArtistasPanel();
        tabbedPane.addTab("Artistas", artistasPanel);

        JPanel bandasPanel = createBandasPanel();
        tabbedPane.addTab("Bandas", bandasPanel);

        JPanel albunsPanel = createAlbunsPanel();
        tabbedPane.addTab("Álbuns", albunsPanel);

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

        updateArtistasList(listModel);

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(700, 400));
        panel.add(scrollPane, BorderLayout.CENTER);

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

        updateBandasList(listModel);

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(700, 400));
        panel.add(scrollPane, BorderLayout.CENTER);

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

        updateAlbunsList(listModel);

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(700, 400));
        panel.add(scrollPane, BorderLayout.CENTER);

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

        updateMusicasList(listModel);

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(700, 400));
        panel.add(scrollPane, BorderLayout.CENTER);

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

            entities.Banda bandaSelecionada = null;
            for (entities.Banda b : Dados.bandas) {
                if (b.getNomeArtista().equals(bandaNome)) {
                    bandaSelecionada = b;
                    break;
                }
            }

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

        JLabel title = new JLabel("Busca por Música", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 10, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;

        JLabel nomeMusicaLabel = new JLabel("Nome da Música:");
        formPanel.add(nomeMusicaLabel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;

        JTextField nomeMusicaField = new JTextField(15);
        formPanel.add(nomeMusicaField, gbc);

        gbc.gridx++;
        JButton buscarButton = new JButton("Buscar");
        buscarButton.setPreferredSize(new Dimension(80, 25));
        formPanel.add(buscarButton, gbc);

        JTextArea resultadoArea = new JTextArea(5, 70);
        resultadoArea.setEditable(false);
        resultadoArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        buscarButton.addActionListener(e -> {
            String nomeMusica = nomeMusicaField.getText().trim();
            if (nomeMusica.isEmpty()) {
                resultadoArea.setText("Por favor, insira o nome da música.");
                return;
            }

            boolean encontrado = false;
            for (Musica musica : Dados.musicas) {
                if (musica.getNome().equalsIgnoreCase(nomeMusica)) {
                    resultadoArea.setText(musica.toString());
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                resultadoArea.setText("Música não encontrada.");
            }
        });

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;

        JScrollPane scrollPane = new JScrollPane(resultadoArea);
        formPanel.add(scrollPane, gbc);

        add(formPanel, BorderLayout.CENTER);

        JButton voltarButton = new JButton("Voltar ao Menu");
        voltarButton.addActionListener(e -> {
            Container parent = this.getParent();
            while (!(parent instanceof MainFrame) && parent != null) {
                parent = parent.getParent();
            }
            if (parent instanceof MainFrame) {
                ((MainFrame) parent).showCard("home");
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(voltarButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}

class EstatisticasPanel extends JPanel {
    public EstatisticasPanel() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Estatísticas", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        JPanel statsPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 10, 50));

        JLabel totalArtistasLabel = new JLabel("Total de Artistas: ");
        JLabel totalBandasLabel = new JLabel("Total de Bandas: ");
        JLabel totalMusicasLabel = new JLabel("Total de Músicas: ");
        JLabel totalAlbunsLabel = new JLabel("Total de Álbuns: ");
        JLabel totalStreamsLabel = new JLabel("Total de Streams por Álbum: ");
        JLabel mediaStreamsLabel = new JLabel("Média de Streams por Álbum: ");
        JLabel faixasPorAlbumLabel = new JLabel("Número de Faixas por Álbum: ");
        JLabel totalPorTipoLabel = new JLabel("Total por Tipo (Bandas vs Artistas Solo): ");

        statsPanel.add(totalArtistasLabel);
        statsPanel.add(totalBandasLabel);
        statsPanel.add(totalMusicasLabel);
        statsPanel.add(totalAlbunsLabel);
        statsPanel.add(totalStreamsLabel);
        statsPanel.add(mediaStreamsLabel);
        statsPanel.add(faixasPorAlbumLabel);
        statsPanel.add(totalPorTipoLabel);

        JButton atualizarButton = new JButton("Atualizar Dados");
        atualizarButton.addActionListener(e -> {
            int totalArtistas = Dados.artistas.size();
            int totalBandas = Dados.bandas.size();
            int totalMusicas = Dados.musicas.size();
            int totalAlbuns = Dados.albuns.size();

            int totalStreams = Dados.albuns.stream()
                    .mapToInt(album -> album.getMusicas().stream().mapToInt(Musica::getStreams).sum())
                    .sum();

            double mediaStreams = Dados.albuns.isEmpty() ? 0 : totalStreams / (double) Dados.albuns.size();

            String faixasPorAlbum = Dados.albuns.stream()
                    .map(album -> String.format("%s: %d faixas", album.getNome(), album.getMusicas().size()))
                    .reduce("", (acc, curr) -> acc + curr + "\n");

            totalArtistasLabel.setText("Total de Artistas: " + totalArtistas);
            totalBandasLabel.setText("Total de Bandas: " + totalBandas);
            totalMusicasLabel.setText("Total de Músicas: " + totalMusicas);
            totalAlbunsLabel.setText("Total de Álbuns: " + totalAlbuns);
            totalStreamsLabel.setText("Total de Streams por Álbum: " + totalStreams);
            mediaStreamsLabel.setText("Média de Streams por Álbum: " + String.format("%.2f", mediaStreams));
            faixasPorAlbumLabel.setText("<html>Número de Faixas por Álbum:<br>" + faixasPorAlbum + "</html>");
            totalPorTipoLabel.setText("Total por Tipo: Bandas = " + totalBandas + ", Artistas Solo = " + totalArtistas);
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(atualizarButton);

        JButton voltarButton = new JButton("Voltar ao Menu");
        voltarButton.addActionListener(e -> {
            Container parent = this.getParent();
            while (!(parent instanceof MainFrame) && parent != null) {
                parent = parent.getParent();
            }
            if (parent instanceof MainFrame) {
                ((MainFrame) parent).showCard("home");
            }
        });

        bottomPanel.add(voltarButton);

        add(statsPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}