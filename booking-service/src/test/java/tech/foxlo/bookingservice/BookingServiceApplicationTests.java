package tech.foxlo.bookingservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.foxlo.bookingservice.dto.BookingDto;
import tech.foxlo.bookingservice.dto.BookingPaymentDto;
import tech.foxlo.bookingservice.service.BookingService;
import tech.foxlo.bookingservice.service.StationService;

@SpringBootTest
class BookingServiceApplicationTests {


    @Autowired
    private StationService stationService;

    @Test
    public void checkTotalCountTest(){
        int size = stationService.allStations().size();
        Assertions.assertEquals(size, 12);
    }


    @Test
    public void checkStationsCost(){
       var booking = new BookingDto("ghatkopar", "chakala-jb-nagar", "Single", 1);
        int cost = stationService.getCostBetween(booking);
        Assertions.assertEquals(30, cost);
    }


}
