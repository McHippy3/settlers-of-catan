package org.settlersofcatan;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Scenes 
{
	public static Node titleScene(Button startButton) 
	{
		VBox root = new VBox();
		root.setAlignment(Pos.CENTER);
		root.setSpacing(100);
		
		//Components
		ImageView image = new ImageView(new Image("res/logo.png"));
		startButton.setPrefSize(100, 25);
		root.getChildren().addAll(image, startButton);
		return root;
	}
	
	public static Node offlineGameScene() 
	{
		//Stack Pane
		StackPane root = new StackPane();
		
		//StackPane Layer 1: Ocean
		Group oceanLayer = new Group();
		for(int r = 0; r < 10; r ++) 
		{
			int yPos = r * 253;
			for(int c = 0; c < 10; c++) 
			{		
				ImageView oceanPic = new ImageView(new Image("res/ocean.png"));
				oceanLayer.getChildren().add(oceanPic);
				oceanPic.setX(c * 199);
				oceanPic.setY(yPos);
			}
		}
		
		//StackPane Layer 2: BorderPane
		BorderPane borderPane = new BorderPane();

		//leftBox 
		VBox leftBox = new VBox();
		leftBox.setPadding(new Insets(25));
		leftBox.setSpacing(25);
		
		//rightBox
		VBox rightBox = new VBox();
		rightBox.setPadding(new Insets(25));
		rightBox.setSpacing(25);
		
		//Player Tiles
        GridPane[] playerTiles = new GridPane[4];
        for(int i = 0; i < playerTiles.length; i++)
        {
            playerTiles[i] = new GridPane();
            playerTiles[i].setAlignment(Pos.CENTER);
            playerTiles[i].setPrefSize(252, 500);
            playerTiles[i].setPadding(new Insets((10)));
            playerTiles[i].setVgap(5);
            playerTiles[i].setHgap(5);
            playerTiles[i].add(new Text("Player " + (i + 1)), 0, 0, 2, 1);
            playerTiles[i].add(new Text("Points: " + i), 0, 1, 1, 1);

            //Setting Color
            BackgroundFill backgroundFill;
            switch(i)
            {
                case 0:
                    backgroundFill = new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY);
                    break;
                case 1:
                    backgroundFill = new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY);
                    break;
                case 2:
                    backgroundFill = new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY);
                    break;
                default:
                    backgroundFill = new BackgroundFill(Color.LIGHTSALMON, CornerRadii.EMPTY, Insets.EMPTY);
                    break;
            }
            playerTiles[i].setBackground(new Background(backgroundFill));
        }
        
        leftBox.getChildren().addAll(playerTiles[0], playerTiles[1]);
        rightBox.getChildren().addAll(playerTiles[2], playerTiles[3]);
        
        //Center 
        VBox center = new VBox();
        center.setSpacing(50);
        center.setAlignment(Pos.CENTER);
        
        //Game Tiles
        Group gameTiles = new Group();
        int numOfColumns = 3;
        double sf = 0.70;
        for(int r = 0; r < 5; r++) 
        {
        	for(int c = 0; c < numOfColumns; c++) 
        	{
        		ImageView img = new ImageView(new Image("res/tile_photos/desert.png"));
        		img.setFitHeight(210 * sf);
        		img.setFitWidth(240 * sf);
        		img.setRotate(90);
        		int x = 205 * c - (105 * r % 2);
        		
        		//Shift right to center
        		if(r != 2) 
        		{
        			x += 105;
        		}
        		
        		if(r == 0 || r == 4) 
        		{
        			x += 105;
        		}
        		
        		img.setX((int) (x * sf));
        		img.setY(180 * r * sf);
        		gameTiles.getChildren().add(img);
        	}
        	
        	if(r < 2) 
        	{
        		numOfColumns++;
        	}
        	else numOfColumns--;
        }
        
        //Command Panel
        HBox commandPanel = new HBox();
        commandPanel.setMinSize(1300, 250);
        commandPanel.setMaxSize(1300, 250);
        commandPanel.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        commandPanel.setBorder(new Border
        		(new BorderStroke
        				(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(1))
        		));
        commandPanel.setPadding(new Insets(25));
        
        center.getChildren().addAll(gameTiles, commandPanel);
		
		borderPane.setLeft(leftBox);
		borderPane.setRight(rightBox);
		borderPane.setCenter(center);
		
		root.getChildren().addAll(oceanLayer, borderPane);
		
		return root;
	}
}
