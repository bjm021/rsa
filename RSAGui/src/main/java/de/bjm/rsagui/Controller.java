package de.bjm.rsagui;

import de.bjm.rsa.RSA;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


import java.util.ArrayList;
import java.util.List;

public class Controller {

    public void initialize() {
    }

    //rsa encrypt
    @FXML
    TextField keyField;

    @FXML
    TextField inputField;

    @FXML
    TextField rsaModuleField;

    @FXML
    TextArea outputArea;



    @FXML
    public void encrypt() {
        if(keyField.getText().isEmpty() || inputField.getText().isEmpty() || rsaModuleField.getText().isEmpty()) {
            outputArea.clear();
            return;
        }

        List<Long> longList = RSA.encryptMessage(inputField.getText(), Long.valueOf(keyField.getText()), Long.valueOf(rsaModuleField.getText()));
        outputArea.clear();
        for (Long l:longList) {
            outputArea.appendText(l.toString());
            outputArea.appendText(".");
        }
    }

    //decrypt

    @FXML
    TextField decryptRSAM;

    @FXML
    TextField decryptKey;

    @FXML
    TextArea inputArea;

    @FXML
    TextField decryptOutputField;


    @FXML
    public void decrypt() {
        if(decryptKey.getText().isEmpty() || decryptRSAM.getText().isEmpty() || inputField.getText().isEmpty()) {
            return;
        }
        List<Long> longList = new ArrayList<>();
        String[] tmpLong = inputArea.getText().split("\\.");
        for (String str:tmpLong) {
            Long l = Long.valueOf(str);
            longList.add(l);
        }

        String result = RSA.decryptMessage(longList, Long.valueOf(decryptKey.getText()), Long.valueOf(decryptRSAM.getText()));
        decryptOutputField.setText(result);
    }



    //key generation

    @FXML
    TextField privateKeyField;

    @FXML
    TextField publicKeyField;

    @FXML
    TextField rsaModule;

    @FXML
    public void generateKeys() {
        Long[] keys = RSA.createKeypair();
        publicKeyField.setText(keys[0].toString());
        privateKeyField.setText(keys[1].toString());
        rsaModule.setText(keys[2].toString());
    }


}
