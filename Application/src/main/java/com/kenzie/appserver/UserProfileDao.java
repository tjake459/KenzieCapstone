package com.kenzie.appserver;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.kenzie.appserver.repositories.model.UserProfileRecord;
import com.kenzie.appserver.service.model.UserProfile;

import static com.kenzie.appserver.Utilties.ConverterUtilities.createRecordFromUserProfile;

public class UserProfileDao {

    private DynamoDBMapper mapper;

    public UserProfileDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    public UserProfileRecord getUser (String id) {
        return mapper.load(UserProfileRecord.class, id);
    }

    public UserProfileRecord addUser (UserProfile userProfile) {
        UserProfileRecord record = createRecordFromUserProfile(userProfile);
        mapper.save(record);
        return record;
    }

    public void deleteUser (String id) {
        UserProfileRecord recordToDelete = mapper.load(UserProfileRecord.class, id);
        mapper.delete(recordToDelete);
    }


}