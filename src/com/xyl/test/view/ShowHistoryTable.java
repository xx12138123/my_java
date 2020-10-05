package com.xyl.test.view;

import com.xyl.test.model.Group;
import com.xyl.test.model.Value;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

public class ShowHistoryTable extends JTable {
    private static final java.lang.String[] TABLE={"操作","加密方式","输入内容","输出内容","偏移量"};
    private List<Group> groupList;
    private DefaultTableModel tableModel;

    public ShowHistoryTable(){
        super();
        groupList=new ArrayList<>();
        tableModel=new DefaultTableModel(new Object[][]{},TABLE);
        setModel(tableModel);
        initView();
    }
    private void initView(){
        JTableHeader jTableHeader = getTableHeader();
        jTableHeader.setResizingAllowed(true);
        getColumnModel().getColumn(0).setMaxWidth(50);
        getColumnModel().getColumn(1).setMaxWidth(100);
        getColumnModel().getColumn(4).setMaxWidth(50);

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)) {
                    JPopupMenu popup = new JPopupMenu();
                    JMenuItem delete = new JMenuItem("删除");
                    JMenuItem copy=new JMenuItem("复制");
                    delete.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e2) {
                            int[] chooseRows=getSelectedRows();
                            for(int i=0;i<chooseRows.length;i++){
                                tableModel.removeRow(chooseRows[i]-i);
                                groupList.remove(chooseRows[i]-i);
                            }
                        }
                    });
                    copy.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int chooseRow=getSelectedRow();
                            int chooseColumn=getSelectedColumn();
                            setSysClipboardText((String) tableModel.getValueAt(chooseRow,chooseColumn));

                        }
                    });
                    popup.add(copy);
                    popup.add(delete);
                    popup.add(new JSeparator());
                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }
    public void addGroup(Group group){
        groupList.add(group);
        String[] add=new String[TABLE.length];
        add[0]=group.isEncrypt()?"加密":"解密";
        add[1]=new String[]{"MD5加密","凯撒加密"}[group.getType()];
        add[2]=group.getInputText();
        add[3]=group.getOutputText();
        add[4]=group.getType()== Value.TYPE_CAESAR?String.valueOf(group.getOffset()):"";
        tableModel.addRow(add);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public static void setSysClipboardText(String writeMe) {
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable tText = new StringSelection(writeMe);
        clip.setContents(tText, null);
    }
}
