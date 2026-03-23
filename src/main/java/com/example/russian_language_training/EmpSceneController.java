package com.example.russian_language_training;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class EmpSceneController {

    // тэги для кнопок, тестовых полей
    @FXML private Button btnEmp1;
    @FXML private Button btnEmp2;
    @FXML private Label wordLabel;
    @FXML private Label allEmpLabel;
    @FXML private Label cEmpLabel;
    @FXML private Label empPercent;
    @FXML private Label uncEmpLabel;

    @FXML private ResourceBundle resources;
    @FXML private URL location;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private final Random random = new Random();


    String[][] words = { // Внутриигровой каталог слов, которые могут попасться в игре.
            {"каталог", "каталОг", "катАлог"},
            {"кремень", "кремЕнь", "крЕмень"},
            {"баловать", "баловАть", "бАловать"},
            {"жалюзи", "жалюзИ", "жАлюзи"},
            {"нанял", "нАнял", "нанЯл"},
            {"свекла", "свЕкла", "свеклА"},
            {"договор", "договОр", "дОговор"},
            {"мусоропровод", "мусоропровОд", "мусоропрОвод"},
            {"звонит", "звонИт", "звОнит"},
            {"включит", "включИт", "вклЮчит"},
            {"солишь", "сОлишь", "солИшь"},
            {"облегчить", "облегчИть", "облЕгчить"},
            {"начать", "начАть", "нАчать"},
            {"торты", "тОрты", "тортЫ"},
            {"банты", "бАнты", "бантЫ"},
            {"перезвонишь", "перезвонИшь", "перезвОнишь"},
            {"балованный", "балОванный", "бАлованный"},
            {"взяла", "взялА", "взЯла"},
            {"граффити", "граффИти", "грАффити"},
            {"включишь", "включИшь", "вклЮчишь"},
            {"проклясть", "проклЯсть", "прОклясть"},
            {"гербовая", "гЕрбовая", "гербОвая"},
            {"забронировать (номер)", "забронИровать", "забронировАть"},
            {"проклятый", "прОклятый", "проклЯтый"},
            {"исчерпать", "исчЕрпать", "исчерпАть"},
            {"кровоточить", "кровоточИть", "кровотОчить"},
            {"красивее", "красИвее", "красивЕе"},
            {"мозаичный", "мозаИчный", "мозАичный"},
            {"предвосхитить", "предвосхИтить", "предвосхитИть"},
            {"начал", "нАчал", "начАл"},
            {"начала", "началА", "нАчала"},
            {"плесневеть", "плЕсневеть", "плесневЕть"},
            {"оптовый", "оптОвый", "Оптовый"},
            {"откупорить", "откУпорить", "откупОрить"},
            {"слыли", "слЫли", "слылИ"},
            {"ходатайство", "ходАтайство", "ходатАйство"},
            {"осведомиться", "освЕдомиться", "осведомИться"},
            {"опрометью", "Опрометью", "опромЕтью"},
            {"отрочество", "Отрочество", "отрОчество"},
            {"развитая", "развитАя", "развИтая"},
            {"поутру (утром)", "поутрУ", "пОутру"},
            {"сливовый", "слИвовый", "сливОвый"},
            {"уведомить", "увЕдомить", "уведомИть"},
            {"черпать", "чЕрпать", "черпАть"},
            {"шарфы", "шАрфы", "шарфЫ"},
            {"экспорт", "Экспорт", "экспОрт"},
            {"яслей", "Яслей", "яслЕй"},
            {"обеспечение", "обеспЕчение", "обеспечЕние"},
            {"иконопись", "Иконопись", "икОнопись"},
            {"духовник", "духовнИк", "духОвник"},
            {"знамение", "знАмение", "знамЕние"},
            {"зубчатый", "зубчАтый", "зУбчатый"},
            {"искра", "Искра", "искрА"},
            {"принудить", "принУдить", "принудИть"},
            {"апостроф", "апострОф", "апОстроф"},
            {"безудержный", "безУдержный", "безудЕржный"},
            {"вторгнуться", "втОргнуться", "вторгнУться"},
            {"каучук", "каучУк", "кАучук"},
            {"кедровый", "кедрОвый", "кЕдровый"},
            {"лоскут", "лОскут", "лоскУт"},
            {"слыла", "слылА", "слЫла"},
            {"избаловать", "избаловАть", "избАловать"},
            {"созыв", "созЫв", "сОзыв"},
            {"щавель", "щавЕль", "щАвель"},
            {"дала", "далА", "дАла"},
            {"взяло", "взЯло", "взялО"},
            {"уведомленный", "увЕдомленный", "уведомлЕнный"},
            {"звала", "звалА", "звАла"},
            {"молода", "молодА", "мОлода"},
            {"лила", "лилА", "лИла"},
            {"танцовщица", "танцОвщица", "танцовщИца"},
            {"начаты", "нАчаты", "начАты"},
            {"созвонимся", "созвонИмся", "созвОнимся"},
            {"углубить", "углубИть", "углУбить"},
            {"склады", "склАды", "складЫ"},
            {"борты", "бОрты", "бортЫ"},
            {"тексты", "тЕксты", "текстЫ"},
            {"средства", "срЕдства", "средствА"},
            {"просверлить", "просверлИть", "просвЕрлить"},
            {"похороны", "пОхороны", "похорОны"},
            {"ходатайствовать", "ходАтайствовать", "ходатАйствовать"},
            {"принять", "принЯть", "прИнять"},
            {"подаришь", "подАришь", "подарИшь"},
            {"давнишний", "давнИшний", "дАвнишний"},
            {"еретик", "еретИк", "ерЕтик"},
            {"ельник", "Ельник", "ельнИк"},
            {"аналог", "анАлог", "аналОг"},
            {"газированный", "газирОванный", "газИрованный"},
            {"водопровод", "водопровОд", "водопрОвод"},
            {"газопровод", "газопровОд", "газопрОвод"},
            {"забронировать (броней)", "забронировАть", "забронИровать"},
            {"донельзя", "донЕльзя", "дОнельзя"},
            {"туфли", "тУфля", "туфлЯ"},
            {"жерло", "жерлО", "жЕрло"},
            {"умершим", "умЕршим", "Умершим"},
            {"сторона", "сторонА", "стОрона"},
            {"плюсы", "плЮсы", "плюсЫ"},
            {"умно", "умнО", "Умно"},
            {"бинты", "бинтЫ", "бИнты"},
            {"сколькими", "скОлькими", "сколькИми"} // 99
    };

    public static int[] last = new int[25]; /* массив для хранения слов, которые уже попадались
                                             у пользователя и дальнейшего предовращения их повторения
                                             в emp сцене (последние 25 слов не будут повторяться) */
    public static int lasts = 0;


    int trueBtn;     // переменная для хранения правильной кнопки в epm сцене
    public static final StatsClass empStats = new StatsClass();

    @FXML void initialize() {
        System.out.println("Checking button load: " + (btnEmp1 != null ? "OK" : "NULL"));

        //установление статистики в label

        if (cEmpLabel != null) {
            cEmpLabel.setText(String.valueOf(empStats.correctAns));
        }
        if (uncEmpLabel != null) {
            uncEmpLabel.setText(String.valueOf(empStats.uncorrectAns));
        }
        if (empPercent != null) {
            empPercent.setText(String.valueOf(empStats.ratio));
            // установка цвета статистики по его значению
            if (empStats.ratio > 50f && empStats.ratio < 60f || empStats.ratio == 50f) {
                empPercent.setTextFill(Color.web("#14c317"));
            } else if (empStats.ratio < 30f || empStats.ratio == 30f) {
                empPercent.setTextFill(Color.web("#ff0000"));
            } else if (empStats.ratio > 60f && empStats.ratio < 70f || empStats.ratio == 60f) {
                empPercent.setTextFill(Color.web("#00aaff"));
            } else if (empStats.ratio > 70f || empStats.ratio == 70f) {
                empPercent.setTextFill(Color.web("#8800ff"));
            } else if (empStats.ratio < 50f && empStats.ratio > 40f || empStats.ratio == 40f) {
                empPercent.setTextFill(Color.web("#ffffff"));
            }
        }
        if (allEmpLabel != null) {
            allEmpLabel.setText(String.valueOf(empStats.allAns));
        }
        // проверка кнопок на нулл
        if (btnEmp1 != null && btnEmp2 != null && wordLabel != null) {
            randomEpm();
        }
    }


    public void randomEpm(){
        if (btnEmp1 == null || btnEmp2 == null || wordLabel == null) {
            System.err.println("Error: elements were not loaded.");
            return;
        }
        //генерация рандомного слова и правильной кнопки (одной из двух)
        trueBtn = ThreadLocalRandom.current().nextInt(1, 3);
        int resRandom = ThreadLocalRandom.current().nextInt(1, 100);
        System.out.println("Word element = " + resRandom);
        System.out.println("Correct button = " + trueBtn);
        String nowWord = words[resRandom-1][0];

        if (lasts >= 25) {lasts = 0;}

        // проверка на повторения
        if ((      resRandom != last[0]
                && resRandom != last[1]
                && resRandom != last[2]
                && resRandom != last[3]
                && resRandom != last[4]
                && resRandom != last[5]
                && resRandom != last[6]
                && resRandom != last[7]
                && resRandom != last[8]
                && resRandom != last[9]
                && resRandom != last[10]
                && resRandom != last[11]
                && resRandom != last[12]
                && resRandom != last[13]
                && resRandom != last[14]
                && resRandom != last[15]
                && resRandom != last[16]
                && resRandom != last[17]
                && resRandom != last[18]
                && resRandom != last[19]
                && resRandom != last[20]
                && resRandom != last[21]
                && resRandom != last[22]
                && resRandom != last[23]
                && resRandom != last[24]) || lasts == 0){
            // установка текста в кнопки и надписи
            wordLabel.setText(words[resRandom-1][0]);
            btnEmp1.setText(trueBtn == 1 ? words[resRandom-1][1] : words[resRandom-1][2]);
            btnEmp2.setText(trueBtn == 2 ? words[resRandom-1][1] : words[resRandom-1][2]);

            last[lasts] = resRandom;
            lasts += 1;
        } else {
            System.out.println("Random again");
            randomEpm();
            // если условие не сработало, значит слово повторялось в последних 25 словах и генерация будет проведена заново
        }
    }

    @FXML void quit(ActionEvent event) {
        Platform.exit();
    }

    @FXML void aboutApp(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        showXScene(currentStage, "aboutApp.fxml");
    }

    /*
    нажатие на две кнопки в emp сцене, включая проверку правильного ответа, перехода с emp сцены на correctEmpScene и
    uncorrectEmpScene, изменение переменных со статистикой и ее обновление
    */
    @FXML void btnEmp1Clck(ActionEvent event) throws IOException {
        boolean res = trueBtn == 1;

        if (res) {
            System.out.println("Correct");
            empStats.changeStats(1, "correctAns");
            empStats.changeStats(1, "allAns");
        } else {
            System.out.println("Uncorrect");
            empStats.changeStats(1, "uncorrectAns");
            empStats.changeStats(1, "allAns");
        }

        empStats.updateStats();

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        String resultScene = res ? "correctEmpScene.fxml" : "uncorrectEmpScene.fxml";
        showXScene(currentStage, resultScene);

        new Thread(() -> {
            try {
                Thread.sleep(500);

                Platform.runLater(() -> {
                    try {
                        Parent exerciseRoot = FXMLLoader.load(getClass().getResource("EmpScene.fxml"));

                        cEmpLabel = (Label) exerciseRoot.lookup("#cEmpLabel");
                        uncEmpLabel = (Label) exerciseRoot.lookup("#uncEmpLabel");
                        empPercent = (Label) exerciseRoot.lookup("#empPercent");

                        if (cEmpLabel != null) cEmpLabel.setText(String.valueOf(empStats.correctAns));
                        if (uncEmpLabel != null) uncEmpLabel.setText(String.valueOf(empStats.uncorrectAns));
                        if (empPercent != null) empPercent.setText(String.valueOf(empStats.ratio));

                        Scene exerciseScene = new Scene(exerciseRoot);
                        currentStage.setScene(exerciseScene);
                        currentStage.show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
        empStats.updateStats();
    }

    @FXML void btnEmp2Clck(ActionEvent event) throws IOException {
        boolean res = trueBtn == 2;

        if (res) {
            System.out.println("Correct");
            empStats.changeStats(1, "correctAns");
            empStats.changeStats(1, "allAns");
        } else {
            System.out.println("Uncorrect");
            empStats.changeStats(1, "uncorrectAns");
            empStats.changeStats(1, "allAns");
        }
        System.out.printf("correct : %d, uncorrect : %d, allAns : %d%n", empStats.correctAns, empStats.uncorrectAns, empStats.allAns);

        empStats.updateStats();

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        String resultScene = res ? "correctEmpScene.fxml" : "uncorrectEmpScene.fxml";
        showXScene(currentStage, resultScene);

        new Thread(() -> {
            try {
                Thread.sleep(500);

                Platform.runLater(() -> {
                    try {
                        Parent exerciseRoot = FXMLLoader.load(getClass().getResource("EmpScene.fxml"));

                        cEmpLabel = (Label) exerciseRoot.lookup("#cEmpLabel");
                        uncEmpLabel = (Label) exerciseRoot.lookup("#uncEmpLabel");
                        empPercent = (Label) exerciseRoot.lookup("#empPercent");

                        if (cEmpLabel != null) cEmpLabel.setText(String.valueOf(empStats.correctAns));
                        if (uncEmpLabel != null) uncEmpLabel.setText(String.valueOf(empStats.uncorrectAns));
                        if (empPercent != null) empPercent.setText(String.valueOf(empStats.ratio));

                        Scene exerciseScene = new Scene(exerciseRoot);
                        currentStage.setScene(exerciseScene);
                        currentStage.show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
        empStats.updateStats();
    }

    // метод для возвращения обротно в меню, включающий главную сцену
    @FXML
    public void backToMenu(ActionEvent event) throws IOException {
        Stage nowStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        showXScene(nowStage, "russianScene.fxml");
    }

    // метод для перемещения в emp сцену, для обновления статистики
    @FXML public void empSceneRun(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("EmpScene.fxml")));
        currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        currentStage.setScene(scene);
        if (root != null) {
            // ищем Label по их fx:id в загруженной сцене
            cEmpLabel = (Label) root.lookup("#cEmpLabel");
            uncEmpLabel = (Label) root.lookup("#uncEmpLabel");
            empPercent = (Label) root.lookup("#empPercent");

            if (cEmpLabel != null && uncEmpLabel != null && empPercent != null) {
                cEmpLabel.setText(String.valueOf(empStats.correctAns));
                uncEmpLabel.setText(String.valueOf(empStats.uncorrectAns));
                empPercent.setText(String.valueOf(empStats.ratio));
            }
        }
        currentStage.show();
        }

    public void showXScene(Stage currentStage, String fxmlName) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlName)));
        Scene scene = new Scene(root);
        currentStage.setScene(scene);
        currentStage.show();
    }
}