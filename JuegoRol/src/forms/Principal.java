package forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;


public class  Principal {
    private JPanel panelJuego;
    private ArrayList<JLabel> arrayMuros = new ArrayList<>();
    private ArrayList<JLabel> arrayEsqueletosVertical = new ArrayList<>();
    private ArrayList<JLabel> arrayEsqueletosHorizontal = new ArrayList<>();
    private ArrayList <Partida> partidas = new ArrayList<>();
    private int segundos = 0;
    private int vidas = 5;

    private JPanel panelInicio;
    private static JFrame frame;
    private JPanel panelCabecera;
    private JLabel labelVidas;
    private JLabel labelNombre;
    private int oro =0;
    private JLabel labelOro;
    private JLabel labelObjetos;
    private JLabel labelTiempo;
    private JLabel labelMuro;
    private JLabel labelTierra;
    private  JLabel labelEspada;
    private  JLabel labelPocion;
    private  JLabel labelMitraPapal;
    private ImageIcon backgroundImage;
    private JLabel backgroundLabel;
    private JLabel labelObjetoOro;
    private JLabel labelObjetoPocion;
    private JLabel labelObjetoEspada;
    private JLabel labelObjetoMitraPapal;


    private  JLabel labelMago;
    private int magoX;
    private int magoY;

    private  JLabel labelGuerrero;
    private int guerreroX;
    private int guerreroY;
    private  JLabel labelSacerdote;
    private int sacerdoteX;
    private int sacerdoteY;
    private JLabel labelEsqueletoVertical;
    private JLabel labelEsqueletoHorizontal;
    private String nombreBoton;

    public Principal(){

        //juego
        panelJuego = new JPanel();
        panelJuego.setPreferredSize(new Dimension(800, 600));
        panelJuego.setSize(new Dimension(800, 600));
        panelJuego.setLayout(null); // si no da nullPointerException

        showPanelInicio();

    }

    private String datosPersonaje(){
        String nombre;
        nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del personaje:");
       // Personaje p1 = new Personaje(nombre);
        return nombre;
    }
    private void  showJuego(String nombre){
        showPanelCabecera(nombre);
       // showPanelBody();
        showBackgroundMazmorra();
    }
    private void showBackgroundMazmorra() {
        int x, y;
        int anchoPanel = panelJuego.getWidth() - panelCabecera.getWidth();
        int alturaPanel = panelJuego.getHeight();

        //MURO

        ImageIcon imgMuro = new ImageIcon("src/images/dungeon/tile004.png");
        Icon iconMuro = new ImageIcon(imgMuro.getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT));
        //muro superior e inferior
        for (x = 0; x <= anchoPanel; x += 32) { // Incrementa la posición x en 32 para cada JLabel
            addLabelMuro(x,0,iconMuro);
            addLabelMuro(x, alturaPanel - 32, iconMuro); // Agrega un muro en la fila inferior
        }
        //muro izquierda
        for (y = 96; y <= alturaPanel - 32; y += 32) {
            addLabelMuro(0, y, iconMuro);

        }
        //muro derecha
        for (y = 32; y <= alturaPanel - 96; y += 32) {
            addLabelMuro(anchoPanel - 32, y, iconMuro);
        }

        // MUROS EXTRA
        int separacionPared = 96;
        // Muros de cuatro bloques de largo

        for (x = separacionPared; x <= anchoPanel - separacionPared - 160; x += 32) {
            addLabelMuro(x+32, separacionPared, iconMuro); // muro superior
        }
            for (x = separacionPared; x <= anchoPanel - separacionPared - 96; x += 32) {
           // addLabelMuro(x, separacionPared, iconMuro); // este muro haz que sea más corto
            addLabelMuro(x , alturaPanel - separacionPared - 32, iconMuro);
        }

        // Muros de cinco bloques de largo
        for (y = separacionPared; y <= alturaPanel - separacionPared - 160; y += 32) {
            addLabelMuro(separacionPared +170, y-96 , iconMuro);
            addLabelMuro(anchoPanel - separacionPared - 32, y, iconMuro);

            addLabelMuro(separacionPared +100, y+300 , iconMuro);
        }

        int grosorPanel= panelJuego.getWidth()-panelCabecera.getWidth()-64;
        int altoPanel = panelJuego.getHeight() -64;
        //OBJETOS EN POSICIONES RANDOM
        //con función no se veía lo siento

        //OBJETO ORO
        labelObjetoOro = new JLabel();

        labelObjetoOro.setSize(32,32);
        ImageIcon imgOro = new ImageIcon("src/images/dungeon/dollar.png");
        Icon iconOro = new ImageIcon(
                imgOro.getImage().getScaledInstance( labelObjetoOro.getWidth(),  labelObjetoOro.getHeight(), Image.SCALE_DEFAULT)
        );

        boolean choca;
        do {
            x = (int) (Math.random() * (grosorPanel - labelObjetoOro.getWidth()));
            y = (int) (Math.random() * (altoPanel - labelObjetoOro.getHeight()));

            choca = false;
            for (JLabel labelMuro : arrayMuros) {
                if (labelMuro.getBounds().intersects(new Rectangle(x, y, labelObjetoOro.getWidth(), labelObjetoOro.getHeight()))) {
                    choca = true;
                }
            }
        } while (choca);

