package main.java.view.utils;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

//From stackoverflow
public class JPanelRotate extends JPanel
{
	private boolean m_isRotating = true;
	
	  Graphics2D g2d;
	  public JPanelRotate()
	  {

	  }
	  
	  
	  
      @Override
     public void paintComponent(Graphics g) {
         super.paintComponent(g);
         g2d = (Graphics2D) g;
         if (m_isRotating) {
             float x = this.getWidth() / 2.0f;
             float y = this.getHeight() / 2.0f;
             g2d.rotate(Math.toRadians(m_isRotating?180.0:0.0), x, y);
         }
     }

     @Override
     public void paintChildren(Graphics g) {
         if (m_isRotating) super.paintChildren(g2d);
         else super.paintChildren(g);
     }   
	}
