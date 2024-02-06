package io.github.et.generator;

import io.github.et.generator.conponent.EventButton;
import io.github.et.generator.logic.IntParser;
import io.github.et.generator.logic.RandomNumber;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class MainApplication extends Application {
    public int up=0;
    public ArrayList<Integer> al=null;
    public ArrayList<Integer> purple=null;
    public ArrayList<Integer> gold=null;
    public RandomNumber randomNumber=null;
    public int onceNum;
    public static int that=0;
    public ArrayList<Integer> tenthNum;
    public static int andThat=0;
    @Override
    public void start(Stage stage) throws IOException {
        stage.setResizable(false);
        File info =new File(System.getProperty ("user.home")+"/.generator/tempInfo/lastInfo.log");
        String last=new String(new FileInputStream(info).readAllBytes());
        Image background =new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/io/et/github/generator/background1.png")));
        AnchorPane root1 = new AnchorPane();
        ImageView imageView=new ImageView();
        imageView.setFitWidth(720);
        imageView.setFitHeight(405);
        imageView.setImage(background);
        TextField number=new TextField(last);
        number.setFont(Font.loadFont(new FileInputStream("./zh-cn.ttf"),75));
        number.setMaxSize(375,50);
        number.setAlignment(Pos.CENTER);
        number.setCursor(new ImageCursor(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/io/et/github/generator/cursor.png")))));
        AnchorPane.setTopAnchor(number,90d);
        AnchorPane.setLeftAnchor(number, 175d);
        Label hint=new Label("请输入最大学号");
        hint.setFont(Font.loadFont(new FileInputStream("./zh-cn.ttf"),20));
        hint.setLabelFor(number);
        AnchorPane.setTopAnchor(hint,10d);
        AnchorPane.setLeftAnchor(hint,0d);
        AnchorPane.setRightAnchor(hint,0d);
        hint.setAlignment(Pos.CENTER);
        EventButton once=new EventButton("祈愿一次");
        AnchorPane.setRightAnchor(once,200d);
        AnchorPane.setBottomAnchor(once,50d);

        EventButton tenth=new EventButton("祈愿十次");
        AnchorPane.setBottomAnchor(tenth,50d);
        AnchorPane.setRightAnchor(tenth,30d);


        Label num=new Label();
        FadeTransition waiting=new FadeTransition(new Duration(6050d),num);
        num.setFont(Font.loadFont(new FileInputStream("./zh-cn.ttf"),150));
        num.setTextFill(Color.BLACK);
        AnchorPane.setBottomAnchor(num,0.0);
        AnchorPane.setLeftAnchor(num,0.0);
        AnchorPane.setRightAnchor(num,0.0);
        AnchorPane.setTopAnchor(num,0.0);
        num.setAlignment(Pos.CENTER);
        num.setVisible(false);

        Label launch=new Label("Generator");
        launch.setFont(Font.loadFont(new FileInputStream("./zh-cn.ttf"),100));
        launch.setAlignment(Pos.CENTER);
        Label announcement=new Label("本软件所用图片、字体等素材原作者为米哈游，取自B站@难忘的旋律Official");
        announcement.setFont(Font.loadFont(new FileInputStream("./zh-cn.ttf"),15));
        announcement.setAlignment(Pos.BOTTOM_CENTER);

        ImageView characterBg=new ImageView(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/io/et/github/generator/character-bg.jpg"))));
        AnchorPane.setTopAnchor(characterBg,60d);
        AnchorPane.setLeftAnchor(characterBg,150d);
        characterBg.setFitWidth(450d);
        characterBg.setFitHeight(225d);


        InnerShadow innerShadow = new InnerShadow();
        innerShadow.setRadius(6);
        innerShadow.setOffsetX(-2);
        innerShadow.setOffsetY(-2);
        innerShadow.setColor(Color.BLACK);
        Text text = new Text("");
        text.setFont(Font.loadFont(new FileInputStream("./zh-cn.ttf"),38));
        TextFlow textFlow = new TextFlow(text);
        AnchorPane.setLeftAnchor(textFlow,150d);
        AnchorPane.setTopAnchor(textFlow,60d);
        AnchorPane.setRightAnchor(textFlow,150d);
        textFlow.setTextAlignment(TextAlignment.CENTER);
        text.setFill(Color.rgb(70,130,180));
        text.setEffect(innerShadow);

        number.setOnKeyPressed(key->{
            if(key.getCode()== KeyCode.ENTER){
                if (IntParser.condition(number.getText())) {
                    try {
                        new FileOutputStream(info).write(number.getText().getBytes());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    if (randomNumber == null) {
                        randomNumber = new RandomNumber(Integer.parseInt(number.getText()));
                        up = randomNumber.getUp();
                        al = randomNumber.getAl();
                        purple = randomNumber.getPurple();
                        gold = randomNumber.getGold();
                    }
                    if (text.getText().isEmpty()) {
                        text.setText(
                                "UP：" + up + "\n" +
                                        "常驻：" + al.get(0) + " " + al.get(1) + " " + al.get(2) + "\n" +
                                        "紫：" + purple.get(0) + " " + purple.get(1) + " " + purple.get(2)
                        );
                    }
                    number.setVisible(false);
                    hint.setVisible(false);
                    characterBg.setVisible(true);
                    textFlow.setVisible(true);
                } else{
                    hint.setTextFill(Color.RED);
                    hint.setText("请输入数字（≥10且≤999999）");
                }
            }
        });

        Label[] calc =new Label[10];
        for (int i=0;i<10;i++){calc[i]=new Label();}
        for(Label i:calc){
            i.setAlignment(Pos.CENTER);
            i.setRotate(90);
            i.setFont(Font.loadFont(new FileInputStream("./zh-cn.ttf"),45));
            i.setVisible(false);
            AnchorPane.setTopAnchor(i,170d);
            AnchorPane.setLeftAnchor(i,610d);
            AnchorPane.setRightAnchor(i,-190d);
            AnchorPane.setBottomAnchor(i,170d);
        }


        Button skipOnce=new Button("跳过");
        AnchorPane.setRightAnchor(skipOnce,0d);
        skipOnce.setFont(Font.loadFont(new FileInputStream("./zh-cn.ttf"),15));
        skipOnce.setOnAction(action->{
            waiting.stop();
            skipOnce.setVisible(false);
            imageView.setImage(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/io/et/github/generator/background.png"))));
            num.setText(String.valueOf(onceNum));
            num.setVisible(true);
            FadeTransition waiting1=new FadeTransition(new Duration(1000d),num);
            waiting1.setFromValue(1.0);
            waiting1.setToValue(1.0);
            waiting1.setOnFinished(event->{
                if(gold.contains(onceNum)){
                    num.setTextFill(Color.rgb(238,232,170));
                }else if(purple.contains(onceNum)){
                    num.setTextFill(Color.rgb(216,191,216));
                }else {
                    num.setTextFill(Color.WHITE);
                }
                root1.setOnMouseClicked(re->{
                    imageView.setImage(background);
                    once.setVisible(true);
                    tenth.setVisible(true);
                    num.setVisible(false);
                    characterBg.setVisible(true);
                    textFlow.setVisible(true);
                    root1.setOnMouseClicked(res->{});
                    num.setTextFill(Color.BLACK);
                });
            });
            waiting1.play();
        });
        skipOnce.setVisible(false);


        FadeTransition waitingTask=new FadeTransition(new Duration(6050d),imageView);
        FadeTransition waitingAgain=new FadeTransition(new Duration(1000d),number);
        Button skipTenth=new Button("跳过");
        AnchorPane.setRightAnchor(skipTenth,0d);
        skipTenth.setFont(Font.loadFont(new FileInputStream("./zh-cn.ttf"),15));
        skipTenth.setOnAction(act->{
            waitingTask.stop();
            waitingAgain.stop();
            root1.setOnMouseClicked(e->{});
            imageView.setImage(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/io/et/github/generator/background.png"))));
            root1.setOnMouseClicked(nth->{});
            num.setVisible(false);
            tenthNum=randomNumber.sort(tenthNum);
            for(int i=0;i< calc.length;i++){
                calc[i].setText(String.valueOf(tenthNum.get(i)));
                calc[i].setTranslateX(0);
                if(gold.contains(tenthNum.get(i))){
                    calc[i].setTextFill(Color.rgb(238,232,170));
                    calc[i].setBorder(new Border(new BorderStroke(Color.rgb(238,232,170), BorderStrokeStyle.SOLID,new CornerRadii(10),new BorderWidths(5))));
                } else if (purple.contains(tenthNum.get(i))) {
                    calc[i].setTextFill(Color.rgb(216,191,216));
                    calc[i].setBorder(new Border(new BorderStroke(Color.rgb(216,191,216), BorderStrokeStyle.SOLID,new CornerRadii(10),new BorderWidths(5))));
                }else {
                    calc[i].setTextFill(Color.WHITE);
                    calc[i].setBorder(new Border(new BorderStroke(Color.rgb(173,216,230), BorderStrokeStyle.SOLID,new CornerRadii(10),new BorderWidths(1))));
                }
            }
            skipTenth.setVisible(false);
            TranslateTransition trans=new TranslateTransition();
            trans.setDuration(new Duration(500d-50*andThat));
            trans.setNode(calc[andThat]);
            trans.setByX(-715 + 70 * andThat);
            calc[andThat].setVisible(true);
            trans.setOnFinished(fi->{
                if(andThat<9) {
                    andThat += 1;
                    trans.setDuration(new Duration(500d-50*andThat));
                    trans.setNode(calc[andThat]);
                    trans.setByX(-715 + 70 * andThat);
                    calc[andThat].setVisible(true);
                    trans.play();
                }else{
                    andThat=0;
                    root1.setOnMouseClicked(nan-> {
                        that = 0;
                        for(Label i:calc){
                            i.setVisible(false);
                        }
                        imageView.setImage(background);
                        once.setVisible(true);
                        tenth.setVisible(true);
                        characterBg.setVisible(true);
                        textFlow.setVisible(true);
                        root1.setOnMouseClicked(res -> {
                        });
                        num.setTextFill(Color.BLACK);
                    });
                }
            });
            trans.play();
        });
        skipTenth.setVisible(false);

        root1.getChildren().add(launch);
        root1.getChildren().add(announcement);
        AnchorPane.setLeftAnchor(launch,0.0);
        AnchorPane.setRightAnchor(launch,0.0);
        AnchorPane.setBottomAnchor(launch,0.0);
        AnchorPane.setTopAnchor(launch,0.0);
        AnchorPane.setLeftAnchor(announcement,0d);
        AnchorPane.setRightAnchor(announcement,0d);
        AnchorPane.setTopAnchor(announcement,0d);
        AnchorPane.setBottomAnchor(announcement,0d);
        root1.getChildren().add(imageView);
        root1.getChildren().add(number);
        root1.getChildren().add(hint);
        root1.getChildren().add(once);
        root1.getChildren().add(tenth);
        root1.getChildren().add(num);
        root1.getChildren().add(skipOnce);
        root1.getChildren().add(skipTenth);
        root1.getChildren().add(characterBg);
        root1.getChildren().add(textFlow);
        for (Label i:calc){
            root1.getChildren().add(i);
        }

        imageView.setVisible(false);
        number.setVisible(false);
        hint.setVisible(false);
        once.setVisible(false);
        tenth.setVisible(false);
        characterBg.setVisible(false);
        textFlow.setVisible(false);
        FadeTransition ft=new FadeTransition(new Duration(3000d),launch);
        FadeTransition anoFt=new FadeTransition(new Duration(3000d),announcement);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        anoFt.setFromValue(1.0);
        anoFt.setToValue(0.0);
        ft.setOnFinished(event->{
            announcement.setVisible(false);
            launch.setVisible(false);
            imageView.setVisible(true);
            number.setVisible(true);
            hint.setVisible(true);
            once.setVisible(true);
            tenth.setVisible(true);
        });
        FadeTransition ft1=new FadeTransition(new Duration(3000d),launch);
        FadeTransition anoFt1=new FadeTransition(new Duration(3000d),announcement);
        ft1.setFromValue(0.0);
        ft1.setToValue(1.0);
        anoFt1.setFromValue(0.0);
        anoFt1.setToValue(1.0);
        ft1.setOnFinished(e->{
            anoFt.play();
            ft.play();
        });
        ft1.play();
        anoFt1.play();
        once.setOnAction(action->{
            if (IntParser.condition(number.getText())) {
                try {
                    new FileOutputStream(info).write(number.getText().getBytes());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if(randomNumber==null) {
                    randomNumber =new RandomNumber(Integer.parseInt(number.getText()));
                    up= randomNumber.getUp();
                    al= randomNumber.getAl();
                    purple=randomNumber.getPurple();
                    gold=randomNumber.getGold();
                }
                if(text.getText().isEmpty()){
                    text.setText(
                            "UP："+up+"\n" +
                            "常驻："+ al.get(0)+" "+al.get(1)+" "+al.get(2)+"\n"+
                            "紫："+purple.get(0)+" "+purple.get(1)+" "+purple.get(2)
                    );
                }
                onceNum=randomNumber.randomBetween();
                skipOnce.setVisible(true);
                number.setVisible(false);
                hint.setVisible(false);
                once.setVisible(false);
                tenth.setVisible(false);
                textFlow.setVisible(false);
                characterBg.setVisible(false);
                Image img;
                if(gold.contains(onceNum)){
                    img= new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/io/et/github/generator/1.gif")));
                } else if (purple.contains(onceNum)) {
                    img= new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/io/et/github/generator/1_p.gif")));
                }else{
                    img= new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/io/et/github/generator/1_w.gif")));
                }
                imageView.setImage(img);
                waiting.setFromValue(0);
                waiting.setToValue(0);
                waiting.setOnFinished(val -> {
                    skipOnce.setVisible(false);
                    imageView.setImage(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/io/et/github/generator/background.png"))));
                    num.setText(String.valueOf(onceNum));
                    num.setVisible(true);
                    FadeTransition waiting1 = new FadeTransition(new Duration(1000d), num);
                    waiting1.setFromValue(1.0);
                    waiting1.setToValue(1.0);
                    waiting1.setOnFinished(event -> {
                        if(gold.contains(onceNum)){
                            num.setTextFill(Color.rgb(238,232,170));
                        }else if(purple.contains(onceNum)){
                            num.setTextFill(Color.rgb(216,191,216));
                        }else {
                            num.setTextFill(Color.WHITE);
                        }
                        root1.setOnMouseClicked(re -> {
                            imageView.setImage(background);
                            once.setVisible(true);
                            tenth.setVisible(true);
                            num.setVisible(false);
                            characterBg.setVisible(true);
                            textFlow.setVisible(true);
                            root1.setOnMouseClicked(res -> {
                            });
                            num.setTextFill(Color.BLACK);
                        });
                    });
                    waiting1.play();
                });
                waiting.play();
            }
            else{
                hint.setTextFill(Color.RED);
                hint.setText("请输入数字（≥10且≤999999）");
            }




        });





        tenth.setOnAction(event ->{
            if (IntParser.condition(number.getText())) {
                try {
                    new FileOutputStream(info).write(number.getText().getBytes());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if(randomNumber==null) {
                    randomNumber =new RandomNumber(Integer.parseInt(number.getText()));
                    up= randomNumber.getUp();
                    al= randomNumber.getAl();
                    purple=randomNumber.getPurple();
                    gold=randomNumber.getGold();
                }
                if(text.getText().isEmpty()){
                    text.setText(
                            "UP："+up+"\n" +
                                    "常驻："+ al.get(0)+" "+al.get(1)+" "+al.get(2)+"\n"+
                                    "紫："+purple.get(0)+" "+purple.get(1)+" "+purple.get(2)
                    );
                }
                tenthNum=randomNumber.RandBetween10();
                skipTenth.setVisible(true);
                number.setVisible(false);
                hint.setVisible(false);
                once.setVisible(false);
                tenth.setVisible(false);
                textFlow.setVisible(false);
                characterBg.setVisible(false);
                Image img;
                if(randomNumber.containsGold(tenthNum)){
                    img=new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/io/et/github/generator/10.gif")));
                }else{
                    img=new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/io/et/github/generator/10_p.gif")));
                }
                imageView.setImage(img);
                waitingTask.setFromValue(1d);
                waitingTask.setToValue(1d);
                waitingTask.setOnFinished(e->{
                    imageView.setImage(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/io/et/github/generator/background.png"))));
                    num.setText(tenthNum.get(0).toString());
                    num.setTextFill(Color.BLACK);
                    num.setVisible(true);
                    waitingAgain.setFromValue(1d);
                    waitingAgain.setToValue(1d);
                    waitingAgain.setOnFinished(evt->{
                        if(gold.contains(tenthNum.get(that))){
                            num.setTextFill(Color.rgb(238,232,170));
                        } else if (purple.contains(tenthNum.get(that))) {
                            num.setTextFill(Color.rgb(216,191,216));
                        }else {
                            num.setTextFill(Color.WHITE);
                        }
                        root1.setOnMouseClicked(c->{
                            MainApplication.that++;
                            if(that<10) {
                                num.setText(tenthNum.get(that).toString());
                                num.setTextFill(Color.BLACK);
                                root1.setOnMouseClicked(res -> {
                                });
                                waitingAgain.play();
                            }else{
                                root1.setOnMouseClicked(nth->{});
                                num.setVisible(false);
                                tenthNum=randomNumber.sort(tenthNum);
                                for(int i=0;i< calc.length;i++){
                                    calc[i].setText(String.valueOf(tenthNum.get(i)));
                                    calc[i].setTranslateX(0);
                                    if(gold.contains(tenthNum.get(i))){
                                        calc[i].setTextFill(Color.rgb(238,232,170));
                                        calc[i].setBorder(new Border(new BorderStroke(Color.rgb(238,232,170), BorderStrokeStyle.SOLID,new CornerRadii(10),new BorderWidths(5))));
                                    } else if (purple.contains(tenthNum.get(i))) {
                                        calc[i].setTextFill(Color.rgb(216,191,216));
                                        calc[i].setBorder(new Border(new BorderStroke(Color.rgb(216,191,216), BorderStrokeStyle.SOLID,new CornerRadii(10),new BorderWidths(5))));
                                    }else {
                                        calc[i].setTextFill(Color.WHITE);
                                        calc[i].setBorder(new Border(new BorderStroke(Color.rgb(173,216,230), BorderStrokeStyle.SOLID,new CornerRadii(10),new BorderWidths(1))));
                                    }
                                }
                                skipTenth.setVisible(false);
                                TranslateTransition trans=new TranslateTransition();
                                trans.setDuration(new Duration(500d-50*andThat));
                                trans.setNode(calc[andThat]);
                                trans.setByX(-715 + 70 * andThat);
                                calc[andThat].setVisible(true);
                                trans.setOnFinished(fi->{
                                    if(andThat<9) {
                                        andThat += 1;
                                        trans.setDuration(new Duration(500d-50*andThat));
                                        trans.setNode(calc[andThat]);
                                        trans.setByX(-715 + 70 * andThat);
                                        calc[andThat].setVisible(true);
                                        trans.play();
                                    }else{
                                        andThat=0;
                                        root1.setOnMouseClicked(nan-> {
                                            that = 0;
                                            for(Label i:calc){
                                                i.setVisible(false);
                                            }
                                            imageView.setImage(background);
                                            once.setVisible(true);
                                            tenth.setVisible(true);
                                            characterBg.setVisible(true);
                                            textFlow.setVisible(true);
                                            root1.setOnMouseClicked(res -> {
                                            });
                                            num.setTextFill(Color.BLACK);
                                        });
                                    }
                                });
                                trans.play();


                            }

                        });
                    });
                    waitingAgain.play();
                });
                waitingTask.play();
            }
            else{
                hint.setTextFill(Color.RED);
                hint.setText("请输入数字（≥10且≤999999）");
            }
        });



        Scene scene = new Scene(root1,718, 403);
        scene.setCursor(new ImageCursor(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/io/et/github/generator/cursor.png")))));
        stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/io/et/github/generator/icons/1.png"))));
        stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/io/et/github/generator/icons/2.png"))));
        stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/io/et/github/generator/icons/3.png"))));
        stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/io/et/github/generator/icons/4.png"))));
        stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/io/et/github/generator/icons/5.png"))));
        stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/io/et/github/generator/icons/6.png"))));
        stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/io/et/github/generator/icons/7.png"))));
        stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/io/et/github/generator/icons/8.png"))));
        stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/io/et/github/generator/icons/9.png"))));
        stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/io/et/github/generator/icons/10.png"))));
        stage.setTitle("Generator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        File logTempDir=new File(System.getProperty ("user.home")+"/.generator/tempInfo");
        File logTemp=new File(System.getProperty ("user.home")+"/.generator/tempInfo/lastInfo.log");
        try{
            if(!(logTemp.exists()&&logTempDir.exists())){
                logTempDir.mkdirs();
                logTemp.createNewFile();
            }
        }catch(Exception e){
            throw new IOException();
        }
        launch();
    }
}