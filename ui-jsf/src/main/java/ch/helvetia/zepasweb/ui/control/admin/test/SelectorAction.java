package ch.helvetia.zepasweb.ui.control.admin.test;

import com.helvetia.heap.jsf.HeapController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@HeapController(viewId = "/selector.xhtml")
public class SelectorAction implements Serializable  {
    private String selectedValue;
    private List<Rabattklasse> rabattklassen;
    private String postleitzahl = "";

    public String init() {
        rabattklassen = new ArrayList<Rabattklasse>();
        rabattklassen.add(new Rabattklasse("1", "Mitarbeiter Helvetia"));
        rabattklassen.add(new Rabattklasse("2", "Nebenamtliche Mitarbeiter Helvetia"));
        rabattklassen.add(new Rabattklasse("3", "Rentenbezüger Helvetia"));
        rabattklassen.add(new Rabattklasse("4", "Angehörige Mitarbeiter Helvetia"));
        rabattklassen.add(new Rabattklasse("A", "Mitarbeiter/Angehörige Coop Rechtsschutz"));
        rabattklassen.add(new Rabattklasse("B", "Angehörige Bank Vontobel AG"));
        rabattklassen.add(new Rabattklasse("C", "Mitarbeiter Bank Vontobel AG"));
        setSelectedValue("");
        return null;
    }

    public String getSelectedValue() {
        return selectedValue;
    }

    public void setSelectedValue(String selectedValue) {
        if ("-".equals(selectedValue)) {
            selectedValue = null;
        }
        this.selectedValue = selectedValue;
    }

    public List<Rabattklasse> getRabattklassen() {
        return rabattklassen;
    }

    public void setRabattklassen(List<Rabattklasse> rabattklassen) {
        this.rabattklassen = rabattklassen;
    }

    public String getPostleitzahl() {
        return postleitzahl;
    }

    public void setPostleitzahl(String postleitzahl) {
        this.postleitzahl = postleitzahl;
    }

    public class Rabattklasse {

        private String code = "";
        private String textLong = "";

        public Rabattklasse(String code, String textLong) {
            this.code = code;
            this.textLong = textLong;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getTextLong() {
            return textLong;
        }

        public void setTextLong(String textLong) {
            this.textLong = textLong;
        }
    }
}
