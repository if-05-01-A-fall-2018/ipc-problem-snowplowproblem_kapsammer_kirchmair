import com.sun.org.omg.CORBA.Initializer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;

public class Controller implements Initializable {

        @FXML
        private Button carLeftSideButton;

        @FXML
        private Button plowButton;

        @FXML
        private Button carRightSide;

        @FXML
        private TextArea leftQueueText;

        @FXML
        private TextArea rightQueueText;

        @FXML
        private TextArea onStreetText;

        private Repository repo;

        private Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), ae -> onTimerTick()));

        private Character nextSide;

        @Override
        public void initialize(URL location, ResourceBundle resources) {
                repo = Repository.getRepo();
                timeline.setCycleCount(Animation.INDEFINITE);
                timeline.play();
                nextSide = 'r';
        }

        private void onTimerTick() {
                rightQueueText.clear();
                leftQueueText.clear();
                onStreetText.clear();

                if (!repo.isSnowPlowDriving()) repo.startCar('r');
                if (nextSide.equals('r')) nextSide = 'l';
                else nextSide = 'r';

                if (repo.isSnowPlowDriving())
                        plowButton.setDisable(true);
                else
                        plowButton.setDisable(false);

                leftQueueText.setText(String.format("%d",repo.getCars('l').size()));
                rightQueueText.setText(String.format("%d",repo.getCars('r').size()));
                if (repo.getCars('s').size() == 0 && repo.isSnowPlowDriving()) {
                        onStreetText.setText(String.format("Snowplow is driving"));
                        /*
                        SnowPlow plow = repo.getPlow();
                        plow.run();
                        */
                }
                else onStreetText.setText(String.format("%d",repo.getCars('s').size()));
        }

        public void onCarRightSideClicked(javafx.event.ActionEvent actionEvent) {
                repo.drive(new Car(), 'r');
        }

        public void onPlowClicked(javafx.event.ActionEvent actionEvent) {
                if (repo.isSnowPlowDriving()) return;
                repo.startSnowPlow();
        }

        public void onCarLeftSideClicked(javafx.event.ActionEvent actionEvent) {
                repo.drive(new Car(), 'l');
        }


}


