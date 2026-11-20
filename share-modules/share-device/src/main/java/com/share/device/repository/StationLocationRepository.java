package com.share.device.repository;

import com.share.device.domain.StationLocation;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StationLocationRepository extends MongoRepository<StationLocation, String> {

    Optional<StationLocation> findByStationId(Long stationId);


    // 根据 stationId 更新 location
    @Query("{ 'stationId': ?0 }")
    @Update("{ '$set': { 'location': ?1 } }")
    void updateLocationByStationId(Long stationId, GeoJsonPoint location);

}
