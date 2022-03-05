package de.androidcrypto.recyclerviewstoreloadobjects;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class GenerateDataset extends AppCompatActivity {

    Button isinDelete, yearDelete, generateDataset, printDataset;
    Button saveDataset, loadDataset;
    EditText stockIsin, entryYear;

    String stockMovementsFilename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_dataset);

        isinDelete = findViewById(R.id.btnSDIYIsinDelete);
        yearDelete = findViewById(R.id.btnSDIYYearDelete);
        generateDataset = findViewById(R.id.btnSDIYGenerate);
        printDataset = findViewById(R.id.btnSDIYPrint);
        saveDataset = findViewById(R.id.btnSDIYSave);
        loadDataset = findViewById(R.id.btnSDIYLoad);

        stockIsin = findViewById(R.id.etSDIYStockIsin);
        entryYear = findViewById(R.id.etSDIYYear);

        ArrayList<StockMovementsModal> bookingModelArrayList = new ArrayList<>();

        isinDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stockIsin.setText("");
            }
        });

        yearDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yearDelete.setText("");
            }
        });

        generateDataset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("*** generate new datasets in the database ***");
                Editable isin = stockIsin.getText();
                Editable year = entryYear.getText();
                System.out.println("for ISIN: " + isin.toString() + " for year: " + year);

                ArrayList<String> daysInYearWithoutWeenends = getListOfDaysWithoutWeekends(year.toString());
                System.out.println("arraylist generated with entries: " + daysInYearWithoutWeenends.size());
/*
    public StockMovementsModal(String date, String dateUnix,
                               String stockName, String stockIsin,
                               String direction, String amountEuro,
                               String numberShares, String bank,
                               String securitiesAccount,
                               String note, String totalNumberShares,
                               String totalPurchaseCosts,
                               String dataYear, String dataMonth,
                               String active) {
 */

                // now store each date
                for (int i = 0; i < daysInYearWithoutWeenends.size(); i++) {
                    String date = daysInYearWithoutWeenends.get(i);
                    StockMovementsModal bookingModel = new StockMovementsModal(
                            date, "", "stockname", isin.toString(),
                            "", "", "", "",
                            "", "", "", "",
                            year.toString(), "", "true");
                    bookingModelArrayList.add(bookingModel);
                }

                System.out.println("datasets created:" + bookingModelArrayList.size());

            }
        });

        printDataset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("*** generated dataset ***");
                int datasetSize = bookingModelArrayList.size();
                System.out.println("total datasets: " + datasetSize);
                for (int i = 0; i < datasetSize; i++) {
                    System.out.println("i: " + i +
                            " date: " + bookingModelArrayList.get(i).getDate() +
                            " isin: " + bookingModelArrayList.get(i).getStockIsin());
                }
                System.out.println("++ printout completed ++");
            }
        });

        saveDataset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("*** save datasets to file ***");
                Editable year = entryYear.getText();
                stockMovementsFilename = "movements";

                // store in year directories, not directly in files
                File baseDir = new File(getFilesDir(), year.toString());
                if (!baseDir.exists()) {
                    baseDir.mkdirs();
                }
                String dataFilename = stockMovementsFilename + "_" +
                        year + ".dat";
                String dataFilenameComplete = baseDir + File.separator + dataFilename;
                System.out.println("data will be saved in " + dataFilenameComplete);
                FileOutputStream fout= null;
                try {
                    fout = new FileOutputStream(dataFilenameComplete);
                    ObjectOutputStream oos = new ObjectOutputStream(fout);
                    oos.writeObject(bookingModelArrayList);
                    fout.close();
                    System.out.println("data saved");
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("ERROR: data NOT saved");
                }
            }
        });

        loadDataset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("*** load datasets from file ***");
                Editable year = entryYear.getText();
                stockMovementsFilename = "movements";

                // store in year directories, not directly in files
                File baseDir = new File(getFilesDir(), year.toString());
                if (!baseDir.exists()) {
                    System.out.println("*** ERROR no directory found ***");
                    return;
                }
                String dataFilename = stockMovementsFilename + "_" +
                        year + ".dat";
                String dataFilenameComplete = baseDir + File.separator + dataFilename;
                System.out.println("data will be loaded from " + dataFilenameComplete);
                File loadFile = new File(dataFilenameComplete);
                if (!loadFile.exists()) {
                    System.out.println("*** ERROR no data file found ***");
                    return;
                }

                ArrayList<StockMovementsModal> bookingModelArrayListLoad = new ArrayList<>();
                FileInputStream fin= null;
                try {
                    fin = new FileInputStream(dataFilenameComplete);
                    ObjectInputStream ois = new ObjectInputStream(fin);
                    bookingModelArrayListLoad = (ArrayList<StockMovementsModal>)ois.readObject();
                    fin.close();
                } catch (FileNotFoundException | ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("data loaded");
                int datasetSize = bookingModelArrayListLoad.size();
                System.out.println("total datasets: " + datasetSize);
                for (int i = 0; i < datasetSize; i++) {
                    System.out.println("i: " + i +
                            " date: " + bookingModelArrayListLoad.get(i).getDate() +
                            " isin: " + bookingModelArrayListLoad.get(i).getStockIsin());
                }
                System.out.println("++ printout completed ++");

            }
        });

    }

    // this function returns an arraylist of String with all days in a given year in format yyyy-mm-dd
    // excluded are the weekends = saturday and sunday BUT included are
    // 01.01. and 31.12. regardless if they are weekend days
    // this is to force that each table of days starts with 01.01.xxxx and ends with 31.12.xxxx
    private ArrayList<String> getListOfDaysWithoutWeekends(String year) {
        String s =  year + "-01-01"; // e.g. 2022-01-01
        String e = year + "-12-31"; // e.g. 2022-12-31
        LocalDate start = LocalDate.parse(s);
        LocalDate end = LocalDate.parse(e);
        List<LocalDate> totalDates = new ArrayList<>();
        ArrayList<String> totalDatesWorkdays = new ArrayList<>();
        while (!start.isAfter(end)) {
            totalDates.add(start);
            start = start.plusDays(1);
        }
        //System.out.println("** complete list **");
        //System.out.println(Arrays.deepToString(totalDates.toArray()));
        // now remove the weekends weekends
        //for (int counter = 0; counter < totalDates.size(); counter++) { // checks for all days
        totalDatesWorkdays.add(totalDates.get(0).toString()); // 01.01.
        for (int counter = 1; counter < (totalDates.size() - 1); counter++) { // checks NOT for 01.01. and 31.12.
            LocalDate date = totalDates.get(counter);
            if (date.getDayOfWeek().getValue() != 6 & date.getDayOfWeek().getValue() != 7) {
                totalDatesWorkdays.add(date.toString());
            }
        }
        totalDatesWorkdays.add(totalDates.get(totalDates.size()-1).toString()); // 31.12.
        return totalDatesWorkdays;
    }
}