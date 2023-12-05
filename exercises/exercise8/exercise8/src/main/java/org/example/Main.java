package org.example;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;



public class Main {
    public static void main(String[] args) {
        //Parse XML file
        parseXML("sampleFiles/sampleXML.xml");

        //Parse JSON
        parseJSON("sampleFiles/sampleJSON.json");

        //Add a new Book to XML file
        addBookToXML("sampleFiles/sampleXML.xml");

        //Parse XML file
        parseXML("sampleFiles/sampleXML.xml");

        //Add a new Book to JSON
        addBookToJSON("sampleFiles/sampleJSON.json");

        //Parse JSON
        parseJSON("sampleFiles/sampleJSON.json");

    }
    private static void parseXML(String xmlFilePath) {
        try {
            //Read file
            File inputFile = new File(xmlFilePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            // Print XML
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("Book");
            System.out.println("----------------------------");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("Title: " + eElement.getElementsByTagName("title").item(0).getTextContent());
                    System.out.println("Published Year: " + eElement.getElementsByTagName("publishedYear").item(0).getTextContent());
                    System.out.println("Number of Pages: " + eElement.getElementsByTagName("numberOfPages").item(0).getTextContent());

                    NodeList authors = eElement.getElementsByTagName("author");
                    System.out.print("Authors: ");
                    for (int i = 0; i < authors.getLength(); i++) {
                        System.out.print(authors.item(i).getTextContent() + " ");
                    }
                    System.out.println("\n----------------------");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void addBookToXML(String xmlFilePath) {
        try {
            //Read file
            File inputFile = new File(xmlFilePath);
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(inputFile);

            //Create a new Book element
            Element newBook = doc.createElement("Book");
            Element title = doc.createElement("title");
            title.appendChild(doc.createTextNode("New Book"));
            Element publishedYear = doc.createElement("publishedYear");
            publishedYear.appendChild(doc.createTextNode("2023"));
            Element numberOfPages = doc.createElement("numberOfPages");
            numberOfPages.appendChild(doc.createTextNode("250"));
            Element authors = doc.createElement("authors");
            Element author = doc.createElement("author");
            author.appendChild(doc.createTextNode("New Author"));
            authors.appendChild(author);

            newBook.appendChild(title);
            newBook.appendChild(publishedYear);
            newBook.appendChild(numberOfPages);
            newBook.appendChild(authors);

            //Append the new Book element to the root
            Node bookShelf = doc.getElementsByTagName("BookShelf").item(0);
            bookShelf.appendChild(newBook);

            //Save the file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(xmlFilePath));
            transformer.transform(source, result);

            System.out.println("A new book added to XML successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void parseJSON(String jsonFilePath) {
        try {
            // Read json file
            String jsonContent = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(jsonContent);
            JSONObject jsonObject = (JSONObject)obj;
            JSONObject bookShelf = (JSONObject) jsonObject.get("BookShelf");
            JSONArray booksArray = (JSONArray) bookShelf.get("Book");
            // Print file
            System.out.println("Parsed JSON:");
            for (Object bookObj : booksArray) {
                JSONObject book = (JSONObject) bookObj;
                System.out.println("Title: " + book.get("title"));
                System.out.println("Published Year: " + book.get("publishedYear"));
                System.out.println("Number of Pages: " + book.get("numberOfPages"));

                JSONArray authors = (JSONArray) book.get("authors");
                System.out.print("Authors: ");
                for (Object author : authors) {
                    System.out.print(author + " ");
                }
                System.out.println("\n----------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addBookToJSON(String jsonFilePath) {
        try {
            //Read file
            String jsonContent = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(jsonContent);
            JSONObject jsonObject = (JSONObject)obj;
            JSONObject bookShelf = (JSONObject) jsonObject.get("BookShelf");
            JSONArray booksArray = (JSONArray) bookShelf.get("Book");

            //Add a new book to JSON file
            JSONObject newBook = new JSONObject();
            newBook.put("title", "A New Book");
            newBook.put("publishedYear", 2023);
            newBook.put("numberOfPages", 250);

            JSONArray newAuthors = new JSONArray();
            newAuthors.add("First New Author");
            newAuthors.add("Second New Author");
            newBook.put("authors", newAuthors);

            booksArray.add(newBook);

            // Save the file
            FileWriter writer = new FileWriter(jsonFilePath);
            writer.write(((JSONObject) obj).toJSONString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}