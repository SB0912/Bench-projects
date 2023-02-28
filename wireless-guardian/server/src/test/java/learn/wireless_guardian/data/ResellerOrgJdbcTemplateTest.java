package learn.wireless_guardian.data;

import learn.wireless_guardian.models.Business;
import learn.wireless_guardian.models.ResellerOrg;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ResellerOrgJdbcTemplateTest {

    @Autowired
    ResellerOrgJdbcTemplate repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void findAll() {
        List<ResellerOrg> resellerOrgs = repository.findAll();
        assertNotNull(resellerOrgs);
        assertTrue(resellerOrgs.size() > 0);
    }

    @Test
    void shouldFindResellerA() {
        ResellerOrg resellerOrg = repository.findById(1);
        assertEquals("reseller group A", resellerOrg.getResellerOrgId());
    }

    @Test
    void shouldAddResellerOrg() {
        ResellerOrg resellerOrg = new ResellerOrg();
        resellerOrg.setName("reseller group E");
        resellerOrg.setParentOrg("reseller group D");
        resellerOrg.setChildOrg(null);
        ResellerOrg actual = repository.add(resellerOrg);
        assertNotNull(actual);
        assertEquals(5, actual.getResellerOrgId());
    }

    @Test
    void shouldUpdateResellerOrg() {

        ResellerOrg resellerOrg = new ResellerOrg();
        resellerOrg.setResellerOrgId(4);
        resellerOrg.setName("reseller group D");
        resellerOrg.setParentOrg("reseller group C");
        resellerOrg.setChildOrg("reseller group E");

        assertTrue(repository.update(resellerOrg));
    }

    @Test
    void shouldDeleteResellerOrg() {
        assertTrue(repository.deleteById(1));
        assertFalse(repository.deleteById(1));
    }
}