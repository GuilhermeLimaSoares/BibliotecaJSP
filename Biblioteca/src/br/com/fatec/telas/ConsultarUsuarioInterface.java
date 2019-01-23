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
public class ConsultarUsuarioInterface implements Initializable {

    @FXML
    private Label lbNome;

    @FXML
    private TextField txtNome;

    @FXML
    private TableView<Usuario> listaUsuario;

    @FXML
    private TableColumn<Usuario,String> tid;

    @FXML
    private TableColumn<Usuario,String> tnome;

    @FXML
    private TableColumn<Usuario,String> tlogin;

    @FXML
    private TableColumn<Usuario,String> tsenha;

    @FXML
    private TableColumn<Usuario,String> tstatus;

    @FXML
    private TableColumn<Usuario,String> ttipo;
    
    @FXML
    private Button btAlterar;

    @FXML
    private Button btExcluir;

    @FXML
    private Button btVoltar;
    
    @FXML
    private Button btConsultar;

    UsuarioControler usuCont = null;
    
    ObservableList<Usuario> oList = null;

    List<Usuario> lista = null;
    
    public static Usuario usuSaidaTela = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initComponentes();
    }       
    
    private void initComponentes (){

        usuCont = new UsuarioControler();

        btConsultar.setOnAction((ActionEvent event) -> {
            Usuario usu = new Usuario(0,txtNome.getText(),"","","","");
            try {
                montaLista(usu);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(LoginInterface.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,"Erro = Na Lista");
            }
        });

        btAlterar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TablePosition pos = listaUsuario.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                setUsuario(listaUsuario.getItems().get(row));
                FXMLLoader loader = new FXMLLoader(Biblioteca.class.getResource("/br/com/fatec/xmls/AlterarUsuarioInterface.fxml"));
                Parent novatela = null;
                try {
                    novatela = loader.load();
                    Stage stg = Biblioteca.getStage();
                    stg.setScene(new Scene(novatela));
                    stg.show();
                } catch (IOException ex) {
                    Logger.getLogger(ConsultarUsuarioInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        btExcluir.setOnAction((ActionEvent event) -> {
            TablePosition pos = listaUsuario.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();
            Usuario usu = listaUsuario.getItems().get(row);
            try {
                usuCont.excluirUsuario(usu);
                listaUsuario.getItems().remove(row);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(ConsultarUsuarioInterface.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,"Erro = No Excluir");

            }
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
                    Logger.getLogger(ConsultarUsuarioInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
    }
    
    public void montaLista(Usuario usu) throws SQLException, ClassNotFoundException {
        lista = usuCont.listarUsuario(usu);
        oList = FXCollections.observableArrayList(lista);
        tid.setCellValueFactory(new PropertyValueFactory<Usuario,String>("id"));
        tnome.setCellValueFactory(new PropertyValueFactory<Usuario,String>("nome"));
        tlogin.setCellValueFactory(new PropertyValueFactory<Usuario,String>("login"));
        tsenha.setCellValueFactory(new PropertyValueFactory<Usuario,String>("senha"));
        tstatus.setCellValueFactory(new PropertyValueFactory<Usuario,String>("status"));
        ttipo.setCellValueFactory(new PropertyValueFactory<Usuario,String>("tipo"));
        listaUsuario.setItems(oList);
    }
    
    

    public void setUsuario(Usuario usuP) {
        this.usuSaidaTela = usuP;
    }
    
    public Usuario getUsuario() {
        return this.usuSaidaTela;
    }


}
