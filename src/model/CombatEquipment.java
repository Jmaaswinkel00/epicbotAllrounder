package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CombatEquipment {

    private String mainWeapon;
    private String offHand;
    private String helmet;
    private String chest;
    private String legs;
    private String boots;
    private String gloves;
    private String cloak;
    private String ring;
    private String amulet;
    private String quiver;
    public CombatEquipment(String mainWeapon, String offHand, String helmet, String chest, String legs, String boots, String gloves, String cloak, String ring, String amulet, String quiver) {
        this.mainWeapon = mainWeapon;
        this.offHand = offHand;
        this.helmet = helmet;
        this.chest = chest;
        this.legs = legs;
        this.boots = boots;
        this.gloves = gloves;
        this.cloak = cloak;
        this.ring = ring;
        this.amulet = amulet;
        this.quiver = quiver;
    }
}
