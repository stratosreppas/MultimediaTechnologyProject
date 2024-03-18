package com.example.librarygui.interfaces;
import java.io.*;

public interface FileIO {

    static void writeSingleString(String filename, String content) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write("writing to the file\n");
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void writeMultipleStrings(String filename, String[] content) {
        try {
            DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(filename));
            for (String line : content) {
                byte[] bytes = line.getBytes(); // Get bytes of the string
                outputStream.writeInt(bytes.length); // Write the length of the byte array
                outputStream.write(bytes); // Write the bytes to the stream
            }
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String readSingleString(String filename) {
        try {
            filename = filename;
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            reader.close();
            return line;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    static String[] readMultipleStrings(String filename) {
        try {
            DataInputStream inputStream = new DataInputStream(new FileInputStream(filename));
            StringBuilder outputBuilder = new StringBuilder();
            while (inputStream.available() > 0) {
                int length = inputStream.readInt(); // Read the length of the byte array
                byte[] bytes = new byte[length]; // Create a byte array of appropriate length
                inputStream.readFully(bytes); // Read the bytes
                String line = new String(bytes); // Convert bytes to string
                outputBuilder.append(line).append("\n"); // Append the string to output
            }
            inputStream.close();
            String output = outputBuilder.toString().trim(); // Trim the trailing newline
            return output.split("\n"); // Split the output by newline to get an array of strings
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    static boolean exists_file(String filename) {
        try {
            filename = filename;
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            reader.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    static void delete_file(String filename) {
        try {
            filename = filename;
            File file = new java.io.File(filename);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void format_directory(String path) {
        try {
            File directory = new File(path);
                // Delete all files and subdirectories recursively
                File[] contents = directory.listFiles();
                System.out.println(contents.toString());
                if (contents != null) {
                    for (File f : contents) {
                        delete_file(f.getPath());
                        System.out.println(f.getPath());
                    }
                }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void delete_line(String filename, String line) {
        try {
            filename = filename;
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String output = "";
            String currentLine;
            while((currentLine = reader.readLine()) != null) {
                if(currentLine.equals(line)) continue;
                output += currentLine + "\n";
            }
            reader.close();
            writeSingleString(filename, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
