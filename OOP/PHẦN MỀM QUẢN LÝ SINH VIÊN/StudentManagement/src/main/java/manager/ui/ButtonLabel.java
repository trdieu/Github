/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager.ui;

import java.awt.Color;
import javax.swing.JLabel;

/**
 *
 * @author Dell
 */
public class ButtonLabel extends JLabel{
    private Color backgoundColor;
    private Color foregoundColor;
    private Color defaultBackgoundColor;
    private Color defaultForegoundColor;
    
    public ButtonLabel() {
        
    }
    
    public void setDefaultForegoundColor(Color defaultForegoundColor) {
        this.defaultForegoundColor = defaultForegoundColor;
    }

    public void setDefaultBackgoundColor(Color defaultBackgoundColor) {
        this.defaultBackgoundColor = defaultBackgoundColor;
    }
    
    public ButtonLabel(Color backgoundColor, Color foregoundColor) {
        this.backgoundColor = backgoundColor;
        this.foregoundColor = foregoundColor;
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        setOpaque(true);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonLabelMouseExited(evt);
            }
        });
    }
    
    public void buttonLabelMouseEntered(java.awt.event.MouseEvent evt) {
        defaultBackgoundColor = getBackground();
        defaultForegoundColor = getForeground();
        if(backgoundColor == null) {
            setBackground(defaultBackgoundColor);
        } else {
            setBackground(backgoundColor);
        } 
        
        if(foregoundColor == null) {
            setForeground(defaultForegoundColor);
        } else {
            setForeground(foregoundColor);
        } 
    }
    
    public void buttonLabelMouseExited(java.awt.event.MouseEvent evt) {
        setBackground(defaultBackgoundColor);
        setForeground(defaultForegoundColor);
    }
}
