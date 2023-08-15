package com.stellervoyage.backend.service;

import com.stellervoyage.backend.dto.destination.DestinationRequest;
import com.stellervoyage.backend.dto.destination.DestinationResponse;
import com.stellervoyage.backend.exceptions.UserAlreadyExistsException;
import com.stellervoyage.backend.model.Destination;
import com.stellervoyage.backend.repository.DestinationRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DestinationService {
    private final  DestinationRepository destinationRepository;

    Logger logger = LoggerFactory.getLogger(DestinationService.class);

    /**
     * get destination details
     * @param destinationName
     * @return DestinationResponse
     */
    public DestinationResponse getDestinationDetails(String destinationName) {
        var destination = destinationRepository.findByName(destinationName)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Destination with name - %s does not exist".formatted(destinationName)));

        return DestinationResponse.builder()
                .name(destination.getName())
                .culture(destination.getCulture())
                .planet(destination.getPlanet())
                .touristAttractions(destination.getTouristAttractions())
                .build();
    }

    /**
     * get all destinations in a planet
     * @param planet
     * @return List<DestinationResponse>
     */
    public List<DestinationResponse> getDestinationByPlanet(String planet) {
        var destinations = destinationRepository.findByPlanet(planet)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Incorrect Email or User with Uuid - %s does not exist".formatted(planet)));

        return  destinations.stream()
                .map(destination -> DestinationResponse.builder()
                        .name(destination.getName())
                        .planet(destination.getPlanet())
                        .culture(destination.getCulture())
                        .touristAttractions(destination.getTouristAttractions())
                        .build()).toList();
    }

    /**
     * Get the list of Destinations based on the search query from name, planet, culture
     * @param query
     * @return List<DestinationResponse>
     */
    public List<DestinationResponse> searchDestinations(String query) {
        var destinations = destinationRepository.searchDestinations(query);

        return destinations.stream()
                .map(destination -> DestinationResponse.builder()
                        .name(destination.getName())
                        .planet(destination.getPlanet())
                        .culture(destination.getCulture())
                        .touristAttractions(destination.getTouristAttractions())
                        .build()).toList();
    }

    /**
     * create a new Destination from request
     * @param request
     * @return DestinationResponse
     */
    public DestinationResponse createDestination(DestinationRequest request) {
        if (destinationRepository.existsByName(request.getName())) {
            throw new UserAlreadyExistsException("Destination %s already exists".formatted(request.getName()));
        }
        var destination = Destination.builder()
                .destinationId(UUID.randomUUID())
                .name(request.getName())
                .culture(request.getCulture())
                .planet(request.getPlanet())
                .touristAttractions(request.getTouristAttractions())
                .build();
        var savedDestination = destinationRepository.save(destination);

        logger.info("Destination saved successfully");

        return DestinationResponse.builder()
                .name(savedDestination.getName())
                .planet(savedDestination.getPlanet())
                .touristAttractions(savedDestination.getTouristAttractions())
                .culture(savedDestination.getCulture())
                .build();
    }
}
