import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

class Controller implements Initializable {

    //---------------------------------------------
    // Membervariablen für Steuerelemente
    //---------------------------------------------        
    @FXML
    private TextField textFieldEingabe;

    @FXML
    private Button buttonDruecken;

    @FXML
    private Label labelAusgabe;

    @FXML
    private Slider sldr_count;

    @FXML
    private RadioButton rdb_exTXT;

    @FXML
    private ToggleGroup grp_ExportType;

    @FXML
    private RadioButton rdb_exCSV;

    @FXML
    private RadioButton rdb_ene;

    @FXML
    private ToggleGroup grp_EneMeneMiste;

    @FXML
    private RadioButton rdb_miste;

    @FXML
    private RadioButton rdb_mene;

    private PrintWriter printwriter;
    private String exportType;
    private String optionEneMeneMiste;

    private HashMap<String, Integer> map;

    private double sldrInput;



//    private FileReader fileReader;
//    private BufferedReader br;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //---------------------------------------------
        // Eventhandler
        //---------------------------------------------    
        buttonDruecken.setOnAction(event -> {
            String eingabe = textFieldEingabe.getText();
            setSelectedRadioButton();

            Integer zahl = 0;
            switch (optionEneMeneMiste){
                case "ene":
                    zahl = map.get("ene");
                    map.put("ene", (zahl+1));
                    break;
                case "mene":
                    zahl = map.get("mene");
                    map.put("mene", (zahl+1));
                    break;
                case "miste":
                    zahl = map.get("miste");
                    map.put("miste", (zahl+1));
                    break;
            }
            sldrInput = sldr_count.getValue();
            String ausgabe = eingabe + "\n Slider: " + sldrInput + "\nEne: " + map.get("ene") + " - Mene: " + map.get("mene") + " - Miste: " + map.get("miste");
            labelAusgabe.setText(ausgabe);

            switch (exportType) {
                case "txt":
                    exportTxt(ausgabe);
                    break;
                case "csv":
                    exportCsv(ausgabe);
                    break;
            }

        });

        //---------------------------------------------
        // Start
        //---------------------------------------------
        // Code wird ausgeführt,
        // wenn Fenster und Steuerelemente bereit sind
        exportType = "kein Export gesetzt";
        optionEneMeneMiste = "keine Option gesetzt";
        sldrInput = 0.0;

        map = new HashMap<String,Integer>();
        map.put("ene",0);
        map.put("mene",0);
        map.put("miste",0);


//        try {
//            fileReader = new FileReader("Ausgabe.txt");
//            br = new BufferedReader(fileReader);
//            String read = br.readLine();
//            System.out.println(read);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    public void exportTxt(String text) {
        try {
            printwriter = new PrintWriter(new File("Ausgabe.txt"));
            printwriter.print(text);
            printwriter.close();
            System.out.println("TXT Done");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void exportCsv(String text) {
        try {
            printwriter = new PrintWriter(new File("Ausgabe.csv"));
            printwriter.print(text);
            printwriter.close();
            System.out.println("csv Done");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setSelectedRadioButton() {
        if (rdb_exTXT.isSelected()) {
            exportType = "txt";
        }
        if (rdb_exCSV.isSelected()) {
            exportType = "csv";
        }
        if (rdb_ene.isSelected()) {
            optionEneMeneMiste = "ene";
        }
        if (rdb_mene.isSelected()) {
            optionEneMeneMiste = "mene";
        }
        if (rdb_miste.isSelected()) {
            optionEneMeneMiste = "miste";
        }

    }
}
