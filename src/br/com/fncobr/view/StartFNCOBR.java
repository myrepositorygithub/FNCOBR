package br.com.fncobr.view;

import br.com.fncobr.util.Skin;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Thiago Dias
 */
public class StartFNCOBR {

    private final URL ICON = getClass().getResource("/br/com/fncobr/resources/cabal.png");

    public StartFNCOBR() {
        init();
        createTray();
    }

    private void createTray() {
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();

            Image image = Toolkit.getDefaultToolkit().getImage(ICON);

            ActionListener iniciarAC = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            };

            ActionListener configurarAc = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    FrmConfig f = new FrmConfig();
                    f.setIconImage(image);
                    f.setVisible(true);
                }
            };

            ActionListener sairAc = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            };

            PopupMenu popup = new PopupMenu();

            MenuItem executar = new MenuItem("Iniciar");
            executar.addActionListener(iniciarAC);
            popup.add(executar);

            MenuItem configurar = new MenuItem("Configurar");
            configurar.addActionListener(configurarAc);
            popup.add(configurar);

            MenuItem sair = new MenuItem("Sair");
            sair.addActionListener(sairAc);
            popup.add(sair);

            trayIcon = new TrayIcon(image, "Macro COBR", popup);
            trayIcon.setImageAutoSize(true);

            try {
                tray.add(trayIcon);
                trayIcon.displayMessage("Macro iniciado!", "Pronto para configurar!", TrayIcon.MessageType.INFO);
            } catch (AWTException e) {
                System.err.println("TrayIcon não pode ser adicionado no sistema.");
            }
        } else {
            System.err.println("Bandeja do sistema não é suportado.");
        }
    }

    private TrayIcon trayIcon;

    public static void main(String[] args) {

        StartFNCOBR bR = new StartFNCOBR();

    }

    private void init() {
        try {
            UIManager.setLookAndFeel(Skin.MCWIN.getSkin());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(StartFNCOBR.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
