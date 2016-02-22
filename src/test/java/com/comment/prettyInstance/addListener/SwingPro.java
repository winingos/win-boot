package com.comment.prettyInstance.addListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Mtime on 2016/2/4.
 */
public class SwingPro {
    private JFrame mainWin = new JFrame("使用注解绑定事件监听器");

    /**
     * 使用注解设置Listener
     */
    @ActionListenerFor(listener = OkListener.class)
    private JButton ok = new JButton("确定");

    @ActionListenerFor(listener = CancelListener.class)
    private JButton cancel = new JButton("取消");

    public SwingPro init() {
        JPanel jp = new JPanel();

        // 使得注解生效
        try {
            ActionListenerInstaller.isntall(this);
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace(System.out);
        }

        jp.add(ok);
        jp.add(cancel);
        mainWin.add(jp);
        mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWin.pack();
        mainWin.setVisible(true);

        return this;
    }

    public static void main(String[] args) {
        new SwingPro().init();
    }
}

class OkListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "你点击了确认按钮!");
    }
}

class CancelListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "你点击了取消按钮!");
    }
}
