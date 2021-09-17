package com.example.laba;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MainRestController {

    @GetMapping("/allData")
    public List<Kurs> allData() {
        List<Kurs> kursListOutput = new ArrayList<>();

        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("src/main/resources/files/data.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray objects = (JSONArray) obj;
            System.out.println(objects);

            int j = 0;
            for (int i = 1992; i <= 2021; i++) {
                JSONObject oneStartObj = (JSONObject)(objects.get(j++));
                JSONObject yearObject = (JSONObject) oneStartObj.get(Integer.toString(i));
                String usd = (String)yearObject.get("USD");
                String eur = (String)yearObject.get("EUR");
                String rub = (String)yearObject.get("RUB");
                kursListOutput.add(new Kurs(Integer.toString(i),
                        Double.parseDouble(usd),
                        Double.parseDouble(eur),
                        Double.parseDouble(rub)));
            }

            return kursListOutput;
        } catch (IOException | ParseException e) {
            System.out.println("Error!");
            return kursListOutput;
        }
    }
}
