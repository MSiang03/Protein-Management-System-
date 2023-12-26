package login_register;

import dao.ProteinDao;
import protein.Protein;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.List;

public class Protein_Dashboard {
    private int index = 0;
    private int show_num = 20;
    Object[][] a1;
    Object[] name1 = {"id","proteins","sequences","length","annotations"};

    public void initTable(DefaultTableModel table, List<Protein> proteins){
        int i = 0;
        a1 = new Object[proteins.size()][6];
        table.setRowCount(0);

        while(index < proteins.size()){
            a1[index][0] = proteins.get(index).getId();
            a1[index][1] = proteins.get(index).getProteins();
            a1[index][2] = proteins.get(index).getSequences();
            a1[index][3] = proteins.get(index).getLength();
            a1[index][4] = proteins.get(index).getAnnotations();
            table.addRow(a1[index]);
            i++;
            index++;
            if(i == show_num) break;
        }

    }

    public Protein_Dashboard(){
        frame = new JFrame("X-Protein");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(800,500);
        frame.setLocationRelativeTo(null);

        DefaultTableModel tablemod = new DefaultTableModel();

        for(Object o: name1){
            tablemod.addColumn(o);
        }

        initTable(tablemod,ProteinDao.getAll());
        table1.setModel(tablemod);

        frame.setVisible(true);

        importButton.addActionListener(e -> {
            String seq = JOptionPane.showInputDialog(panel1,"Input the path of database","Import database",JOptionPane.QUESTION_MESSAGE);
            String filePath = seq.replaceAll("\"","");
            File file = new File(filePath);

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                br.readLine();
                DefaultTableModel model = (DefaultTableModel) table1.getModel();
                JOptionPane.showMessageDialog(null,"Please wait for a while...");
                model.setColumnIdentifiers(name1);
                Object[] tableLines = br.lines().toArray();

                for (Object tableLine : tableLines) {

                    Protein protein = new Protein();
                    String line = tableLine.toString();
                    String[] dataRow = line.split("\t");

                    protein.setId(Integer.parseInt(dataRow[0]));
                    protein.setProteins(dataRow[1]);
                    protein.setSequences(dataRow[2]);
                    protein.setAnnotations(dataRow[3]);
                    protein.setLength(dataRow[2].length());
                    ProteinDao.insert(protein);
                }

                initTable(tablemod,ProteinDao.getAll());

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            JOptionPane.showMessageDialog(null,"success！");

        });
        nextButton.addActionListener(e -> {
            //next page
            initTable(tablemod,ProteinDao.getAll());
        });
        searchButton.addActionListener(e -> {
            String seq = JOptionPane.showInputDialog(panel1,"Input the protein's sequences","Search proteins",JOptionPane.QUESTION_MESSAGE);
            if(seq == null || seq.isEmpty()){
                return;
            }
            index = 0;
            initTable(tablemod,ProteinDao.selBySeq(seq));
        });


        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> source = (JComboBox<String>)e.getSource();
                String selectedOption = (String)source.getSelectedItem();
                show_num = Integer.parseInt(selectedOption);
                index = 0;
                initTable(tablemod,ProteinDao.getAll());
            }

        });
        matchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Seq1 = seq1Tf.getText();
                String Seq2 = seq2Tf.getText();
                if (Seq1.isEmpty() || Seq2.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please insert the sequences to MATCH!");
                }
                else {
                    JOptionPane.showMessageDialog(null, String.format("The similarity is %.1f%c",similarity(Seq1,Seq2)*100,'%'));
                }
            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = table1.getSelectedRow();
                DefaultTableModel model = (DefaultTableModel) table1.getModel();

                if(seq1Tf.getText().isEmpty()){
                    seq1Tf.setText(model.getValueAt(row,2).toString());
                }
                else {
                    seq2Tf.setText(model.getValueAt(row,2).toString());
                }

            }
        });
    }

    public static double similarity(String s1, String s2) {
        String longer = s1, shorter = s2;
        if (s1.length() < s2.length()) { // longer should always have greater length
            longer = s2; shorter = s1;
        }
        int longerLength = longer.length();
        //if (longerLength == 0) { return 1.0; /* both strings are zero length */ }
        return (longerLength - editDistance(longer, shorter)) / (double) longerLength;

    }

    public static int editDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        int[] costs = new int[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0)
                    costs[j] = j;
                else {
                    if (j > 0) {
                        int newValue = costs[j - 1];
                        if (s1.charAt(i - 1) != s2.charAt(j - 1))
                            newValue = Math.min(Math.min(newValue, lastValue),
                                    costs[j]) + 1;
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0)
                costs[s2.length()] = lastValue;
        }
        return costs[s2.length()];
    }


    public static void main(String[] args) {
        new Protein_Dashboard();

    }

    private JTable table1;
    private JPanel panel1;
    private JButton nextButton;
    private JButton searchButton;
    private JButton importButton;
    private JPanel tablePane;
    private JComboBox comboBox;
    private JTextField seq2Tf;
    private JTextField seq1Tf;
    private JButton matchButton;
    private JLabel Sequence1;
    private final JFrame frame;

}
