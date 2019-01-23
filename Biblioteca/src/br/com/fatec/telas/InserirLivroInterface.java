/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.telas;

import br.com.fatec.bean.Livro;
import br.com.fatec.controler.LivroControler;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import biblioteca.Biblioteca;

/**
 * FXML Controller class
 *
 * @author ProfAlexandre
 */
public class InserirLivroInterface implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button btSalvar;

    @FXML
    private Label lbTitulo;

    @FXML
    private TextField txtTitulo;

    @FXML
    private Label lbAutor;

    @FXML
    private TextField txtAutor;

    @FXML
    private Label lbCategoria;

    @FXML
    private TextField txtCategoria;

    @FXML
    private Label lbIdioma;

    @FXML
    private TextField txtIdioma;

    @FXML
    private Label lbEditora;

    @FXML
    private TextField txtEditora;
    
    @FXML
    private Button btVoltar;

    LivroControler lioCont = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initComponentes();
    }    
    
    private void initComponentes () {
        btSalvar.setOnAction((ActionEvent event) -> {
            lioCont = new LivroControler();
            Livro lio = new Livro(0,txtTitulo.getText(),txtAutor.getText(),txtCategoria.getText(),txtIdioma.getText(),txtEditora.getText());
            try {
                lio = lioCont.inserirLivro(lio);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(LoginInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, lio.getTitulo());
        });
        
        
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
                    Logger.getLogger(LoginInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    
}
