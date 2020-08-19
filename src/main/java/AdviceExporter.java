import database.Slip;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//| slipId = 5 | id = 10 | advice = advice |
public class AdviceExporter {
    private final String dirPath = PropertiesManager.getProperty("dirPath");

    public void exportToFile(List<Slip> allAdvice) {
        try {
            PrintWriter zapis = new PrintWriter(dirPath + "cytaty.txt");
            for (Slip slips : allAdvice) {
                zapis.println("| slipId = " + slips.getSlipId() + " | id = " + slips.getId() + " | advice = " + slips.getAdvice());
            }
            zapis.close();
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku!");
        }
    }

    //eksport naszych porad do eksela
    public void exportToSheet(List<Slip> allAdvice) {
        Workbook workbook = new XSSFWorkbook(); //utworzenie workbooka (xlsx)

        Sheet sheet = workbook.createSheet("My favourite advices"); //utworzenie arkusza

        //nagłówki tabeli
        String[] columns = {"slipId", "id", "advice"};
        createHeaderRow(columns, sheet);

        //wypełnienie danymi
        createDataRows(sheet, allAdvice);

        //resize column tak żeby pasowały do zawartosci
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        //zapis do pliku
        saveWorkbookToFile(workbook);
    }

    private void createHeaderRow(String[] columns, Sheet sheet) {
        Row headerRow = sheet.createRow(0);

        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }
    }

    private void createDataRows(Sheet sheet, List<Slip> slips) {
        int rowNumber = 1;

        for (Slip slip : slips) {
            Row row = sheet.createRow(rowNumber++);
            row.createCell(0).setCellValue(slip.getSlipId());
            row.createCell(1).setCellValue(slip.getId());
            row.createCell(2).setCellValue(slip.getAdvice());
        }
    }

    private void saveWorkbookToFile(Workbook workbook) {
        //zapis do pliku!
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(dirPath + "cytaty.xlsx");
            workbook.write(fileOutputStream);

            fileOutputStream.close();
            workbook.close();
        } catch (IOException e) {
            System.out.println("Nie udało się weksportować cytatów!");
        }
    }
}
