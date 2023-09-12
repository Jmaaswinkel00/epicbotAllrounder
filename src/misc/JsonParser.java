package misc;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import model.CombatEquipment;
import model.SkillingEquipment;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonParser {
    private Gson gson;
    public JsonParser() {
        this.gson = new Gson();
    }

    public void readCombatEquipmentJson() {
        try {
            FileReader fileReader = new FileReader("data/CombatEquipment.json");
            CombatEquipment combatEquipment = gson.fromJson(fileReader, CombatEquipment.class);

        } catch (FileNotFoundException ignored) {

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
