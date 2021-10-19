package com.mimuw.amwms.evolution.input;

import com.mimuw.amwms.evolution.exceptions.*;

import com.mimuw.amwms.evolution.resources.Plant;
import com.mimuw.amwms.evolution.resources.Resource;
import com.mimuw.amwms.evolution.tools.Move;
import com.mimuw.amwms.evolution.tools.Program;
import com.mimuw.amwms.evolution.world.BlankSpace;
import com.mimuw.amwms.evolution.world.FoodSpace;
import com.mimuw.amwms.evolution.world.Space;
import com.mimuw.amwms.evolution.world.World;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
//TODO EXCEPTION TRY CATCH CHECK - systemm.err.print

public class Reader {

//    public class WrongType extends Exception {
//        public WrongType(String message) {
//            super(message);
//        }
//    }

//    public class WrongNumberOfArguments extends Exception {
//        public WrongNumberOfArguments(String message) {
//            super(message);
//        }
//    }

//    public class NonExistingParameter extends Exception {
//        public NonExistingParameter(String message) {
//            super(message);
//        }
//    }

    private final int NUMBER_OF_PARAMETERS = 15;

    private Space[][] board;
    private String stringListOfInstructions;
    private String stringBeginProgram;


    private ArrayList<String> inputVerses = new ArrayList<>();
    private ArrayList<String> namesOfParsedParameters  = new ArrayList<>();

