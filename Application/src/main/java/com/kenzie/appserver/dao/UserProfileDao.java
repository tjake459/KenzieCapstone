package com.kenzie.appserver.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.kenzie.appserver.repositories.model.UserProfileRecord;
import com.kenzie.appserver.service.model.UserProfile;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.kenzie.appserver.Utilties.ConverterUtilities.createRecordFromProfile;

@Service
public class UserProfileDao {

    private DynamoDBMapper mapper;

    public UserProfileDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    public Optional<UserProfileRecord> getUser (String username) {
        return Optional.ofNullable(mapper.load(UserProfileRecord.class, username));
    }

    public UserProfileRecord addUser (UserProfile userProfile) {
        UserProfileRecord record = createRecordFromProfile(userProfile);
        mapper.save(record);
        return record;
    }

    public void deleteUser (String username) {
        UserProfileRecord recordToDelete = mapper.load(UserProfileRecord.class, username);
        mapper.delete(recordToDelete);
    }


}
