package br.com.fncobr.util;

/**
 *
 * @author Thiago Dias
 */
public enum Skin {

    MCWIN("com.jtattoo.plaf.mcwin.McWinLookAndFeel"),
    AERO("com.jtattoo.plaf.aero.AeroLookAndFeel");

    private final String skin;

    Skin(String skin) {
        this.skin = skin;
    }

    public String getSkin() {
        return skin;
    }

}