    // parses whole file "parametry.txt"
    public void parseInputParameters(DataCreator data, File file) {
        try {
            readInput(file);
            checkInputAndParse(data);
        }
        catch (ProgramException e) {
            e.printMessage();
            System.exit(1);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void readInput(File file) throws FileNotFoundException {
        Scanner scan  = new Scanner(file);

        while (scan.hasNextLine()) {
            inputVerses.add(scan.nextLine());
        }
    }

    private void checkInputAndParse(DataCreator data)
            throws WrongNumberOfArguments, WrongType, NonExistingParameter, OutOfRange, InvalidMove {
        if (inputVerses.size() != NUMBER_OF_PARAMETERS)
            throw new WrongNumberOfArguments("WRONG NUMBER OF PARAMETERS");

        for (String verse : inputVerses) {
            String [] dividedVerse = verse.split(" ");

            // exception when list of instructions or begin program are blank
            if (dividedVerse.length == 1) {
                if (dividedVerse[0].equals("spis_instr")) {
                    stringListOfInstructions = "";
                    namesOfParsedParameters.add("spis_instr");
                    continue;
                }
                if (dividedVerse[0].equals("pocz_progr")) {
                    stringBeginProgram = "";
                    namesOfParsedParameters.add("pocz_progr");
                    continue;
                }
            }

            // checking if there is only one parameter and its value in verse
            if (dividedVerse.length != 2)
                throw new WrongNumberOfArguments("TOO MANY PARAMETERS IN ONE VERSE");

            parseOneParameter(dividedVerse[0], dividedVerse[1], data);
        }

        data.setBeginProgram(new Program(stringBeginProgram));
        data.setListOfInstructions(convertInstructions(stringListOfInstructions));

        if (!noParameterDoubling())
            throw new WrongNumberOfArguments("DOUBLED PARAMETERS IN INPUT");
    }

    private void parseOneParameter(String nameOfParameter, String value, DataCreator data)
            throws NonExistingParameter, WrongType, OutOfRange {
        Scanner scan = new Scanner(value);
        namesOfParsedParameters.add(nameOfParameter);

        switch (nameOfParameter) {
                case "ile_tur":
                    if (!scan.hasNextInt())
                        throw new WrongType("ILE_TUR");
//                    numberOfDays = scan.nextInt();
                    data.setNumberOfDays(scan.nextInt());
                    break;
                case "koszt_tury":
                    if (!scan.hasNextInt())
                        throw new WrongType("KOSZT_TURY");
//                    dailyPayment = scan.nextInt();
                    data.setDailyPayment(scan.nextInt());
                    break;
                case "spis_instr":
                    if (!scan.hasNext())
                        throw new WrongType("SPIS_INSTR");
                    stringListOfInstructions = scan.next();
                    break;
                case "co_ile_wypisz":
                    if (!scan.hasNextInt())
                        throw new WrongType("CO_ILE_WYPISZ");
//                    reportFrequency = scan.nextInt();
                    data.setReportFrequency(scan.nextInt());
                    break;
                case "pocz_ile_robów":
                    if (!scan.hasNextInt())
                        throw new WrongType("POCZ_ILE_ROBÓW");
//                    beginNumberOfRobs = scan.nextInt();
                    data.setBeginNumberOfRobs(scan.nextInt());
                    break;
                case "pocz_progr":
                    if (!scan.hasNext())
                        throw new WrongType("POCZ_PROGR");
                    stringBeginProgram = scan.next();
                    break;
                case "pocz_energia":
                    if (!scan.hasNextInt())
                        throw new WrongType("POCZ_ENERGIA");
//                    beginEnergyOfRobs = scan.nextInt();
                    data.setBeginEnergyOfRobs(scan.nextInt());
                    break;
                case "ile_daje_jedzenie":
                    if (!scan.hasNextInt())
                        throw new WrongType("ILE_DAJE_JEDZENIE");
//                    energyFromFood = scan.nextInt();
                    data.setEnergyFromFood(scan.nextInt());
                    break;
                case "ile_rośnie_jedzenie":
                    if (!scan.hasNextInt())
                        throw new WrongType("ILE_ROśNIE_JEDZENIE");
//                    foodGrowthTime = scan.nextInt();
                    data.setFoodGrowthTime(scan.nextInt());
                    break;
                case "pr_powielenia":
                    if (!scan.hasNextFloat())
                        throw new WrongType("PR_POWIELENIA");
                    float probabilityOfCloning = scan.nextFloat();
                    if (probabilityOfCloning <= 1.0 && probabilityOfCloning >= 0.0)
                        data.setProbabilityOfReproducing(probabilityOfCloning);
                    else
                        throw new OutOfRange("PR_POWIELENIA");
                    break;
                case "ułamek_energii_rodzica":
                    if (!scan.hasNextFloat())
                        throw new WrongType("UłAMEK_ENERGII_RODZICA");
                    float fractionOfParentEnergy = scan.nextFloat();

                    if (fractionOfParentEnergy <= 1.0 && fractionOfParentEnergy >= 0.0)
                        data.setFractionOfParentEnergy(fractionOfParentEnergy);
                    else
                        throw new OutOfRange("UłAMEK_ENERGII_RODZICA");
                    break;
                case "limit_powielania":
                    if (!scan.hasNextInt())
                        throw new WrongType("LIMIT_POWIELANIA");
//                    minLimitForCloning = scan.nextInt();
                    data.setMinLimitForReproducing(scan.nextInt());
                    break;
                case "pr_usunięcia_instr":
                    if (!scan.hasNextFloat())
                        throw new WrongType("PR_USUNIĘCIA_INSTR");
                    float probabilityOfDeletion = scan.nextFloat();

                    if (probabilityOfDeletion <= 1.0 && probabilityOfDeletion >= 0.0)
                        data.setProbabilityOfDeletion(probabilityOfDeletion);
                    else
                        throw new OutOfRange("PR_USUNIĘCIA_INSTR");
                    break;
                case "pr_dodania_instr":
                    if (!scan.hasNextFloat())
                        throw new WrongType("PR_DODANIA_INSTR");
                    float probabilityOfAddition = scan.nextFloat();

                    if (probabilityOfAddition <= 1.0 && probabilityOfAddition >= 0.0)
                        data.setProbabilityOfAddition(probabilityOfAddition);
                    else
                        throw new OutOfRange("PR_DODANIA_INSTR");
                    break;
                case "pr_zmiany_instr":
                    if (!scan.hasNextFloat())
                        throw new WrongType("PR_ZAMIANY_INSTR");
                    float probabilityOfChange = scan.nextFloat();

                    if (probabilityOfChange <= 1.0 && probabilityOfChange >= 0.0)
                        data.setProbabilityOfChange(probabilityOfChange);
                    else
                        throw new OutOfRange("PR_ZAMIANY_INSTR");
                    break;
                default:
                    throw new NonExistingParameter(nameOfParameter);
            }
    }

    private ArrayList<Move> convertInstructions(String s) throws InvalidMove {
        ArrayList<Move> instructions = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            instructions.add(Move.stringToMove(s.charAt(i)));
        }

        return instructions;
    }

    private boolean noParameterDoubling() {
//        System.out.println(namesOfParsedParameters);
        return namesOfParsedParameters.stream().filter(s -> s.equals("ile_tur")).count() == 1L
                && namesOfParsedParameters.stream().filter(s -> s.equals("koszt_tury")).count() == 1L
                && namesOfParsedParameters.stream().filter(s -> s.equals("spis_instr")).count() == 1L
                && namesOfParsedParameters.stream().filter(s -> s.equals("co_ile_wypisz")).count() == 1L
                && namesOfParsedParameters.stream().filter(s -> s.equals("pocz_ile_robów")).count() == 1L
                && namesOfParsedParameters.stream().filter(s -> s.equals("pocz_progr")).count() == 1L
                && namesOfParsedParameters.stream().filter(s -> s.equals("pocz_energia")).count() == 1L
                && namesOfParsedParameters.stream().filter(s -> s.equals("ile_daje_jedzenie")).count() == 1L
                && namesOfParsedParameters.stream().filter(s -> s.equals("ile_rośnie_jedzenie")).count() == 1L
                && namesOfParsedParameters.stream().filter(s -> s.equals("pr_powielenia")).count() == 1L
                && namesOfParsedParameters.stream().filter(s -> s.equals("ułamek_energii_rodzica")).count() == 1L
                && namesOfParsedParameters.stream().filter(s -> s.equals("limit_powielania")).count() == 1L
                && namesOfParsedParameters.stream().filter(s -> s.equals("pr_usunięcia_instr")).count() == 1L
                && namesOfParsedParameters.stream().filter(s -> s.equals("pr_dodania_instr")).count() == 1L
                && namesOfParsedParameters.stream().filter(s -> s.equals("pr_zmiany_instr")).count() == 1L;
    }

    // parse world map
    public void readWorld(DataCreator data, File file) {
        try {
            Scanner scanWorld = new Scanner(file);
            ArrayList<String> verses = new ArrayList<>();

            while (scanWorld.hasNextLine()) {
                verses.add(scanWorld.nextLine());
            }
            scanWorld.close();

            int height = verses.size();
            int width = verses.get(0).length();

            System.out.println(height);
            System.out.println(width);


            if (width == 0 || height == 0)
                throw new InvalidWorldBoardInput("EMPTY WORLD BOARD");

            board = new Space[width][height];


            for (int y = 0; y < height; y++) {
                String verse = verses.get(y);

                if (verse.length() < width)
                    throw new WrongInputVerseLength("SHORT");
                if (verse.length() > width)
                    throw new WrongInputVerseLength("LONG");

                for (int x = 0; x < width; x++) {
                    if (verse.charAt(x) == 'x') {
                        Resource food = new Plant(data.getEnergyFromFood(), data.getFoodGrowthTime());
                        board[x][y] = new FoodSpace(food, x, y);
                    } else
                        board[x][y] = new BlankSpace(x, y);
                }
            }

        }
        catch (InvalidWorldBoardInput | WrongInputVerseLength e) {
            e.printMessage();
            System.exit(1);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        data.setWorld(new World(board));
    }

}
