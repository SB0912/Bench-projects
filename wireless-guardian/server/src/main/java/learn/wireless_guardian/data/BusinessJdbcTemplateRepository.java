package learn.wireless_guardian.data;

import learn.wireless_guardian.data.mappers.BusinessMapper;
import learn.wireless_guardian.data.mappers.SiteMapper;
import learn.wireless_guardian.models.Business;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class BusinessJdbcTemplateRepository implements BusinessRepository{

    private final JdbcTemplate jdbcTemplate;

    public BusinessJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Business> findAll() {
        final String sql = "select " +
                "business_id, " +
                "business_name, " +
                "address, " +
                "city, " +
                "state, " +
                "zip_code, " +
                "contact_email, " +
                "contact_phone " +
                "from business limit 1000;";
        return jdbcTemplate.query(sql, new BusinessMapper());
    }

    @Override
    public Business findById(int businessId) {

        final String sql = "select * "
                + "from business "
                + "where business_id = ?;";

        Business business = jdbcTemplate.query(sql, new BusinessMapper(), businessId).stream()
                .findAny().orElse(null);

        if (business != null) {
            addSites(business);
        }

        return business;
    }

    @Override
    public Business add(Business business) {

        final String sql = "insert into business (business_name, address, city, state, zip_code, contact_email, contact_phone) values (?,?,?,?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, business.getBusinessName());
            ps.setString(2, business.getAddress());
            ps.setString(3, business.getCity());
            ps.setString(4, business.getState());
            ps.setInt(5, business.getZipCode());
            ps.setString(6, business.getContactEmail());
            ps.setString(7, business.getContactPhone());

            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        business.setBusinessId(keyHolder.getKey().intValue());
        return business;
    }

    @Override
    public boolean update(Business business) {

        final String sql = "update business set "
                + "business_name = ?, "
                + "address = ?, "
                + "city = ?, "
                + "state = ?, "
                + "zip_code = ?, "
                + "contact_email = ?, "
                + "contact_phone = ? "
                + "where business_id = ?;";

        return jdbcTemplate.update(sql,
                business.getBusinessName(),
                business.getAddress(),
                business.getCity(),
                business.getState(),
                business.getZipCode(),
                business.getContactEmail(),
                business.getContactPhone(),
                business.getBusinessId()) > 0;
    }

    @Override
    public boolean deleteById(int businessId) {
        jdbcTemplate.update("delete from site where business_id = ?", businessId);
        return jdbcTemplate.update("delete from business where business_id = ?", businessId) > 0;

    }

    private void addSites(Business business) {

        final String sql = "Select " +
                "site_id, " +
                "b.business_id, " +
                "ro.reseller_org_id, " +
                "latitude, " +
                "longitude, " +
                "services_sold, " +
                "revenue, " +
                "multiple_sites " +
                "FROM site s " +
                "inner join business b on s.business_id = b.business_id " +
                "inner join reseller_org ro on s.reseller_org_id = ro.reseller_org_id " +
                "where s.business_id = ?;";

        var sitesBusinesses = jdbcTemplate.query(sql, new SiteMapper(), business.getBusinessId());
        business.setSites(sitesBusinesses);
    }
}
