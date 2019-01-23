/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.telas;

import br.com.fatec.bean.Livro;
import br.com.fatec.bean.Usuario;
import br.com.fatec.controler.LivroControler;
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
public class AlterarLivroInterface implements Initializable {

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
    
    @FXML
    private Label lbIdT;

    @FXML
    private Label lbId;

    LivroControler lioCont = null;
    
    Livro lio = null;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         initComponentes(); 
    }    

    private void initComponentes (){

        lioCont = new LivroControler();
        ConsultarLivroInterface telaAnterior = new ConsultarLivroInterface();
        setLivro(telaAnterior.getLivro());

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
                    Logger.getLogger(AlterarLivroInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        btSalvar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                lio = new Livro(Integer.parseInt(lbIdT.getText()),
                                  txtTitulo.getText(),
                                  txtAutor.getText(),
                                  txtCategoria.getText(),
                                  txtIdioma.getText(),
                                  txtEditora.getText());
                
                
                

                try {
                    lioCont.alterarLivro(lio);
                } catch (SQLException ex) {
                    Logger.getLogger(AlterarLivroInterface.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AlterarLivroInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                FXMLLoader loader = new FXMLLoader(Biblioteca.class.getResource("/br/com/fatec/xmls/MenuInterface.fxml"));
                Parent novatela = null;
                try {
                    novatela = loader.load();
                    Stage stg = Biblioteca.getStage();
                    stg.setScene(new Scene(novatela));
                    stg.show();
                } catch (IOException ex) {
                    Logger.getLogger(AlterarLivroInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }
    
   public void setLivro(Livro lio) {
        JOptionPane.showMessageDialog(null, lio.getTitulo());
        this.lbIdT.setText(String.valueOf(lio.getId()));
        this.txtTitulo.setText(lio.getTitulo());
        this.txtAutor.setText(lio.getAutor());
        this.txtCategoria.setText(lio.getCategoria());
        this.txtIdioma.setText(lio.getIdioma());
        this.txtEditora.setText(lio.getEditora());
    }

//    private void setComanda(Comanda comanda) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    
}
    
