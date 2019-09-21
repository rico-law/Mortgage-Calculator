package com.learningpackage;
import java.lang.Math;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
Simple Program that calculates mortgage to get basics of java
    Input:
        Asks for Loan amount
        Annual Interest rate
        Years
    Output:
        Mortgage
*/

public class Main {

    enum InputType {
        LOAN,
        RATE,
        YEARS
    }

    enum ErrorType {
        NOTNUM,
        NOTPOSITIVE
    }

    // Prints errors given type of error and for which parameter
    public static void printError(InputType inputType, ErrorType errorType) {
        if (errorType == ErrorType.NOTPOSITIVE) {
            System.out.print("Cannot pass in a negative parameter for ");
        }
        else {
            System.out.print("Cannot pass in a non-number parameter for ");
        }

        if (inputType == InputType.LOAN) {
            System.out.println("loans!");
        }
        else if (inputType == InputType.RATE) {
            System.out.println("rate!");
        }
        else {
            System.out.println("years!");
        }
    }

    // Takes input from user for different values asked for and checks it
    // Returns -1, if user input does not meet requirements
    public static double takeInput(InputType inputType) {
        // Grab user input
        Scanner input = new Scanner(System.in);
        double numReadFromUser = 0;
        try {
            numReadFromUser = input.nextDouble();
        }
        catch (InputMismatchException e) {
            printError(inputType, ErrorType.NOTNUM);
            return -1;
        }

        // Make sure the input from user is not negative
        if (numReadFromUser < 0 ) {
            printError(inputType, ErrorType.NOTPOSITIVE);
            return -1;
        }
        return numReadFromUser;
    }

    public static void main(String[] args) {
        // Constants
        final byte monthsInYear = 12;
        final byte percent = 100;

        // Loan Amount
        System.out.print("Type in your loan amount: ");
        double loan = takeInput(InputType.LOAN);
        if (loan == -1) {
            return;
        }

        // Interest Rate
        System.out.print("Annual Interest Rate: ");
        double rate = takeInput(InputType.RATE);
        if (rate == -1) {
            return;
        }
        double ratePerMonth = rate / percent / monthsInYear;

        // Years the loan will be out for
        System.out.print("Years: ");
        double years = takeInput(InputType.YEARS);
        if (years == -1) {
            return;
        }
        double numPayments = years * monthsInYear;

        // Calculate Mortgage
        double mortgage = loan * ratePerMonth * Math.pow(1 + ratePerMonth, numPayments) /
                (Math.pow(1 + ratePerMonth, numPayments) - 1);

        // Make sure dollar amount only has 2 decimal places
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        System.out.println("Mortgage: $" +  df.format(mortgage));
    }
}
