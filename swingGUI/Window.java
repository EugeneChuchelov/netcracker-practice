package swingGUI;

import buildings.factories.DwellingFactory;
import buildings.factories.OfficeFactory;
import buildings.interfaces.Building;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;
import buildings.utils.Buildings;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Window extends JFrame {
    private JTextArea buildingInfoLabel = new JTextArea();
    private JTextArea floorInfoLabel = new JTextArea();
    private JTextArea spaceInfoLabel = new JTextArea();
    private Building currentBuilding = null;
    private String buildingType = "";
    private JPanel floorsSchema = new JPanel();
    private JScrollPane floorsPlot = new JScrollPane(floorsSchema);

    public Window() {
        super("Buildings GUI");
        setSize(1000, 800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        //Container container = this.getContentPane();
        getContentPane().setLayout(new GridLayout(1, 4));

        initMenu();

        JPanel buildingPanel = new JPanel();
        buildingPanel.setBorder(BorderFactory.createTitledBorder("Building Info: "));
        buildingPanel.add(buildingInfoLabel);

        JPanel floorPanel = new JPanel();
        floorPanel.setBorder(BorderFactory.createTitledBorder("Floor Info: "));
        floorPanel.add(floorInfoLabel);

        JPanel spacePanel = new JPanel();
        spacePanel.setBorder(BorderFactory.createTitledBorder("Space Info: "));
        spacePanel.add(spaceInfoLabel);


        getContentPane().add(buildingPanel);
        getContentPane().add(floorPanel);
        getContentPane().add(spacePanel);
        getContentPane().add(floorsPlot);
        buildingInfoLabel.setEditable(false);
        floorInfoLabel.setEditable(false);
        spaceInfoLabel.setEditable(false);

    }

    private void initMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu themeMenu = new JMenu("Look&Feel");

        JMenuItem addDwelling = new JMenuItem("Open Dwelling");
        JMenuItem addOffice = new JMenuItem("Open Office");

        addDwelling.addActionListener((event) -> {
            Buildings.setFactory(new DwellingFactory());
            buildingType = "Dwelling";
            openFile();
        });

        addOffice.addActionListener((event) -> {
            Buildings.setFactory(new OfficeFactory());
            buildingType = "Office";
            openFile();
        });

        fileMenu.add(addDwelling);
        fileMenu.add(addOffice);

        ButtonGroup group = new ButtonGroup();

        JRadioButtonMenuItem metal = new JRadioButtonMenuItem("Metal");
        metal.addActionListener(e -> {
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                SwingUtilities.updateComponentTreeUI(getContentPane());
            } catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException ex) {
                ex.printStackTrace();
            }
        });
        group.add(metal);
        themeMenu.add(metal);

        JRadioButtonMenuItem nimbus = new JRadioButtonMenuItem("Nimbus");
        nimbus.addActionListener(e -> {
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                SwingUtilities.updateComponentTreeUI(getContentPane());
            } catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException ex) {
                ex.printStackTrace();
            }
        });
        group.add(nimbus);
        themeMenu.add(nimbus);

        JRadioButtonMenuItem motif = new JRadioButtonMenuItem("Motif");
        motif.addActionListener(e -> {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                SwingUtilities.updateComponentTreeUI(getContentPane());
            } catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException ex) {
                ex.printStackTrace();
            }
        });
        group.add(motif);
        themeMenu.add(motif);

        JRadioButtonMenuItem windows = new JRadioButtonMenuItem("Windows");
        windows.addActionListener(e -> {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                SwingUtilities.updateComponentTreeUI(getContentPane());
            } catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException ex) {
                ex.printStackTrace();
            }
        });
        group.add(windows);
        themeMenu.add(windows);

        JRadioButtonMenuItem windowsClassic = new JRadioButtonMenuItem("Windows Classic");
        windowsClassic.addActionListener(e -> {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
                SwingUtilities.updateComponentTreeUI(getContentPane());
            } catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException ex) {
                ex.printStackTrace();
            }
        });
        group.add(windowsClassic);
        themeMenu.add(windowsClassic);

        menuBar.add(fileMenu);
        menuBar.add(themeMenu);
        setJMenuBar(menuBar);
    }

    private void openFile() {
        JFileChooser openFile = new JFileChooser();
        int ret = openFile.showDialog(null, "Open file");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = openFile.getSelectedFile();
            try {
                Scanner buildingScanner = new Scanner(file);
                currentBuilding = Buildings.readBuilding(buildingScanner);
                buildingScanner.close();
                initPanels();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (InputMismatchException e) {
                JFrame jFrame = new JFrame("Some Error Occurred");
                JOptionPane.showMessageDialog(jFrame, "Wrong file format", "Error Occurred", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private void initPanels() {
        clear();
        buildingInfoLabel.setText(
                "Type: " + buildingType + "\n"
                        + "Floors quantity: " + currentBuilding.getSize() + "\n"
                        + "Summary area: " + currentBuilding.getAreaTotal() + "\n");
        setInfo(0, 0);
        Floor[] floors = currentBuilding.toArray();

        floorsSchema.setLayout(new BoxLayout(floorsSchema, BoxLayout.Y_AXIS));

        for (int i = 0; i < floors.length; i++) {
            JPanel floorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            floorPanel.setBorder(BorderFactory.createTitledBorder("Floor " + i));
            Space[] spaces = floors[i].toArray();
            for (int j = 0; j < spaces.length; j++) {
                JButton button = new JButton(j + "");
                int finalI = i;
                int finalJ = j;
                button.addActionListener(e -> setInfo(finalI, finalJ));
                floorPanel.add(button);
            }
            floorPanel.setPreferredSize(floorPanel.getPreferredSize());
            JScrollPane floorPane = new JScrollPane(floorPanel);
            floorsSchema.add(floorPane);
        }
    }

    private void setInfo(int floor, int space) {
        floorInfoLabel.setText(
                "Number: " + floor + "\n" +
                        "Spaces quantity: " + currentBuilding.getFloor(floor).getSize() + "\n" +
                        "Total area: " + currentBuilding.getFloor(floor).getAreaTotal() + "\n" +
                        "Total rooms quantity: " + currentBuilding.getFloor(floor).getRoomsTotal());
        spaceInfoLabel
                .setText("Number " + space + "\n" +
                        "Area: " + currentBuilding.getFloor(floor).getSpace(space).getArea() + "\n" +
                        "Rooms quantity: " + currentBuilding.getFloor(floor).getSpace(space).getRoomsQuantity());
    }

    private void clear() {
        floorsSchema.removeAll();
        buildingInfoLabel.removeAll();
        floorInfoLabel.removeAll();
        spaceInfoLabel.removeAll();
    }
}
