import java.util.Arrays;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.beans.binding.StringBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Calculator extends Application {
    public ArrayList<String> num = new ArrayList<String>(3);

    public static Button makeButton(String txt) {
        Button button = new Button(txt);
        button.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        button.setMaxWidth(Double.MAX_VALUE);
        button.setMaxHeight(Double.MAX_VALUE);
        button.setPadding(new Insets(25,35,25,35));
        button.setFont(new Font(20));
        button.setBorder(new Border(
                new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(4), new BorderWidths(1))));
        return button;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("My Calculator");

        GridPane gPane = new GridPane();
        
        // ColumnConstraints column = new ColumnConstraints();

        // column.setPercentWidth(25);
        // column.setFillWidth(true);

        // ColumnConstraints column2 = new ColumnConstraints();

        // column2.setPercentWidth(25);
        // column2.setFillWidth(true);
        // ColumnConstraints column3 = new ColumnConstraints();

        // column3.setPercentWidth(25);
        // column3.setFillWidth(true);
        // ColumnConstraints column4 = new ColumnConstraints();

        // column4.setPercentWidth(25);
        // column4.setFillWidth(true);
        TextField textField = new TextField();

        Label label = new Label();
        label.setPadding(new Insets(30));
        label.setBorder(new Border(
                new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
        label.setMaxWidth(Double.MAX_VALUE);
        textField.setPadding(new Insets(40));
        textField.setBorder(new Border(
                new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
        Button bn1 = makeButton("AC");
        bn1.setOnAction(new Updater(label, textField, bn1));
        Button bn2 = makeButton("%");
        bn2.setOnAction(new Updater(label, textField, bn2));
        Button bn3 = makeButton("/");
        bn3.setOnAction(new Updater(label, textField, bn3));
        Button bn4 = makeButton("0");
        bn4.setOnAction(new Updater(label, textField, bn4));
        Button bn5 = makeButton(".");
        bn5.setOnAction(new Updater(label, textField, bn5));
        Button bn6 = makeButton("=");
        bn6.setOnAction(new Updater(label, textField, bn6));
        Button bn7 = makeButton("BackSpc");
        bn7.setOnAction(new Updater(label, textField, bn7));
       
       
        gPane.add(label, 0, 0, 4, 1);
        GridPane.setHgrow(label, Priority.ALWAYS);
        gPane.add(textField, 0, 1, 4, 1);
        GridPane.setHgrow(textField, Priority.ALWAYS);
        gPane.add(bn1, 0, 2, 2, 1);
        GridPane.setHgrow(bn1, Priority.ALWAYS);
        gPane.add(bn2, 2, 2);
        GridPane.setHgrow(bn2, Priority.ALWAYS);
        gPane.add(bn3, 3, 2);
        GridPane.setHgrow(bn3, Priority.ALWAYS);
        gPane.add(bn4, 0, 6, 2, 1);
        GridPane.setHgrow(bn4, Priority.ALWAYS);
        gPane.add(bn5, 2, 6);
        GridPane.setHgrow(bn5, Priority.ALWAYS);
        gPane.add(bn6, 3, 6);
        GridPane.setHgrow(bn5, Priority.ALWAYS);
        gPane.add(bn7, 0, 7, 4, 1);
        GridPane.setHgrow(bn7, Priority.ALWAYS);

        String[] symbols = { "7", "8", "9", "×", "4", "5", "6", "-", "1", "2","3", "+" };
        int count = 0;
        for (int row = 3; row < 7; row++) {
            for (int col = 0; col < 4; col++) {
                Button button = makeButton(symbols[count]);
                button.setOnAction(new Updater(label, textField, button));
                gPane.add(button, col, row);
                GridPane.setHgrow(button, Priority.ALWAYS);
                count++;
            }

            if (count >= 12) {
                break;
            }
        
        }
        gPane.setHgap(0);
        gPane.setVgap(0);

        // gPane.getColumnConstraints().addAll(column, column2, column3, column4);
        Scene scene = new Scene(gPane);

        stage.setScene(scene);

        stage.show();

    }

    public class Updater implements EventHandler<ActionEvent> {
        Label l;
        TextField tf;
        Button bn;

        public Updater(Label l, TextField tf, Button bn) {
            this.l = l;
            this.tf = tf;
            this.bn = bn;

        }

        @Override
        public void handle(ActionEvent event) {

            if (this.bn.getText() != "AC" && this.bn.getText() != "BackSpc") {
                this.tf.appendText(bn.getText());

                num.add(this.bn.getText());
            }
            if (this.bn.getText() == "AC") {
                this.tf.clear();
                this.l.setText("");
                num.clear();
            }
            if (this.bn.getText() == "BackSpc") {
                
                if (this.tf.getLength()==num.size() && this.tf.getLength()!=0){
                    this.tf.deleteText(tf.getLength() - 1, tf.getLength());
                    num.remove(num.size() - 1);}
                else if(this.tf.getLength()>num.size()){
                    this.tf.deleteText(tf.getLength() - 1, tf.getLength());}
                else {tf.clear();}
                    
            }
            if (bn.getText() == "=") {
                if (num.get(1) == "+") {

                    int sum = Integer.parseInt(num.get(0)) + Integer.parseInt(num.get(2));
                    this.tf.appendText("" + sum);
                    this.l.setText("" + sum);

                }
                else if (num.get(1) == "-") {

                    int diff = Integer.parseInt(num.get(0)) - Integer.parseInt(num.get(2));
                    this.tf.appendText("" + diff);
                    this.l.setText("" + diff);

                }
                else if (num.get(1) == "×") {
                    num.add(this.bn.getText());
                    int product = Integer.parseInt(num.get(0)) * Integer.parseInt(num.get(2));
                    this.tf.appendText("" + product);
                    this.l.setText("" + product);

                }
                else if (num.get(1) == "/") {

                    double divid = Integer.parseInt(num.get(0)) / (double) Integer.parseInt(num.get(2));
                    this.tf.appendText("" + divid);
                    this.l.setText("" + divid);

                }
                else if (num.get(1) == "%") {

                    int modul = Integer.parseInt(num.get(0)) % Integer.parseInt(num.get(2));
                    this.tf.appendText("" + modul);
                    this.l.setText("" + modul);

                }

            }

        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
