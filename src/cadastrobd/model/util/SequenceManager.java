/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd.model.util;

import cadastrobd.model.util.ConectorBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SequenceManager {
    public int getValue(String sequenceName){
        ConectorBD cb = new ConectorBD();
        int nextValue = -1;
        try {
            ResultSet rs = cb.getSelect("select NEXT VALUE for " + sequenceName);
            if (rs.next()) {
                nextValue = rs.getInt(1);
            }
            ConectorBD.close(rs);
            ConectorBD.close(cb.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nextValue;
    }
}
