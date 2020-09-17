import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class makeBooking {

    private String booking = " ";
    private String learnersRadioBtn;
    private String driversRadioBtn;
    private Connection connection;

    makeBooking(Connection connection){
        this.connection = connection;
    }

public void setRadioBtn(){
    JRadioButton learnersRadioBtn = new JRadioButton();
    JRadioButton driversRadioBtn = new JRadioButton();

    ButtonGroup G = new ButtonGroup();

    this.learnersRadioBtn = null;
    this.driversRadioBtn = null;

    G.add(learnersRadioBtn);
    G.add(driversRadioBtn);

    if(learnersRadioBtn.isSelected()){
    booking = "Learners Licence";
    } else if(driversRadioBtn.isSelected()){
        booking = "Drivers Licence";
    }else{
        booking = "NO Button selected";
    }
}

public String getRadioBtn(){
    return booking;
}

    public List<String> makeBooking() throws SQLException {
        List<String> departments = new ArrayList<>();
        PreparedStatement get_users = connection.prepareStatement("select * from person");
        ResultSet resultSet = get_users.executeQuery();
        while (resultSet.next()) {
            departments.add(resultSet.getString("first_name"));
        }
        System.out.println(departments);
        return departments;
    }
}
