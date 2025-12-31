package com.automationexercise.utils;

//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//import java.io.FileReader;
//import java.io.IOException;
//
//public class JSONReader {
//
//    public static String existingUser(String data) throws IOException, ParseException {
//        JSONParser jsonParser = new JSONParser();
//        FileReader fileReader = new FileReader("src\\test\\resources\\testData\\ExistingUser.json");
//        Object obj = jsonParser.parse(fileReader);
//        JSONObject existingUser = (JSONObject) obj;
//        return (String)existingUser.get(data);
//    }
//
//    public static String accountDetails(String data) throws IOException, ParseException {
//        JSONParser jsonParser = new JSONParser();
//        FileReader fileReader = new FileReader("src\\test\\resources\\testData\\AccountDetails.json");
//        Object obj = jsonParser.parse(fileReader);
//        JSONObject accountDetails = (JSONObject) obj;
//        return (String)accountDetails.get(data);
//    }
//
//    public static String paymentDetails(String data) throws IOException, ParseException {
//        JSONParser jsonParser = new JSONParser();
//        FileReader fileReader = new FileReader("src\\test\\resources\\testData\\PaymentDetails.json");
//        Object obj = jsonParser.parse(fileReader);
//        JSONObject paymentDetails = (JSONObject) obj;
//        return (String)paymentDetails.get(data);
//    }
//
//    public static String poloBrandProducts(String data) throws IOException, ParseException {
//        JSONParser jsonParser = new JSONParser();
//        FileReader fileReader = new FileReader("src\\test\\resources\\testData\\PoloBrandProducts.json");
//        Object obj = jsonParser.parse(fileReader);
//        JSONObject poloBrandProducts = (JSONObject) obj;
//        return (String)poloBrandProducts.get(data);
//    }
//
//    public static String madameBrandProducts(String data) throws IOException, ParseException {
//        JSONParser jsonParser = new JSONParser();
//        FileReader fileReader = new FileReader("src\\test\\resources\\testData\\MadameBrandProducts.json");
//        Object obj = jsonParser.parse(fileReader);
//        JSONObject madameBrandProducts = (JSONObject) obj;
//        return (String)madameBrandProducts.get(data);
//    }
//}


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

public class JSONReader {

    private static JSONObject loadJson(String resourcePath) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        InputStream inputStream = JSONReader.class.getClassLoader().getResourceAsStream(resourcePath);

        if (inputStream == null) {
            throw new IOException("Resource not found: " + resourcePath);
        }

        return (JSONObject) jsonParser.parse(new InputStreamReader(inputStream));
    }

    public static String existingUser(String data) throws IOException, ParseException {
        JSONObject existingUser = loadJson("testData/ExistingUser.json");
        return (String) existingUser.get(data);
    }

    public static String accountDetails(String data) throws IOException, ParseException {
        JSONObject accountDetails = loadJson("testData/AccountDetails.json");
        return (String) accountDetails.get(data);
    }

    public static String paymentDetails(String data) throws IOException, ParseException {
        JSONObject paymentDetails = loadJson("testData/PaymentDetails.json");
        return (String) paymentDetails.get(data);
    }

    public static String poloBrandProducts(String data) throws IOException, ParseException {
        JSONObject poloBrandProducts = loadJson("testData/PoloBrandProducts.json");
        return (String) poloBrandProducts.get(data);
    }

    public static String madameBrandProducts(String data) throws IOException, ParseException {
        JSONObject madameBrandProducts = loadJson("testData/MadameBrandProducts.json");
        return (String) madameBrandProducts.get(data);
    }
}
