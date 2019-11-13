package ehu.isad.db;

import ehu.isad.model.StudentsModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentsKud {

    // Singleton patroia
    private static StudentsKud instantzia = new StudentsKud();

    public static StudentsKud getInstantzia() {
        return instantzia;
    }

    private StudentsKud () {

    }

    public List<StudentsModel> lortuIkasleak() {
        List<StudentsModel> emaitza = new ArrayList<>();

        DBKudeatzaile dbKud = DBKudeatzaile.getInstantzia();
        String query = "SELECT studentId, firstName, lastName, imagePath FROM studentsModel";
        ResultSet rs = dbKud.execSQL(query);

        try {
            while (rs.next()) {
                Integer id = rs.getInt("studentId");
                String fName = rs.getString("firstName");
                String lName = rs.getString("lastName");
                String iPath = rs.getString("imagePath");

                emaitza.add(new StudentsModel(id, fName, lName, iPath));
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return emaitza;
    }
}
