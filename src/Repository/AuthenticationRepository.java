package Repository;

import Repository.IAuthenticationRepository;

import java.util.HashMap;

public class AuthenticationRepository implements IAuthenticationRepository {
    DbContext db;
    HashMap<String, String> records;

    public AuthenticationRepository(DbContext db) {
        this.db = db;
        records = new HashMap<>();
    }

    @Override
    public void createRecord(String identifier, String passwordHash) {
        records.put(identifier, passwordHash);
    }

    @Override
    public boolean isValid(String identifier, String passwordHash) {
        return passwordHash.equals(records.get(identifier));
    }
}
