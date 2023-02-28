package learn.wireless_guardian.data;

import learn.wireless_guardian.data.mappers.ResellerOrgMapper;
import learn.wireless_guardian.data.mappers.SalesRepMapper;
import learn.wireless_guardian.data.mappers.SiteMapper;
import learn.wireless_guardian.models.ResellerOrg;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class ResellerOrgJdbcTemplate implements ResellerOrgRepository{

    private final JdbcTemplate jdbcTemplate;

    public ResellerOrgJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<ResellerOrg> findAll() {
        final String sql = "select " +
                "reseller_org_id, " +
                "name, " +
                "parent_org_id, " +
                "child_org_id " +
                "from reseller_org limit 1000;";
        return jdbcTemplate.query(sql, new ResellerOrgMapper());
    }

    @Override
    public ResellerOrg findById(int resellerOrgId) {

        final String sql = "select * "
                + "from reseller_org "
                + "where reseller_org_id = ?;";

        ResellerOrg resellerOrg = jdbcTemplate.query(sql, new ResellerOrgMapper(), resellerOrgId).stream()
                .findAny().orElse(null);

        if (resellerOrg != null) {
            addSalesReps(resellerOrg);
            addSites(resellerOrg);
        }

        return resellerOrg;
    }

    @Override
    public ResellerOrg add(ResellerOrg resellerOrg) {

        final String sql = "insert into reseller_org (name, parent_org_id, child_org_id) values (?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, resellerOrg.getName());
            ps.setInt(2, resellerOrg.getParentOrgId());
            ps.setInt(3, resellerOrg.getChildOrgId());

            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        resellerOrg.setResellerOrgId(keyHolder.getKey().intValue());
        return resellerOrg;
    }

    @Override
    public boolean update(ResellerOrg resellerOrg) {
        final String sql = "update reseller_org set "
                + "name = ?, "
                + "parent_org_id = ?, "
                + "child_org_id = ? "
                + "where reseller_org_id = ?;";

        return jdbcTemplate.update(sql,
                resellerOrg.getName(),
                resellerOrg.getParentOrgId(),
                resellerOrg.getChildOrgId(),
                resellerOrg.getResellerOrgId()) > 0;
    }

    @Override
    public boolean deleteById(int resellerOrgId) {
        jdbcTemplate.update("delete from sales_rep where reseller_org_id = ?", resellerOrgId);
        jdbcTemplate.update("delete from site where reseller_org_id = ?", resellerOrgId);
        return jdbcTemplate.update("delete from reseller_org where reseller_org_id = ?", resellerOrgId) > 0;
    }

    private void addSalesReps(ResellerOrg resellerOrg) {

        final String sql = "Select " +
                "sales_rep_id, " +
                "first_name, " +
                "last_name, " +
                "city, " +
                "state, " +
                "ro.reseller_org_id, " +
                "ro.name, " +
                "ro.parent_org_id, " +
                "ro.child_org_id " +
                "from sales_rep sr " +
                "inner join reseller_org ro on sr.reseller_org_id = ro.reseller_org_id " +
                "where ro.reseller_org_id = ?;";

        var salesRepsResellerOrg = jdbcTemplate. query(sql, new SalesRepMapper(), resellerOrg.getResellerOrgId());
        resellerOrg.setSalesReps(salesRepsResellerOrg);
    }

    private void addSites(ResellerOrg resellerOrg) {

        final String sql = "Select " +
                "site_id, " +
                "business_id, " +
                "latitude, " +
                "longitude, " +
                "services_sold, " +
                "revenue, " +
                "multiple_sites, " +
                "ro.reseller_org_id, " +
                "ro.name, " +
                "ro.parent_org_id, " +
                "ro.child_org_id " +
                "from site st " +
                "inner join reseller_org ro on st.reseller_org_id = ro.reseller_org_id " +
                "where ro.reseller_org_id = ?;";

        var sitesResellerOrg = jdbcTemplate. query(sql, new SiteMapper(), resellerOrg.getResellerOrgId());
        resellerOrg.setSites(sitesResellerOrg);
    }
}
