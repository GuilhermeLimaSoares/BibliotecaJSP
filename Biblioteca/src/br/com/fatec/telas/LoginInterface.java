/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.telas;

import br.com.fatec.bean.Usuario;
import br.com.fatec.controler.UsuarioControler;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import biblioteca.Biblioteca;

/**
 * FXML Controller class
 *
 * @author ProfAlexandre
 */
public class LoginInterface implements Initializable {

    @FXML
    private Label lbLogin;

    @FXML
    private Label lbSenha;

    @FXML
    private TextField txtLogin;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private Button btEntrar;
    
    @FXML
    private Button btnCadastrar;
    
    UsuarioControler usuCont = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initComponentes();
    }   
    
    private void initComponentes () {
        btEntrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                usuCont = new UsuarioControler();
                Usuario usu = new Usuario(0,"",txtLogin.getText(),txtSenha.getText(),"","");
                try {
                    usu = usuCont.validaUsuario(usu);
                    try
                        {
                            FXMLLoader loader = new FXMLLoader(Biblioteca.class.getResource("/br/com/fatec/xmls/MenuInterface.fxml"));
                            Parent novatela  = loader.load();
                            Stage stg = Biblioteca.getStage();
                            stg.setScene(new Scene(novatela));
                            stg.show();
                        } catch(Exception e) {
                        } catch (Throwable ex) {                    
                        Logger.getLogger(LoginInterface.class.getName()).log(Level.SEVERE, null, ex);
                    }                    
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(LoginInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(null, usu.getNome());
            }
        });
        
        
//         btnCadastrar.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                FXMLLoader loader = new FXMLLoader(Biblioteca.class.getResource("/br/com/fatec/xmls/InserirUsuarioInterface.fxml"));
//                Parent novatela = null;
//                try {
//                    novatela = loader.load();
//                    Stage stg = Biblioteca.getStage();
//                    stg.setScene(new Scene(novatela));
//                    stg.show();
//                } catch (IOException ex) {
//                    Logger.getLogger(LoginInterface.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
        
    }
    
    

    /**
     * Initializes the controller class.
     */
     
    
}
