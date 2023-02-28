package learn.wireless_guardian.data.mappers;

import learn.wireless_guardian.models.ResellerOrg;
import learn.wireless_guardian.models.SalesRep;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SalesRepMapper implements RowMapper<SalesRep> {

    @Override
    public SalesRep mapRow(ResultSet resultSet, int i) throws SQLException {
        SalesRep salesRep = new SalesRep();
        salesRep.setSalesRepId(resultSet.getInt("sales_rep_id"));
        salesRep.setFirstName(resultSet.getString("first_name"));
        salesRep.setLastName(resultSet.getString("last_name"));
        salesRep.setCity(resultSet.getString("city"));
        salesRep.setState(resultSet.getString("state"));
        salesRep.setResellerOrgId(resultSet.getInt("reseller_org_id"));
        return salesRep;
    }
}
