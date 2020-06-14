
package question3;

import question3.tp3.PileI;
import question3.tp3.PilePleineException;
import question3.tp3.PileVideException;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Décrivez votre classe Controleur ici.
 * 
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Controleur extends JPanel {

    private JButton push, add, sub, mul, div, clear;
    private PileModele<Integer> pile;
    private JTextField donnee;

    public Controleur(PileModele<Integer> pile) {
        super();
        this.pile = pile;
        this.donnee = new JTextField(8);

        this.push = new JButton("push");
        this.add = new JButton("+");
        this.sub = new JButton("-");
        this.mul = new JButton("*");
        this.div = new JButton("/");
        this.clear = new JButton("[]");

        setLayout(new GridLayout(2, 1));
        add(donnee);
        donnee.addActionListener(null /* null est à remplacer */);
        JPanel boutons = new JPanel();
        boutons.setLayout(new FlowLayout());
        
        ActionListener pushEvent=new ActionListener(){ public void actionPerformed(ActionEvent ae)
            {
                try{
                    Integer op=operande();
                    if(op!=null)
                        pile.empiler(op);
                    actualiserInterface();
                }catch(NumberFormatException ex){}catch(question3.tp3.PilePleineException exp){}
            }};
            
       ActionListener addEvent=new ActionListener(){ public void actionPerformed(ActionEvent ae)
            {
                try{
                    pile.empiler(pile.depiler() + pile.depiler());
                    actualiserInterface();
                }catch(question3.tp3.PileVideException ex){}catch(question3.tp3.PilePleineException exp){}
            }};
       ActionListener subEvent=new ActionListener(){ public void actionPerformed(ActionEvent ae)
            {
                try{
                    pile.empiler(pile.depiler() - pile.depiler());
                    actualiserInterface();
                }catch(question3.tp3.PileVideException ex){}catch(question3.tp3.PilePleineException exp){}
            }};
        ActionListener mulEvent=new ActionListener(){ public void actionPerformed(ActionEvent ae)
            {
                try{
                    pile.empiler(pile.depiler() * pile.depiler());
                    actualiserInterface();
                }catch(question3.tp3.PileVideException ex){}catch(question3.tp3.PilePleineException exp){}
            }};
         ActionListener divEvent=new ActionListener(){ public void actionPerformed(ActionEvent ae)
            {
                try{
                    int s=pile.depiler();
                    if(s!=0)
                        pile.empiler(pile.depiler() / s);
                    actualiserInterface();
                }catch(question3.tp3.PileVideException ex){}catch(question3.tp3.PilePleineException exp){}
            }};
        ActionListener clearEvent=new ActionListener(){ public void actionPerformed(ActionEvent ae)
            {
                try{
                    while(pile.sommet()!=null)
                        pile.depiler();
                    actualiserInterface();
                }catch(question3.tp3.PileVideException ex){}
            }};
        boutons.add(push);  push.addActionListener(pushEvent);
        boutons.add(add);   add.addActionListener(addEvent);
        boutons.add(sub);   sub.addActionListener(subEvent);
        boutons.add(mul);   mul.addActionListener(mulEvent);
        boutons.add(div);   div.addActionListener(divEvent);
        boutons.add(clear); clear.addActionListener(clearEvent);
        add(boutons);
        boutons.setBackground(Color.red);
        actualiserInterface();
    }

    public void actualiserInterface() {
        if(pile.estVide()){
          add.setEnabled(false);
          sub.setEnabled(false);
          mul.setEnabled(false);
          div.setEnabled(false);
          clear.setEnabled(false);
          push.setEnabled(true);
       }
        if(pile.taille()== 1){
          add.setEnabled(false);
          sub.setEnabled(false);
          mul.setEnabled(false);
          div.setEnabled(false);
          clear.setEnabled(true);
          push.setEnabled(true);
        }
         if(pile.taille()> 1){
          add.setEnabled(true);
          sub.setEnabled(true);
          mul.setEnabled(true);
          div.setEnabled(true);
          clear.setEnabled(true);
          push.setEnabled(true);
        }
         if(pile.estPleine()) {
          push.setEnabled(false);
          add.setEnabled(true);
          sub.setEnabled(true);
          mul.setEnabled(true);
          div.setEnabled(true);
          clear.setEnabled(true);
        }
    }

    private Integer operande() throws NumberFormatException {
        String text=donnee.getText();
        if(text==null||text.isEmpty())
            return null;
        return Integer.parseInt(text);
    }

    // à compléter
    // en cas d'exception comme division par zéro, 
    // mauvais format de nombre suite à l'appel de la méthode operande
    // la pile reste en l'état (intacte)

}
