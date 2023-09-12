package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillingEquipment {

    private String equipment;
    private String bait;
    public SkillingEquipment(String equipment, String bait){
        this.equipment = equipment;
        this.bait = bait;
    }

}