        labelObjetoOro.setLocation(x, y);
        labelObjetoOro.setIcon(iconOro);
        panelJuego.add(labelObjetoOro);

        //OBJETO POCIÓN
        labelObjetoPocion = new JLabel();
        labelObjetoPocion.setSize(32,32);
        ImageIcon imgPocion = new ImageIcon("src/images/dungeon/potion.png");
        Icon iconPocion = new ImageIcon(
                imgPocion.getImage().getScaledInstance( labelObjetoPocion.getWidth(), labelObjetoPocion.getHeight(), Image.SCALE_DEFAULT)
        );


        do {
            x = (int) (Math.random() * (grosorPanel - labelObjetoPocion.getWidth()));
            y = (int) (Math.random() * (altoPanel - labelObjetoPocion.getHeight()));

            choca = false;
            for (JLabel labelMuro : arrayMuros) {
                if (labelMuro.getBounds().intersects(new Rectangle(x, y, labelObjetoPocion.getWidth(), labelObjetoPocion.getHeight()))) {
                    choca = true;
                    //break;
                }
            }
        } while (choca);

        labelObjetoPocion.setIcon(iconPocion);
        labelObjetoPocion.setLocation(x, y);
        panelJuego.add(labelObjetoPocion);

        //OBJETO ESPADA
        labelObjetoEspada = new JLabel();
        labelObjetoEspada.setSize(32,32);
        ImageIcon imgEspada = new ImageIcon("src/images/dungeon/sword.png");
        Icon iconEspada = new ImageIcon(
                imgEspada.getImage().getScaledInstance( labelObjetoEspada.getWidth(), labelObjetoEspada.getHeight(), Image.SCALE_DEFAULT)
        );

        do {
            x = (int) (Math.random() * (grosorPanel - labelObjetoEspada.getWidth()));
            y = (int) (Math.random() * (altoPanel - labelObjetoEspada.getHeight()));

            choca = false;
            for (JLabel labelMuro : arrayMuros) {
                if (labelMuro.getBounds().intersects(new Rectangle(x, y, labelObjetoEspada.getWidth(), labelObjetoEspada.getHeight()))) {
                    choca = true;
                    //  break;
                }
            }
        } while (choca);

        labelObjetoEspada.setIcon(iconEspada);
        labelObjetoEspada.setLocation(x, y);
        panelJuego.add(labelObjetoEspada);

        //OBJETO MITRA PAPAL
        labelObjetoMitraPapal = new JLabel();
        labelObjetoMitraPapal.setSize(32,32);
        ImageIcon imgMitraPapal = new ImageIcon("src/images/dungeon/mitra.png");
        Icon iconMitraPapal = new ImageIcon(
                imgMitraPapal.getImage().getScaledInstance( labelObjetoMitraPapal.getWidth(), labelObjetoMitraPapal.getHeight(), Image.SCALE_DEFAULT)
        );
        do {
            x = (int) (Math.random() * (grosorPanel - labelObjetoMitraPapal.getWidth()));
            y = (int) (Math.random() * (altoPanel - labelObjetoMitraPapal.getHeight()));

            choca = false;
            for (JLabel labelMuro : arrayMuros) {
                if (labelMuro.getBounds().intersects(new Rectangle(x, y, labelObjetoMitraPapal.getWidth(), labelObjetoEspada.getHeight()))) {
                    choca = true;
                    //  break;
                }
            }
        } while (choca);

        labelObjetoMitraPapal.setIcon(iconMitraPapal);
        labelObjetoMitraPapal.setLocation(x, y);
        panelJuego.add(labelObjetoMitraPapal);

        //ESQUELETOS
        showEsqueletos();

