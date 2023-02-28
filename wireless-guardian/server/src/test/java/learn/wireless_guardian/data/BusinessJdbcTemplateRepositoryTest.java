package learn.wireless_guardian.data;

import learn.wireless_guardian.models.Business;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class BusinessJdbcTemplateRepositoryTest {
    @Autowired
    BusinessJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void findAll() {
        List<Business> businesses = repository.findAll();
        assertNotNull(businesses);
        assertTrue(businesses.size() > 0);
    }

    @Test
    void shouldBusiness2() {
        Business business2 = repository.findById(2);
        assertEquals("Business2", business2.getBusinessName());
    }

    @Test
    void shouldAddBusiness() {
        Business business = new Business();
        business.setBusinessName("TEST");
        business.setAddress("123 test st");
        business.setCity("test city");
        business.setState("TS");
        business.setZipCode(12345);
        business.setContactEmail("test@test.com");
        Business actual = repository.add(business);
        assertNotNull(actual);
        assertEquals(7, actual.getBusinessId());
    }

    @Test
    void shouldUpdateBusiness() {

        Business business = new Business();
        business.setBusinessId(3);
        business.setBusinessName("Business3");
        business.setAddress("123 Elm");
        business.setCity("Des Moines");
        business.setState("WI");
        business.setZipCode(55555);
        business.setContactEmail("slater2014@gmail.com");
        business.setContactPhone("6125978989");

        assertTrue(repository.update(business));
    }

    @Test
    void shouldDeleteBusiness() {
        assertTrue(repository.deleteById(3));
        assertFalse(repository.deleteById(3));
    }
}