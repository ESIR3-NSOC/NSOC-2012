package esir3.nsh.projet.regulation;

import esir3.nsh.projet.util.*;
import esir3.nsh.projet.communication.*;


public class Regulation {

    Communication com;
    private int lum_int = 0;
    private int lum_ext = 0;
    private int light_level = 0;
    private int shutter_pos = 0;


    public Regulation(Communication com) {
        this.com = com;
    }

    public void actionPerformed(int action) {

        /** La fonction update mets toutes les valeurs a jour avant une prise de decision **/

        update();

        switch (action) {
            case Util.UP:

                /** On test s'il fait jour ou nuit. Notre valeur de repere est 125 lux **/
                if (lum_ext > 125) {

                    /** On test la température exterieure, ce qui determinera la gestion des volets **/
                    if (com.getTemperatureExt() > 10 && com.getTemperatureExt() <= 24) {

                        /** On test si les volets sont a l'état bas **/

                        if (ShutterisDown()) com.setShutterIntermediate();

                        /** On test si les volets sont a l'état haut et le niveau de l'ampoule inferieur  ou egale a 95% **/

                        else if (ShutterisUp() && light_level <= 95) com.setLightLevel(light_level + 5);

                        /** On test si le niveau de l'ampoule est compris entre 95 et  99% ** et on incremente de 1% cette fois ci **/
                        else if (light_level > 95 && light_level <= 99) com.setLightLevel(light_level + 1);

                        /** Sinon on monte les volets **/
                        else com.setShutterUp();

                    }

                    /** Si nous n'avons pas une temperature exterieure comprise entre (10-24°) :
                     - on test si les volets sont a l'etat bas, si oui on les positionnent a l'intermediaire
                     - sinon on test le niveau de la lumiere et on l'augmente de 5%
                     - sinon on test si le niveau de l'ampoule n'est pas a 99%, si oui on augmente de 1% **/

                    else {
                        if (ShutterisDown()) com.setShutterIntermediate();
                        else if (light_level <= 95) com.setLightLevel(light_level + 5);
                        else if (light_level > 95 && light_level <= 99) com.setLightLevel(light_level + 1);
                    }

                }

                /** Si nous n'avons pas une luminosite exterieure superieur a 125 lux :
                 - on test si le niveau de la lumiere et on l'augmente de 5%
                 - sinon on test si le niveau de l'ampoule n'est pas a 99%, si oui on augmente de 1% **/


                else if (light_level <= 95) com.setLightLevel(light_level + 5);
                else if (light_level > 95 && light_level <= 99) com.setLightLevel(light_level + 1);


                break;

            case Util.DOWN:

                /** On test s'il y a de l'eblouissement et que les volets ne soient pas a l'etat bas **/
                if (LightisDazziling() && !ShutterisDown()) {

                    /**     On test si la température extérieur est inférieur ou égale à 10 **/

                    if (com.getTemperatureExt() <= 10) {


                        /** On test si la luminosite exterieur / 2 <1600 afin de determiner dnas quel position mettre les volets
                         - on test si les volets sont a l'etat haut, si oui on les positionnent a l'intermediaire
                         - sinon on test si les volets sont a l'etat intermediaire, si oui on baisse les volets et regule la luminosite a 500 lux **/

                        if ((lum_ext / 2) < 1600) {
                            if (ShutterisUp()) com.setShutterIntermediate(); // Volet niveau moyen
                            else if (ShutterisIntermediate()) downAndReg();

                        } else downAndReg();
                    }

                    /** Si la température extérieur est supérieur à 10 **/

                    else {

                        /** Si la temperature exterieur est superieur à 10 :
                         - on test si les volets ne sont pas a l'etat intermediaire, si oui on les y positionnent
                         - sinon on test que le niveau de lumiere est superieure a 5%, si oui on baisse de 5% **/

                        if (!ShutterisIntermediate()) com.setShutterIntermediate();
                        else if (light_level >= 5) com.setLightLevel(light_level - 5);
                        else if (light_level >= 1 && light_level < 5)
                            com.setLightLevel(light_level - 1); // si lum compris entre 1 et 5 on diminue de 1%
                    }
                }

                /** Il n'y a pas d'eblouissement **/

                else {

                    if (lum_ext > 125) {
                        /** On vérifie que les lampes ne soit pas déja éteintes **/

                        if (light_level >= 5) com.setLightLevel(light_level - 5); // On diminue de 5%

                        else if (light_level >= 1 && light_level < 5) com.setLightLevel(light_level - 1);

                        /** Si les lampes sont éteintes et que les volets sont motée **/

                        else if (ShutterisUp()) com.setShutterIntermediate(); // On met les volet à l'état intermediaire

                        /** Si les volets sont à l'état intermédiaire et qu'il fait froid **/

                        else if (ShutterisIntermediate() && com.getTemperatureExt() <= 10) {

                            com.setShutterDown();
                            lumReg('+', (lum_int - 5), 1);
                            int arrondMin = (int) Math.floor(light_level / 10);
                            com.setLightLevel(arrondMin * 10);


                        }

                    } else {
                        if (!ShutterisDown()) com.setShutterDown(); // On met les volet à l'état bas
                        else if (light_level >= 5) com.setLightLevel(light_level - 5); // On diminue de 5%
                        else if (light_level >= 1 && light_level < 5) com.setLightLevel(light_level - 1);
                    }
                }

                break;
        }
    }

    public void downAndReg() {
        com.setShutterDown(); // Volet niveau bas
        lumReg('+', 500, 5);
    }

    /* Test booleen pour savoir si le volet est  l'tat bas ou non */
    public boolean ShutterisDown() {
        if (com.getShutterState() == 0) return true;
        else return false;
    }

    /* Test booleen pour savoir si le volet est  l'tat intermediaire ou non. Mais ce test n'est pas ncessaire !*/
    public boolean ShutterisIntermediate() {
        if (com.getShutterState() == 1) return true;
        else return false;
    }

    /* Test booleen pour savoir si le volet est  l'tat haut ou non */
    public boolean ShutterisUp() {
        if (com.getShutterState() == 2) return true;
        else return false;
    }

    /* Test booleen pour savoir si la lumiere est suprieur  500 lux */
    public boolean LightisGood() {
        if (com.getLightBrightnessInt() >= 500) return true;
        else return false;
    }

    /* Test booleen pour savoir si la lumiere est suprieur  1600 lux : EBLOUISSEMENT */
    public boolean LightisDazziling() {
        if (com.getLightBrightnessInt() >= 1600) return true;
        else return false;
    }


    public void lumReg(char c, int l, int pas) {

        if (c == '-') {

            while (com.getLightBrightnessInt() >= l) {
                update();
                com.setLightLevel(light_level - pas);
            }
        } else if (c == '+') {

            while (com.getLightBrightnessInt() < l && com.getLightLevel() < 100) {
                update();
                System.out.println("lum int :" + lum_int);
                com.setLightLevel(light_level + pas);
            }
        }

    }

    public void update() {
        lum_int = com.getLightBrightnessInt();
        lum_ext = com.getLightBrightnessExt();
        light_level = com.getLightLevel();
        shutter_pos = com.getShutterState();
    }
}
