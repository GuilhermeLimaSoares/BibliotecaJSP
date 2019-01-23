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
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import biblioteca.Biblioteca;

/**
 * FXML Controller class
 *
 * @author ProfAlexandre
 */
public class AlterarUsuarioInterface implements Initializable {

    @FXML
    private Button btSalvar;

    @FXML
    private Label lbNome;

    @FXML
    private TextField txtNome;

    @FXML
    private Label lbLogin;

    @FXML
    private TextField txtLogin;

    @FXML
    private Label lbSenha;

    @FXML
    private TextField txtSenha;

    @FXML
    private Label lbStatus;

    @FXML
    private TextField txtStatus;

    @FXML
    private Label lbTipo;

    @FXML
    private TextField txtTipo;
    
    @FXML
    private Button btVoltar;
    
    @FXML
    private Label lbIdT;

    @FXML
    private Label lbId;

    UsuarioControler usuCont = null;
    
    Usuario usu = null;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         initComponentes(); 
    }    

    private void initComponentes (){

        usuCont = new UsuarioControler();
        ConsultarUsuarioInterface telaAnterior = new ConsultarUsuarioInterface();
        setUsuario(telaAnterior.getUsuario());

        btVoltar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(Biblioteca.class.getResource("/br/com/fatec/xmls/MenuInterface.fxml"));
                Parent novatela = null;
                try {
                    novatela = loader.load();
                    Stage stg = Biblioteca.getStage();
                    stg.setScene(new Scene(novatela));
                    stg.show();
                } catch (IOException ex) {
                    Logger.getLogger(AlterarUsuarioInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        btSalvar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                usu = new Usuario(Integer.parseInt(lbIdT.getText()),
                                  txtNome.getText(),
                                  txtLogin.getText(),
                                  txtSenha.getText(),
                                  txtStatus.getText(),
                                  txtTipo.getText());

                try {
                    usuCont.alterarUsuario(usu);
                } catch (SQLException ex) {
                    Logger.getLogger(AlterarUsuarioInterface.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AlterarUsuarioInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                FXMLLoader loader = new FXMLLoader(Biblioteca.class.getResource("/br/com/fatec/xmls/MenuInterface.fxml"));
                Parent novatela = null;
                try {
                    novatela = loader.load();
                    Stage stg = Biblioteca.getStage();
                    stg.setScene(new Scene(novatela));
                    stg.show();
                } catch (IOException ex) {
                    Logger.getLogger(AlterarUsuarioInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }
    
    public void setUsuario(Usuario usu) {
        JOptionPane.showMessageDialog(null, usu.getLogin());
        this.lbIdT.setText(""+usu.getId());
        this.txtNome.setText(usu.getNome());
        this.txtLogin.setText(usu.getLogin());
        this.txtSenha.setText(usu.getSenha());
        this.txtStatus.setText(usu.getStatus());
        this.txtTipo.setText(usu.getTipo());
    }
    
}
    
