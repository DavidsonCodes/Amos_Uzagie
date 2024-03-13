package com.Davidson.EncryptionSystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class Developer implements Developers{
    File file = new File("project.txt");
    public ResultSet loadDevelopers(){
        ResultSet resultSet = null;
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/developer", "root", "ihvadream");
             Statement statement = connection.createStatement();
             BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String createTable = "CREATE TABLE IF NOT EXISTS developers(name Text(20), age Integer, location Text(20), skill Text(20))";
            statement.execute(createTable);

            String line;
            while ((line = reader.readLine()) != null){
                //Calling a method to split developer information on the current line and assigning it to variables
                String[] developerData = splitLine(line);
                String name = developerData[0].trim();
                int age = Integer.parseInt(developerData[1]);
                String location = developerData[2].trim();
                String skill = developerData[3].trim();

                //Statement that inserts the current line data into the table
                String dataEntry = String.format("INSERT INTO developers(name, age, location, skill) VALUES('%s', %d, '%s', '%s')", name, age, location,skill);
                statement.execute(dataEntry);
            }

            //Statement to fetch all data from the table.
            resultSet = statement.executeQuery("SELECT * FROM developers");
            printTable(resultSet);



        } catch (SQLException | IOException e){
            System.out.println(e.getMessage());
        }
        return resultSet;
    }

    public String[] splitLine(String line){
        String[] currentLine = line.split(", ");
        String s = currentLine[currentLine.length-1];
        currentLine[currentLine.length-1] = s.substring(0, s.length()-1);
        return currentLine;
    }

    public void printTable(ResultSet resultSet) {
        try {
            System.out.println("Name \t\t\tAge \tLocation \tSkill");
            System.out.println();
            while (resultSet.next()) {
                String name = resultSet.getString(1);
                int age = resultSet.getInt(2);
                String location = resultSet.getString(3);
                String skill = resultSet.getString(4);
                System.out.println(name + "\t\t" + age + "\t\t" + location + "\t\t" + skill);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Developer developer = new Developer();
        try (ResultSet resultSet = developer.loadDevelopers()) {
            //developer.printTable(resultSet);
            System.out.println();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
