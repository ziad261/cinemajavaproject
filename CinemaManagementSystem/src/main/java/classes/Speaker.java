/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.io.Serializable;

/**
 *
 * @author alielrogbany
 */
public class Speaker implements Serializable {
    public static final long serialVersionUID = 6529685098267757690L;
    
    private String speakerType;

    public Speaker(String speakerType) {
        this.speakerType = speakerType;
    }

    public String getSpeakerType() {
        return speakerType;
    }

    public void setSpeakerType(String speakerType) {
        this.speakerType = speakerType;
    }
}
