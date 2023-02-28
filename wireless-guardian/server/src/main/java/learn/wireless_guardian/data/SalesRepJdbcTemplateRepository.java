package learn.wireless_guardian.data;

import learn.wireless_guardian.data.mappers.BusinessMapper;
import learn.wireless_guardian.data.mappers.SalesRepMapper;
import learn.wireless_guardian.models.Business;
import learn.wireless_guardian.models.SalesRep;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class SalesRepJdbcTemplateRepository implements SalesRepRepository{

    private final JdbcTemplate jdbcTemplate;

    public SalesRepJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<SalesRep> findAll() {
        final String sql = "select " +
                "sales_rep_id, " +
                "first_name, " +
                "last_name, " +
                "city, " +
                "state, " +
                "reseller_org_id " +
                "from sales_rep limit 1000;";
        return jdbcTemplate.query(sql, new SalesRepMapper());
    }

    @Override
    public SalesRep findById(int salesRepId) {

        final String sql = "select * "
                + "from sales_rep "
                + "where sales_rep_id = ?;";

        SalesRep salesRep = jdbcTemplate.query(sql, new SalesRepMapper(), salesRepId).stream()
                .findAny().orElse(null);

        return salesRep;
    }

    @Override
    public SalesRep add(SalesRep salesRep) {
        final String sql = "insert into sales_rep (first_name, last_name, city, state, reseller_org_id) values (?,?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, salesRep.getFirstName());
            ps.setString(2, salesRep.getLastName());
            ps.setString(3, salesRep.getCity());
            ps.setString(4, salesRep.getState());
            ps.setInt(5, salesRep.getResellerOrgId());

            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        salesRep.setSalesRepId(keyHolder.getKey().intValue());
        return salesRep;
    }

    @Override
    public boolean update(SalesRep salesRep) {

        final String sql = "update sales_rep set "
                + "first_name = ?, "
                + "last_name = ?, "
                + "city = ?, "
                + "state = ?, "
                + "reseller_org_id = ? "
                + "where sales_rep_id = ?;";

        return jdbcTemplate.update(sql,
                salesRep.getFirstName(),
                salesRep.getLastName(),
                salesRep.getCity(),
                salesRep.getState(),
                salesRep.getResellerOrgId(),
                salesRep.getSalesRepId()) > 0;
    }

    @Override
    public boolean deleteById(int salesRepId) {
        return jdbcTemplate.update("delete from sales_rep where sales_rep_id = ?", salesRepId) > 0;
    }
}
