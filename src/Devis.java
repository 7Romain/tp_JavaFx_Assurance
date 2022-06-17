
public class Devis {

    public int faireDevis(boolean plus25, boolean plusPermis, boolean anciennete, int nbAccident) {
        int conducteur = 0;

        final int ROUGE = 1;
        final int ORANGE = 2;
        final int VERT = 3;
        final int BLEU = 4;

        if (plus25) {
            conducteur++;
        }

        if (plusPermis) {
            conducteur++;
        }
        if (anciennete) {
            conducteur++;
        }

        if (nbAccident == 0) {
            conducteur++;
        } else {

            conducteur = 1 + conducteur - nbAccident;
        }

        switch (conducteur) {

            case ROUGE:
                return ROUGE;

            case ORANGE:
                return ORANGE;

            case VERT:
                return VERT;

            case BLEU:
                return BLEU;

            default:
                return 5;

        }

    }
}
