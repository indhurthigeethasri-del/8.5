import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TipCalculator extends Application {

    @Override
    public void start(Stage stage) {

        Label billLabel = new Label("Bill Amount:");
        TextField billField = new TextField();

        Label tipLabel = new Label("Tip Percentage:");

        RadioButton r1 = new RadioButton("5%");
        RadioButton r2 = new RadioButton("10%");
        RadioButton r3 = new RadioButton("15%");

        ToggleGroup group = new ToggleGroup();
        r1.setToggleGroup(group);
        r2.setToggleGroup(group);
        r3.setToggleGroup(group);

        r2.setSelected(true);

        Label peopleLabel = new Label("Number of People:");

        ComboBox<Integer> peopleBox = new ComboBox<>();
        peopleBox.getItems().addAll(1, 2, 3, 4, 5);
        peopleBox.setValue(1);

        Button calculateButton = new Button("Calculate Tip");

        Label tipResult = new Label("Tip Amount: ");
        Label totalResult = new Label("Total Bill: ");
        Label personResult = new Label("Per Person: ");

        calculateButton.setOnAction(e -> {
            try {
                double bill = Double.parseDouble(billField.getText());

                if (bill < 0) {
                    tipResult.setText("Enter a valid bill amount.");
                    totalResult.setText("");
                    personResult.setText("");
                    return;
                }

                RadioButton selected =
                    (RadioButton) group.getSelectedToggle();

                double percentage = Double.parseDouble(
                    selected.getText().replace("%", "")
                );

                int people = peopleBox.getValue();

                double tip = bill * percentage / 100;
                double total = bill + tip;
                double perPerson = total / people;

                tipResult.setText(
                    String.format("Tip Amount: %.2f", tip)
                );

                totalResult.setText(
                    String.format("Total Bill: %.2f", total)
                );

                personResult.setText(
                    String.format("Per Person: %.2f", perPerson)
                );

            } catch (NumberFormatException ex) {
                tipResult.setText("Please enter a valid bill amount.");
                totalResult.setText("");
                personResult.setText("");
            } catch (NullPointerException ex) {
                tipResult.setText("Please select a tip percentage.");
            }
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(12);

        grid.add(billLabel, 0, 0);
        grid.add(billField, 1, 0);

        grid.add(tipLabel, 0, 1);
        grid.add(r1, 1, 1);
        grid.add(r2, 2, 1);
        grid.add(r3, 3, 1);

        grid.add(peopleLabel, 0, 2);
        grid.add(peopleBox, 1, 2);

        grid.add(calculateButton, 1, 3);

        grid.add(tipResult, 0, 4, 3, 1);
        grid.add(totalResult, 0, 5, 3, 1);
        grid.add(personResult, 0, 6, 3, 1);

        Scene scene = new Scene(grid, 480, 300);

        stage.setTitle("Tip Calculator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
