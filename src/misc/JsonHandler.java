package misc;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import model.CombatEquipment;
import model.SkillingEquipment;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonHandler {
    private Gson gson;
    public JsonHandler() {
        this.gson = new Gson();
    }

    //this is not functional for now (just testing different methods)
    public void readCombatEquipmentJson() {
        try ( FileReader fileReader = new FileReader("data/CombatEquipment.json")){
            JsonParser jsonParser = new JsonParser();
            JsonElement json = jsonParser.parse(fileReader);
            JsonElement melee = json.getAsJsonObject().get("melee");


            CombatEquipment combatEquipment = gson.fromJson(melee.getAsJsonObject().get("1"), CombatEquipment.class);

            System.out.println(combatEquipment.getMainWeapon());
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readSkillingEquipmentJson() {
        try {
            FileReader fileReader = new FileReader("data/SkillingEquipment.json");
            SkillingEquipment skillingEquipment = gson.fromJson(fileReader, SkillingEquipment.class);

        } catch (FileNotFoundException ignored) {

        }
    }
}
