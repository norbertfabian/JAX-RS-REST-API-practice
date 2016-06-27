package cz.fabian.practice.jaxrs.server.result;

import cz.fabian.practice.jaxrs.server.model.Profile;

import java.util.List;

/**
 * Created by nfabian on 24.6.16.
 */
public class ProfileResult extends ServiceResult {

    private Profile entity;
    private List<Profile> entities;

    public ProfileResult(Profile entity) {
        this.entity = entity;
    }

    public ProfileResult(List<Profile> entities) {
        this.entities = entities;
    }

    public ProfileResult(String errorMsg) {
        super(errorMsg);
    }

    public Profile getEntity() {
        return entity;
    }

    public List<Profile> getEntities() {
        return entities;
    }
}
