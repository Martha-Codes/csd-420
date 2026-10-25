
package com.guzman.csd420;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;

/**
 * CSD-420 Advanced Java
 * Module 1.3 - Random Playing Cards
 *
 * This program displays four randomly selected cards
 * from a standard deck of 52 cards.
 *
 * The Refresh button uses a lambda expression to
 * display a new selection of four cards.
 *
 * @author Martha Guzman
 * @version 1.0
 */
public class Guzman_Mod1_CSD420 extends Application {

    // Holds the four card images displayed in the window.
    private final HBox cardBox = new HBox(10);

    // Stores the previous selection of cards.
    private ArrayList<Integer> previousCards =
            new ArrayList<>();

    @Override
    public void start(Stage stage) {

        // Center the cards horizontally.
        cardBox.setAlignment(Pos.CENTER);

        // Create a button to refresh the cards.
        Button refreshButton = new Button("Refresh Cards");

        // Lambda expression handles the button click.
        refreshButton.setOnAction(event -> displayCards());

        // Display four cards when the program starts.
        displayCards();

        // Place the cards above the Refresh button.
        VBox root = new VBox(20, cardBox, refreshButton);
        root.setAlignment(Pos.CENTER);

        // Create the application window.
        Scene scene = new Scene(root, 650, 300);

        stage.setTitle("CSD-420 Random Playing Cards");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Randomly selects and displays four unique cards.
     * Each refresh produces a different set of cards.
     */
    private void displayCards() {

        ArrayList<Integer> deck = new ArrayList<>();

        // Add all 52 cards to the deck.
        for (int i = 1; i <= 52; i++) {
            deck.add(i);
        }

        // Randomly shuffle the deck.
        Collections.shuffle(deck);

        // Select the first four cards.
        ArrayList<Integer> selectedCards =
                new ArrayList<>(deck.subList(0, 4));

        // Shuffle again if the same set was displayed.
        while (selectedCards.containsAll(previousCards)
                && previousCards.size() == 4) {

            Collections.shuffle(deck);
            selectedCards =
                    new ArrayList<>(deck.subList(0, 4));
        }

        // Save the current cards for the next refresh.
        previousCards = selectedCards;

        // Remove the previously displayed images.
        cardBox.getChildren().clear();

        // Display each selected card.
        for (int cardNumber : selectedCards) {

            String imagePath =
                    "/cards/" + cardNumber + ".png";

            // Load the image from the resources folder.
            Image image = new Image(
                    getClass().getResourceAsStream(imagePath)
            );

            ImageView imageView = new ImageView(image);

            // Resize the image while keeping its proportions.
            imageView.setFitHeight(150);
            imageView.setPreserveRatio(true);

            // Add the card image to the display.
            cardBox.getChildren().add(imageView);
        }
    }

    /**
     * Launches the JavaFX application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
