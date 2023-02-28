package learn.wireless_guardian.data.mappers;

import learn.wireless_guardian.models.Business;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BusinessMapper implements RowMapper<Business> {

    @Override
    public Business mapRow(ResultSet resultSet, int i) throws SQLException {
        Business business = new Business();
        business.setBusinessId(resultSet.getInt("business_id"));
        business.setBusinessName(resultSet.getString("business_name"));
        business.setAddress(resultSet.getString("address"));
        business.setCity(resultSet.getString("city"));
        business.setState(resultSet.getString("state"));
        business.setZipCode(resultSet.getInt("zip_code"));
        business.setContactEmail(resultSet.getString("contact_email"));
        business.setContactPhone(resultSet.getString("contact_phone"));
        return business;
    }
}
