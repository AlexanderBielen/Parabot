package org.parabot.core.ui.newui.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.parabot.core.Core;
import org.parabot.core.settings.Configuration;
import org.parabot.core.ui.newui.BotUI;
import org.parabot.core.ui.newui.components.DialogHelper;
import org.parabot.core.ui.newui.controllers.services.LoginService;
import org.parabot.core.user.UserAuthenticator;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Fryslan, JKetelaar
 */
public class LoginUIController implements Initializable {

    @FXML
    private AnchorPane loginPanel;
    @FXML
    private Label      title, description;
    @FXML
    private Button loginButton, registerButton;

    private boolean disabled;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.title.setText(Configuration.BOT_TITLE);
        this.description.setText(Configuration.BOT_SLOGAN);
    }

    @FXML
    private void login(ActionEvent event) {
        UserAuthenticator authenticator = Core.getInjector().getInstance(UserAuthenticator.class);

        LoginService service = new LoginService(authenticator);
        service.start();

        service.setOnRunning(event12 -> toggleButtons());

        EventHandler handler = event14 -> {
            if (service.getResult()) {
                Stage stage = (Stage) loginPanel.getScene().getWindow();
                Core.getInjector().getInstance(BotUI.class).switchState(BotUI.ViewState.SERVER_SELECTOR, stage);
            } else {
                DialogHelper.showError("Login", "Login failed", "It seems the login process failed, please try again.");
            }

            toggleButtons();
        };

        service.setOnSucceeded(handler);
    }

    @FXML
    private void register(ActionEvent event) {
        Stage stage = (Stage) loginPanel.getScene().getWindow();
        Core.getInjector().getInstance(BotUI.class).switchState(BotUI.ViewState.REGISTER, stage);
    }

    @FXML
    void clearAccount(ActionEvent event) {

    }

    private void toggleButtons() {
        loginButton.setDisable(!disabled);
        registerButton.setDisable(!disabled);
        disabled = !disabled;
    }
}