        //TIERRA
        ImageIcon imgTierra = new ImageIcon("src/images/dungeon/tile001.png");
        Icon iconTierra = new ImageIcon(imgTierra.getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT));
        // Rellenar los espacios y la zona interior con tierra
        for (x = 0; x <= anchoPanel ; x += 32) {
            for (y = 32; y <= alturaPanel ; y += 32) {
                addLabelTierra(x, y, iconTierra);
            }
        }
    }

    private void addLabelTierra(int x, int y, Icon iconTierra) {
        labelTierra = new JLabel();
        labelTierra.setSize(32, 32);
        labelTierra.setLocation(x, y);
        labelTierra.setIcon(iconTierra);
        panelJuego.add(labelTierra);
    }

    private void addLabelMuro(int x, int y, Icon iconMuro){
        labelMuro = new JLabel();
        labelMuro.setSize(32, 32);
        labelMuro.setLocation(x, y);
        labelMuro.setIcon(iconMuro);
        panelJuego.add(labelMuro);
        arrayMuros.add(labelMuro);
    }

    private void showPanelInicio() {

        // Crear panel de inicio
        panelInicio = new JPanel();
        panelInicio.setLayout(null); // Desactivar el layout

        // Ajustar ubicación y tamaño del panel de inicio
        panelInicio.setBounds(0, 0, panelJuego.getWidth(), panelJuego.getHeight());

        // FONDO DE LA PANTALLA
        backgroundImage = new ImageIcon("src/images/fondo4.jpg");
        backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, panelJuego.getWidth(), panelJuego.getHeight());
        panelInicio.add(backgroundLabel);

        // Declarar un array de botones
        JButton[] botones = new JButton[3];
        String[] nombresBotones = {"Mago", "Guerrero", "Sacerdote"};

        // Crear los botones y asignarles un ActionListener
        for (int i = 0; i < botones.length; i++) {
            botones[i] = new JButton(nombresBotones[i]);
            botones[i].setBounds(348, 200 + i * 50, 100, 30); // Ajustar posición y tamaño de los botones
            botones[i].setBackground(new Color(205,204,60));
            botones[i].setFocusPainted(false); // para que le botón no se quede seleccionado

            int indice = i; // Necesario para que la variable sea "final" en el ActionListener
            botones[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Acción al seleccionar un personaje
                    // Puedes acceder al botón específico utilizando el índice "indice"
                     nombreBoton = nombresBotones[indice];


                    if(nombresBotones[indice]== "Mago"){
                        JOptionPane.showMessageDialog(null, nombreBoton+"! Desata la magia y conquista.\n¡Poder arcano en tus manos!");
                        //poner esto en una función, devolver m1, llamarla aqui y usar variable

                        //PERSONAJE
                        showMago();

                    }else if (nombresBotones[indice]== "Guerrero"){
                        JOptionPane.showMessageDialog(null, nombreBoton+"! Espada en alto.\n¡Demuestra tu valentía!");
                        //PERSONAJE
                        showGuerrero();

                    } else{
                        JOptionPane.showMessageDialog(null,  nombreBoton+"! Sanador divino.\n¡Guía y protege a tus aliados!");
                        //PERSONAJE
                        showSacerdote();
                    }

                    String nombre;
                    nombre = datosPersonaje();

                    panelInicio.setVisible(false);
                    panelJuego.setVisible(true);
                    showJuego(nombre);
                }
            });
            panelInicio.add(botones[i]);
        }
        // Agregar panel de inicio al panel principal
        panelJuego.add(panelInicio);

    }



    private void showEsqueletos(){
        int x,y;
        int anchoPanel= panelJuego.getWidth()-panelCabecera.getWidth()-64;
        int altoPanel = panelJuego.getHeight() -64;
        for (int j = 0; j < 6; j++) {
            //ESQUELETOS VERTICALES
            ImageIcon imgEsqueletoVerticalAbajo = new ImageIcon("src/images/skeleton/skeleton_down.gif");
            Icon iconEsqueletoVerticalAbajo = new ImageIcon(imgEsqueletoVerticalAbajo.getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));

            ImageIcon imgEsqueletoVerticalArriba = new ImageIcon("src/images/skeleton/skeleton_up.gif");
            Icon iconEsqueletoVerticalArriba = new ImageIcon(imgEsqueletoVerticalArriba.getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));

            //ESQUELETOS HORIZONTALES
            ImageIcon imgEsqueletoHorizontalIzquierda = new ImageIcon("src/images/skeleton/skeleton_left.gif");
            Icon iconEsqueletoHorizontalIzquierda = new ImageIcon(imgEsqueletoHorizontalIzquierda.getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));

            ImageIcon imgEsqueletoVerticalDerecha = new ImageIcon("src/images/skeleton/skeleton_right.gif");
            Icon iconEsqueletoVerticalDerecha = new ImageIcon(imgEsqueletoVerticalDerecha.getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));

            boolean choca;
            do {
                x = (int) (Math.random() * (anchoPanel - 64));
                y = (int) (Math.random() * (altoPanel - 64));

                choca = false;
                for (JLabel labelMuro : arrayMuros) {
                    if (labelMuro.getBounds().intersects(new Rectangle(x, y, 64, 64))) {
                        choca = true;
                        break;
                    }
                }
            } while (choca);

            //PONER EN LOS OBJETOS QUE SI CHOCA CON ESQUELETO QUE SE GENERE EN OTRO ESPACIO
            if (j == 0 || j == 1 || j == 2) {
                addLabelEsqueletosVertical(x, y, iconEsqueletoVerticalAbajo);
            } else {
                addLabelEsqueletosHorizontal(x, y, iconEsqueletoHorizontalIzquierda);
            }
        }

    }

    private void addLabelEsqueletosVertical (int x, int y, Icon iconEsqueletoVertical) {

        //tres esqueletos con movimiento vertical
        labelEsqueletoVertical = new JLabel();
        labelEsqueletoVertical.setSize(64,64);
        labelEsqueletoVertical.setLocation(x,y);
        labelEsqueletoVertical.setIcon(iconEsqueletoVertical);
        panelJuego.add(labelEsqueletoVertical);
        arrayEsqueletosVertical.add(labelEsqueletoVertical);
    }
    private void addLabelEsqueletosHorizontal (int x, int y, Icon iconEsqueletoHorizontal) {

        //tres esqueletos con movimiento vertical
        labelEsqueletoHorizontal = new JLabel();
        labelEsqueletoHorizontal.setSize(64,64);
        labelEsqueletoHorizontal.setLocation(x,y);
        labelEsqueletoHorizontal.setIcon(iconEsqueletoHorizontal);
        panelJuego.add(labelEsqueletoHorizontal);
        arrayEsqueletosHorizontal.add(labelEsqueletoHorizontal);
    }
    private void showMago() {
        labelMago = new JLabel();
        labelMago.setSize(50, 50);
        magoX = 0;
        magoY = 32;

        ImageIcon imgMagoDown = new ImageIcon("src/images/wizard/wizard_down.gif");
        Icon iconMagoDown = new ImageIcon(imgMagoDown.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));

        labelMago.setIcon(iconMagoDown);
        labelMago.setLocation(magoX, magoY);
        panelJuego.add(labelMago);

        panelJuego.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                int prevX = magoX; // Guarda la posición anterior en X
                int prevY = magoY; // Guarda la posición anterior en Y
                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        // Arriba
                        magoY -= 15;
                        updateMagoIcon("src/images/wizard/wizard_up.gif");
                        break;
                    case KeyEvent.VK_DOWN:
                        // Abajo
                        magoY += 15;
                        updateMagoIcon("src/images/wizard/wizard_down.gif");
                        break;
                    case KeyEvent.VK_LEFT:
                        // Izquierda
                        magoX -= 15;
                        updateMagoIcon("src/images/wizard/wizard_left.gif");
                        break;
                    case KeyEvent.VK_RIGHT:
                        // Derecha
                        magoX += 15;
                        updateMagoIcon("src/images/wizard/wizard_right.gif");
                        break;
                }
                labelMago.setLocation(magoX, magoY);
                // Verificar colisiones con los muros
                for (JLabel labelMuro : arrayMuros) {
                    if (labelMago.getBounds().intersects(labelMuro.getBounds())) {
                        // Revertir el movimiento si hay colisión
                        magoX = prevX;
                       magoY = prevY;
                        labelMago.setLocation(magoX, magoY);
                    }
                }
            }
        });

        panelJuego.setFocusable(true);
        panelJuego.requestFocusInWindow();
    }
    private void updateMagoIcon(String imagePath) {
        ImageIcon magoIcon = new ImageIcon(imagePath);
        Icon resizedMagoIcon = new ImageIcon(magoIcon.getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));
        labelMago.setIcon(resizedMagoIcon);
    }
    private void showGuerrero() {
        labelGuerrero = new JLabel();
        labelGuerrero.setSize(50, 50);
        guerreroX = 0;
        guerreroY = 32;

        ImageIcon imgGuerreroDown = new ImageIcon("src/images/warrior/warrior_down.gif");
        Icon iconGuerreroDown = new ImageIcon(imgGuerreroDown.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));

        labelGuerrero.setIcon(iconGuerreroDown);
        labelGuerrero.setLocation(guerreroX, guerreroY);
        panelJuego.add(labelGuerrero);

        panelJuego.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                int prevX = guerreroX; // Guarda la posición anterior en X
                int prevY = guerreroY; // Guarda la posición anterior en Y

                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        // Arriba
                        guerreroY -= 15;
                        updateGuerreroIcon("src/images/warrior/warrior_up.gif");
                        break;
                    case KeyEvent.VK_DOWN:
                        // Abajo
                        guerreroY += 15;
                        updateGuerreroIcon("src/images/warrior/warrior_down.gif");
                        break;
                    case KeyEvent.VK_LEFT:
                        // Izquierda
                        guerreroX -= 15;
                        updateGuerreroIcon("src/images/warrior/warrior_left.gif");
                        break;
                    case KeyEvent.VK_RIGHT:
                        // Derecha
                        guerreroX += 15;
                        updateGuerreroIcon("src/images/warrior/warrior_right.gif");
                        break;
                }
                labelGuerrero.setLocation(guerreroX, guerreroY);
                // Verificar colisiones con los muros
                for (JLabel labelMuro : arrayMuros) {
                    if (labelGuerrero.getBounds().intersects(labelMuro.getBounds())) {
                        // Revertir el movimiento si hay colisión
                        guerreroX = prevX;
                        guerreroY = prevY;
                        labelGuerrero.setLocation(guerreroX, guerreroY);
                    }
                }
            }
        });

        panelJuego.setFocusable(true);
        panelJuego.requestFocusInWindow();
    }

    private void updateGuerreroIcon(String imagePath) {
        ImageIcon guerreroIcon = new ImageIcon(imagePath);
        Icon resizedGuerreroIcon = new ImageIcon(guerreroIcon.getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));
        labelGuerrero.setIcon(resizedGuerreroIcon);
    }

    private void showSacerdote() {
        labelSacerdote = new JLabel();
        labelSacerdote.setSize(50, 50);
        sacerdoteX = 0;
        sacerdoteY = 32;

        ImageIcon imgSacerdoteDown = new ImageIcon("src/images/priest/priest_down.gif");
        Icon iconSacerdoteDown = new ImageIcon(imgSacerdoteDown.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));

        labelSacerdote.setIcon(iconSacerdoteDown);
        labelSacerdote.setLocation(sacerdoteX, sacerdoteY);


        panelJuego.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                int prevX = sacerdoteX; // Guarda la posición anterior en X
                int prevY = sacerdoteY; // Guarda la posición anterior en Y
                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        // Arriba
                        sacerdoteY -= 15;
                        updateSacerdoteIcon("src/images/priest/priest_up.gif");
                        break;
                    case KeyEvent.VK_DOWN:
                        // Abajo
                        sacerdoteY += 15;
                        updateSacerdoteIcon("src/images/priest/priest_down.gif");
                        break;
                    case KeyEvent.VK_LEFT:
                        // Izquierda
                        sacerdoteX -= 15;
                        updateSacerdoteIcon("src/images/priest/priest_left.gif");
                        break;
                    case KeyEvent.VK_RIGHT:
                        // Derecha
                        sacerdoteX += 15;
                        updateSacerdoteIcon("src/images/priest/priest_right.gif");
                        break;
                }
                labelSacerdote.setLocation(sacerdoteX, sacerdoteY);
                // Verificar colisiones con los muros
                for (JLabel labelMuro : arrayMuros) {
                    if (labelSacerdote.getBounds().intersects(labelMuro.getBounds())) {
                        // Revertir el movimiento si hay colisión
                        sacerdoteX = prevX;
                        sacerdoteY = prevY;
                        labelSacerdote.setLocation(sacerdoteX, sacerdoteY);
                    }
                }
            }
        });

        panelJuego.add(labelSacerdote);
        panelJuego.setFocusable(true);
        panelJuego.requestFocusInWindow();
    }

    private void updateSacerdoteIcon(String imagePath) {
        ImageIcon sacerdoteIcon = new ImageIcon(imagePath);
        Icon resizedSacerdoteIcon = new ImageIcon(sacerdoteIcon.getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));
        labelSacerdote.setIcon(resizedSacerdoteIcon);
    }

    private void showPanelCabecera(String nombre){

        //LABEL NOMBRE
        labelNombre = new JLabel();
        labelNombre.setText( nombre+" :))");
        labelNombre.setBounds(675, 50, 100, 20);
        labelNombre.setForeground(Color.white);
        panelJuego.add(labelNombre);


        //LABEL VIDAS
        labelVidas = new JLabel();
        labelVidas.setText("Vidas: "+ vidas);
        labelVidas.setBounds(675, 80, 100, 20);
        labelVidas.setForeground(Color.white);
        panelJuego.add(labelVidas);

        //LABEL MONEDAS
        labelOro =new JLabel();
        labelOro.setText("Oro: ");
        labelOro.setBounds(675, 110, 100, 20);
        labelOro.setForeground(Color.white);
        panelJuego.add(labelOro);


        //LABEL TIEMPO
        labelTiempo = new JLabel();
        labelTiempo.setText("0 segundos");
        labelTiempo.setBounds(675, 140, 100, 20);
        labelTiempo.setForeground(Color.white);
        panelJuego.add(labelTiempo);


        //LABEL OBJETOS
        labelObjetos = new JLabel();
        labelObjetos.setText("Objetos:");
        labelObjetos.setBounds(675, 170, 100, 20);
        labelObjetos.setForeground(Color.white);
        panelJuego.add(labelObjetos);



        //LABEL OBJETOS
        labelEspada = new JLabel();
        labelEspada.setBounds(675, 195, 100, 20);
        labelEspada.setForeground(Color.white);
        panelJuego.add(labelEspada);

        //LABEL OBJETOS
       labelPocion = new JLabel();
        labelPocion.setBounds(675, 210, 100, 20);
        labelPocion.setForeground(Color.white);
        panelJuego.add(labelPocion);

        //LABEL OBJETOS
        labelMitraPapal = new JLabel();
        labelMitraPapal.setBounds(675, 225, 100, 20);
        labelMitraPapal.setForeground(Color.white);
        panelJuego.add(labelMitraPapal);

        // cabecera
        panelCabecera = new JPanel();
        panelCabecera.setLocation(650,0);
        panelCabecera.setSize(panelJuego.getWidth()-650,panelJuego.getHeight());//ERES IDIOTA!!!!!!!!!!!!
        panelCabecera.setBackground(new Color(0,0,0));
        backgroundImage = new ImageIcon("src/images/fondoCabecera.jpg");
        backgroundLabel = new JLabel(backgroundImage);
        panelCabecera.add(backgroundLabel);

        panelJuego.add(panelCabecera);

        if(nombreBoton == "Mago") {
            // tiempo transcurrido
            Timer timerMago = new Timer(1000, new timerActionListenerMago());
            timerMago.start();
        } else if (nombreBoton == "Guerrero") {
            Timer timerGuerrero = new Timer(1000, new timerActionListenerGuerrero());
            timerGuerrero.start();
        } else {
            Timer timerSacerdote = new Timer(1000, new timerActionListenerSacerdote());
            timerSacerdote.start();
        }

    }

    public class timerActionListenerMago implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //TEMPORIZADOR
            segundos++; // Incrementar el tiempo transcurrido
            labelTiempo.setText( segundos + " segundos"); // Actualizar la etiqueta de tiempo

            oro=colisionesMago();


            if (oro >= 50 ) {

                JOptionPane.showMessageDialog(panelJuego, "¡Felicidades! HAS GANADO\n¡Has alcanzado 50 de oro!");
                ((Timer) e.getSource()).stop();
                try {
                    guardarEstadisticas(labelNombre.getText(),"Mago", segundos, vidas, oro);
                    Partida partida = new Partida(labelNombre.getText(),"Mago", segundos, vidas, oro);
                    partidas.add(partida);
                    guardarRankingGeneral();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
            labelOro.setText("Oro: "+oro);

            if (vidas  <=0) {

                JOptionPane.showMessageDialog(panelJuego, "¡Lo siento! HAS PERDIDO\nTienes 0 vidas :c");
                ((Timer) e.getSource()).stop();
                try {
                    guardarEstadisticas(labelNombre.getText(),"Mago", segundos, 0, oro);
                    Partida partida = new Partida(labelNombre.getText(),"Mago", segundos, vidas, oro);
                    partidas.add(partida);
                    guardarRankingGeneral();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }




        }

    }
    public class timerActionListenerGuerrero implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //TEMPORIZADOR
            segundos++; // Incrementar el tiempo transcurrido
            labelTiempo.setText( segundos + " segundos"); // Actualizar la etiqueta de tiempo

            oro=colisionesGuerrero();


            if (oro >= 50) {
                JOptionPane.showMessageDialog(panelJuego, "¡Felicidades! HAS GANADO\n¡Has alcanzado 50 de oro!");
                ((Timer) e.getSource()).stop();
                try {
                    guardarEstadisticas(labelNombre.getText(),"Guerrero", segundos, vidas, oro);
                    Partida partida = new Partida(labelNombre.getText(),"Guerrero", segundos, vidas, oro);
                    partidas.add(partida);
                    guardarRankingGeneral();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            labelOro.setText("Oro: "+oro);
            if (vidas  <=0) {

                JOptionPane.showMessageDialog(panelJuego, "¡Lo siento! HAS PERDIDO\nTienes 0 vidas :c");
                ((Timer) e.getSource()).stop();
                try {
                    guardarEstadisticas(labelNombre.getText(),"Guerrero", segundos, 0, oro);
                    Partida partida = new Partida(labelNombre.getText(),"Guerrero", segundos, vidas, oro);
                    partidas.add(partida);
                    guardarRankingGeneral();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

    }
    public class timerActionListenerSacerdote implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //TEMPORIZADOR
            segundos++; // Incrementar el tiempo transcurrido
            labelTiempo.setText( segundos + " segundos"); // Actualizar la etiqueta de tiempo

            oro= colisionesSacerdote();


            if (oro >= 50) {
                JOptionPane.showMessageDialog(panelJuego, "¡Felicidades! HAS GANADO\n¡Has alcanzado 50 de oro!");
                ((Timer) e.getSource()).stop();
                try {
                    guardarEstadisticas(labelNombre.getText(),"Sacerdote", segundos, vidas, oro);
                    Partida partida = new Partida(labelNombre.getText(),"Sacerdote", segundos, vidas, oro);
                    partidas.add(partida);
                    guardarRankingGeneral();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            labelOro.setText("Oro: "+oro);

            if (vidas <=0) {

                JOptionPane.showMessageDialog(panelJuego, "¡Lo siento! HAS PERDIDO\nTienes 0 vidas :c");
                ((Timer) e.getSource()).stop();
                try {
                    guardarEstadisticas(labelNombre.getText(),"Sacerdote", segundos, 0, oro);
                    Partida partida = new Partida(labelNombre.getText(),"Sacerdote", segundos, vidas, oro);
                    partidas.add(partida);
                    guardarRankingGeneral();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

        }

    }
    private int colisionesMago(){
        try {
            if (labelMago.getBounds().intersects(labelObjetoOro.getBounds())) {
                oro += 10;
                reUbicarOro();
            }
            if(labelMago.getBounds().intersects(labelObjetoEspada.getBounds())){
                panelJuego.remove(labelObjetoEspada); // Elimina la JLabel "labelNombre" del panel "panelJuego"
                panelJuego.revalidate(); // Vuelve a validar el panel
                panelJuego.repaint(); // Vuelve a pintar el panel para reflejar los cambios

                labelEspada.setText("- Espada");
            }
            if(labelMago.getBounds().intersects(labelObjetoPocion.getBounds())){
                panelJuego.remove(labelObjetoPocion); // Elimina la JLabel "labelNombre" del panel "panelJuego"
                panelJuego.revalidate(); // Vuelve a validar el panel
                panelJuego.repaint(); // Vuelve a pintar el panel para reflejar los cambios

                labelPocion.setText("- Poción");
            }
            if(labelMago.getBounds().intersects(labelObjetoMitraPapal.getBounds())){
                panelJuego.remove(labelObjetoMitraPapal); // Elimina la JLabel "labelNombre" del panel "panelJuego"
                panelJuego.revalidate(); // Vuelve a validar el panel
                panelJuego.repaint(); // Vuelve a pintar el panel para reflejar los cambios

                labelMitraPapal.setText("- Mitra Papal");
            }
            int x= labelMago.getX();
            int y = labelMago.getY();


                for (JLabel labelEsqueleto : arrayEsqueletosVertical) {
                    if (labelEsqueleto.getBounds().intersects(new Rectangle(x, y, labelMago.getWidth(), labelMago.getHeight()))){
                        System.out.println("Choca con enemigo");
                        vidas = vidas-1;
                        labelVidas.setText("Vidas: "+vidas);


                    }
                }

                for (JLabel labelEsqueleto : arrayEsqueletosHorizontal) {
                    if (labelEsqueleto.getBounds().intersects(new Rectangle(x, y, labelMago.getWidth(), labelMago.getHeight()))){
                        System.out.println("Choca con enemigo");
                        vidas = vidas-1;
                        labelVidas.setText("Vidas: "+vidas);


                    }
                }

        }catch (NullPointerException e){
            System.out.println();
        }

        return oro;
    }
    private int colisionesGuerrero(){
        try{
            if (labelGuerrero.getBounds().intersects(labelObjetoOro.getBounds())) {
                oro += 10;
                reUbicarOro();
            }
            if(labelGuerrero.getBounds().intersects(labelObjetoEspada.getBounds())){
                panelJuego.remove(labelObjetoEspada); // Elimina la JLabel "labelNombre" del panel "panelJuego"
                panelJuego.revalidate(); // Vuelve a validar el panel
                panelJuego.repaint(); // Vuelve a pintar el panel para reflejar los cambios

                labelEspada.setText("- Espada");
            }
            if(labelGuerrero.getBounds().intersects(labelObjetoPocion.getBounds())){
                panelJuego.remove(labelObjetoPocion); // Elimina la JLabel "labelNombre" del panel "panelJuego"
                panelJuego.revalidate(); // Vuelve a validar el panel
                panelJuego.repaint(); // Vuelve a pintar el panel para reflejar los cambios

                labelPocion.setText("- Poción");
            }
            if(labelGuerrero.getBounds().intersects(labelObjetoMitraPapal.getBounds())){
                panelJuego.remove(labelObjetoMitraPapal); // Elimina la JLabel "labelNombre" del panel "panelJuego"
                panelJuego.revalidate(); // Vuelve a validar el panel
                panelJuego.repaint(); // Vuelve a pintar el panel para reflejar los cambios

                labelMitraPapal.setText("- Mitra Papal");
            }
            int x= labelGuerrero.getX();
            int y = labelGuerrero.getY();


            for (JLabel labelEsqueleto : arrayEsqueletosVertical) {
                if (labelEsqueleto.getBounds().intersects(new Rectangle(x, y, labelGuerrero.getWidth(), labelGuerrero.getHeight()))){
                    System.out.println("Choca con enemigo");
                    vidas = vidas-1;
                    labelVidas.setText("Vidas: "+vidas);


                }
            }

            for (JLabel labelEsqueleto : arrayEsqueletosHorizontal) {
                if (labelEsqueleto.getBounds().intersects(new Rectangle(x, y, labelGuerrero.getWidth(), labelGuerrero.getHeight()))){
                    System.out.println("Choca con enemigo");
                    vidas = vidas-1;
                    labelVidas.setText("Vidas: "+vidas);


                }
            }

        } catch (NullPointerException e){
            System.out.println();
        }
        return oro;
    }
    private int colisionesSacerdote(){
        try{
            if (labelSacerdote.getBounds().intersects(labelObjetoOro.getBounds())) {
                oro += 10;
                reUbicarOro();
            }
            if(labelSacerdote.getBounds().intersects(labelObjetoEspada.getBounds())){
                panelJuego.remove(labelObjetoEspada); // Elimina la JLabel "labelNombre" del panel "panelJuego"
                panelJuego.revalidate(); // Vuelve a validar el panel
                panelJuego.repaint(); // Vuelve a pintar el panel para reflejar los cambios

                labelEspada.setText("- Espada");
            }
            if(labelSacerdote.getBounds().intersects(labelObjetoPocion.getBounds())){
                panelJuego.remove(labelObjetoPocion); // Elimina la JLabel "labelNombre" del panel "panelJuego"
                panelJuego.revalidate(); // Vuelve a validar el panel
                panelJuego.repaint(); // Vuelve a pintar el panel para reflejar los cambios

                labelPocion.setText("- Poción");
            }
            if(labelSacerdote.getBounds().intersects(labelObjetoMitraPapal.getBounds())){
                panelJuego.remove(labelObjetoMitraPapal); // Elimina la JLabel "labelNombre" del panel "panelJuego"
                panelJuego.revalidate(); // Vuelve a validar el panel
                panelJuego.repaint(); // Vuelve a pintar el panel para reflejar los cambios

                labelMitraPapal.setText("- Mitra Papal");
            }

            int x= labelSacerdote.getX();
            int y = labelSacerdote.getY();


            for (JLabel labelEsqueleto : arrayEsqueletosVertical) {
                if (labelEsqueleto.getBounds().intersects(new Rectangle(x, y, labelSacerdote.getWidth(), labelSacerdote.getHeight()))){
                    System.out.println("Choca con enemigo");
                    vidas = vidas-1;
                    labelVidas.setText("Vidas: "+vidas);


                }
            }

            for (JLabel labelEsqueleto : arrayEsqueletosHorizontal) {
                if (labelEsqueleto.getBounds().intersects(new Rectangle(x, y, labelSacerdote.getWidth(), labelSacerdote.getHeight()))){
                    System.out.println("Choca con enemigo");
                    vidas = vidas-1;
                    labelVidas.setText("Vidas: "+vidas);


                }
            }
        }catch (NullPointerException e){
            System.out.println();
        }
        return oro;
    }
    private void reUbicarOro(){
        boolean choca;
        int x,y;
        int grosorPanel= panelJuego.getWidth()-panelCabecera.getWidth()-64;
        int altoPanel = panelJuego.getHeight() -64;


        do {
            x = (int) (Math.random() * (grosorPanel - labelObjetoOro.getWidth()));
            y = (int) (Math.random() * (altoPanel - labelObjetoOro.getHeight()));

            choca = false;

            for (JLabel labelMuro : arrayMuros) {
                if (labelMuro.getBounds().intersects(new Rectangle(x, y, labelObjetoOro.getWidth(), labelObjetoOro.getHeight()))) {
                    choca = true;
                }
            }
        } while (choca);

        labelObjetoOro.setLocation(x, y);

    }

    //VENTANA DE ENTRADA Y VENTANA DE SALIDA
    private static class frameWindowsListener extends WindowAdapter {
        // para poder usar el frame
        JFrame frame;
        public frameWindowsListener(JFrame frame) {
            this.frame = frame;
        }
        //
        @Override
        public void windowOpened(WindowEvent e) {
            super.windowOpened(e);
            JOptionPane.showMessageDialog(frame, "Bienvenido a JUEGO DE ROL");
        }

        @Override
        public void windowClosing(WindowEvent e) {
            super.windowClosing(e);

            int confirmado = JOptionPane.showConfirmDialog(null,
                    "¿Éstas seguro de que quieres cerrar el Juego?",
                    "Mensaje",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (confirmado == JOptionPane.YES_OPTION){
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            } else {
                frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            }
        }
    }
    private static void guardarEstadisticas(String nombrePersonaje, String tipoPersonaje, int duracionPartida, int vidasRestantes, int monedasObtenidas) throws IOException {
        BufferedWriter wr;
        wr = new BufferedWriter(new FileWriter("src/forms/estadisticas.txt", true));
        wr.write("Nombre del personaje: " + nombrePersonaje + "\n");
        wr.write("Tipo de personaje: " + tipoPersonaje + "\n");
        wr.write("Duración de la partida: " + duracionPartida + " segundos\n");
        wr.write("Vidas restantes: " + vidasRestantes + "\n");
        wr.write("Cantidad de monedas obtenidas: " + monedasObtenidas + "\n");
        wr.write("-------------------------------\n");
        wr.close();

        //BASE DE DATOS
        String db_url = "jdbc:mysql://localhost:3306/juego";
        String user= "root";
        String paswd= "mysql";
        String insertQy = "insert into estadisticas values(?,?,?,?,?)";

        try {
            Connection con = DriverManager.getConnection(db_url, user, paswd);
            PreparedStatement ps = con.prepareStatement(insertQy);

            ps.setString(1, nombrePersonaje );
            ps.setString(2,tipoPersonaje);
            ps.setInt(3,duracionPartida);
            ps.setInt(4,vidasRestantes);
            ps.setInt(5,monedasObtenidas);

            int addRows = ps.executeUpdate();
            if(addRows>0){
                System.out.println("Todo ha ido bien");
            }
            con.close();
        } catch (Exception e){
            System.out.println("La conexión de la base de datos ha fallado");
        }

    }
    private void guardarRankingGeneral() throws IOException {
        BufferedWriter wr;
        wr = new BufferedWriter(new FileWriter("src/forms/ranking.txt", true));
        for (Partida partida : partidas) {
            wr.write("Nombre: " + partida.getNombre() + "\n");
            wr.write("Puntuación: " + partida.getPuntuacion() + "\n");
            System.out.println("Puntos conseguidos: "+partida.getPuntuacion());
            wr.write("-------------------------\n");

        }
        wr.close();



    }

    public static void main(String[] args) {

        //juego
        frame = new JFrame("Juego de Rol");
        frame.setContentPane(new Principal().panelJuego);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false); // que no cambie el tamañp
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        //Cambiar el icono de la ventana
        Toolkit pantalla = Toolkit.getDefaultToolkit(); //??
        Image icono = pantalla.getImage("src/images/politecnics.png");
        frame.setIconImage(icono);

        // hay que pasar el frame como parámetro para usarlo en la función
        frame.addWindowListener(new frameWindowsListener(frame));
    }
    private void createUIComponents() {
        //generado automáticamente por IntelliJ GUI Designer si lo quito explota
    }

}
