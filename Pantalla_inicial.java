package com.mycompany.proyecto21;

import java.awt.Color;
import java.util.Arrays;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class Pantalla_inicial extends javax.swing.JFrame {

    //CARTAS OPONENTE
    public static int vidaOponente = 100;
    public static int escudosOponente = 0;
    public static int cartasJugadasOponente = 0;
    public static int numeroTotalOponente = 0;
    public static boolean jugadasOponenteTerminadas = false;

    public static String[] descartesRojasOponente = new String[22];
    public static String[] cartasEnJuegoRojasOponente = new String[22];
    public static String[] mazoRojoOponente = {"1 diamantes", "2 diamantes", "3 diamantes", "4 diamantes", "5 diamantes", "6 diamantes",
        "7 diamantes", "8 diamantes", "9 diamantes", "10 diamantes", "11 diamantes",
        "1 corazones", "2 corazones", "3 corazones", "4 corazones", "5 corazones", "6 corazones",
        "7 corazones", "8 corazones", "9 corazones", "10 corazones", "11 corazones"};

    public static String[] descartesNegrasOponente = new String[22];
    public static String[] cartasEnJuegoNegrasOponente = new String[22];
    public static String[] mazoNegroOponente = {"1 treboles", "2 treboles", "3 treboles", "4 treboles", "5 treboles", "6 treboles",
        "7 treboles", "8 treboles", "9 treboles", "10 treboles", "11 treboles",
        "1 picas", "2 picas", "3 picas", "4 picas", "5 picas", "6 picas",
        "7 picas", "8 picas", "9 picas", "10 picas", "11 picas"};

    //CARTAS JUGADOR
    public static int vidaJugador = 100;
    public static int escudosJugador = 0;
    public static int cartasJugadas = 0;
    public static int numeroTotalJugador = 0;
    public static boolean jugadasJugadorTerminadas = false;

    public static String[] descartesRojas = new String[22];
    public static String[] cartasEnJuegoRojas = new String[22];
    public static String[] mazoRojo = {"1 diamantes", "2 diamantes", "3 diamantes", "4 diamantes", "5 diamantes", "6 diamantes",
        "7 diamantes", "8 diamantes", "9 diamantes", "10 diamantes", "11 diamantes",
        "1 corazones", "2 corazones", "3 corazones", "4 corazones", "5 corazones", "6 corazones",
        "7 corazones", "8 corazones", "9 corazones", "10 corazones", "11 corazones"};

    public static String[] descartesNegras = new String[22];
    public static String[] cartasEnJuegoNegras = new String[22];
    public static String[] mazoNegro = {"1 treboles", "2 treboles", "3 treboles", "4 treboles", "5 treboles", "6 treboles",
        "7 treboles", "8 treboles", "9 treboles", "10 treboles", "11 treboles",
        "1 picas", "2 picas", "3 picas", "4 picas", "5 picas", "6 picas",
        "7 picas", "8 picas", "9 picas", "10 picas", "11 picas"};
    
    public Pantalla_inicial() {
        initComponents();
        VidaOponente.setText(String.valueOf(vidaOponente));
        VidaJugador.setText(String.valueOf(vidaJugador));
        EscudosOponente.setText(String.valueOf(escudosOponente));
        EscudosJugador.setText(String.valueOf(escudosJugador));

        CartaJugador.setForeground(Color.white);
        CartaJugador.setHorizontalAlignment(SwingConstants.CENTER);
        CartaJugador.setVerticalAlignment(SwingConstants.CENTER);
        CartaJugador.setOpaque(true);
        CartaJugador.setBackground(Color.lightGray);

        CartaOponente.setForeground(Color.white);
        CartaOponente.setHorizontalAlignment(SwingConstants.CENTER);
        CartaOponente.setVerticalAlignment(SwingConstants.CENTER);
        CartaOponente.setOpaque(true);
        CartaOponente.setBackground(Color.lightGray);
    }

    
    
    public void jugadaOponente() {
        if (cartasJugadasOponente >= 8 || numeroTotalOponente >= 17) {
            jugadasOponenteTerminadas = true;
        } else {
            boolean jugadaMazoNegro = true;

            if (vidaJugador <= 25) {
                jugadaMazoNegro = true;
            } else if (vidaOponente <= 21) {
                jugadaMazoNegro = false;
            } else {
                jugadaMazoNegro = true;
            }

            if (jugadaMazoNegro == false) {
                cartasJugadasOponente++;
                int cartasUsadas = 0;
                for (String cadena : mazoRojoOponente) {
                    if (cadena == "empty") {
                        cartasUsadas++;
                    }
                }
                if (cartasUsadas == 22) {
                    mazoRojoOponente = Arrays.copyOf(descartesRojasOponente, 22);
                }

                Random rand = new Random();
                boolean cardOkay = false;
                String cartaRobada;
                do {
                    boolean isPlayed = false;
                    int randomNum = rand.nextInt((21 - 0) + 1) + 0;
                    cartaRobada = mazoRojoOponente[randomNum];
                    if (cartaRobada != "empty") {

                        for (String cadena : cartasEnJuegoRojasOponente) {
                            if (cadena == cartaRobada) {
                                isPlayed = true;
                            }
                        }

                        if (!isPlayed) {
                            cardOkay = true;
                            cartasEnJuegoRojasOponente[randomNum] = cartaRobada;
                            descartesRojasOponente[randomNum] = cartaRobada;
                            mazoRojoOponente[randomNum] = "empty";
                        }
                    }
                } while (cardOkay == false);

                String numberStringRed = "";
                numberStringRed = cartaRobada.substring(0, 2);
                Pattern patternRed = Pattern.compile("\\s");
                Matcher matcherRed = patternRed.matcher(numberStringRed);
                boolean foundRed = matcherRed.find();
                if (foundRed) {
                    numberStringRed = cartaRobada.substring(0, 1);
                }
                int numberRed = Integer.parseInt(numberStringRed);
                //System.out.println(numberRed);
                numeroTotalOponente = numberRed + numeroTotalOponente;

                counterOponente.setText(String.valueOf(numeroTotalOponente));

                //System.out.println(cartaRobada);
                CartaOponente.setText(cartaRobada);
                CartaOponente.setBackground(Color.red);
            } else {
                cartasJugadasOponente++;
                int cartasUsadasBlack = 0;
                for (String cadena : mazoNegroOponente) {

                    if (cadena == "empty") {
                        cartasUsadasBlack++;
                    }
                }
                if (cartasUsadasBlack == 22) {
                    mazoNegroOponente = Arrays.copyOf(descartesNegrasOponente, 22);
                }

                Random randBlack = new Random();
                boolean cardOkayBlack = false;
                String cartaRobadaBlack;
                do {
                    boolean isPlayed = false;
                    int randomNum = randBlack.nextInt((21 - 0) + 1) + 0;
                    cartaRobadaBlack = mazoNegroOponente[randomNum];
                    if (cartaRobadaBlack != "empty") {

                        for (String cadena : cartasEnJuegoNegrasOponente) {
                            if (cadena == cartaRobadaBlack) {
                                isPlayed = true;
                            }
                        }

                        if (!isPlayed) {
                            cardOkayBlack = true;
                            cartasEnJuegoNegrasOponente[randomNum] = cartaRobadaBlack;
                            descartesNegrasOponente[randomNum] = cartaRobadaBlack;
                            mazoNegroOponente[randomNum] = "empty";
                        }
                    }
                } while (cardOkayBlack == false);

                String numberString = "";
                numberString = cartaRobadaBlack.substring(0, 2);
                Pattern pattern = Pattern.compile("\\s");
                Matcher matcher = pattern.matcher(numberString);
                boolean found = matcher.find();
                if (found) {
                    numberString = cartaRobadaBlack.substring(0, 1);
                }
                int number = Integer.parseInt(numberString);
                //System.out.println(number);
                numeroTotalOponente = number + numeroTotalOponente;

                counterOponente.setText(String.valueOf(numeroTotalOponente));

                //System.out.println(cartaRobadaBlack);
                CartaOponente.setText(cartaRobadaBlack);
                CartaOponente.setBackground(Color.black);
            }
        }

    }

    public void recuentoFinal() {

        if (jugadasOponenteTerminadas == false) {
            do {
                jugadaOponente();
            } while (jugadasOponenteTerminadas == false);
        }

        if (jugadasJugadorTerminadas && jugadasOponenteTerminadas) {
            if (numeroTotalOponente > 21 || (numeroTotalJugador <= 21 && numeroTotalJugador > numeroTotalOponente)) {
                if (numeroTotalJugador <= 21) {
                    for (var i = 0; i < cartasEnJuegoNegras.length; i++) {
                        if (cartasEnJuegoNegras[i] != null) {
                            String numberString = "";
                            String typeOfCardString = "";
                            numberString = cartasEnJuegoNegras[i].substring(0, 2);
                            typeOfCardString = cartasEnJuegoNegras[i].substring(3, cartasEnJuegoNegras[i].length());
                            Pattern pattern = Pattern.compile("\\s");
                            Matcher matcher = pattern.matcher(numberString);
                            boolean found = matcher.find();
                            if (found) {
                                numberString = cartasEnJuegoNegras[i].substring(0, 1);
                                typeOfCardString = cartasEnJuegoNegras[i].substring(2, cartasEnJuegoNegras[i].length());
                            }
                            int number = Integer.parseInt(numberString);

                            if (typeOfCardString.equals("treboles")) {
                                //System.out.println("treboles");
                                if (escudosOponente >= number) {
                                    escudosOponente -= number;
                                    number = 0;
                                } else {
                                    number -= escudosOponente;
                                    escudosOponente = 0;
                                }
                                vidaOponente -= number;
                                EscudosOponente.setText(String.valueOf(escudosOponente));
                                VidaOponente.setText(String.valueOf(vidaOponente));
                            } else if (typeOfCardString.equals("picas")) {
                                //System.out.println("picas");
                                vidaOponente = (int) (vidaOponente - (Math.ceil(number / 2)));
                                VidaOponente.setText(String.valueOf(vidaOponente));
                            }
                        }
                    }

                    for (var i = 0; i < cartasEnJuegoRojas.length; i++) {
                        if (cartasEnJuegoRojas[i] != null) {
                            String numberString = "";
                            String typeOfCardString = "";
                            numberString = cartasEnJuegoRojas[i].substring(0, 2);
                            typeOfCardString = cartasEnJuegoRojas[i].substring(3, cartasEnJuegoRojas[i].length());
                            Pattern pattern = Pattern.compile("\\s");
                            Matcher matcher = pattern.matcher(numberString);
                            boolean found = matcher.find();
                            if (found) {
                                numberString = cartasEnJuegoRojas[i].substring(0, 1);
                                typeOfCardString = cartasEnJuegoRojas[i].substring(2, cartasEnJuegoRojas[i].length());
                            }
                            int number = Integer.parseInt(numberString);

                            if (typeOfCardString.equals("corazones")) {
                                vidaJugador += number;
                                if (vidaJugador > 100) {
                                    vidaJugador = 100;
                                }
                                VidaJugador.setText(String.valueOf(vidaJugador));
                            } else if (typeOfCardString.equals("diamantes")) {
                                escudosJugador += number;
                                if (escudosJugador > 25) {
                                    escudosJugador = 25;
                                }
                                EscudosJugador.setText(String.valueOf(escudosJugador));
                            }
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Jugador gana la ronda con: " + numeroTotalJugador);
                } else {
                    JOptionPane.showMessageDialog(null, "Ronda empatada");
                }
            } else if (numeroTotalJugador > 21 || (numeroTotalOponente <= 21 && numeroTotalJugador < numeroTotalOponente)) {
                if (numeroTotalOponente <= 21) {
                    for (var i = 0; i < cartasEnJuegoNegrasOponente.length; i++) {
                        if (cartasEnJuegoNegrasOponente[i] != null) {
                            String numberString = "";
                            String typeOfCardString = "";
                            numberString = cartasEnJuegoNegrasOponente[i].substring(0, 2);
                            typeOfCardString = cartasEnJuegoNegrasOponente[i].substring(3, cartasEnJuegoNegrasOponente[i].length());
                            Pattern pattern = Pattern.compile("\\s");
                            Matcher matcher = pattern.matcher(numberString);
                            boolean found = matcher.find();
                            if (found) {
                                numberString = cartasEnJuegoNegrasOponente[i].substring(0, 1);
                                typeOfCardString = cartasEnJuegoNegrasOponente[i].substring(2, cartasEnJuegoNegrasOponente[i].length());
                            }
                            int number = Integer.parseInt(numberString);

                            if (typeOfCardString.equals("treboles")) {
                                //System.out.println("treboles");
                                if (escudosJugador >= number) {
                                    escudosJugador -= number;
                                    number = 0;
                                } else {
                                    number -= escudosJugador;
                                    escudosJugador = 0;
                                }
                                vidaJugador -= number;
                                EscudosJugador.setText(String.valueOf(escudosJugador));
                                VidaJugador.setText(String.valueOf(vidaJugador));
                            } else if (typeOfCardString.equals("picas")) {
                                //System.out.println("picas");
                                vidaJugador = (int) (vidaJugador - (Math.ceil(number / 2)));
                                VidaJugador.setText(String.valueOf(vidaJugador));
                            }
                        }
                    }

                    for (var i = 0; i < cartasEnJuegoRojasOponente.length; i++) {
                        if (cartasEnJuegoRojasOponente[i] != null) {
                            String numberString = "";
                            String typeOfCardString = "";
                            numberString = cartasEnJuegoRojasOponente[i].substring(0, 2);
                            typeOfCardString = cartasEnJuegoRojasOponente[i].substring(3, cartasEnJuegoRojasOponente[i].length());
                            Pattern pattern = Pattern.compile("\\s");
                            Matcher matcher = pattern.matcher(numberString);
                            boolean found = matcher.find();
                            if (found) {
                                numberString = cartasEnJuegoRojasOponente[i].substring(0, 1);
                                typeOfCardString = cartasEnJuegoRojasOponente[i].substring(2, cartasEnJuegoRojasOponente[i].length());
                            }
                            int number = Integer.parseInt(numberString);

                            if (typeOfCardString.equals("corazones")) {
                                vidaOponente += number;
                                if (vidaOponente > 100) {
                                    vidaOponente = 100;
                                }
                                VidaOponente.setText(String.valueOf(vidaOponente));
                            } else if (typeOfCardString.equals("diamantes")) {
                                escudosOponente += number;
                                if (escudosOponente > 25) {
                                    escudosOponente = 25;
                                }
                                EscudosOponente.setText(String.valueOf(escudosOponente));
                            }
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Oponente gana la ronda con: " + numeroTotalOponente);
                } else {
                    JOptionPane.showMessageDialog(null, "Ronda empatada");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ronda empatada");
            }

            if (vidaOponente <= 0) {
                JOptionPane.showMessageDialog(null, "¡Jugador gana la partida!");
                vidaOponente = 100;
                vidaJugador = 100;
                escudosOponente = 0;
                escudosJugador = 0;
                VidaOponente.setText(String.valueOf(vidaOponente));
                VidaJugador.setText(String.valueOf(vidaJugador));
                EscudosOponente.setText(String.valueOf(escudosOponente));
                EscudosJugador.setText(String.valueOf(escudosJugador));
                CartaJugador.setBackground(Color.lightGray);
                CartaOponente.setBackground(Color.lightGray);
                CartaJugador.setText("");
                CartaOponente.setText("");
            } else if (vidaJugador <= 0) {
                JOptionPane.showMessageDialog(null, "¡Oponente gana la partida!");
                vidaOponente = 100;
                vidaJugador = 100;
                escudosOponente = 0;
                escudosJugador = 0;
                VidaOponente.setText(String.valueOf(vidaOponente));
                VidaJugador.setText(String.valueOf(vidaJugador));
                EscudosOponente.setText(String.valueOf(escudosOponente));
                EscudosJugador.setText(String.valueOf(escudosJugador));
                CartaJugador.setBackground(Color.lightGray);
                CartaOponente.setBackground(Color.lightGray);
                CartaJugador.setText("");
                CartaOponente.setText("");
            }
            cartasEnJuegoRojasOponente = new String[22];
            cartasEnJuegoNegrasOponente = new String[22];
            cartasJugadasOponente = 0;
            numeroTotalOponente = 0;
            jugadasOponenteTerminadas = false;
            numeroTotalOponente = 0;
            counterOponente.setText(String.valueOf(numeroTotalOponente));

            cartasEnJuegoRojas = new String[22];
            cartasEnJuegoNegras = new String[22];
            numeroTotalJugador = 0;
            cartasJugadas = 0;
            jugadasJugadorTerminadas = false;
            numeroTotalJugador = 0;
            counterJugador.setText(String.valueOf(numeroTotalJugador));

            CartaJugador.setBackground(Color.lightGray);
            CartaOponente.setBackground(Color.lightGray);
            CartaJugador.setText("");
            CartaOponente.setText("");
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        CartaOponente = new javax.swing.JLabel();
        CartaJugador = new javax.swing.JLabel();
        BotonPasar = new javax.swing.JButton();
        VidaJugador = new javax.swing.JLabel();
        VidaOponente = new javax.swing.JLabel();
        counterJugador = new javax.swing.JLabel();
        counterOponente = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        EscudosOponente = new javax.swing.JLabel();
        EscudosJugador = new javax.swing.JLabel();
        black_back = new javax.swing.JButton();
        red_back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Vida Jugador:");

        jLabel2.setText("Vida Oponente:");

        CartaOponente.setText("Imagen oponente");

        CartaJugador.setText("Imagen jugador");
        CartaJugador.setToolTipText("");
        CartaJugador.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        BotonPasar.setText("Pasar");
        BotonPasar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonPasarActionPerformed(evt);
            }
        });

        VidaJugador.setText("VidasJugador");

        VidaOponente.setText("VidasOponente");

        jLabel3.setText("Escudo Oponente:");

        jLabel4.setText("Escudo Jugador:");

        EscudosOponente.setText("EscudosOponente");

        EscudosJugador.setText("EscudosJugador");

        black_back.setText("Robar del mazo negro");
        black_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                black_backActionPerformed(evt);
            }
        });

        red_back.setText("Robar del mazo rojo");
        red_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                red_backActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addComponent(counterOponente, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(CartaOponente, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(EscudosJugador))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(VidaOponente))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(EscudosOponente))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(VidaJugador))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(counterJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(black_back))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BotonPasar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CartaJugador, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addComponent(red_back)
                .addGap(0, 132, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(VidaOponente))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EscudosOponente)
                    .addComponent(jLabel3))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(CartaOponente, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(counterOponente, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(VidaJugador))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(EscudosJugador))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(CartaJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BotonPasar)
                            .addComponent(black_back)
                            .addComponent(red_back))
                        .addGap(93, 93, 93))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(counterJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(218, 218, 218))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BotonPasarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonPasarActionPerformed

        jugadasJugadorTerminadas = true;
        recuentoFinal();

    }//GEN-LAST:event_BotonPasarActionPerformed

    private void black_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_black_backActionPerformed

        if (cartasJugadas >= 8 || numeroTotalJugador >= 21) {
            jugadasJugadorTerminadas = true;
        } else {
            cartasJugadas++;
            int cartasUsadas = 0;
            for (String cadena : mazoNegro) {
                if (cadena == "empty") {
                    cartasUsadas++;
                }
            }
            if (cartasUsadas == 22) {
                mazoNegro = Arrays.copyOf(descartesNegras, 22);
            }

            Random rand = new Random();
            boolean cardOkay = false;
            String cartaRobada;
            do {
                boolean isPlayed = false;
                int randomNum = rand.nextInt((21 - 0) + 1) + 0;
                cartaRobada = mazoNegro[randomNum];
                if (cartaRobada != "empty") {
                    for (String cadena : cartasEnJuegoNegras) {
                        if (cadena == cartaRobada && cartaRobada != "empty") {
                            isPlayed = true;
                        }
                    }

                    if (!isPlayed) {
                        cardOkay = true;
                        cartasEnJuegoNegras[randomNum] = cartaRobada;
                        descartesNegras[randomNum] = cartaRobada;
                        mazoNegro[randomNum] = "empty";
                    }
                }
            } while (cardOkay == false);
            String numberString = "";
            numberString = cartaRobada.substring(0, 2);
            Pattern pattern = Pattern.compile("\\s");
            Matcher matcher = pattern.matcher(numberString);
            boolean found = matcher.find();
            if (found) {
                numberString = cartaRobada.substring(0, 1);
            }
            int number = Integer.parseInt(numberString);
            //System.out.println(number);
            numeroTotalJugador = number + numeroTotalJugador;

            counterJugador.setText(String.valueOf(numeroTotalJugador));

            //System.out.println(cartaRobada);
            CartaJugador.setText(cartaRobada);
            CartaJugador.setBackground(Color.black);
            if (!jugadasOponenteTerminadas) {
                jugadaOponente();
            }
        }

    }//GEN-LAST:event_black_backActionPerformed

    private void red_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_red_backActionPerformed

        if (cartasJugadas >= 8 || numeroTotalJugador >= 21) {
            jugadasJugadorTerminadas = true;
        } else {
            cartasJugadas++;
            int cartasUsadas = 0;
            for (String cadena : mazoRojo) {

                if (cadena == "empty") {
                    cartasUsadas++;
                }
            }
            if (cartasUsadas == 22) {
                mazoRojo = Arrays.copyOf(descartesRojas, 22);
            }

            Random rand = new Random();
            boolean cardOkay = false;
            String cartaRobada;
            do {
                boolean isPlayed = false;
                int randomNum = rand.nextInt((21 - 0) + 1) + 0;
                cartaRobada = mazoRojo[randomNum];
                if (cartaRobada != "empty") {

                    for (String cadena : cartasEnJuegoRojas) {
                        if (cadena == cartaRobada) {
                            isPlayed = true;
                        }
                    }

                    if (!isPlayed) {
                        cardOkay = true;
                        cartasEnJuegoRojas[randomNum] = cartaRobada;
                        descartesRojas[randomNum] = cartaRobada;
                        mazoRojo[randomNum] = "empty";
                    }
                }
            } while (cardOkay == false);

            String numberString = "";
            numberString = cartaRobada.substring(0, 2);
            Pattern pattern = Pattern.compile("\\s");
            Matcher matcher = pattern.matcher(numberString);
            boolean found = matcher.find();
            if (found) {
                numberString = cartaRobada.substring(0, 1);
            }
            int number = Integer.parseInt(numberString);
            //System.out.println(number);
            numeroTotalJugador = number + numeroTotalJugador;

            counterJugador.setText(String.valueOf(numeroTotalJugador));
            //System.out.println(cartaRobada);
            CartaJugador.setText(cartaRobada);
            CartaJugador.setBackground(Color.red);
            if (!jugadasOponenteTerminadas) {
                jugadaOponente();
            }
        }

    }//GEN-LAST:event_red_backActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Pantalla_inicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pantalla_inicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pantalla_inicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pantalla_inicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pantalla_inicial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonPasar;
    private javax.swing.JLabel CartaJugador;
    private javax.swing.JLabel CartaOponente;
    private javax.swing.JLabel EscudosJugador;
    private javax.swing.JLabel EscudosOponente;
    private javax.swing.JLabel VidaJugador;
    private javax.swing.JLabel VidaOponente;
    private javax.swing.JButton black_back;
    private javax.swing.JLabel counterJugador;
    private javax.swing.JLabel counterOponente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton red_back;
    // End of variables declaration//GEN-END:variables
}
