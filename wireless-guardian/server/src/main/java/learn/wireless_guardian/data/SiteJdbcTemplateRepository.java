package learn.wireless_guardian.data;

import learn.wireless_guardian.data.mappers.SalesRepMapper;
import learn.wireless_guardian.data.mappers.SiteMapper;
import learn.wireless_guardian.models.SalesRep;
import learn.wireless_guardian.models.Site;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.function.Predicate;

@Repository
public class SiteJdbcTemplateRepository implements SiteRepository{

    private final JdbcTemplate jdbcTemplate;

    public SiteJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Site> findAll() {
        final String sql = """
        select *
        from site
        limit 1000;
    """;
        return jdbcTemplate.query(sql, new SiteMapper());
    }

    @Override
    public Site findById(int siteId) {
        final String sql = "select * "
                + "from site "
                + "where site_id = ?;";

        return jdbcTemplate.query(sql, new SiteMapper(), siteId).stream()
                .findAny().orElse(null);
    }

    @Override
    @Transactional
    public List<Site> findByBusinessId(int businessId) {
        final String sql = "select * "
                + "from site "
                + "where business_id = ?;";

        return jdbcTemplate.query(sql, new SiteMapper(), businessId);
    }

    @Override
    @Transactional
    public List<Site> findByResellerOrgId(int resellerOrgId) {
        final String sql = "select * "
                + "from site "
                + "where reseller_org_id = ?;";

        return jdbcTemplate.query(sql, new SiteMapper(), resellerOrgId);
    }

    @Override
    public Site add(Site site) {
        final String sql = "insert into site (" +
                "business_id, " +
                "reseller_org_id, " +
                "latitude, " +
                "longitude, " +
                "services_sold, " +
                "revenue, " +
                "multiple_sites) " +
                "values (?,?,?,?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, site.getBusinessId());
            ps.setInt(2, site.getResellerOrgId());
            ps.setDouble(3, site.getLatitude());
            ps.setDouble(4, site.getLongitude());
            ps.setString(5, site.getServicesSold());
            ps.setInt(6, site.getRevenue());
            ps.setBoolean(7, site.isMultipleSites());

            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        site.setSiteId(keyHolder.getKey().intValue());
        return site;
    }

    @Override
    public boolean update(Site site) {
        final String sql = "update site set "
                + "business_id = ?, "
                + "reseller_org_id = ?, "
                + "latitude = ?, "
                + "longitude = ?, "
                + "services_sold = ?, "
                + "revenue = ?, "
                + "multiple_sites = ? "
                + "where site_id = ?;";

        return jdbcTemplate.update(sql,
                site.getBusinessId(),
                site.getResellerOrgId(),
                site.getLatitude(),
                site.getLongitude(),
                site.getServicesSold(),
                site.getRevenue(),
                site.isMultipleSites(),
                site.getSiteId()) > 0;
    }

    @Override
    public boolean deleteById(int siteId) {
        return jdbcTemplate.update("delete from site where site_id = ?", siteId) > 0;

    }
}