package view;

import javax.swing.*;
import java.awt.*;

public class VBoard
{
    private JFrame frame;
    private JButton[] whiteButtons;
    private JButton[] blackButtons;
    private JLabel whiteKazan;
    private JLabel blackKazan;
    private JPanel mainPanel;
    private JPanel whitePanel;
    private JPanel kazanPanel;
    private JPanel blackPanel;

    public static void main(String[] args)
    {
        VBoard b = new VBoard("Agile");
    }
    public VBoard(String title)
    {
        frame = new JFrame(title);
        mainPanel = new JPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        whitePanel = new JPanel();
        kazanPanel = new JPanel();
        blackPanel = new JPanel();

        whiteButtons = new JButton[10];
        blackButtons = new JButton[10];


        BoxLayout boxlayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);

        mainPanel.setLayout(boxlayout);

        FlowLayout flowL = new FlowLayout();

        whitePanel.setLayout(flowL);
        kazanPanel.setLayout(flowL);
        blackPanel.setLayout(flowL);

        fillWithButtons(whitePanel, whiteButtons);
        fillWithButtons(blackPanel, blackButtons);

        whiteKazan = new JLabel("White Kazan: ");
        blackKazan = new JLabel ("Black Kazan: ");

        kazanPanel.add(whiteKazan);
        kazanPanel.add(blackKazan);

        mainPanel.add(whitePanel);
        mainPanel.add(kazanPanel);
        mainPanel.add(blackPanel);


        frame.setSize(450, 300);
        frame.add(mainPanel);
        frame.setVisible(true);

    }


    private void fillWithButtons(JPanel panelToPopulate, JButton[] buttonArray)
    {
        for(int i = 1; i < 10; i++)
        {
            JButton newButton = new JButton(Integer.toString(i));
            buttonArray[i] = newButton;
            panelToPopulate.add(newButton);
        }
    }
}