package AutomationExercise.Utilities;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jayway.jsonpath.JsonPath;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Properties;

public class DataUtils {
    //TODO: Assign paths to be used
    public final static String CONFIG_PATH = "src/test/resources/config/";
    public final static String TEST_DATA_PATH = "src/test/resources/test-data/";
    public final static String ENVIRONMENT_PATH = "src/test/resources/test-data/environment.properties";


    private DataUtils() {}

    //TODO: read any field from the same json file
    public static String getJsonValue(String jsonFileName,String field){
        try {
            FileReader reader = new FileReader(TEST_DATA_PATH + jsonFileName + ".json");
            Object jsonData = new Gson().fromJson(reader, Object.class);
            return JsonPath.read(jsonData, "$." + field);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "exception1";
    }

    //TODO: read data from json file
    public static String getJsonData(String jsonFileName,String field){
        try{
            FileReader reader = new FileReader(TEST_DATA_PATH + jsonFileName + ".json");
            //parse json directly into a json element
            JsonElement jsonElement = JsonParser.parseReader(reader);

            return jsonElement.getAsJsonObject().get(field).getAsString();

        }catch (Exception e){
            e.printStackTrace();
        }
        return "exception2";
    }

    //TODO: read data from excel sheet
    public static String getExcelData(String excelFilename, String sheetName, int rowNum, int colNum){
        XSSFWorkbook workBook;
        XSSFSheet sheet;
        String cellData;

        try{
            workBook = new XSSFWorkbook(TEST_DATA_PATH + excelFilename);
            sheet = workBook.getSheet(sheetName);
            cellData = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
            return cellData;
        }catch (Exception e){
            e.printStackTrace();
            return "exception3";
        }
    }


    //TODO:get properties from .properties file
    public static String getEnvironmentProperty(String key){
        try{
            Properties prop = new Properties();
            prop.load(new FileInputStream(ENVIRONMENT_PATH));
            return prop.getProperty(key);
        }catch (Exception e){
            e.printStackTrace();
            return "exception4";
        }
    }


    //TODO: get properties from any .Property file
    public static String getPropertyValue(String fileName , String key){
        try{
            Properties prop = new Properties();
            prop.load(new FileInputStream(TEST_DATA_PATH + fileName + ".properties"));
            return prop.getProperty(key);
        }catch (Exception e){
            e.printStackTrace();
            return "exception5";
        }
    }


    //TODO: get Config from any .properties file
    public static String getConfigValue(String fileName, String key) {
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream(CONFIG_PATH + fileName + ".properties"));
            return prop.getProperty(key);
        } catch (Exception e) {
            return "exception6";
        }
    }


}
