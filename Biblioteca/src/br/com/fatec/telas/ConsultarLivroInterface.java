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
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
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
public class ConsultarLivroInterface implements Initializable {

    @FXML
    private Label lbTitulo;

    @FXML
    private TextField txtTitulo;

    @FXML
    private TableView<Livro> listaLivro;

    @FXML
    private TableColumn<Livro,String> tid;

    @FXML
    private TableColumn<Livro,String> ttitulo;

    @FXML
    private TableColumn<Livro,String> tautor;

    @FXML
    private TableColumn<Livro,String> tcategoria;

    @FXML
    private TableColumn<Livro,String> tidioma;

    @FXML
    private TableColumn<Livro,String> teditora;
    
    @FXML
    private Button btAlterar;

    @FXML
    private Button btExcluir;

    @FXML
    private Button btVoltar;
    
    @FXML
    private Button btConsultar;

    LivroControler lioCont = null;
    
    ObservableList<Livro> oList = null;

    List<Livro> lista = null;
    
    public static Livro lioSaidaTela = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initComponentes();
    }       
    
    private void initComponentes (){

        lioCont = new LivroControler();

        btConsultar.setOnAction((ActionEvent event) -> {
            Livro lio = new Livro(0,txtTitulo.getText(),"","","","");
            try {
                montaLista(lio);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(LoginInterface.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,"Erro = Na Lista");
            }
        });

        btAlterar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TablePosition pos = listaLivro.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                setLivro(listaLivro.getItems().get(row));
                
                FXMLLoader loader = new FXMLLoader(Biblioteca.class.getResource("/br/com/fatec/xmls/AlterarLivroInterface.fxml"));
//                FXMLLoader loader = new FXMLLoader(Biblioteca.class.getResource("/br/com/fatec/xmls/MenuInterface.fxml"));
                Parent novatela = null;
                try {
                    novatela = loader.load();
                    Stage stg = Biblioteca.getStage();
                    stg.setScene(new Scene(novatela));
                    stg.show();
                } catch (IOException ex) {
                    Logger.getLogger(ConsultarLivroInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
            
//                System.out.println("O botão está funcionando");
            
//                System.out.println("O botão está funcionando");
            
            }
        });
        
        btExcluir.setOnAction((ActionEvent event) -> {
            TablePosition pos = listaLivro.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();
            Livro lio = listaLivro.getItems().get(row);
            try {
                lioCont.excluirLivro(lio);
                listaLivro.getItems().remove(row);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(ConsultarLivroInterface.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,"Erro = No Excluir");

            }
        });

        btVoltar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(Biblioteca.class.getResource("/br/com/fatec/xmls/MenuInterface.fxml"));
//                  FXMLLoader loader = new FXMLLoader(Biblioteca.class.getResource("/br/com/fatec/xmls/AlterarComandaInterface.fxml"));
                Parent novatela = null;
                try {
                    novatela = loader.load();
                    Stage stg = Biblioteca.getStage();
                    stg.setScene(new Scene(novatela));
                    stg.show();
                } catch (IOException ex) {
                    Logger.getLogger(ConsultarLivroInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
    }
    
    public void montaLista(Livro lio) throws SQLException, ClassNotFoundException {
        lista = lioCont.listarLivro(lio);
        oList = FXCollections.observableArrayList(lista);
        tid.setCellValueFactory(new PropertyValueFactory<>("id"));
        ttitulo.setCellValueFactory(new PropertyValueFactory<Livro,String>("titulo"));
        tautor.setCellValueFactory(new PropertyValueFactory<Livro,String>("autor"));
        tcategoria.setCellValueFactory(new PropertyValueFactory<Livro,String>("categoria"));
        tidioma.setCellValueFactory(new PropertyValueFactory<Livro,String>("idioma"));
        teditora.setCellValueFactory(new PropertyValueFactory<Livro,String>("editora"));
        listaLivro.setItems(oList);
    }
    
    

    public void setLivro(Livro lioP) {
        this.lioSaidaTela = lioP;
    }
    
    public Livro getLivro() {
        return this.lioSaidaTela;
    }


}
