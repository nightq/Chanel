package com.nightq.freedom.chanel.baseModels;

import com.firebase.client.AuthData;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by Nightq on 16/2/3.
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class UserModel extends BaseModel {

    private String name;
    private String displayName;
    private long id;
    private String avatar;

    public UserModel(AuthData authData) {
        name = authData.getUid();
        displayName = authData.getUid();
    }

}
