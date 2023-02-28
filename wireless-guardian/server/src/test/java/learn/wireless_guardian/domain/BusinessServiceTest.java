package learn.wireless_guardian.domain;

import learn.wireless_guardian.data.BusinessRepository;
import learn.wireless_guardian.models.Business;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class BusinessServiceTest {

    @Autowired
    BusinessService service;

    @MockBean
    BusinessRepository businessRepository;

    @Test
    void shouldAdd() {
        Business business = new Business(0,
                "Test",
                "123 test st",
                "minneapolis",
                "MN",
                55414,
                "test@email.com",
                "6125978989");
        Business mockOut = new Business(7,
                "Test",
                "123 test st",
                "minneapolis",
                "MN",
                55414,
                "test@email.com",
                "6125978989");

        when(businessRepository.add(business)).thenReturn(mockOut);

        Result<Business> actual = service.add(business);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());
    }

    @Test
    void shouldNotAddWhenInvalid() {

        Business business = new Business(99,
                "Test",
                "123 test st",
                "minneapolis",
                "MN",
                55414,
                "test@email.com",
                "6125978989");

        Result<Business> actual = service.add(business);
        assertEquals(ResultType.INVALID, actual.getType());

        business.setBusinessId(0);
        business.setBusinessName(null);
        actual = service.add(business);
        assertEquals(ResultType.INVALID, actual.getType());

        business.setBusinessName("TEST");
        business.setAddress("   ");
        actual = service.add(business);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldUpdate() {
        Business business = new Business(1,
                "Test",
                "123 test st",
                "minneapolis",
                "MN",
                55414,
                "test@email.com",
                "6125978989");

        when(businessRepository.update(business)).thenReturn(true);
        Result<Business> actual = service.update(business);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldNotUpdateMissing() {
        Business business = new Business(99,
                "Test",
                "123 test st",
                "minneapolis",
                "MN",
                55414,
                "test@email.com",
                "6125978989");

        when(businessRepository.update(business)).thenReturn(false);
        Result<Business> actual = service.update(business);
        assertEquals(ResultType.NOT_FOUND, actual.getType());
    }

    @Test
    void shouldNotUpdateWhenInvalid() {

        Business business = new Business(99,
                null,
                "123 test st",
                "minneapolis",
                "MN",
                55414,
                "test@email.com",
                "6125978989");

        Result<Business> actual = service.update(business);
        assertEquals(ResultType.INVALID, actual.getType());

        business.setBusinessId(0);
        business.setBusinessName(null);
        actual = service.update(business);
        assertEquals(ResultType.INVALID, actual.getType());

        business.setBusinessName("TEST");
        business.setAddress("   ");
        actual = service.update(business);
        assertEquals(ResultType.INVALID, actual.getType());
    }
}