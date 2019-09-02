package javaapplicationfilemanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class JavaApplicationFileManager {
    
    public static void main(String[] args) {
        
        Scanner scaner = new Scanner(System.in);
        String answer;
        String path = "";
        String destination = "";
        String directory = "";
        String oldName = "";
        String newName = "";
        System.out.println(" File Manager :LIST, INFO, CREATE_DIRECTORY, RENAME_DIRECORY, COPY, MOVE, DELETE");
        System.out.println("\nLIST enter: LIST");
        System.out.println("INFO enter: INFO");
        System.out.println("CREATE_DIRECTORY enter: CREATE");
        System.out.println("RENAME_DIRECTORY enter: RENAME");
        System.out.println("COPY enter: COPY");
        System.out.println("MOVE enter: MOVE");
        System.out.println("DELETE_DIRECTORY enter:DELETE \n  Enter here :");
        
        answer = scaner.next();
        
        switch (answer) {
            
            case "LIST":
                try (BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in))) {
                    System.out.println("Enter file path: ");
                    path = bufferRead.readLine();
                    
                } catch (IOException e) {
                    System.out.println(e);
                }
                
                File aFile = new File(path);
                
                if (aFile.exists() && aFile.isDirectory()) {
                    
                    String[] elements = aFile.list();
                    for (String element : elements) {
                        System.out.println(element);
                    }
                    
                }
                
                break;
            
            case "INFO":
                try (BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in))) {
                    System.out.println("Enter file path: ");
                    path = bufferRead.readLine();
                } catch (IOException e) {
                    System.out.println(e);
                }
                
                File bFile = new File(path);
                
                if (bFile.exists()) {
                    System.out.println(bFile.getName());
                    System.out.println(bFile.getAbsolutePath());
                    System.out.println(bFile.length() + " B");
                    
                    Instant instantLastModified = Instant.ofEpochMilli(bFile.lastModified());
                    LocalDateTime dateTimeLastModified = LocalDateTime.ofInstant(instantLastModified, ZoneId.systemDefault());
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd. MMMM yyyy. HH:mm:ss");
                    System.out.println(dateTimeLastModified.format(dateTimeFormatter));
                }
                break;
            
            case "CREATE":
                File testDirectory;
                try (BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in))) {
                    System.out.println("Enter directory name :");
                    directory = bufferRead.readLine();
                    
                    testDirectory = new File(directory);
                    
                    if (!testDirectory.exists()) {
                        testDirectory.mkdir();
                        System.out.println("Created a directory " + testDirectory.getName());
                    } else {
                        System.out.println(testDirectory.getName() + " already exists.");
                    }
                    
                } catch (Exception e) {
                    System.out.println();
                    
                }
                
                break;
            
            case "RENAME":
                try (BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in))) {
                    System.out.println("Enter old file path and name: ");
                    oldName = bufferRead.readLine();
                    
                    System.out.println("Enter new file path and name: ");
                    newName = bufferRead.readLine();
                    
                    File dFile = new File(oldName);
                    File eFile = new File(newName);
                    
                    if (!dFile.exists()) {
                        System.out.println("The old file doesn't exists.");
                    }
                    
                    if (eFile.exists()) {
                        System.out.println("New file name already exists.");
                    }
                    if(dFile.renameTo(eFile)){
                        System.out.println("Renamed.");
                    }
                    
                } catch (IOException e) {
                    System.out.println(e);
                }
                
                break;
            
            case "COPY":
                try (BufferedReader bufRead = new BufferedReader(new InputStreamReader(System.in))) {
                    System.out.println("Enter path of file you want to copy: ");
                    oldName = bufRead.readLine();
                    
                    System.out.println("Enter path and name of copied file: ");
                    newName = bufRead.readLine();
                    
                } catch (IOException e) {
                    System.out.println(e);
                }
                
                File orFile = new File(oldName);
                File coFile = new File(newName);
                
                if (orFile.exists()) {
                    try (FileInputStream inStream = new FileInputStream(orFile);
                            FileOutputStream outStream = new FileOutputStream(coFile);) {
                        byte[] buffer = new byte[1024];
                        int length;
                        
                        while ((length = inStream.read(buffer)) > 0) {
                            outStream.write(buffer, 0, length);
                        }
                        
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                }
                
                break;
            
            case "MOVE":
                try (BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in))) {
                    System.out.println("Enter file path: ");
                    path = bufferRead.readLine();
                    
                    System.out.println("Enter destination folder: ");
                    destination = bufferRead.readLine();
                } catch (IOException e) {
                    System.out.println(e);
                }
                
                File ifile = new File(path);
                File yfile = new File(destination + ifile.getName());
                
                try (FileInputStream inStream = new FileInputStream(ifile);
                        FileOutputStream outStream = new FileOutputStream(yfile)) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = inStream.read(buffer)) > 0) {
                        outStream.write(buffer, 0, length);
                    }
                    
                    inStream.close();
                    outStream.close();
                    
                    ifile.delete();
                } catch (IOException exc) {
                    System.out.println(exc);
                    
                }
                
                break;
            
            case "DELETE":
                try (BufferedReader buffRead = new BufferedReader(new InputStreamReader(System.in))) {
                    System.out.println("Enter the path and name of file you want to delete: ");
                    directory = buffRead.readLine();
                    
                    File dF = new File(directory);
                    
                    if (dF.exists()) {
                        
                        dF.delete();
                        System.out.println("File Deleted!");
                    } else {
                        System.out.println("File doesn't exists.");
                    }
                    
                } catch (IOException e) {
                    System.out.println(e);
                }
                
                break;
            
            default:
                
                System.out.println("Error!");
            
        }
        
    }
    
}
