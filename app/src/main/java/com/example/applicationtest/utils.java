package com.example.applicationtest;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public abstract class utils {

    public static void writeTofile(String text, String filename, AppCompatActivity app) {

        try {
            File testFile = new File(app.getFilesDir(), filename);
            if (!testFile.exists())
                testFile.createNewFile();

            // Adds a line to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(testFile, false/*append*/));
            writer.write(text);
            writer.close();
        } catch (IOException e) {
            Log.e("ReadWriteFile", "Unable to write to the TestFile.txt file.");
        }
    }

    public static String ReadFromfile(String filename, AppCompatActivity app) {
        File testFile = new File(app.getFilesDir(), filename);
        if (testFile != null) {
            StringBuilder stringBuilder = new StringBuilder();
            // Reads the data from the file
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(testFile));
                String line;

                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }
                reader.close();
            } catch (Exception e) {
                Log.e("ReadWriteFile", "Unable to read the TestFile.txt file.");
            }
            return stringBuilder.toString();

        }
        return null;
    }


    public static String ReadFromDataFile(String name, Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(name + ".json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

    public static String prettyfier(int value) {
        StringBuilder s = new StringBuilder();
        if (value < 1000) {
            s.append(value);
            return s.toString();

        }
        value /= 1000;
        if (value < 1000) {
            s.append(value + " K");
            return s.toString();

        }

        value /= 1000;
        if (value < 1000) {
            s.append(value + " m");
            return s.toString();

        }
        value /= 1000;
        if (value < 1000) {
            s.append(value + " M");
            return s.toString();

        }
        value /= 1000;
        if (value < 1000) {
            s.append(value + " B");
            return s.toString();

        }
        value /= 1000;
        if (value < 1000) {
            s.append(value + " T");
            return s.toString();

        }
        value /= 1000;
        if (value < 1000) {
            s.append(value + " Qa");
            return s.toString();

        }
        value /= 1000;
        if (value < 1000) {
            s.append(value + " Q");
            return s.toString();

        }
        value /= 1000;
        if (value < 1000) {
            s.append(value + " Sx");
            return s.toString();
        }

        value /= 1000;
        if (value < 1000) {
            s.append(value + " St");
            return s.toString();
        }

        return s.toString();
    }


    public static String prettyfier(double value) {
        StringBuilder s = new StringBuilder();
        if (value < 1000) {
            s.append(String.format("%.2f", value));
            return s.toString();

        }
        value /= 1000;
        if (value < 1000) {
            s.append(String.format("%.2f", value) + " K");
            return s.toString();

        }

        value /= 1000;
        if (value < 1000) {
            s.append(String.format("%.2f", value) + " m");
            return s.toString();

        }
        value /= 1000;
        if (value < 1000) {
            s.append(String.format("%.2f", value) + " M");
            return s.toString();

        }
        value /= 1000;
        if (value < 1000) {
            s.append(String.format("%.2f", value) + " B");
            return s.toString();

        }
        value /= 1000;
        if (value < 1000) {
            s.append(String.format("%.2f", value) + " T");
            return s.toString();

        }
        value /= 1000;
        if (value < 1000) {
            s.append(String.format("%.2f", value) + " Qa");
            return s.toString();

        }
        value /= 1000;
        if (value < 1000) {
            s.append(String.format("%.2f", value) + " Q");
            return s.toString();

        }
        value /= 1000;
        if (value < 1000) {
            s.append(String.format("%.2f", value) + " Sx");
            return s.toString();
        }

        value /= 1000;
        if (value < 1000) {
            s.append(String.format("%.2f", value) + " St");
            return s.toString();
        }

        return s.toString();
    }


}
