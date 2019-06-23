import util.ThreadHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainJFrame extends JFrame {
    JMenuBar jMenuBar;
    JMenu jMenuHelp, jMenuLink, jMenuDonation;
    JButton inButton, outButton;
    JTextField inTextField, outTextField;

    public MainJFrame() {
        // frame init
        FrameInit();
        // menu bar
        MenuInit();
        // function
        FunctionInit();
        // transaction
        TransactionInit();

        // 重新绘制界面
        this.setVisible(false);
        this.setVisible(true);
    }

    private void FrameInit() {
        this.setTitle("字符画转换器");
        this.setSize(440, 230);//注意：该行代码保持在前，才可以使得窗口位于中央
        this.setResizable(false);
        //设置窗口位置
//        this.setLocationRelativeTo(null);//窗口放置于屏幕中央
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();
        int xPos = (dim.width / 2) - (this.getWidth() / 2);
        int yPos = (dim.height / 2) - (this.getHeight() / 2);
        this.setLocation(xPos, yPos);
        this.setResizable(false);//窗口大小保持不变
        //窗口初始化
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void MenuInit() {
        this.jMenuBar = new JMenuBar();
        //1
        this.jMenuHelp = new JMenu("帮助说明");
        JMenuItem help1 = new JMenuItem("使用说明");
        this.jMenuHelp.add(help1);
        //2
        this.jMenuLink = new JMenu("开源链接");
        JMenuItem link1 = new JMenuItem("博客");
        this.jMenuLink.add(link1);
        JMenuItem link2 = new JMenuItem("代码");
        this.jMenuLink.add(link2);
        //3
        this.jMenuDonation = new JMenu("支持作者");
        JMenuItem donation1 = new JMenuItem("请作者喝茶");
        this.jMenuDonation.add(donation1);
        //menubar
        jMenuBar.add(jMenuHelp);
        jMenuBar.add(jMenuLink);
        jMenuBar.add(jMenuDonation);
        this.setJMenuBar(this.jMenuBar);
    }

    private void FunctionInit() {
//        this.setLayout(new GridLayout(2,2));
        this.setLayout(null);
        int INTERVAL = 10;

        //输入文件夹按钮
        inButton = new JButton("选择");
        inButton.setBounds(INTERVAL, INTERVAL, 100, 50);
        inButton.setContentAreaFilled(false);//消除按钮背景颜色
        inButton.setOpaque(false);//去除边框
        inButton.setFocusPainted(false);//去除突起
        this.add(inButton);
        //输入文件夹文本框
        inTextField = new JTextField("输入文件夹路径");
        inTextField.setBounds(INTERVAL + 100 + INTERVAL, INTERVAL, 300, 50);
        this.add(inTextField);
        //输出文件夹按钮
        outButton = new JButton("选择");
        outButton.setBounds(INTERVAL, INTERVAL + 50 + INTERVAL, 100, 50);
        outButton.setContentAreaFilled(false);//消除按钮背景颜色
        outButton.setOpaque(false);//去除边框
        outButton.setFocusPainted(false);//去除突起
        this.add(outButton);
        //输出文件夹文本框
        outTextField = new JTextField("输出文件夹路径");
        outTextField.setBounds(INTERVAL + 100 + INTERVAL, INTERVAL + 50 + INTERVAL, 300, 50);
        this.add(outTextField);
        //设置事件监听
        inButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                jfc.showOpenDialog(null);
                File infile = jfc.getSelectedFile();
                if (infile != null) {
                    inTextField.setText(infile.getPath());
                }
            }
        });

        outButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                jfc.showOpenDialog(null);
                File outfile = jfc.getSelectedFile();
                if (outfile != null) {
                    outTextField.setText(outfile.getPath());
                }
            }
        });
    }

    private void TransactionInit() {
        JRadioButton b1 = new JRadioButton("IMG");
        b1.setBounds(10, 10 + 50 + 10 + 50 + 10, 50, 30);
        JRadioButton b2 = new JRadioButton("GIF");
        b2.setBounds(10 + 50 + 10, 10 + 50 + 10 + 50 + 10, 50, 30);
        JRadioButton b3 = new JRadioButton("FLV");
        b3.setBounds(10 + 50 + 10 + 50 + 10, 10 + 50 + 10 + 50 + 10, 50, 30);
        ButtonGroup group = new ButtonGroup();
        group.add(b1);
        group.add(b2);
        group.add(b3);
        b1.setSelected(true);
        this.add(b1);
        this.add(b2);
        this.add(b3);
        //事务操作按钮
        JButton button1 = new JButton("开始");
        button1.setBounds(10 + 50 + 10 + 50 + 10 + 40 + 10 + 50, 10 + 50 + 10 + 50 + 10, 80, 30);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inpath = inTextField.getText();
                String outpath = outTextField.getText();
                if (inpath.equals("") || outpath.equals("") || inpath.equals("输入文件夹路径") || outpath.equals("输出文件夹路径")) {
                    JOptionPane.showMessageDialog(null, "请检查输入输出路径的正确性！", "格式检查", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (b1.isSelected()) {
                        if (JOptionPane.YES_NO_OPTION == JOptionPane.showConfirmDialog(null, "choose one", "choose one", JOptionPane.YES_NO_OPTION)) {
                            new IMGThread(TYPE.IMG, inpath, outpath, 4).start();
                        }
                    } else if (b2.isSelected()) {
                        if (JOptionPane.YES_NO_OPTION == JOptionPane.showConfirmDialog(null, "choose one", "choose one", JOptionPane.YES_NO_OPTION)) {
                            new IMGThread(TYPE.GIF, inpath, outpath, 4).start();
                        }
                    } else if (b3.isSelected()) {
                        JOptionPane.showMessageDialog(null, "待开发！", "SORRY", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "请选择字符画转换对象的格式！", "输入文件格式", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        this.add(button1);
        JButton button2 = new JButton("停止");
        button2.setBounds(10 + 50 + 10 + 50 + 10 + 40 + 10 + 50 + 20 + 80, 10 + 50 + 10 + 50 + 10, 80, 30);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(null, "暂未开发停止功能，关闭窗口即可！", "SORRY", JOptionPane.ERROR_MESSAGE);
                if (JOptionPane.YES_NO_OPTION == JOptionPane.showConfirmDialog(null, "是否暂停所有操作并退出？", "关闭程序", JOptionPane.YES_NO_OPTION)) {
                    System.exit(0);
                }
            }
        });
        this.add(button2);
    }

    public enum TYPE {IMG, GIF, FLV}

    private class IMGThread extends Thread {
        private TYPE type;
        private String in;
        private String out;
        private int speed;

        public IMGThread(TYPE type, String in, String out, int speed) {
            this.type = type;
            this.in = in;
            this.out = out;
            this.speed = speed;
        }

        public void run() {
            JOptionPane.showMessageDialog(null, "多线程处理开始！", "提示", JOptionPane.INFORMATION_MESSAGE);
            switch (type) {
                case IMG:
                    ThreadHelper.ImageThread(in, out, speed);
                    break;
                case GIF:
                    ThreadHelper.GifThread(in, out, speed);
                    break;
                case FLV:
                    break;
                default:
                    break;
            }
        }
    }

    public static void main(String[] args) {
        //TODO 程序入口
        MainJFrame mainJFrame = new MainJFrame();
    }
}
