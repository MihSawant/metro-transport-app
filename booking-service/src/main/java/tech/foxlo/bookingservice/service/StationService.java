package tech.foxlo.bookingservice.service;

import org.springframework.stereotype.Service;
import tech.foxlo.bookingservice.dto.BookingDto;
import tech.foxlo.bookingservice.exception.StationNotFoundException;
import tech.foxlo.bookingservice.repository.StationRepository;

import java.util.List;

@Service
public class StationService {

    private final StationRepository repository;

    public StationService(StationRepository repository) {
        this.repository = repository;
    }


    public List<String> allStations(){
        return repository.findAllNames();
    }



    public int getCostBetween(BookingDto booking){
       int totalCost = 0;
       boolean single = booking.journey().equals("Single");
        if(!allStations().contains(booking.from()) ||
            !allStations().contains(booking.to())){
                throw new StationNotFoundException("Station does not exist !");
        } else{
            var one = repository.findStationByName(booking.from()).get();
            var two = repository.findStationByName(booking.to()).get();
            var diff = Math.abs(one.getId() - two.getId());

            if(diff == 1 || diff == 2){
                return one.getCost();
            } else if(diff > 2 && diff < 5){
                totalCost = 20;
            } else if(diff  >= 5 && diff <= 8){
                totalCost = 30;
            } else{
                totalCost = 40;
            }
            totalCost = single ? totalCost : totalCost * 2;
        }
        return totalCost * booking.people();
    }
}
